package org.hbs.core.beans.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "portletsusers")
public class UserPortlets implements IUserPortlets
{

	private static final long	serialVersionUID	= 101745777600744144L;

	protected int				displayOrder;

	protected IPortlets			portlets;

	protected int				ptAutoId;

	protected boolean			status				= true;

	protected IUsers			users;

	public UserPortlets()
	{
		super();
	}

	public UserPortlets(int displayOrder, boolean status, IUsers users, IPortlets portlets)
	{
		super();
		this.displayOrder = displayOrder;
		this.portlets = portlets;
		this.status = status;
		this.users = users;
	}

	public UserPortlets(int displayOrder, IPortlets portlets, int ptAutoId, Boolean status, IUsers users)
	{
		super();
		this.displayOrder = displayOrder;
		this.portlets = portlets;
		this.ptAutoId = ptAutoId;
		this.status = status;
		this.users = users;
	}

	@Column(name = "displayOrder")
	public int getDisplayOrder()
	{
		return displayOrder;
	}

	@ManyToOne(targetEntity = Portlets.class)
	@JoinColumn(name = "portletId", nullable = false)
	public IPortlets getPortlets()
	{
		return portlets;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ptAutoId")
	public int getPtAutoId()
	{
		return ptAutoId;
	}

	@Column(name = "status")
	public boolean isStatus()
	{
		return status;
	}

	@ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId", nullable = false)
	public IUsers getUsers()
	{
		return users;
	}

	public void setDisplayOrder(int displayOrder)
	{
		this.displayOrder = displayOrder;
	}

	public void setPortlets(IPortlets portlets)
	{
		this.portlets = portlets;
	}

	public void setPtAutoId(int ptAutoId)
	{
		this.ptAutoId = ptAutoId;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

	public void setUsers(IUsers users)
	{
		this.users = users;
	}
}
