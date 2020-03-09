package org.hbs.extractor.event;

import java.io.Serializable;
import java.util.Date;

import org.hbs.core.beans.model.channel.ConfigurationEmail;
import org.springframework.messaging.MessagingException;

public class KafkaEmailReferenceBean implements Serializable{

	private static final long serialVersionUID = -6990780389417212191L;
	public int messageNumber;
	public Date sentDate;
	public ConfigurationEmail config;

	public KafkaEmailReferenceBean(int messageNumber, Date sentDate, ConfigurationEmail config) throws MessagingException
	{
		super();
		this.messageNumber = messageNumber;
		this.sentDate = sentDate;
		this.config = config;
	}

}
