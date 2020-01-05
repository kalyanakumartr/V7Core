package org.hbs.core.beans.model.channel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hbs.core.beans.model.CommonFileUpload;

@Entity
@Table(name = "emailattachments")
public class EmailAttachments extends CommonFileUpload
{
	private static final long	serialVersionUID	= 917678364001988324L;

	protected IChannelMessages	message;

	public EmailAttachments()
	{
		super();
	}

	@ManyToOne(targetEntity = ChannelMessages.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "messageId")
	public IChannelMessages getMessage()
	{
		return message;
	}

	public void setMessage(IChannelMessages message)
	{
		this.message = message;
	}
}
