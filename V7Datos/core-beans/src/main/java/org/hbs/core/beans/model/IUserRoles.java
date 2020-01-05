package org.hbs.core.beans.model;

import org.hbs.core.util.ICRUDBean;

/**
 * UserRoles entity. @author MyEclipse Persistence Tools
 */

public interface IUserRoles extends ICRUDBean
{
	public IRoles getRoles();

	public long getAutoId();

	public IUsers getUsers();

	public void setRoles(IRoles roles);

	public void setAutoId(long urAutoId);

	public void setUsers(IUsers users);
}