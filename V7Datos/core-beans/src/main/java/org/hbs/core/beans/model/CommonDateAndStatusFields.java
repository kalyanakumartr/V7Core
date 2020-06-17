package org.hbs.core.beans.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hbs.core.beans.TimestampDeserializer;
import org.hbs.core.beans.TimestampSerializer;
import org.hbs.core.util.IConstProperty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@MappedSuperclass
public abstract class CommonDateAndStatusFields implements ICommonDateAndStatusFields, IConstProperty
{
	private static final long	serialVersionUID		= -4784531727752023870L;

	protected Timestamp			createdDate;

	protected String			createdDateByTimeZone	= "";

	protected Timestamp			modifiedDate;

	protected String			modifiedDateByTimeZone	= "";

	protected Boolean			status					= true;

	public CommonDateAndStatusFields()
	{
		super();
	}

	public CommonDateAndStatusFields(Timestamp createdDate, Timestamp modifiedDate, Boolean status)
	{
		super();
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
	}

	@Column(name = "createdDate")
	@JsonSerialize(using = TimestampSerializer.class)
	@JsonDeserialize(using = TimestampDeserializer.class)
	public Timestamp getCreatedDate()
	{
		return createdDate;
	}

	@Column(name = "modifiedDate")
	@JsonSerialize(using = TimestampSerializer.class)
	@JsonDeserialize(using = TimestampDeserializer.class)
	public Timestamp getModifiedDate()
	{
		return modifiedDate;
	}

	@Column(name = "status")
	public Boolean getStatus()
	{
		return status;
	}

	public void setCreatedDate(Timestamp createdDate)
	{
		this.createdDate = createdDate;
	}

	@Transient
	public void createdDateByTimeZone()
	{
		if (this.getCountryTimeZone() != null)
			this.createdDateByTimeZone = EDate.DD_MMM_YYYY_HH_MM_SS_AM_PM.byTimeZone(this.getCountryTimeZone(), createdDate);

	}

	public void setModifiedDate(Timestamp modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	@Transient
	public void modifiedDateByTimeZone()
	{
		if (this.getCountryTimeZone() != null)
			this.modifiedDateByTimeZone = EDate.DD_MMM_YYYY_HH_MM_SS_AM_PM.byTimeZone(this.getCountryTimeZone(), modifiedDate);
	}

	public void setStatus(Boolean status)
	{
		this.status = status;
	}

	@Transient
	public String getCreatedDateByTimeZone()
	{
		return createdDateByTimeZone;
	}

	@Transient
	public String getModifiedDateByTimeZone()
	{
		return modifiedDateByTimeZone;
	}

	public void setCreatedDateByTimeZone(String createdDateByTimeZone)
	{
		this.createdDateByTimeZone = createdDateByTimeZone;
	}

	public void setModifiedDateByTimeZone(String modifiedDateByTimeZone)
	{
		this.modifiedDateByTimeZone = modifiedDateByTimeZone;
	}
}
