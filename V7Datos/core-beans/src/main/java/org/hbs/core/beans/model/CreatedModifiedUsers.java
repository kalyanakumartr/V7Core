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
		return createdUser.getEmployeeId();
	}
	@Transient
	public String getCreatedUserName()
	{
		return CommonValidator.isNullOrEmpty(createdUser.getLastName()) ? "" : createdUser.getLastName() + IConstProperty.COMMA_SPACE + createdUser.getUserName();
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
		return modifiedUser.getEmployeeId();
	}
	@Transient
	public String getModifiedUserName()
	{
		return CommonValidator.isNullOrEmpty(modifiedUser.getLastName()) ? "" : modifiedUser.getLastName() + IConstProperty.COMMA_SPACE + modifiedUser.getUserName();
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

}
