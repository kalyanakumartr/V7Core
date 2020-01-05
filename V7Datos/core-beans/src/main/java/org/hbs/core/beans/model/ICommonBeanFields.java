package org.hbs.core.beans.model;

public interface ICommonBeanFields extends ICommonDateAndStatusFields
{

	public IUsers getCreatedUser();

	public IUsers getModifiedUser();

	public void setCreatedUser(IUsers createdUser);

	public void setModifiedUser(IUsers modifiedUser);

}
