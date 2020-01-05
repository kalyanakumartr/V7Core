package org.hbs.core.beans.model;

import java.sql.Timestamp;

import org.hbs.core.util.ICRUDBean;

/**
 * UserLog entity. @author MyEclipse Persistence Tools
 */

public interface IUserLog extends ICRUDBean
{
	long getAutoId();

	String getIpAddress();

	Timestamp getUserLoginTime();

	Timestamp getUserLogoutTime();

	IUsers getUsers();

	boolean isFetchBlock();

	void setAutoId(long autoId);

	void setFetchBlock(boolean fetchBlock);

	void setIpAddress(String ulIpaddress);

	void setUserLoginTime(Timestamp userLoginTime);

	void setUserLogoutTime(Timestamp userLogoutTime);

	void setUsers(IUsers users);
}