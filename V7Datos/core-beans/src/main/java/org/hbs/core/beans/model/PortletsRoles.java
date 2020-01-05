package org.hbs.core.beans.model;

import java.io.Serializable;

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
@Table(name = "portletsroles")
public class PortletsRoles implements Serializable
{

	private static final long	serialVersionUID	= -3458382661801969783L;

	protected int				displayOrder		= 0;

	protected IPortlets			portlets;

	protected int				prAutoId;

	protected IRoles			roles;

	public PortletsRoles()
	{
		super();
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
	@Column(name = "prAutoId")
	public int getPrAutoId()
	{
		return prAutoId;
	}

	@ManyToOne(targetEntity = Roles.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", nullable = false)
	public IRoles getRoles()
	{
		return roles;
	}

	public void setDisplayOrder(int displayOrder)
	{
		this.displayOrder = displayOrder;
	}

	public void setPortlets(IPortlets portlets)
	{
		this.portlets = portlets;
	}

	public void setPrAutoId(int prAutoId)
	{
		this.prAutoId = prAutoId;
	}

	public void setRoles(IRoles roles)
	{
		this.roles = roles;
	}
}
