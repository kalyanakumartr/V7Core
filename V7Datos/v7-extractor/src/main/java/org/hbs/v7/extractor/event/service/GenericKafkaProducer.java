package org.hbs.v7.extractor.event.service;

import org.hbs.core.security.resource.IPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class GenericKafkaProducer implements IPath
{
	private static final long				serialVersionUID	= 1643040639961382216L;
	private static final Logger				logger				= LoggerFactory.getLogger(GenericKafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, Object>	kafkaTemplate;

	public void sendMessage(ETopic eTopic, EMedia eMedia, ETemplate eTemplate, Object object)
	{
		this.kafkaTemplate.send(eTopic.name(), eMedia.ordinal(), eTemplate.name(), new Gson().toJson(object));
		logger.info("Send Message ::: " + eTemplate.name());
	}

	public void sendMessage(ETopic eTopic, EMedia eMedia, Object object)
	{
		this.kafkaTemplate.send(eTopic.name(), eMedia.ordinal(), eMedia.name(), object);
		logger.info("Send Message ::: " + eTopic.name());
	}

}
