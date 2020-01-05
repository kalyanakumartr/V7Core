package org.hbs.core.beans.model;

import org.hbs.core.util.ICRUDBean;

public interface IUsersAttachments extends ICommonDateAndStatusFields, ICommonFileUpload, ICRUDBean
{
	public IUsers getUsers();

	public void setUsers(IUsers users);

}
