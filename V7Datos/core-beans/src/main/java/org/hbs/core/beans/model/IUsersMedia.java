package org.hbs.core.beans.model;

// @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property =
// "UsersMedia")
// @JsonSubTypes({ @JsonSubTypes.Type(value = UsersMedia.class, name = "UsersMedia"), })
public interface IUsersMedia extends ICommunicationMedia
{
	public IUsers getUsers();

	public void setUsers(IUsers users);
}
