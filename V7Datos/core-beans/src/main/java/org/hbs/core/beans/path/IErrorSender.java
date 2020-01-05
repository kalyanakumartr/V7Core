package org.hbs.core.beans.path;

import java.io.Serializable;

public interface IErrorSender extends Serializable
{
	public String	CONFIGURATION_ALREADY_EXISTS		= "configuration.already.exists";
	public String	CONFIGURATION_BLOCKED_SUCCESSFULLY	= "configuration.blocked.successfully";
	public String	CONFIGURATION_CREATED_SUCCESSFULLY	= "configuration.created.successfully";
	public String	CONFIGURATION_DATA_UPDATED_RECENTLY	= "configuration.data.updated.recently";
	public String	CONFIGURATION_NOT_FOUND				= "configuration.not.found";
	public String	CONFIGURATION_UPDATED_SUCCESSFULLY	= "configuration.updated.successfully";

	public String	GROUP_ALREADY_EXISTS				= "group.already.exists";
	public String	GROUP_BLOCKED_SUCCESSFULLY			= "group.blocked.successfully";
	public String	GROUP_CREATED_SUCCESSFULLY			= "group.created.successfully";
	public String	GROUP_DATA_UPDATED_RECENTLY			= "group.data.updated.recently";
	public String	GROUP_NOT_FOUND						= "group.not.found";
	public String	GROUP_UPDATED_SUCCESSFULLY			= "group.updated.successfully";

	public String	GROUPMEMBER_ALREADY_EXISTS			= "groupmember.already.exists";
	public String	GROUPMEMBER_BLOCKED_SUCCESSFULLY	= "groupmember.blocked.successfully";
	public String	GROUPMEMBER_CREATED_SUCCESSFULLY	= "groupmember.created.successfully";
	public String	GROUPMEMBER_DATA_UPDATED_RECENTLY	= "groupmember.data.updated.recently";
	public String	GROUPMEMBER_NOT_FOUND				= "groupmember.not.found";
	public String	GROUPMEMBER_UPDATED_SUCCESSFULLY	= "groupmember.updated.successfully";

	public String	INVALID_REQUEST_PARAMETERS			= "invalid.request.parameters";

}
