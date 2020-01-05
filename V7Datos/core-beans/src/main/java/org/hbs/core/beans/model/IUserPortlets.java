package org.hbs.core.beans.model;

import org.hbs.core.util.ICRUDBean;

/**
 * UserRoles entity. @author MyEclipse Persistence Tools
 */

public interface IUserPortlets extends ICRUDBean
{
	public IPortlets getPortlets();

	public int getPtAutoId();

	public boolean isStatus();

	public void setPortlets(IPortlets portlets);

	public void setPtAutoId(int ptAutoId);

	public void setStatus(boolean status);

	public IUsers getUsers();

	public void setUsers(IUsers users);

	public void setDisplayOrder(int displayOrder);

	public int getDisplayOrder();
}