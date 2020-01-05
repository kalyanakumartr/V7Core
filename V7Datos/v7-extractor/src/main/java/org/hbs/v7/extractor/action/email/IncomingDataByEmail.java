package org.hbs.v7.extractor.action.email;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;

import org.apache.commons.io.IOUtils;
import org.hbs.core.security.resource.IPath;
import org.hbs.core.util.CommonValidator;
import org.hbs.extractor.beans.model.DataAttachments;
import org.hbs.extractor.beans.model.DataAttachments.EDataTrace;
import org.hbs.extractor.beans.model.IncomingData;
import org.hbs.extractor.beans.model.IncomingData.EIncomingStatus;
import org.hbs.extractor.dao.IncomingDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import com.sun.mail.imap.IMAPMessage;

@Service
public class IncomingDataByEmail implements IPath
{
	private static final long	serialVersionUID	= -3529623337510779624L;

	private final Logger		logger				= LoggerFactory.getLogger(IncomingDataByEmail.class);

	
	private IncomingDao			incomingDao;

	@KafkaListener(topicPartitions = @TopicPartition(topic = ATTACHMENT_TOPIC, partitions = { "0" }), groupId = EMPLOYEE_ID, clientIdPrefix = "Email")
	public void consume(UIDMimeMessage uidMessage) throws IOException, MessagingException
	{
		logger.info(String.format("#### -> Consumed message -> %s", uidMessage.uniqueId));

		try
		{
			if (IMAPMessage.class.isInstance(uidMessage.message))
			{
				/*
				 * If the mail is auto generated mail, then the message.getSentDate() should be
				 * null. So we use the message.getReceivedDate() to get the sentDate
				 */
				Date sentDate = uidMessage.message.getSentDate() == null ? uidMessage.message.getReceivedDate() : uidMessage.message.getSentDate();

				if (uidMessage.message.getContentType() != null)
				{
					Address[] froms = uidMessage.message.getFrom();

					String candidateEmailId = null;
					//DataExtractor.getInstance(uidMessage.producerId).dataFramer(RegExFor.Email, Arrays.toString(froms));

					if (CommonValidator.isNullOrEmpty(candidateEmailId))
					{
						candidateEmailId = froms == null ? "" : ((InternetAddress) froms[0]).getAddress();
					}

					if (CommonValidator.isNotNullNotEmpty(candidateEmailId) && CommonValidator.isNotNullNotEmpty(uidMessage.message.getSubject()))
					{
						IncomingData incomingData = processMultiPart(uidMessage);

						if (CommonValidator.isNotNullNotEmpty(incomingData))
						{
							incomingData.setCandidateEmail(candidateEmailId);
							incomingData.setMedia(EMedia.Email);
							incomingData.setIncomingStatus(EIncomingStatus.New);
							incomingData.setSubject(uidMessage.message.getSubject());
							incomingData.setSentTime(sentDate.getTime());
							incomingData.setUniqueId(uidMessage.uniqueId);
							incomingDao.save(incomingData);
						}
					}
				}
			}
		}
		finally
		{
			uidMessage.message = null;
		}
	}

	private IncomingData processMultiPart(UIDMimeMessage uidMsg)
	{
		Set<DataAttachments> attachmentsSet = null;
		FileOutputStream outStream = null;
		try
		{
			BodyPart bodyPart = null;
			attachmentsSet = new HashSet<DataAttachments>();
			Multipart multipart = (Multipart) uidMsg.message.getContent();
			DataAttachments attachment = null;
			for (int idx = 0; idx < multipart.getCount(); idx++)
			{
				bodyPart = multipart.getBodyPart(idx);

				if (CommonValidator.isNotNullNotEmpty(bodyPart, bodyPart.getFileName()) && CommonValidator.isEqual(bodyPart.getDisposition(), Part.ATTACHMENT))
				{
					if (bodyPart.getSize() > 4113632)
					{
						break;
					}

					uidMsg.setFileName(bodyPart.getFileName());
					outStream = uidMsg.getOutputStream();

					IOUtils.copy(bodyPart.getInputStream(), outStream);

					if (bodyPart.getInputStream() != null)
						bodyPart.getInputStream().close();

					if (outStream != null)
						outStream.close();

					attachment = new DataAttachments();
					attachment.setUploadFileName(bodyPart.getFileName());
					attachment.setStatus(true);
					attachment.setTrace(EDataTrace.YetToTrace);
					attachment.setUploadFileFolderURL(uidMsg.getOutputPath());
					File file = uidMsg.getOutputFile();// Don't Change Position

					attachment.setUploadFileSize(file.length());
					attachment.setUploadFileDate(new Timestamp(System.currentTimeMillis()));
					attachment.setUploadFileLastModifiedDate(new Timestamp(file.lastModified()));
					attachmentsSet.add(attachment);

				}

			}
			IncomingData incomingData = new IncomingData();
			incomingData.setAttachmentList(attachmentsSet);
		}
		catch (MessagingException | IOException excep)
		{
			excep.printStackTrace();
		}
		return null;
	}
}
