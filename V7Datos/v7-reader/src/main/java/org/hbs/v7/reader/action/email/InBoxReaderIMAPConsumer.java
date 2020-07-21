package org.hbs.v7.reader.action.email;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.io.IOUtils;
import org.hbs.core.beans.model.Producers;
import org.hbs.core.kafka.IKafkaConstants;
import org.hbs.core.kafka.KAFKAPartition;
import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.CustomException;
import org.hbs.v7.beans.DataInTopicBean;
import org.hbs.v7.beans.InBoxReaderTopicBean;
import org.hbs.v7.beans.UIDMimeMessageBean;
import org.hbs.v7.beans.model.DataAttachments;
import org.hbs.v7.beans.model.IncomingData;
import org.hbs.v7.beans.model.PartitionFinder;
import org.hbs.v7.beans.model.DataAttachments.EDataTrace;
import org.hbs.v7.beans.model.IncomingData.EExtension;
import org.hbs.v7.beans.model.IncomingData.EIncomingStatus;
import org.hbs.v7.dao.IncomingDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.imap.IMAPFolder;

@Service
public class InBoxReaderIMAPConsumer extends InBoxReaderIMAPBase implements IKafkaConstants
{

	private static final long	serialVersionUID	= -3529623337510779624L;

	@Autowired
	private IncomingDao			incomingDao;
	private final Logger		logger				= LoggerFactory.getLogger(InBoxReaderIMAPConsumer.class);

	@KafkaListener(topicPartitions = @TopicPartition(topic = MESSAGE_TOPIC, partitions = { NORMAL, EXPEDITE }), groupId = MESSAGE_GROUP, clientIdPrefix = EMAIL)
	public void consume(@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition, String payload)
	{
		logger.info(String.format("#### -> Consumed message -> %s", payload));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>" + new Date() + ">>>>>>>flow > " + partition);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		try
		{
			ObjectMapper oMapper = new ObjectMapper();
			InBoxReaderTopicBean inBoxReaderBean = oMapper.readValue(payload, InBoxReaderTopicBean.class);

			this.config = inBoxReaderBean.config;

			logger.info(inBoxReaderBean.messageNumber + " -    -    -   " + inBoxReaderBean.sentDate);

			IMAPFolder imapFolder = getIMAPFolder(EFolder.Inbox);
			Message[] messages = imapFolder.getMessages(new int[] { inBoxReaderBean.messageNumber });

			if (CommonValidator.isArrayFirstNotNull(messages) && messages[0].getSentDate().compareTo(inBoxReaderBean.sentDate) == 0)
			{
				Message message = messages[0];
				logger.info(message.getMessageNumber() + " -S- " + inBoxReaderBean.messageNumber + "   -  " + inBoxReaderBean.sentDate + "  -   " + message.getSentDate());

				IncomingData incomingData = processMultiPart(new UIDMimeMessageBean(config, message));

				if (CommonValidator.isNotNullNotEmpty(incomingData))
				{
					incomingData.setCandidateEmail(message.getFrom()[0].toString());
					incomingData.setMedia(EMedia.Email);
					incomingData.setIncomingStatus(EIncomingStatus.New);
					incomingData.setSubject(message.getSubject());
					incomingData.setSentTime(message.getSentDate().getTime());
					incomingData.setUniqueId(imapFolder.getUID(message) + "");
					incomingData.setReaderInstance(this.getClass().getSimpleName());
					incomingData.setCreatedDate(new Timestamp(System.currentTimeMillis()));
					incomingData.setProducer(new Producers(config.getProducerId()));
					incomingDao.save(incomingData);

					for (DataAttachments _DATT : incomingData.getAttachmentList())
					{
						DataInTopicBean inBean = _DATT.getDataInTopicBean();
						KAFKAPartition ePartition = PartitionFinder.getInstance().find(ETopic.DataExtract, incomingData.getPriority(), inBean.getExtension());

						gKafkaProducer.send(ETopic.DataExtract, ePartition, inBean);
					}
				}
				logger.info(incomingData.getAttachmentList().toString());

			}

		}
		catch (Exception excep)
		{
			excep.printStackTrace();
		}
		finally
		{
			PersistantStoreHandler.getInstance().removeStore(this);
		}
	}

	private IncomingData processMultiPart(UIDMimeMessageBean uidMsg)
	{
		Set<DataAttachments> attachmentsSet = null;
		FileOutputStream outStream = null;
		try
		{
			BodyPart bodyPart = null;
			IncomingData incomingData = new IncomingData(uidMsg.message);
			attachmentsSet = new HashSet<DataAttachments>();
			Multipart multipart = (Multipart) uidMsg.message.getContent();
			for (int idx = 0; idx < multipart.getCount(); idx++)
			{
				bodyPart = multipart.getBodyPart(idx);

				if (CommonValidator.isNotNullNotEmpty(bodyPart, bodyPart.getFileName()) && CommonValidator.isEqual(bodyPart.getDisposition(), Part.ATTACHMENT))
				{
					if (bodyPart.getSize() > 4113632)
					{
						break;
					}
					System.out.println(">>>>>>>>>>>>> bodyPart.getFileName()>>>>>>>>>>>>>>>>" + bodyPart.getFileName());
					uidMsg.setFileName(bodyPart.getFileName());
					outStream = uidMsg.getOutputStream();

					IOUtils.copy(bodyPart.getInputStream(), outStream);

					if (bodyPart.getInputStream() != null)
						bodyPart.getInputStream().close();

					if (outStream != null)
						outStream.close();

					unpackAndCreateDataAttachmentSet(incomingData, uidMsg, attachmentsSet);
				}

			}

			incomingData.setAttachmentList(attachmentsSet);
			return incomingData;
		}
		catch (MessagingException | IOException | CustomException excep)
		{
			excep.printStackTrace();
		}
		return null;
	}

