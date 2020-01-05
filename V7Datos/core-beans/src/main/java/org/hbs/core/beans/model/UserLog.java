package org.hbs.core.beans.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hbs.core.util.CommonValidator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "userlog")
public class UserLog implements IUserLog
{
	private static final long	serialVersionUID	= -5336666543176665572L;

	private long				autoId;

	private boolean				fetchBlock			= false;

	private String				ipAddress;

	private Timestamp			userLoginTime;

	private Timestamp			userLogoutTime;

	private IUsers				users;

	public UserLog()
	{
		super();
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "autoId")
	public long getAutoId()
	{
		return this.autoId;
	}

	@Override
	@Column(name = "ipAddress")
	public String getIpAddress()
	{
		return this.ipAddress;
	}

	@Override
	@Column(name = "userLoginTime")
	public Timestamp getUserLoginTime()
	{
		return this.userLoginTime;
	}

	@Override
	@Column(name = "userLogoutTime")
	public Timestamp getUserLogoutTime()
	{
		return this.userLogoutTime;
	}

	@Override
	@ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId", nullable = false)
	@JsonDeserialize(as = Users.class)
	public IUsers getUsers()
	{
		return this.users;
	}

	@Override
	@Column(name = "fetchBlock")
	public boolean isFetchBlock()
	{
		return fetchBlock;
	}

	@Override
	public void setAutoId(long autoId)
	{
		this.autoId = autoId;
	}

	@Override
	public void setFetchBlock(boolean fetchBlock)
	{
		this.fetchBlock = fetchBlock;
	}

	@Override
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	@Override
	public void setUserLoginTime(Timestamp userLoginTime)
	{
		this.userLoginTime = userLoginTime;
	}

	@Override
	public void setUserLogoutTime(Timestamp ulUserLogoutTime)
	{
		this.userLogoutTime = ulUserLogoutTime;
		if (CommonValidator.isNotNullNotEmpty(ulUserLogoutTime))
			this.fetchBlock = true;
	}

	@Override
	public void setUsers(IUsers users)
	{
		this.users = users;
	}

}
