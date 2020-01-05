package org.hbs.v7.extractor.action.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.UIDFolder;

import org.hbs.v7.extractor.action.core.AttachmentInfo;

public class UIDMimeMessage extends AttachmentInfo
{

	private static final long	serialVersionUID	= -7422334914516037990L;

	public String				producerId;
	public String				uniqueId;
	public Message				message;

	public UIDMimeMessage(String producerId, UIDFolder _UIDFolder, Message message) throws MessagingException
	{
		super();
		//this.uniqueId = _UIDFolder.getUID(message) + "";
		this.message = message;
		this.producerId = producerId;
	}

}
