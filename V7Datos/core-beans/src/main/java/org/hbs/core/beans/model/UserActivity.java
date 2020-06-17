package org.hbs.core.beans.model;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// @Entity
// @Table(name = "useractivity")
public class UserActivity extends CommonBeanFields implements IUserActivity
{
	public enum Activity implements EnumInterface
	{
		Password, PasswordByAdmin, Save, Update
	}

	private static final long	serialVersionUID	= -8681657293532683320L;

	private String				action;

	private String				afterMsg;

	private int					autoId;

	private String				beforeMsg;

	private String				className;

	private String				groupName;

	public UserActivity()
	{
		super();
	}

	public UserActivity(String action, String afterMsg, int autoId, String beforeMsg, String className, String groupName)
	{
		super();
		this.action = action;
		this.afterMsg = afterMsg;
		this.autoId = autoId;
		this.beforeMsg = beforeMsg;
		this.className = className;
		this.groupName = groupName;
	}

	public UserActivity(EnumInterface eAction, String description, String className, Object beforeMsg, Object afterMsg, String groupName) // Update
																																			// or
																																			// SoftDelete
	{
		super();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		this.action = eAction.name() + SPACE + description;
		this.className = className;
		if (CommonValidator.isNotNullNotEmpty(beforeMsg))
			this.beforeMsg = new StringBuilder(gson.toJson(beforeMsg)).toString();
		if (CommonValidator.isNotNullNotEmpty(afterMsg))
			this.afterMsg = new StringBuilder(gson.toJson(afterMsg)).toString();
		this.groupName = groupName;
	}

	public UserActivity(IUsers user, EnumInterface eAction, String description, String groupName)// search
	{
		super();

		this.action = eAction.name() + SPACE + description;
		this.groupName = groupName;
		this.createdUser = user;
		this.createdDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	public UserActivity(IUsers user, EnumInterface eAction, String description, Class<?> clazz)// add
	{
		super();
		this.action = eAction.name() + SPACE + description;
		this.className = clazz.getSimpleName();
		this.createdUser = user;
		this.createdDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	public UserActivity(IUsers user, EnumInterface eAction, Class<?> clazz)// add
	{
		super();
		this.action = eAction.name();
		this.className = clazz.getSimpleName();
		this.createdUser = user;
		this.createdDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	public String findDifference() throws JsonProcessingException, IOException
	{
		// ObjectMapper jackson = new ObjectMapper();
		// JsonNode beforeNode = jackson.readTree(beforeMsg);
		// JsonNode afterNode = jackson.readTree(afterMsg);
		// JsonNode patchNode = null; // JsonDiff.asJson(beforeNode, afterNode);
		return null; // patchNode.toString();
	}

	@Column(name = "action")
	public String getAction()
	{
		return action;
	}

	@Column(name = "afterMsg")
	public String getAfterMsg()
	{
		return afterMsg;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "professionalId")
	public int getAutoId()
	{
		return autoId;
	}

	@Column(name = "beforeMsg")
	public String getBeforeMsg()
	{
		return beforeMsg;
	}

	@Column(name = "className")
	public String getClassName()
	{
		return className;
	}

	@Column(name = "groupName")
	public String getGroupName()
	{
		return groupName;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	public void setAfterMsg(String afterMsg)
	{
		this.afterMsg = afterMsg;
	}

	public void setAutoId(int autoId)
	{
		this.autoId = autoId;
	}

	public void setBeforeMsg(String beforeMsg)
	{
		this.beforeMsg = beforeMsg;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}
}
