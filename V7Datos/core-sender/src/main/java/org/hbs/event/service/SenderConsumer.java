package org.hbs.event.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.model.V7Messages;
import org.hbs.core.dao.MessageDao;
import org.hbs.core.kafka.IKafkaConstants;
import org.hbs.core.security.resource.IPathBase.EMediaMode;
import org.hbs.core.security.resource.IPathBase.EMediaType;
import org.hbs.core.util.CustomException;
import org.hbs.sender.bo.EmailSenderBo;
import org.hbs.sender.bo.SMSSenderBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SenderConsumer implements IKafkaConstants
{

	@Autowired
	SMSSenderBo					senderBo;

	@Autowired
	EmailSenderBo				emailSenderBo;

	@Autowired
	MessageDao					messageDao;

	private static final long	serialVersionUID	= -3892033320967613405L;
	// private final Logger logger = LoggerFactory.getLogger(SenderConsumer.class);

	@KafkaListener(topicPartitions = @TopicPartition(topic = INTERNAL_TOPIC, partitions = { "0" }), groupId = INTERNAL_GROUP, clientIdPrefix = EMAIL)
	public void consumeEmail(@Payload String payload, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String messageId) throws IOException, MessagingException, CustomException, ClassNotFoundException
	{
		// logger.info("\n\n\n-----------------SenderConsumer------Starting--------------------------");
		// logger.info(String.format("#### -> consumeEmail message -> %s", message));
		System.out.println(String.format(messageId + " <<< key#### -> consumeEmail RECEIVED_MESSAGE_KEY message -> %s", payload));

		ObjectMapper oMapper = new ObjectMapper();
		UserFormBean ufBean = oMapper.readValue(payload, UserFormBean.class);

		List<V7Messages> messageList = messageDao.getByMessageId(ufBean.user.getProducerId(), messageId);

		for (V7Messages message : messageList)
		{
			message.setDataMap(ufBean.updateObjectAsMap());
			message.generateConfigurationFromProducerProperty(EMediaMode.Internal);
			message.setRecipients(ufBean.user.getMediaToDisplay(EMediaType.Primary).getEmailId());
			emailSenderBo.sendEmailByMessage(message);
		}
		// //logger.info("MAIL TRIGGER STATUS -----> "+status);

		// logger.info("-----------------SenderConsumer------End--------------------------\\n\\n\\n");

	}

	@KafkaListener(topicPartitions = @TopicPartition(topic = INTERNAL_TOPIC, partitions = { "1" }), groupId = INTERNAL_GROUP, clientIdPrefix = SMS)
	public void consumeSMS(String message) throws IOException
	{
		System.out.println(String.format("#### -> consumeSMS message -> %s", message));
		// logger.info("\n\n\n-----------------SenderConsumer------Starting--------------------------");
		// logger.info(String.format("#### -> consumeSMS message -> %s", message));
		// logger.info("-----------------SenderConsumer------End--------------------------\\n\\n\\n");
	}
}