package org.hbs.core.beans.model.channel;

import org.hbs.core.util.EnumInterface;

public interface IChannel
{
	public enum EChannel implements EnumInterface
	{
		Email, Indeed, LinkedIn, SMS, WhatsApp;
	}

	public enum EEmail implements EnumInterface
	{
		Authentication, Debug, FromId, FromName, HostAddress, Password, Port, Protocol, TTLS, Username;
	}

	public enum EWhatsApp implements EnumInterface
	{
		AccountSID, AuthToken, WhatsAppWebURL;
	}

	public enum ESMS implements EnumInterface
	{
		AccountSID, AuthToken, Password, ReceiptantMobile, RequestURL, ResponseURL, UserName, WebsiteURL;

	}

	public enum ELinkedIn implements EnumInterface
	{
		AccountSID, AuthToken, Password, RequestURL, ResponseURL, UserName, WebsiteURL;

	}

	public enum EIndeed implements EnumInterface
	{
		AccountSID, AuthToken, Password, RequestURL, ResponseURL, UserName, WebsiteURL;

	}
}
