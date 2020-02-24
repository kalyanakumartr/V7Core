package org.hbs.core.beans.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hbs.core.util.ICRUDBean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "country")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Country implements ICRUDBean, Comparable<Country>
{

	private static final long	serialVersionUID	= 8372130046238222330L;
	protected String			country;
	protected String			countryName;
	protected int				displayOrder;
	protected boolean			status;

	public Country()
	{
		super();
	}

	public Country(String country, String countryName, boolean status)
	{
		super();
		this.country = country;
		this.countryName = countryName;
		this.status = status;
	}

	@Id
	@Column(name = "country")
	public String getCountry()
	{
		return country;
	}

	@Column(name = "countryName")
	public String getCountryName()
	{
		return countryName;
	}

	@Column(name = "displayOrder")
	@JsonIgnore
	public int getDisplayOrder()
	{
		return displayOrder;
	}

	@Column(name = "status")
	@JsonIgnore
	public boolean isStatus()
	{
		return status;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public void setCountryName(String countryName)
	{
		this.countryName = countryName;
	}

	public void setDisplayOrder(int displayOrder)
	{
		this.displayOrder = displayOrder;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

	@Override
	public int compareTo(Country country)
	{
		return countryName.compareTo(country.getCountryName());
	}
}
