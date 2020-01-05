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

/**
 * @author ananth
 */
@Entity
@Table(name = "userroles")
public class UserRoles implements IUserRoles
{

	private static final long	serialVersionUID	= 8909239704346625769L;

	protected IRoles			roles;

	protected long				autoId;

	protected IUsers			users;

	public UserRoles()
	{
		super();
	}

	@ManyToOne(targetEntity = Roles.class)
	@JoinColumn(name = "roleId", nullable = false)
	public IRoles getRoles()
	{
		return roles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "autoId")
	public long getAutoId()
	{
		return autoId;
	}

	@ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId", nullable = false)
	public IUsers getUsers()
	{
		return users;
	}

	public void setRoles(IRoles roles)
	{
		this.roles = roles;
	}

	public void setAutoId(long autoId)
	{
		this.autoId = autoId;
	}

	public void setUsers(IUsers users)
	{
		this.users = users;
	}

}
