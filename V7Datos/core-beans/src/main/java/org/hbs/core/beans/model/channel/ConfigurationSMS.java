package org.hbs.core.beans.model.channel;

public class ConfigurationSMS extends ConfigurationBase
{

	private static final long	serialVersionUID	= -6696593306922499884L;
	private String				accountSID;
	private String				authToken;
	private String				password;
	private String				receiptantMobile;
	private String				requestURL;
	private String				responseURL;
	private String				userName;
	private String				websiteURL;

	public ConfigurationSMS()
	{
		super();
	}

	public String getAccountSID()
	{
		return accountSID;
	}

	public String getAuthToken()
	{
		return authToken;
	}

	public String getPassword()
	{
		return password;
	}

	public String getReceiptantMobile()
	{
		return receiptantMobile;
	}

	public String getRequestURL()
	{
		return requestURL;
	}

	public String getResponseURL()
	{
		return responseURL;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getWebsiteURL()
	{
		return websiteURL;
	}

	public void setAccountSID(String accountSID)
	{
		this.accountSID = accountSID;
	}

	public void setAuthToken(String authToken)
	{
		this.authToken = authToken;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setReceiptantMobile(String receiptantMobile)
	{
		this.receiptantMobile = receiptantMobile;
	}

	public void setRequestURL(String requestURL)
	{
		this.requestURL = requestURL;
	}

	public void setResponseURL(String responseURL)
	{
		this.responseURL = responseURL;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void setWebsiteURL(String websiteURL)
	{
		this.websiteURL = websiteURL;
	}

}
