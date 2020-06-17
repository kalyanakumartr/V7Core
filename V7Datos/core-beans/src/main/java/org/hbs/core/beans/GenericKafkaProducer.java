package org.hbs.core.beans;

import org.hbs.core.security.resource.IPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GenericKafkaProducer implements IPath
{
	private static final long				serialVersionUID	= 1643040639961382216L;
	// private static final Logger logger = LoggerFactory.getLogger(GenericKafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, Object>	kafkaTemplate;

	public void sendMessage(ETopic eTopic, EMedia eMedia, ETemplate eTemplate, Object object) throws JsonProcessingException
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(object);

			System.out.println(">>>>>>>>>>>>>>jsonString>>>>>>>>>>>>>>>>>> " + jsonString);
			this.kafkaTemplate.send(eTopic.getTopic(), eMedia.ordinal(), eTemplate.name(), jsonString);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// logger.info("Send Message ::: " + eTemplate.name());
	}

	public void sendMessage(ETopic eTopic, EMedia eMedia, Object object)
	{
		this.kafkaTemplate.send(eTopic.name(), eMedia.ordinal(), eMedia.name(), object);
		// logger.info("Send Message ::: " + eTopic.name());
	}

}
