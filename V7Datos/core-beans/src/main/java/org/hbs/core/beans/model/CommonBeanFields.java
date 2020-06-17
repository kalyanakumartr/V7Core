package org.hbs.core.beans.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hbs.core.util.ICRUDBean;
import org.hbs.core.util.IConstProperty;

@MappedSuperclass
public abstract class CommonBeanFields extends ProducersBase implements ICommonBeanFields, IConstProperty, ICRUDBean
{
	private static final long	serialVersionUID	= -4784531727752023870L;

	protected IUsers			createdUser;

	protected IUsers			modifiedUser;

	public CommonBeanFields()
	{
		super();
	}

	@ManyToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "createdBy", nullable = true)
	public IUsers getCreatedUser()
	{
		return createdUser;
	}

	@ManyToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "modifiedBy", nullable = true)
	public IUsers getModifiedUser()
	{
		return modifiedUser;
	}

	public void setCreatedUser(IUsers createdUser)
	{
		this.createdUser = createdUser;
	}

	public void setModifiedUser(IUsers modifiedUser)
	{
		this.modifiedUser = modifiedUser;
	}

}