package org.hbs.sender;

import org.hbs.core.beans.MessageFormBean;
import org.hbs.core.beans.SMSResponseBean;
import org.hbs.core.security.resource.IPath;

public interface ISMSGateway extends IPath
{
	SMSResponseBean sendSMSMessages(MessageFormBean messageFormBean);

	String callBackResponse(String responseUrl, String token, String messageId);
}
