package org.hbs.event.service;

import java.io.IOException;

import org.hbs.core.security.resource.IPath;
import org.hbs.sender.bo.SMSSenderBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

// @Service
public class SenderConsumer implements IPath
{

	@Autowired
	SMSSenderBo					senderBo;

	private static final long	serialVersionUID	= -3892033320967613405L;
	private final Logger		logger				= LoggerFactory.getLogger(SenderConsumer.class);

	// @KafkaListener(topicPartitions = @TopicPartition(topic = INTERNAL_TOPIC, partitions = { "0"
	// }), groupId = EMPLOYEE_ID, clientIdPrefix = EMAIL)
	public void consumeEmail(String message) throws IOException
	{
		logger.info("\n\n\n-----------------SenderConsumer------Starting--------------------------");
		logger.info(String.format("#### -> consumeEmail message -> %s", message));

		// String status = senderBo.testConnection(new Gson().fromJson(message,
		// ConfigurationFormBean.class));
		// String status = RestClientUtil.sendUserCreationMail(new Gson().fromJson(message,
		// UserFormBean.class));
		// logger.info("MAIL TRIGGER STATUS -----> "+status);

		logger.info("-----------------SenderConsumer------End--------------------------\\n\\n\\n");

	}

	// @KafkaListener(topicPartitions = @TopicPartition(topic = INTERNAL_TOPIC, partitions = { "1"
	// }), groupId = EMPLOYEE_ID, clientIdPrefix = SMS)
	public void consumeSMS(String message) throws IOException
	{
		logger.info("\n\n\n-----------------SenderConsumer------Starting--------------------------");
		logger.info(String.format("#### -> consumeSMS message -> %s", message));
		logger.info("-----------------SenderConsumer------End--------------------------\\n\\n\\n");
	}
}