package org.hbs.core.beans.model;

import org.hbs.core.util.EBusinessKey;
import org.hbs.core.util.EnumInterface;

public interface IAddress extends EBusinessKey
{
	public enum AddressType implements EnumInterface
	{
		BillingAddress, CommunicationAddress, OfficialAddress, PermanentAddress, PresentAddress, ShippingAddress;
	}

	public String getAddressId();

	public String getAddressLine1();

	public String getAddressLine2();

	public String getAddressLine3();

	public AddressType getAddressType();

	public String getCity();

	public Country getCountry();

	public String getLandmark();

	public String getPincode();

	public State getState();

	public void setAddressId(String addressId);

	public void setAddressLine1(String addressLine1);

	public void setAddressLine2(String addressLine2);

	public void setAddressLine3(String addressLine3);

	public void setAddressType(AddressType addressType);

	public void setCity(String city);

	public void setCountry(Country country);

	public void setLandmark(String landmark);

	public void setPincode(String pincode);

	public void setState(State state);
}