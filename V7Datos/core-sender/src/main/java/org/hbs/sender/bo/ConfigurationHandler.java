package org.hbs.sender.bo;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.hbs.core.beans.model.IMessages;
import org.hbs.core.beans.model.channel.ConfigurationEmail;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.CustomException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class ConfigurationHandler implements Serializable
{
	private static final long			serialVersionUID		= -7528213270732528611L;
	private static ConfigurationHandler	configurationHandler	= null;
	private Map<String, Object>			connectionMap			= null;

	public static ConfigurationHandler getInstance()
	{
		if (configurationHandler == null)
		{
			configurationHandler = new ConfigurationHandler();
		}
		return configurationHandler;
	}

	private ConfigurationHandler()
	{
		connectionMap = new ConcurrentHashMap<String, Object>();
	}

	public JavaMailSender createEmailSender(IMessages message) throws CustomException
	{
		if (CommonValidator.isNotNullNotEmpty(message.getConfiguration()))
		{
			if (connectionMap.containsKey(message.getConfiguration().getConnectionId()) == false)
			{
				ConfigurationEmail configuration = (ConfigurationEmail) message.getConfiguration();
				JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
				mailSender.setHost(configuration.getHostAddress());
				mailSender.setPort(Integer.parseInt(configuration.getPort()));
				mailSender.setUsername(configuration.getUserName());
				mailSender.setPassword(configuration.getPassword());
				Properties props = mailSender.getJavaMailProperties();

				for (String key : configuration.getAdditionalProperties().keySet())
				{
					props.put(key, configuration.getAdditionalProperties().get(key));
				}

				connectionMap.put(message.getConfiguration().getConnectionId(), mailSender);
			}
			return (JavaMailSender) connectionMap.get(message.getConfiguration().getConnectionId());
		}

		throw new CustomException("Email Sender or Receiver emailId id not available.");
	}
}
