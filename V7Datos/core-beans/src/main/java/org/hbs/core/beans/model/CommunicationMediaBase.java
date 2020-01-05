package org.hbs.core.beans.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CommunicationMediaBase implements ICommunicationMediaBase
{
	protected String	emailId;
	protected String	mobileNo;
	protected String	phoneNo;
	protected String	whatsAppNo;

	public CommunicationMediaBase()
	{
		super();
	}

	@Override
	@Column(name = "emailId")
	public String getEmailId()
	{
		return emailId;
	}

	@Override
	@Column(name = "mobileNo")
	public String getMobileNo()
	{
		return mobileNo;
	}

	@Override
	@Column(name = "phoneNo")
	public String getPhoneNo()
	{
		return phoneNo;
	}

	@Override
	@Column(name = "whatsAppNo")
	public String getWhatsAppNo()
	{
		return whatsAppNo;
	}

	@Override
	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	@Override
	public void setMobileNo(String mobileNo)
	{
		this.mobileNo = mobileNo;
	}

	@Override
	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}

	@Override
	public void setWhatsAppNo(String whatsAppNo)
	{
		this.whatsAppNo = whatsAppNo;
	}

}