	private void unpackAndCreateDataAttachmentSet(IncomingData incomingData, UIDMimeMessageBean uidMsg, Set<DataAttachments> attachmentsSet) throws IOException, CustomException, MessagingException
	{
		System.out.println(uidMsg.getFileName() + " >>>>>>>>>>>>> uidMsg.getExtension()>>>>>>>>>>>>>>>>" + uidMsg.getExtension());
		switch ( uidMsg.getExtension() )
		{
			case Invalid :
				throw new CustomException("Unsupported File '" + uidMsg.getFileName() + "'. Allow Files Only With Extensions " + EExtension.format());
			case Zip :
			{
				incomingData.setBulkAttachment(true);
				unpackZipFile(incomingData, uidMsg, attachmentsSet);
				break;
			}
			case _7z :
			{
				incomingData.setBulkAttachment(true);
				unpack7zFile(incomingData, uidMsg, attachmentsSet);
				break;
			}
			default :
			{
				System.out.println("Single Attachment File. " + uidMsg.getFileName());
				createDataAttachment(incomingData, uidMsg, attachmentsSet);
				System.out.println("CreateDataAttachment Completed For " + uidMsg.getFileName());
				break;
			}
		}
	}

	private void createDataAttachment(IncomingData incomingData, UIDMimeMessageBean uidMsg, Set<DataAttachments> attachmentsSet) throws MessagingException
	{
		DataAttachments attachment = new DataAttachments();
		attachment.setUploadFileName(uidMsg.getFileName());
		attachment.setStatus(true);
		attachment.setPriority(incomingData.getPriority());
		attachment.setTrace(incomingData.isBulkAttachment() ? EDataTrace.MainDocument : EDataTrace.YetToTrace);
		attachment.setUploadFileFolderURL(uidMsg.getOutputPath());
		attachment.setIncomingData(incomingData);
		attachment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		File file = uidMsg.getOutputFile();// Don't Change Position

		attachment.setUploadFileSize(file.length());
		attachment.setUploadFileDate(new Timestamp(System.currentTimeMillis()));
		attachment.setUploadFileLastModifiedDate(new Timestamp(file.lastModified()));

		attachmentsSet.add(attachment);
		uidMsg.setFileName(null); // Reset for safer side
	}

	private void unpack7zFile(IncomingData incomingData, UIDMimeMessageBean uidMsg, Set<DataAttachments> attachmentsSet) throws IOException, MessagingException
	{
		SevenZFile sevenZFile = null;
		FileOutputStream out = null;
		try
		{
			sevenZFile = uidMsg.getSevenZFile();
			SevenZArchiveEntry entry;
			while ( (entry = sevenZFile.getNextEntry()) != null )
			{
				if (entry.isDirectory())
				{
					continue;
				}
				File curfile = new File(uidMsg.getOutputPath(entry.getName()));
				File parent = curfile.getParentFile();
				if (!parent.exists())
				{
					parent.mkdirs();
				}
				out = new FileOutputStream(curfile);
				byte[] content = new byte[(int) entry.getSize()];
				sevenZFile.read(content, 0, content.length);
				out.write(content);
				out.close();
				System.out.println("unpack7zFile :: Bulk Attachment File. Unzipped " + entry.getName());
				createDataAttachment(incomingData, uidMsg, attachmentsSet);
				System.out.println("unpack7zFile :: CreateDataAttachment Completed For " + entry.getName());
			}
			sevenZFile.close();
		}
		finally
		{
			// Double check for Resource Free up
			if (sevenZFile != null)
				sevenZFile.close();
			if (out != null)
				out.close();
		}

	}

	private void unpackZipFile(IncomingData incomingData, UIDMimeMessageBean uidMsg, Set<DataAttachments> attachmentsSet) throws IOException, MessagingException
	{
		ZipInputStream zipIn = null;
		FileOutputStream out = null;
		try
		{
			zipIn = uidMsg.getZIPInputStream();
			ZipEntry entry = zipIn.getNextEntry();

			while ( (entry = zipIn.getNextEntry()) != null )
			{
				if (entry.isDirectory())
				{
					continue;
				}
				File curfile = new File(uidMsg.getOutputPath(entry.getName()));
				File parent = curfile.getParentFile();
				if (!parent.exists())
				{
					parent.mkdirs();
				}
				out = new FileOutputStream(curfile);
				byte[] content = new byte[(int) entry.getSize()];
				zipIn.read(content, 0, content.length);
				out.write(content);
				out.close();

				System.out.println("unpackZipFile :: Bulk Attachment File. Unzipped " + entry.getName());
				createDataAttachment(incomingData, uidMsg, attachmentsSet);
				System.out.println("unpackZipFile :: CreateDataAttachment Completed For " + entry.getName());
			}

			zipIn.close();
		}
		finally
		{
			// Double check for Resource Free up
			if (zipIn != null)
				zipIn.close();
			if (out != null)
				out.close();
		}
	}
}
