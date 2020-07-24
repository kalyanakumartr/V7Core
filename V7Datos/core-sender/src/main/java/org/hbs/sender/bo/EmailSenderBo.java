package org.hbs.sender.bo;

import java.io.IOException;

import javax.mail.MessagingException;

import org.hbs.core.beans.MessageFormBean;
import org.hbs.core.beans.model.IMessages;
import org.hbs.core.beans.model.IMessages.EMessageStatus;
import org.hbs.core.security.resource.IPathBase.EReturn;
import org.hbs.core.util.CustomException;
import org.springframework.security.core.Authentication;

public interface EmailSenderBo
{
	EReturn testConnection(IMessages message) throws CustomException;
	
	EMessageStatus sendEmailByMessage(Authentication auth, String token, IMessages message);

	EReturn sendEmailToUserOrGroup(Authentication auth, String token, MessageFormBean formBean) throws Exception;

	void updateMessageStatus(Authentication auth, String messageId, EMessageStatus messageStatus);

	EMessageStatus sendEmailByMessage(IMessages message) throws MessagingException, IOException, CustomException;

}
