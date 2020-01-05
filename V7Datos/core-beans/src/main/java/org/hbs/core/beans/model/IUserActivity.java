package org.hbs.core.beans.model;

import java.io.IOException;

import org.hbs.core.util.ICRUDBean;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IUserActivity extends ICommonBeanFields, ICRUDBean
{

	public String findDifference() throws JsonProcessingException, IOException;

	public String getAction();

	public String getAfterMsg();

	public int getAutoId();

	public String getBeforeMsg();

	public String getClassName();

	public String getGroupName();

	public void setAction(String action);

	public void setAfterMsg(String afterMsg);

	public void setAutoId(int autoId);

	public void setBeforeMsg(String beforeMsg);

	public void setClassName(String className);

	public void setGroupName(String groupName);

}