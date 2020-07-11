package org.hbs.core.beans.model.channel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;

public class ConfigurationEmail extends ConfigurationBase
{

	private static final long	serialVersionUID	= 5938436998922289517L;
	private String				protocol;
	private String				socketFactory;
	private String				fallBack;
	private String				enablessl;
	private String				hostAddress;
	private String				userName;
	private String				password;
	private String				port;
	private String				ttls;
	private String				debug;
	private String				fromId;
	private String				fromName;
	private EmailChannel		source;

	public enum EmailChannel implements EnumInterface
	{
		Gmail_IMAP, Gmail_Pop3, Gmail_SMTP;

		ConfigurationEmail config;

		public Properties getProperties()
		{
			Map<String, String[]> propMap = props();
			Properties property = new Properties();
			for (String key : propMap.keySet())
			{
				String[] $Props = propMap.get(key);
				if (CommonValidator.isArrayFirstNotNull($Props))
					property.put($Props[0], $Props[1]);
			}
			return property;
		}

		public Map<String, String[]> props()
		{
			Map<String, String[]> map = new LinkedHashMap<>();
			switch ( this )
			{
				case Gmail_IMAP :
				{
					map.put("SocketFactory", new String[] { "mail.imap.socketFactory.class", this.config == null ? "javax.net.ssl.SSLSocketFactory" : config.socketFactory });
					map.put("FallBack", new String[] { "mail.imap.socketFactory.fallback", this.config == null ? "true,false" : config.fallBack });
					map.put("Enable SSL", new String[] { "mail.imap.ssl.enable", this.config == null ? "true,false" : config.enablessl });
					map.put("TLS Encryption Type", new String[] { "mail.imap.starttls.enable", this.config == null ? "true,false" : config.ttls });
					map.put("Protocol", new String[] { "mail.store.protocol", "imaps" });
					map.put("Hostname", new String[] { "mail.host", "imap.gmail.com" });
					map.put("Port", new String[] { "mail.port", this.config == null ? "587" : config.port });
					map.put("UserName", null);
					map.put("Password", null);
					break;
				}
				case Gmail_Pop3 : // Implement like above
				{
					map.put("SocketFactory", new String[] { "mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory" });
					map.put("FallBack", new String[] { "mail.pop3.socketFactory.fallback", "true,false" });
					map.put("Enable SSL", new String[] { "mail.pop3.ssl.enable", "true,false" });
					map.put("TLS Encryption Type", new String[] { "mail.pop3.starttls.enable", "true,false" });
					map.put("Protocol", new String[] { "mail.store.protocol", "pop3" });
					map.put("Hostname", new String[] { "mail.host", "pop3.gmail.com" });
					map.put("Port", new String[] { "mail.port", "993" });
					map.put("UserName", null);
					map.put("Password", null);
					break;
				}
				case Gmail_SMTP :// Implement like above
				{
					map.put("UserName", new String[] { "mail.smtp.user", "" });
					map.put("Password", new String[] { "mail.smtp.password", "" });
					map.put("Hostname", new String[] { "mail.smtp.host", "smtp.gmail.com" });
					map.put("TLS Encryption Type", new String[] { "mail.smtp.starttls.enable", "true,false" });
					map.put("SocketFactory", new String[] { "mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory" });
					map.put("SSL Port", new String[] { "mail.smtp.socketFactory.port", "465" });
					map.put("Port", new String[] { "mail.smtp.port", "465" });
					map.put("Auth", new String[] { "mail.smtp.auth", "true,false" });

					break;
				}

				default :
					break;
			}
			return map;
		}

	}

	public ConfigurationEmail()
	{
		super();
	}

	public String getProtocol()
	{
		return protocol;
	}

	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	public String getSocketFactory()
	{
		return socketFactory;
	}

	public void setSocketFactory(String socketFactory)
	{
		this.socketFactory = socketFactory;
	}

	public String getFallBack()
	{
		return fallBack;
	}

	public void setFallBack(String fallBack)
	{
		this.fallBack = fallBack;
	}

	public String getEnablessl()
	{
		return enablessl;
	}

	public void setEnablessl(String enablessl)
	{
		this.enablessl = enablessl;
	}

	public String getHostAddress()
	{
		return hostAddress;
	}

	public void setHostAddress(String hostAddress)
	{
		this.hostAddress = hostAddress;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPort()
	{
		return port;
	}

	public void setPort(String port)
	{
		this.port = port;
	}

	public String getTtls()
	{
		return ttls;
	}

	public void setTtls(String ttls)
	{
		this.ttls = ttls;
	}

	public String getDebug()
	{
		return debug;
	}

	public void setDebug(String debug)
	{
		this.debug = debug;
	}

	public String getFromId()
	{
		return fromId;
	}

	public void setFromId(String fromId)
	{
		this.fromId = fromId;
	}

	public String getFromName()
	{
		return fromName;
	}

	public void setFromName(String fromName)
	{
		this.fromName = fromName;
	}

	public EmailChannel getSource()
	{
		source.config = this;
		return source;
	}

	public void setSource(EmailChannel source)
	{
		this.source = source;
	}
}
