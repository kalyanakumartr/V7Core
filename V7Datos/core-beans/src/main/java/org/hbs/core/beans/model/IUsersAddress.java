package org.hbs.core.beans.model;

import org.hbs.core.util.ICRUDBean;

public interface IUsersAddress extends IAddress, ICRUDBean
{
	public IUsers getUsers();

	public void setUsers(IUsers users);

}
