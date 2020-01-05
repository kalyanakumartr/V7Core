package org.hbs.core.beans.model;

import java.io.Serializable;

public interface ICreatedModifiedUsers extends Serializable
{
	public IUsers getCreatedUser();

	public IUsers getModifiedUser();

	public void setCreatedUser(IUsers createdUser);

	public void setModifiedUser(IUsers modifiedUser);

}