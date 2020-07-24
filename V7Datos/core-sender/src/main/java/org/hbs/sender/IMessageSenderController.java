package org.hbs.sender;

import javax.servlet.http.HttpServletResponse;

import org.hbs.core.beans.ConfigurationFormBean;
import org.hbs.core.beans.PasswordFormBean;
import org.hbs.core.beans.model.V7Messages;
import org.hbs.core.beans.model.channel.IChannelMessages;
import org.hbs.core.beans.model.clickatell.SMSCallBackFormBean;
import org.hbs.core.beans.path.IPathSender;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

public interface IMessageSenderController extends IPathSender
{
	@GetMapping
	@RequestMapping(value = DOWNLOAD_ATTACHMENT, produces = MEDIA_TYPE_ZIP)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	public ResponseEntity<?> downloadAttachment(Authentication auth, Long attachmentId, HttpServletResponse response);

	@PostMapping
	@RequestMapping(value = SAVE_MESSAGE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	public ResponseEntity<?> saveMessageAndSendToMedia(Authentication auth, String token, String formBean, MultipartFile[] files);

	@PostMapping
	@RequestMapping(value = SEND_MAIL, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	public ResponseEntity<?> sendEmail(Authentication auth, String token, V7Messages message);

	@PostMapping
	@RequestMapping(value = SEND_SMS, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	public ResponseEntity<?> sendSMS(Authentication auth, IChannelMessages message);

	@PostMapping
	@RequestMapping(value = SEND_SMS_OTP, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendSMS_OTP(Authentication auth, PasswordFormBean pfForm);

	@PostMapping
	@RequestMapping(value = SMS_STATUS_CALLBACK, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> smsCallBackStatus(SMSCallBackFormBean callBackFormBean);

	@PostMapping
	@RequestMapping(value = TEST_CONFIGURATION, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> testConfiguration(Authentication auth, ConfigurationFormBean cfgFormBean);

	@PostMapping
	@RequestMapping(value = ACKNOWLEDGE_MESSAGE_STATUS, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	public ResponseEntity<?> updateMessageStatus(Authentication auth, long messageId, String messageStatus);

}