package org.hbs.core.beans.model.clickatell;

import java.io.Serializable;

/*
 * CAUTION : Even a small changes in the POJO will results ClickaTell SMS Gateway STOP Sending
 * messages and it will FAIL. DoNOT add or remove any field variables in this POJO
 */
public class MessageContent implements Serializable
{

	private static final long	serialVersionUID	= -1702955718515965336L;

	public String[]				to;

	public String				content;

	public String[] getTo()
	{
		return to;
	}

	public void setTo(String[] to)
	{
		this.to = to;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

}
