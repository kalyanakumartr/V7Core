package org.hbs.core.beans.model;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hbs.core.util.IConstProperty;

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
	public IUsers getCreatedUser()
	{
		return createdUser;
	}

	@Override
	@ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "modifiedBy", nullable = true)
	public IUsers getModifiedUser()
	{
		return modifiedUser;
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
