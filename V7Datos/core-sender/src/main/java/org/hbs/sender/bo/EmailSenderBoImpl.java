package org.hbs.sender.bo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.hbs.core.beans.MessageFormBean;
import org.hbs.core.beans.model.IConfiguration;
import org.hbs.core.beans.model.IMessages;
import org.hbs.core.beans.model.IMessages.EMessageStatus;
import org.hbs.core.beans.model.Users;
import org.hbs.core.beans.model.channel.ConfigurationEmail;
import org.hbs.core.beans.model.channel.EmailAttachments;
import org.hbs.core.beans.model.channel.IChannelMessages;
import org.hbs.core.dao.EmailAttachmentDao;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.CustomException;
import org.hbs.core.util.IConstProperty;
import org.hbs.core.util.io.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class EmailSenderBoImpl extends BaseSenderBoImpl implements EmailSenderBo, IConstProperty
{
	private static final long	serialVersionUID	= 1740404040024487684L;

	@Value("${application.physical.paths}")
	String						applicationPhysicalPaths;

	@Autowired
	EmailAttachmentDao			attachmentDao;

	@Value("${attachement.folder}")
	String						attachmentFolder;

	@Autowired
	IOUtil						ioUtil;
	private final Logger		logger				= LoggerFactory.getLogger(EmailSenderBoImpl.class);

	private List<String> copyAttachmentToServerAndCreateURL(MessageFormBean messageFormBean) throws Exception
	{
		List<String> attachmentURLs = new ArrayList<String>();
		if (CommonValidator.isArrayFirstNotNull(messageFormBean.multipartFiles))
		{
			String _BasePath = getResourceBasePath("ATTACHMENT");
			for (MultipartFile multiFile : messageFormBean.multipartFiles)
			{
				File file = new File(_BasePath + attachmentFolder);
				if (!file.exists())
					file.mkdirs();
				String absolutePath = attachmentFolder + SLASH + multiFile.getOriginalFilename();
				InputStream is = multiFile.getInputStream();
				Files.copy(is, Paths.get(_BasePath + absolutePath), StandardCopyOption.REPLACE_EXISTING);
				attachmentURLs.add(absolutePath);
			}
		}
		return attachmentURLs;
	}

	private JavaMailSender getConfigurationAndCreateEmailSender(Authentication auth, String token, IMessages message) throws Exception
	{
		if (CommonValidator.isNotNullNotEmpty(token, message))
		{
			IConfiguration configuration = configurationBo.getConfigurationByType(auth, EMedia.Email, EMediaType.Primary, EMediaMode.Internal);
			if (CommonValidator.isNotNullNotEmpty(configuration))
			{
				message.setConfiguration(configuration);
				return ConfigurationHandler.getInstance().createEmailSender(message);
			}

			configuration = configurationBo.getConfigurationByType(auth, EMedia.Email, EMediaType.Secondary, EMediaMode.Internal);
			if (CommonValidator.isNotNullNotEmpty(configuration))
			{
				message.setConfiguration(configuration);
				return ConfigurationHandler.getInstance().createEmailSender(message);
			}

			throw new CustomException("Email Configuration NOT found for given id : ");
		}
		throw new CustomException("Authentication Token Or Messages may be NULL.");
	}

	private IMessages getFabricatedMessage(String key, MessageFormBean messageFormBean, Users user, List<String> attachmentURLs) throws CloneNotSupportedException, InvalidKeyException, CustomException
	{
		if (user == null)
			user = messageFormBean.get(key).user;

		if (CommonValidator.isNotNullNotEmpty(user))
		{
			IChannelMessages message = messageFormBean.message; // .clone()
			message.setMessageId(message.getBusinessKey());
			// message.setUserId(user.getUserId());
			// message.setUserName(user.getUserName());
			// message.setEmailId(key);
			// message.setSource(messageFormBean.get(key).contact);
			// message.setMedia(messageFormBean.get(key).type);
			if (CommonValidator.isListFirstNotEmpty(attachmentURLs))
			{
				EmailAttachments attachment = null;
				for (String _URL : attachmentURLs)
				{
					attachment = new EmailAttachments();
					attachment.setUploadFileFolderURL(_URL);
					attachment.setMessage(message);
					// message.getAttachmentList().add(attachment);
				}
			}
			return message;
		}
		throw new CustomException("User data found.");
	}

	private String getResourceBasePath(String key)
	{
		String path = null;
		if (CommonValidator.isNotNullNotEmpty(applicationPhysicalPaths) && applicationPhysicalPaths.contains(HASH))
		{
			String[] resource = null;
			for (String resources : applicationPhysicalPaths.split(COMMA_SPACE.trim()))
			{
				resource = resources.split(HASH);
				if (CommonValidator.isEqual(resource[0].trim(), key))
				{
					path = resource[1].trim();
					break;
				}
			}
		}
		return path;
	}

	@Override
	public EMessageStatus sendEmailByMessage(Authentication auth, String token, IMessages message)
	{
		try
		{
			// Configuration not exists inside message
			message.setProducer(EAuth.User.getProducer(auth));
			sendMimeMessage(message, getConfigurationAndCreateEmailSender(auth, token, message));
			return EMessageStatus.Completed;
		}
		catch (Exception excep)
		{
			StringWriter logMessageWriter = new StringWriter();
			excep.printStackTrace(new PrintWriter(logMessageWriter));
			logger.error("Processed By " + EAuth.User.getFullName(auth) + EWrap.Brace.enclose(EAuth.User.getUserId(auth)));
			logger.error(logMessageWriter.toString());
		}

		return EMessageStatus.Error;
	}

	public EMessageStatus sendEmailByMessage(IMessages message) throws MessagingException, IOException, CustomException
	{
		// Configuration exists inside message
		return sendMimeMessage(message, ConfigurationHandler.getInstance().createEmailSender(message));
	}

	@Override
	public EReturn sendEmailToUserOrGroup(Authentication auth, String token, MessageFormBean messageFormBean) throws Exception
	{
		return EReturn.Success;
	}

	private EMessageStatus sendMimeMessage(IMessages message, JavaMailSender emailSender) throws MessagingException, IOException
	{
		ConfigurationEmail configuration = (ConfigurationEmail) message.getConfiguration();
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(configuration.getFromId(), configuration.getFromName());
		helper.setTo(message.getRecipients());
		helper.setSubject(message.generateVTLSubject());
		helper.setText(message.generateVTLMessage(), message.isTextHTML());

		// Add Message`
		// No Message for Predefined Messages Based Messages
		if (message.getMessageId() == null && attachmentDao.countByMessageId(message.getMessageId()) > 0)
		{
			List<EmailAttachments> attachmentList = attachmentDao.getByMessageId(message.getMessageId());

			if (CommonValidator.isListFirstNotEmpty(attachmentList))
			{
				String basePath = getResourceBasePath("ATTACHMENT");
				File file = null;
				for (EmailAttachments attachment : attachmentList)
				{
					file = new File(basePath + attachment.getUploadFileFolderURL());
					helper.addAttachment(file.getName(), file);
				}
			}
		}

		// Send Mail
		emailSender.send(mimeMessage);
		return EMessageStatus.Completed;
	}

	@Override
	public void updateMessageStatus(Authentication auth, String messageId, EMessageStatus messageStatus)
	{
		// List<Messages> messageList = messageDao.getByMessageId(messageId);
		// messageList.forEach((message) -> {
		// message.setMessageStatus(messageStatus.name());
		// message.modifiedUserInfo(auth);
		// messageDao.save(message);
		// });
	}

	@Override
	public EReturn testConnection(IMessages message) throws CustomException
	{
		
		try
		{
			JavaMailSenderImpl mailSender = (JavaMailSenderImpl) ConfigurationHandler.getInstance().createEmailSender(message);
			mailSender.testConnection();
		}
		catch (MessagingException excep)
		{
			excep.printStackTrace();
			return EReturn.Failure;
		}
		return EReturn.Success;
	}
}