package org.hbs.core.beans.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.util.EBusinessKey;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
public class Roles extends ProducersBase implements IRoles, EBusinessKey
{

	private static final long		serialVersionUID	= 515193003553697834L;

	private CreatedModifiedUsers	byUser				= new CreatedModifiedUsers();

	private String					description;

	private String					enumKey;

	private Boolean					isAdminRole;

	private Set<MenuRole>			menuRoles			= new LinkedHashSet<MenuRole>(0);

	private Set<PortletsRoles>		portletRoles		= new LinkedHashSet<PortletsRoles>(0);

	private String					roleId;

	private String					roleLongName;

	private String					roleName;

	private String					roleShortName;

	private String					roleType;

	public Roles()
	{
		super();
		this.roleId = getBusinessKey();
	}

	public Roles(String roleId)
	{
		super();
		this.roleId = getBusinessKey();
	}

	@Override
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	@Embedded
	@JsonIgnore
	public CreatedModifiedUsers getByUser()
	{
		return byUser;
	}

	@Override
	@Transient
	@JsonIgnore
	public String getCountryTimeZone()
	{
		if (this.byUser.getCreatedUser() != null && this.byUser.getCreatedUser().getCountry().getCountry() != null && this.byUser.getModifiedUser() == null)
		{
			return this.byUser.getCreatedUser().getCountry().getCountry();
		}
		else if (this.byUser.getModifiedUser().getCountry().getCountry() != null)
		{
			return this.byUser.getModifiedUser().getCountry().getCountry();
		}
		return null;
	}

	@Column(name = "enumKey")
	public String getEnumKey()
	{
		return enumKey;
	}

	@Column(name = "isAdminRole")
	public Boolean getIsAdminRole()
	{
		return isAdminRole;
	}

	@OneToMany(targetEntity = MenuRole.class, fetch = FetchType.LAZY, mappedBy = "roles")
	@Fetch(FetchMode.JOIN)
	public Set<MenuRole> getMenuRoles()
	{
		return menuRoles;
	}

	@OneToMany(targetEntity = PortletsRoles.class, fetch = FetchType.LAZY, mappedBy = "roles")
	@Fetch(FetchMode.JOIN)
	@Where(clause = "displayOrder > 0")
	@OrderBy("displayOrder ASC")
	public Set<PortletsRoles> getPortletRoles()
	{
		return portletRoles;
	}

	@Column(name = "description")
	public String getDescription()
	{
		return description;
	}

	@Id
	@Column(name = "roleId")
	public String getRoleId()
	{
		return roleId;
	}

	@Column(name = "roleLongName")
	public String getRoleLongName()
	{
		return roleLongName;
	}

	@Column(name = "roleName")
	public String getRoleName()
	{
		return roleName;
	}

	@Column(name = "roleShortName")
	public String getRoleShortName()
	{
		return roleShortName;
	}

	@Column(name = "roleType")
	public String getRoleType()
	{
		return roleType;
	}

	public void setByUser(CreatedModifiedUsers byUser)
	{
		this.byUser = byUser;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setEnumKey(String enumKey)
	{
		this.enumKey = enumKey;
	}

	public void setIsAdminRole(Boolean isAdminRole)
	{
		this.isAdminRole = isAdminRole;
	}

	public void setMenuRoles(Set<MenuRole> menuRoles)
	{
		this.menuRoles = menuRoles;
	}

	public void setPortletRoles(Set<PortletsRoles> portletRoles)
	{
		this.portletRoles = portletRoles;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public void setRoleLongName(String roleLongName)
	{
		this.roleLongName = roleLongName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public void setRoleShortName(String roleShortName)
	{
		this.roleShortName = roleShortName;
	}

	public void setRoleType(String roleType)
	{
		this.roleType = roleType;
	}

}