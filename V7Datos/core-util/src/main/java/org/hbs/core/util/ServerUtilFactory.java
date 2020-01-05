package org.hbs.core.util;

import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;

public class ServerUtilFactory implements IConstProperty
{

	private static final long			serialVersionUID	= 7653346655071987488L;
	private static ServerUtilFactory	serverUtilFactory	= null;
	private String						serverTZ			= null;
	public MicroServices				microService;

	@Value("${DomainUrl:http://localhost}")
	public static String				_DomainUrl;

	@Value("${Environment:Dev}")
	private static String				_Environment;

	@Value("${EurekaPort}")
	private static String				_EurekaPort;
	@Value("${OAuthPort}")
	private static String				_OAuthPort;
	@Value("${AdminPort}")
	private static String				_AdminPort;
	@Value("${SenderPort}")
	private static String				_SenderPort;
	@Value("${ViewPort}")
	private static String				_ViewPort;
	@Value("${ExtractorPort}")
	private static String				_ExtractorPort;
	@Value("${UIPort}")
	private static String				_UIPort;

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

	public enum EEnvironment implements EnumInterface
	{
		Dev, UAT, Prod
	}

	public enum MicroServices implements EnumInterface
	{
		UI("core-ui", "8080"), Eureka("core-eureka", "8090"), OAuth("core-oauth", "8100"), Admin("core-admin", "8200"), Sender("core-sender", "8300"), View("core-view",
				"8400"), Extractor("core-extractor", "8500");

		String	context;
		String	port;

		MicroServices(String context, String port)
		{
			this.context = context;
			this.port = port;
		}

		public String getUrl()
		{
			return getUrl(null);
		}

		public String getUrl(String path)
		{
			StringBuffer serviceUrl = new StringBuffer(_DomainUrl);

			switch ( EEnvironment.valueOf(_Environment) )
			{
				case Dev :
				case UAT :
				{
					serviceUrl.append(COLON);
					switch ( this )
					{
						case UI :
						{
							serviceUrl.append(_UIPort == null ? this.port : _UIPort);
							break;
						}
						case Eureka :
						{
							serviceUrl.append(_EurekaPort == null ? this.port : _EurekaPort);
							break;
						}
						case OAuth :
						{
							serviceUrl.append(_OAuthPort == null ? this.port : _OAuthPort);
							break;
						}
						case Admin :
						{
							serviceUrl.append(_AdminPort == null ? this.port : _AdminPort);
							break;
						}
						case Sender :
						{
							serviceUrl.append(_SenderPort == null ? this.port : _SenderPort);
							break;
						}
						case View :
						{
							serviceUrl.append(_ViewPort == null ? this.port : _ViewPort);
							break;
						}
						case Extractor :
						{
							serviceUrl.append(_ExtractorPort == null ? this.port : _ExtractorPort);
							break;
						}
						default :
							break;
					}
				}
				default :
				{
					serviceUrl.append(SLASH + this.context);
					if (path != null)
						serviceUrl.append(path);
					serviceUrl.append(SLASH);
					System.out.println("--------------serviceUrl---------------" + serviceUrl);
				}
			}
			return serviceUrl.toString();
		}

	}
}
