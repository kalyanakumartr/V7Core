package org.hbs.core.beans;

import java.io.Serializable;

import org.hbs.core.beans.model.IMessages.EMessageStatus;

public class SMSResponseBean implements Serializable
{
	private static final long	serialVersionUID	= 2684230694746799002L;

	public String				repsonseId;
	public String				errorCode;
	public String				error;
	public String				errorDescription;
	public EMessageStatus		status;

	public SMSResponseBean()
	{
		super();
	}

	public SMSResponseBean(EMessageStatus status)
	{
		super();
		this.status = status;
	}

	public String getRepsonseId()
	{
		return repsonseId;
	}

	public void setRepsonseId(String repsonseId)
	{
		this.repsonseId = repsonseId;
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

	public EMessageStatus getStatus()
	{
		return status;
	}

	public void setStatus(EMessageStatus status)
	{
		this.status = status;
	}

}
