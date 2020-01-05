package org.hbs.core.beans.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CommonAddress implements IAddress
{

	private static final long	serialVersionUID	= -3036756538790824356L;

	protected String			addressId;
	protected String			addressLine1;
	protected String			addressLine2;
	protected String			addressLine3;
	protected AddressType		addressType			= AddressType.CommunicationAddress;
	protected String			landmark;
	protected String			city;
	protected State				state				= new State();
	protected Country			country				= new Country();
	protected String			pincode;

	public CommonAddress()
	{
		super();
		this.addressId = getBusinessKey();
	}

	@Id
	@Column(name = "addressId")
	public String getAddressId()
	{
		return addressId;
	}

	public void setAddressId(String addressId)
	{
		this.addressId = addressId;
	}

	@Column(name = "addressLine1")
	public String getAddressLine1()
	{
		return addressLine1;
	}

	@Column(name = "addressLine2")
	public String getAddressLine2()
	{
		return addressLine2;
	}

	@Column(name = "addressLine3")
	public String getAddressLine3()
	{
		return addressLine3;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "addressType")
	public AddressType getAddressType()
	{
		return addressType;
	}

	@Column(name = "city")
	public String getCity()
	{
		return city;
	}

	@Override
	@ManyToOne(targetEntity = Country.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "country")
	public Country getCountry()
	{
		return country;
	}

	@Column(name = "landmark")
	public String getLandmark()
	{
		return landmark;
	}

	@Column(name = "pincode")
	public String getPincode()
	{
		return pincode;
	}

	@ManyToOne(targetEntity = State.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "state")
	public State getState()
	{
		return state;
	}

	public void setAddressLine1(String addressLine1)
	{
		this.addressLine1 = addressLine1;
	}

	public void setAddressLine2(String addressLine2)
	{
		this.addressLine2 = addressLine2;
	}

	public void setAddressLine3(String addressLine3)
	{
		this.addressLine3 = addressLine3;
	}

	public void setAddressType(AddressType addressType)
	{
		this.addressType = addressType;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	@Override
	public void setCountry(Country country)
	{
		this.country = country;
	}

	public void setLandmark(String landmark)
	{
		this.landmark = landmark;
	}

	public void setPincode(String pincode)
	{
		this.pincode = pincode;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	@Override
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

}