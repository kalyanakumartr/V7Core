package org.hbs.core.beans.model.channel;

public class ConfigurationWhatsApp extends ConfigurationBase
{

	private static final long	serialVersionUID	= -3041171898471976459L;
	private String				accountSID;
	private String				authToken;
	private String				websiteURL;

	public ConfigurationWhatsApp()
	{
		super();
	}

	public String getAccountSID()
	{
		return accountSID;
	}

	public void setAccountSID(String accountSID)
	{
		this.accountSID = accountSID;
	}

	public String getAuthToken()
	{
		return authToken;
	}

	public void setAuthToken(String authToken)
	{
		this.authToken = authToken;
	}

	public String getWebsiteURL()
	{
		return websiteURL;
	}

	public void setWebsiteURL(String websiteURL)
	{
		this.websiteURL = websiteURL;
	}

}
