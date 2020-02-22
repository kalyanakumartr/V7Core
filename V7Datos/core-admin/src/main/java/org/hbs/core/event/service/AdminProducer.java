package org.hbs.core.event.service;

import org.hbs.core.security.resource.IPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

//@Service
public class AdminProducer implements IPath
{
	private static final long				serialVersionUID	= 1643040639961382216L;
	//private static final Logger				logger				= LoggerFactory.getLogger(AdminProducer.class);

	@Autowired
	private KafkaTemplate<String, Object>	kafkaTemplate;

	public void sendEmailMessage(ETemplate eTemplate, Object object)
	{
		//logger.info("Send Email Message ::: " + eTemplate.name());
		//this.kafkaTemplate.send(INTERNAL_TOPIC, ETopicPartition.Email.ordinal(), eTemplate.name(), new Gson().toJson(object));
	}

	public void sendSMSMessage(ETemplate eTemplate, Object object)
	{
		//logger.info("Send SMSMessage ::: " + eTemplate.name());
		
		
		//this.kafkaTemplate.send(INTERNAL_TOPIC, ETopicPartition.SMS.ordinal(), eTemplate.name(), new Gson().toJson(object));
	}
}
