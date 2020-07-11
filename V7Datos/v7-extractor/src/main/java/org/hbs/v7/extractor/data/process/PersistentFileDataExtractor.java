package org.hbs.v7.extractor.data.process;

import java.io.IOException;

import javax.mail.MessagingException;

import org.hbs.core.kafka.IKafkaConstants;
import org.hbs.extractor.beans.DataInTopicBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PersistentFileDataExtractor implements IKafkaConstants
{
	private static final long	serialVersionUID	= -772482917892822104L;
	private final Logger		logger				= LoggerFactory.getLogger(this.getClass());

	@KafkaListener(topicPartitions = @TopicPartition(topic = DATA_EXTRACT_TOPIC, partitions = { DATA_IN, DATA_IN_EXPEDITE }), groupId = DATA_EXTRACT_GROUP)
	public void persistantDataReader(@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition, String payload) throws IOException, MessagingException
	{
		logger.info(String.format("##PersistentFileDataExtractor## -> Partition %d message -> %s", partition, payload));

		ObjectMapper oMapper = new ObjectMapper();
		DataInTopicBean inBean = oMapper.readValue(payload, DataInTopicBean.class);

		switch ( inBean.getExtension() )
		{
			case Doc :
			case Docx :
			case ODT :
			case PDF :
			case HTM :
			case HTML :
			case XLS :
			case XLSX :
			case Csv :
			case Json :
			default :
				System.out.println(">>>>>>>>>>>>>>>PersistentFileDataExtractor>>>>>>>>>>>>>>>>>>> " + inBean.getExtension());
				break;
		}

	}
}
