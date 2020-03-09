package org.hbs.sender.bo;

import java.io.IOException;

import org.hbs.core.beans.MessageFormBean;
import org.hbs.core.beans.SMSResponseBean;
import org.hbs.core.beans.model.channel.IChannelMessages;
import org.hbs.core.beans.model.clickatell.SMSCallBackFormBean;
import org.hbs.core.security.resource.IPathBase.EReturn;
import org.hbs.core.util.CustomException;
import org.springframework.security.core.Authentication;

public interface SMSSenderBo
{
	SMSResponseBean sendSMSByMessage(Authentication auth, IChannelMessages message) throws CustomException, IOException, ClassNotFoundException;

	EReturn sendSMSToUserOrGroup(Authentication auth, String token, MessageFormBean messageFormBean) throws Exception;

	void updateSMSStatus(SMSCallBackFormBean callBackFormBean);
}