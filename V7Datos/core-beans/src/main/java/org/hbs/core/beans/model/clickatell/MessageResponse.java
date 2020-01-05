package org.hbs.core.beans.model.clickatell;

import java.io.Serializable;

/*
 * CAUTION : Even a small changes in the POJO will results ClickaTell SMS Gateway STOP Sending
 * messages and it will FAIL. DoNOT add or remove any field variables in this POJO
 */
public class MessageResponse implements Serializable
{

	private static final long	serialVersionUID	= -3365731233035235636L;

	public Messages[]			messages;
	public String				errorCode;
	public String				error;
	public String				errorDescription;

	public Messages[] getMessages()
	{
		return messages;
	}

	public void setMessages(Messages[] messages)
	{
		this.messages = messages;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	public String getErrorDescription()
	{
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription)
	{
		this.errorDescription = errorDescription;
	}

}
