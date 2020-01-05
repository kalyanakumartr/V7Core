package org.hbs.core.event.service;

import java.io.IOException;

import org.hbs.core.security.resource.IPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Service
public class AdminConsumer implements IPath
{

	private static final long	serialVersionUID	= -3892033320967613405L;
	private final Logger		logger				= LoggerFactory.getLogger(AdminConsumer.class);

	// @KafkaListener(topics = MESSAGES_TOPIC, groupId = EMPLOYEE_ID)
	public void consume(String message) throws IOException
	{
		logger.info(String.format("#### -> Consumed message -> %s", message));
	}
}