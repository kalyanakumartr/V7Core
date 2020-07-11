package org.hbs.core.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.hbs.core.util.EnumInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GenericKafkaProducer implements IKafkaConstants
{
	private static final long				serialVersionUID	= 1643040639961382216L;
	// private static final Logger logger = LoggerFactory.getLogger(GenericKafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, Object>	kafkaTemplate;
	//
	// @Autowired
	// private ReplyingKafkaTemplate<String, Object, Object> kafkaTemplateReply;

	public void send(ETopic eTopic, KAFKAPartition partition, EnumInterface eTemplate, Object object) throws JsonProcessingException
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(object);

			this.kafkaTemplate.send(eTopic.getTopic(), partition.getPartition(), eTemplate.name(), jsonString);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// logger.info("Send Message ::: " + eTemplate.name());
	}

	// public Object sendAndReceive(ETopic eTopic, KAFKAPartition partition, EnumInterface
	// eTemplate, Object object) throws JsonProcessingException
	// {
	// try
	// {
	// ProducerRecord<String, Object> record = new ProducerRecord<String, Object>(eTopic.getTopic(),
	// partition.getPartition(), eTemplate.name(), object);
	//
	// record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC,
	// DATA_EXTRACT_REPLY_TOPIC.getBytes()));
	//
	// return kafkaTemplateReply.sendAndReceive(record).get().value();
	//
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	// // logger.info("Send Message ::: " + eTemplate.name());
	// return null;
	// }
}
