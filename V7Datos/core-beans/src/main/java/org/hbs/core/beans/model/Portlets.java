package org.hbs.core.beans.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.util.EBusinessKey;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "portlets")
public class Portlets extends CommonDateAndStatusFields implements IPortlets, EBusinessKey
{

	private static final long		serialVersionUID	= 515193003553697834L;

	private String					portletBeanName;

	private String					portletId;

	private String					portletName;

	private String					portletTemplatePath;

	private CreatedModifiedUsers	byUser				= new CreatedModifiedUsers();

	public Portlets()
	{
		super();
		this.portletId = getBusinessKey();
	}

	public Portlets(String rlRoleId)
	{
		super();
		this.portletId = getBusinessKey();
	}

	@Override
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	@Embedded
	public CreatedModifiedUsers getByUser()
	{
		return byUser;
	}

	public void setByUser(CreatedModifiedUsers byUser)
	{
		this.byUser = byUser;
	}

	@Column(name = "portletBeanName")
	public String getPortletBeanName()
	{
		return portletBeanName;
	}

	@Id
	@Column(name = "portletId")
	public String getPortletId()
	{
		return portletId;
	}

	@Column(name = "portletName")
	public String getPortletName()
	{
		return portletName;
	}

	@Column(name = "portletTemplatePath")
	public String getPortletTemplatePath()
	{
		return portletTemplatePath;
	}

	public void setPortletBeanName(String portletBeanName)
	{
		this.portletBeanName = portletBeanName;
	}

	public void setPortletId(String portletId)
	{
		this.portletId = portletId;
	}

	public void setPortletName(String portletName)
	{
		this.portletName = portletName;
	}

	public void setPortletTemplatePath(String portletTemplatePath)
	{
		this.portletTemplatePath = portletTemplatePath;
	}

	@Override
	@Transient
	@JsonIgnore
	public String getCountryTimeZone()
	{
		if (this.byUser.createdUser != null && this.byUser.createdUser.getCountry() != null && this.byUser.modifiedUser == null)
		{
			return this.byUser.createdUser.getCountry().getCountry();
		}
		else if (this.byUser.modifiedUser.getCountry() != null)
		{
			return this.byUser.modifiedUser.getCountry().getCountry();
		}
		return null;
	}
}