package org.hbs.core.beans.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hbs.core.util.IConstProperty;

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
	public Timestamp getCreatedDate()
	{
		return createdDate;
	}

	@Column(name = "modifiedDate")
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

	public void setCreatedDateByTimeZone(String createdDateByTimeZone)
	{
		this.createdDateByTimeZone = createdDateByTimeZone;
	}

	public void setModifiedDate(Timestamp modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	public void setModifiedDateByTimeZone(String modifiedDateByTimeZone)
	{
		this.modifiedDateByTimeZone = modifiedDateByTimeZone;
	}

	public void setStatus(Boolean status)
	{
		this.status = status;
	}
}
