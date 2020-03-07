package org.hbs.core.beans.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "usersmedia")
// @JsonTypeName("UsersMedia")
public class UsersMedia extends CommunicationMedia implements IUsersMedia
{
	private static final long	serialVersionUID	= -6038542520518993638L;

	protected IUsers			users;

	public UsersMedia()
	{
		super();
		this.mediaId = getBusinessKey();
	}

	public UsersMedia(IUsers users)
	{
		super();
		this.mediaId = getBusinessKey();
		this.users = users;
	}

	@Transient
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	@ManyToOne(targetEntity = Users.class)
	@JoinColumn(name = "employeeId", nullable = false)
	@JsonDeserialize(as = Users.class)
	@JsonBackReference
	public IUsers getUsers()
	{
		return users;
	}

	public void setUsers(IUsers users)
	{
		this.users = users;
	}

}
