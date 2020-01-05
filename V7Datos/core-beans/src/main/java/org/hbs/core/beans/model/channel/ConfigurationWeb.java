package org.hbs.core.beans.model.channel;

public class ConfigurationWeb extends ConfigurationBase
{

	private static final long	serialVersionUID	= -6102812339164812067L;
	private String				accountSID;
	private String				authToken;
	private String				websiteURL;

	public ConfigurationWeb()
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
