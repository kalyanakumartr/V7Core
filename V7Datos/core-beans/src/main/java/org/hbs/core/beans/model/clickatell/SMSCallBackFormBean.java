package org.hbs.core.beans.model.clickatell;

import java.io.Serializable;

public class SMSCallBackFormBean implements Serializable
{
	@Override
	public String toString()
	{
		return "SMSCallBackFormBean [integrationName=" + integrationName + ", templateId=" + messageId + ", requestId=" + requestId + ", clientMessageId=" + clientMessageId + ", to=" + to + ", from="
				+ from + ", statusCode=" + statusCode + ", status=" + status + ", statusDescription=" + statusDescription + ", timestamp=" + timestamp + "]";
	}

	private static final long	serialVersionUID	= -7995795518514730783L;

	protected String			integrationName;
	protected String			messageId;
	protected String			requestId;
	protected String			clientMessageId;
	protected String			to;
	protected String			from;
	protected String			statusCode;
	protected String			status;
	protected String			statusDescription;
	protected String			timestamp;

	public String getIntegrationName()
	{
		return integrationName;
	}

	public void setIntegrationName(String integrationName)
	{
		this.integrationName = integrationName;
	}

	public String getMessageId()
	{
		return messageId;
	}

	public void setMessageId(String messageId)
	{
		this.messageId = messageId;
	}

	public String getRequestId()
	{
		return requestId;
	}

	public void setRequestId(String requestId)
	{
		this.requestId = requestId;
	}

	public String getClientMessageId()
	{
		return clientMessageId;
	}

	public void setClientMessageId(String clientMessageId)
	{
		this.clientMessageId = clientMessageId;
	}

	public String getTo()
	{
		return to;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public String getFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public String getStatusCode()
	{
		return statusCode;
	}

	public void setStatusCode(String statusCode)
	{
		this.statusCode = statusCode;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getStatusDescription()
	{
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription)
	{
		this.statusDescription = statusDescription;
	}

	public String getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(String timestamp)
	{
		this.timestamp = timestamp;
	}

}
