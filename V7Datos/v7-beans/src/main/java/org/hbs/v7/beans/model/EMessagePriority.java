package org.hbs.v7.beans.model;

import java.util.Arrays;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;

public enum EMessagePriority implements EnumInterface
{
	Expedite, Normal;

	static final String	HIGH_IMPORTANCE	= "[HIGH]";
	static final String	HIGH_PRIORITY	= "1";
	static final String	X_PRIORITY		= "X-Priority";

	public static EMessagePriority getPriority(Message message) throws MessagingException
	{
		String[] priority = message.getHeader(X_PRIORITY);

		if ((CommonValidator.isArrayFirstNotNull(priority) && Arrays.asList(priority).contains(HIGH_PRIORITY)) || message.getSubject().toUpperCase().indexOf(HIGH_IMPORTANCE) > -1)
			return EMessagePriority.Expedite;
		else
			return EMessagePriority.Normal;
	}
}
