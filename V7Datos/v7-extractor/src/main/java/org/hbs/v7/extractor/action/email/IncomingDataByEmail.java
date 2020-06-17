package org.hbs.v7.extractor.action.email;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;

import org.apache.commons.io.IOUtils;
import org.hbs.core.beans.model.IProducers;
import org.hbs.core.beans.model.Producers;
import org.hbs.core.beans.model.channel.ConfigurationEmail;
import org.hbs.core.security.resource.IPath;
import org.hbs.core.util.CommonValidator;
import org.hbs.extractor.beans.model.DataAttachments;
import org.hbs.extractor.beans.model.DataAttachments.EDataTrace;
import org.hbs.extractor.beans.model.IncomingData;
import org.hbs.extractor.beans.model.IncomingData.EIncomingStatus;
import org.hbs.extractor.dao.IncomingDao;
import org.hbs.extractor.event.KafkaEmailReferenceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sun.mail.imap.IMAPFolder;

@Service
public class IncomingDataByEmail implements IPath
{
	private static final long	serialVersionUID	= -3529623337510779624L;

	private final Logger		logger				= LoggerFactory.getLogger(IncomingDataByEmail.class);
	@Autowired
	private IncomingDao			incomingDao;

	@KafkaListener(topicPartitions = @TopicPartition(topic = ATTACHMENT_TOPIC, partitions = { "0" }), groupId = EMPLOYEE_ID, clientIdPrefix = "email")
	public void consume(String uidJsonStr) throws IOException, MessagingException
	{
		logger.info(String.format("#### -> Consumed message -> %s", uidJsonStr));
		IMAPFolder imapFolder = null;
		try
		{
			KafkaEmailReferenceBean kafkaEmailRef = new Gson().fromJson(uidJsonStr, KafkaEmailReferenceBean.class);

			logger.info(kafkaEmailRef.messageNumber + " -    -    -   " + kafkaEmailRef.sentDate);

			ConfigurationEmail config = kafkaEmailRef.config;
			Store store = EmailConnectionHandler.getInstance().getStore(config.getProducerId() + config.getFromId());
			if (store == null)
			{
				Properties props = new Properties();
				Map<String, String[]> map = config.getSource().props();
				props.setProperty("mail.host", config.getHostAddress());
				props.setProperty("mail.port", config.getPort());
				props.setProperty("mail.store.protocol", config.getProtocol());
				/* Get the Session object for specific Mail Property. */
				Session session = Session.getInstance(props, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication(config.getUserName().trim(), config.getPassword());
					}
				});
				/* Get a Store object that implements the specified protocol. */
				store = session.getStore(config.getProtocol().trim());
				/*
				 * Connect to the current host using the specified Email id and Password.
				 */
				store.connect();
				Folder[] folderArr = store.getDefaultFolder().list();
				for (Folder folder : folderArr)
				{
					System.out.println(config.getFromId().trim() + " Inbox Connected :: Unread Message Count :: " + folder + " ---- " + folder.getUnreadMessageCount());
					if (folder.getName().equalsIgnoreCase("inbox"))
					{
						break;
					}
				}
				EmailConnectionHandler.getInstance().putStore(config.getProducerId() + config.getFromId(), store);
			}

