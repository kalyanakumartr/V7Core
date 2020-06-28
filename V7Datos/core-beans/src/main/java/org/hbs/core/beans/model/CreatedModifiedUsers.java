package org.hbs.core.beans.model;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.IConstProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class CreatedModifiedUsers implements IConstProperty, ICreatedModifiedUsers
{
	private static final long	serialVersionUID	= -4784531727752023870L;

	protected IUsers			createdUser;

	protected IUsers			modifiedUser;

	protected String			createdUserId		= "";

	protected String			modifiedUserId		= "";

	protected String			createdUserName		= "";

	protected String			modifiedUserName	= "";

	protected String			countryId			= "";

	public CreatedModifiedUsers()
	{
		super();
	}

	public CreatedModifiedUsers(IUsers createdUser, IUsers modifiedUser)
	{
		super();
		if (createdUser != null)
			this.createdUser = createdUser;

		if (modifiedUser != null)
			this.modifiedUser = modifiedUser;
	}

	@Override
	@ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "createdBy", nullable = true)
	@JsonIgnore
	public IUsers getCreatedUser()
	{
		return createdUser;
	}

	@Transient
	public String getCreatedUserId()
	{
		if (CommonValidator.isNotNullNotEmpty(createdUser))
		{
			createdUserId = createdUser.getEmployeeId();
		}
		return createdUserId;
	}

	@Transient
	public String getCreatedUserName()
	{
		if (CommonValidator.isNotNullNotEmpty(createdUser))
			createdUserName = CommonValidator.isNullOrEmpty(createdUser.getLastName()) ? "" : createdUser.getLastName() + IConstProperty.COMMA_SPACE + createdUser.getUserName();
		return createdUserName;
	}

	@Override
	@ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "modifiedBy", nullable = true)
	@JsonIgnore
	public IUsers getModifiedUser()
	{
		return modifiedUser;
	}

	@Transient
	public String getModifiedUserId()
	{
		if (CommonValidator.isNotNullNotEmpty(modifiedUser))
			modifiedUserId = modifiedUser.getEmployeeId();
		return modifiedUserId;
	}

	@Transient
	public String getModifiedUserName()
	{
		if (CommonValidator.isNotNullNotEmpty(modifiedUser))
			modifiedUserName = CommonValidator.isNullOrEmpty(modifiedUser.getLastName()) ? "" : modifiedUser.getLastName() + IConstProperty.COMMA_SPACE + modifiedUser.getUserName();
		return modifiedUserName;
	}

	@Override
	public void setCreatedUser(IUsers createdUser)
	{
		this.createdUser = createdUser;
	}

	@Override
	public void setModifiedUser(IUsers modifiedUser)
	{
		this.modifiedUser = modifiedUser;
	}

	@Transient
	public String getCountryId()
	{
		if (this.createdUser != null && this.createdUser.getCountry() != null && this.modifiedUser == null)
		{
			countryId = this.createdUser.getCountry().getCountry();
		}
		// Purposely Checked '!= null' check form modifiedUser
		else if (this.modifiedUser != null && this.modifiedUser.getCountry() != null)
		{
			countryId = this.modifiedUser.getCountry().getCountry();
		}
		return countryId;
	}

}
