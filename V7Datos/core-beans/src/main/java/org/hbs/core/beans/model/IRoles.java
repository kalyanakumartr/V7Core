package org.hbs.core.beans.model;

import java.util.Set;

import org.hbs.core.util.ICRUDBean;

public interface IRoles extends ICommonDateAndStatusFields, IUsersByUser, ICRUDBean
{
	public String getEnumKey();

	public Boolean getIsAdminRole();

	public Set<MenuRole> getMenuRoles();

	public String getDescription();

	public String getRoleId();

	public String getRoleLongName();

	public String getRoleName();

	public String getRoleShortName();

	public String getRoleType();

	public void setEnumKey(String enumKey);

	public void setIsAdminRole(Boolean isAdminRole);

	public void setMenuRoles(Set<MenuRole> menuRoles);

	public void setDescription(String roleDescription);

	public void setRoleId(String roleId);

	public void setRoleLongName(String roleLongName);

	public void setRoleName(String roleName);

	public void setRoleShortName(String roleShortName);

	public void setRoleType(String roleType);

	public Set<PortletsRoles> getPortletRoles();

	public void setPortletRoles(Set<PortletsRoles> portletRoles);

}