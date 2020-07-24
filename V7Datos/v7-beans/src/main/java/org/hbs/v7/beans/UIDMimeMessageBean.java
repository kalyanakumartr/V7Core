package org.hbs.v7.beans;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.hbs.core.beans.model.IConfiguration;

public class UIDMimeMessageBean extends AttachmentInfoBean
{

	private static final long	serialVersionUID	= -7422334914516037990L;

	public String				producerId;
	public Message				message;

	public UIDMimeMessageBean(IConfiguration config, Message message) throws MessagingException
	{
		super();
		this.message = message;
		this.producerId = config.getProducerId();
		this.setFileFolderURL(config.getBaseFolderPath());
		this.setSubFolderPath(EDate.YYYYMMDD.formatted(message.getSentDate()) + SLASH + EDate.HHMM.formatted(message.getSentDate()));
	}

}
