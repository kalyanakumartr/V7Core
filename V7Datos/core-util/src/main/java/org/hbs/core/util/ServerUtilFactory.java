package org.hbs.core.util;

import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

public class ServerUtilFactory implements IConstProperty
{

	private static final long			serialVersionUID	= 7653346655071987488L;
	private static ServerUtilFactory	serverUtilFactory	= null;
	private String						serverTZ			= null;

	@Value("${server.address:#{null}}")
	private String						hostname;

	@Value("${server.port:#{null}}")
	private String						port;

	@Value("${server.servlet.context-path}")
	private String						contextpath;

	@Autowired
	Environment							env;

	public static ServerUtilFactory getInstance()
	{
		if (serverUtilFactory == null)
		{
			serverUtilFactory = new ServerUtilFactory();
		}
		return serverUtilFactory;
	}

	private ServerUtilFactory()
	{
		long milliDiff = Calendar.getInstance().get(Calendar.ZONE_OFFSET);

		for (String id : TimeZone.getAvailableIDs())
		{
			if (TimeZone.getTimeZone(id).getRawOffset() == milliDiff)
			{
				serverTZ = id;
				break;
			}
		}
	}

	public String getTimeZone()
	{
		return serverTZ;
	}

	public String getDomainURL()
	{
		
		if (env.getActiveProfiles()[0].equals("dev"))
		{
			if (hostname == null)
				hostname = "localhost";

			String protocol = (hostname.equals("localhost") || hostname.equals("127.0.0.1")) ? "http://" : "https://";
			return protocol + hostname + ((port == null) ? "" : (":" + port)) + contextpath;
		}
		else
		{
			if(env.getProperty("_DomainURL") == null )
			{
				System.out.println("WARNING : ********************For Production Environment, add '_DomainURL' in OS enviroment********************");
				return "ERROR";
			}
			return env.getProperty("_DomainURL") ;
		}
	}
}