			try
			{
				imapFolder = (IMAPFolder) store.getFolder("inbox");

				// Open the Folder.
				if (!imapFolder.isOpen())
					imapFolder.open(Folder.READ_ONLY);

				// Date initDateTime = null, startTime = null, endTime = null;

				SearchTerm searchTerm = new SearchTerm() {

					@Override
					public boolean match(Message message)
					{
						try
						{
							logger.info(message.getMessageNumber() + " - " + kafkaEmailRef.messageNumber + "   -  " + kafkaEmailRef.sentDate + "  -   " + message.getSentDate());
							if (message.getMessageNumber() == kafkaEmailRef.messageNumber && message.getSentDate().equals(kafkaEmailRef.sentDate))
							{
								logger.info(message.getMessageNumber() + "");
								return true;
							}
						}
						catch (MessagingException e)
						{
							e.printStackTrace();
							return false;
						}
						return false;
					}
				};
				// Message[] messages = (Message[]) imapFolder.search(searchTerm);
				Message message = imapFolder.getMessage(kafkaEmailRef.messageNumber);
				logger.info(message.getMessageNumber() + " -S- " + kafkaEmailRef.messageNumber + "   -  " + kafkaEmailRef.sentDate + "  -   " + message.getSentDate());

				UIDMimeMessage uidMessage = new UIDMimeMessage(config.getProducerId(), imapFolder, message);
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HHmmss");

				uidMessage.setSubFolderPath(
						config.getProducerId() + "\\" + config.getProducerId() + "\\" + DATE_FORMAT.format(message.getSentDate()) + "\\" + TIME_FORMAT.format(message.getSentDate()) + "\\");

				IncomingData incomingData = processMultiPart(uidMessage);

				if (CommonValidator.isNotNullNotEmpty(incomingData))
				{
					incomingData.setCandidateEmail(message.getFrom()[0].toString());
					incomingData.setMedia(EMedia.Email);
					incomingData.setIncomingStatus(EIncomingStatus.New);
					incomingData.setSubject(message.getSubject());
					incomingData.setSentTime(message.getSentDate().getTime());
					incomingData.setUniqueId(imapFolder.getUID(message) + "");
					incomingData.setReaderInstance(EMedia.Email.toString());
					incomingData.setCreatedDate(new Timestamp(System.currentTimeMillis()));
					// incomingData.setProducerProperty(kafkaEmailRef.config.getProducerId());
					IProducers producer = new Producers();
					producer.setProducerId(config.getProducerId());
					incomingData.setProducer(producer);
					incomingDao.save(incomingData);

				}
				logger.info(incomingData.getAttachmentList().toString());

			}
			catch (FolderClosedException excep)
			{
				excep.printStackTrace();
			}
			catch (MessagingException excep)
			{
				excep.printStackTrace();
			}
			finally
			{
				try
				{
					try
					{
						Thread.sleep(10000);
					}
					catch (InterruptedException ie)
					{
					}
					if (imapFolder != null && imapFolder.isOpen())
					{
						imapFolder.close(true);
					}
					if (store != null)
					{
						store.close();
					}
				}
				catch (MessagingException e)
				{
					e.printStackTrace();
				}
			}

			/*
			 * if (IMAPMessage.class.isInstance(uidMessag e.message))
			 */
			// {
			/*
			 * If the mail is auto generated mail, then the message.getSentDate() should be null. So
			 * we use the message.getReceivedDate() to get the sentDate
			 */
			/*
			 * Date sentDate = uidMessage.message.getSentDate() == null ?
			 * uidMessage.message.getReceivedDate() : uidMessage.message.getSentDate(); if
			 * (uidMessage.message.getContentType() != null) { Address[] froms =
			 * uidMessage.message.getFrom(); String candidateEmailId = null;
			 * DataExtractor.getInstance(uidMessage.producerId).dataFramer(RegExFor.Email,
			 * Arrays.toString(froms)); if (CommonValidator.isNullOrEmpty(candidateEmailId)) {
			 * candidateEmailId = froms == null ? "" : ((InternetAddress) froms[0]).getAddress(); }
			 * if (CommonValidator.isNotNullNotEmpty(candidateEmailId) &&
			 * CommonValidator.isNotNullNotEmpty(uidMessage.message.getSubject())) { IncomingData
			 * incomingData = processMultiPart(uidMessage); if
			 * (CommonValidator.isNotNullNotEmpty(incomingData)) {
			 * incomingData.setCandidateEmail(candidateEmailId);
			 * incomingData.setMedia(EMedia.Email);
			 * incomingData.setIncomingStatus(EIncomingStatus.New);
			 * incomingData.setSubject(uidMessage.message.getSubject());
			 * incomingData.setSentTime(sentDate.getTime());
			 * incomingData.setUniqueId(uidMessage.uniqueId); incomingDao.save(incomingData); } } }
			 * }
			 */
		}
		finally
		{
			// uidMessage.message = null;
		}
	}

	private IncomingData processMultiPart(UIDMimeMessage uidMsg)
	{
		Set<DataAttachments> attachmentsSet = null;
		FileOutputStream outStream = null;
		try
		{
			BodyPart bodyPart = null;
			IncomingData incomingData = new IncomingData();
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
					attachment.setUploadSubFolderPath(uidMsg.getSubFolderPath());
					attachment.setIncomingData(incomingData);
					attachment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
					File file = uidMsg.getOutputFile();// Don't Change Position

					attachment.setUploadFileSize(file.length());
					attachment.setUploadFileDate(new Timestamp(System.currentTimeMillis()));
					attachment.setUploadFileLastModifiedDate(new Timestamp(file.lastModified()));
					attachmentsSet.add(attachment);

				}

			}

			incomingData.setAttachmentList(attachmentsSet);
			return incomingData;
		}
		catch (MessagingException | IOException excep)
		{
			excep.printStackTrace();
		}
		return null;
	}
}
