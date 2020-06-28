package org.hbs.core.beans.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import org.hbs.core.beans.model.IAddress.AddressType;
import org.hbs.core.security.resource.IPathBase.EMediaType;
import org.hbs.core.security.resource.IPathBase.ERole;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.Masker;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class CommonUsers extends CommonUsersBase
{
	private static final long	serialVersionUID	= 5331946490137030437L;

	protected String			producerId;
	protected String			producerName;
	protected String			parentProducerId;
	protected String			parentProducerName;

	public CommonUsers()
	{
		super();
	}

	@Transient
	public String getProducerId()
	{
		return producerId;
	}

	@Transient
	public String getProducerName()
	{
		return producerName;
	}

	@Transient
	public String getParentProducerId()
	{
		return parentProducerId;
	}

	@Transient
	public String getParentProducerName()
	{
		return parentProducerName;
	}

	public void setProducerId(String producerId)
	{
		this.producerId = producerId;
	}

	public void setProducerName(String producerName)
	{
		this.producerName = producerName;
	}

	public void setParentProducerId(String parentProducerId)
	{
		this.parentProducerId = parentProducerId;
	}

	public void setParentProducerName(String parentProducerName)
	{
		this.parentProducerName = parentProducerName;
	}

	@Transient
	@JsonIgnore
	public IUsersAddress getAddressToDisplay(AddressType addressType)
	{

		if (CommonValidator.isSetFirstNotEmpty(addressList))
		{
			for (IUsersAddress address : addressList)
			{
				if (CommonValidator.isEqual(address.getAddressType(), addressType))
					return address;
			}
		}
		return null;
	}

	@Transient
	@JsonIgnore
	public IUsersMedia getMediaToDisplay(EMediaType eMediaType)
	{

		if (CommonValidator.isSetFirstNotEmpty(mediaList))
		{
			for (IUsersMedia media : mediaList)
			{
				if (CommonValidator.isEqual(media.getMediaType(), eMediaType))
					return media;
			}
		}
		return null;
	}

	@Override
	@JsonIgnore
	public String getDomainUrl(HttpServletRequest request)
	{
		if (CommonValidator.isNotNullNotEmpty(parentProducer, parentProducer.getDomainContext()))
		{
			if (request.getServletContext().getContextPath().startsWith(SLASH) || CommonValidator.isNotEqual(parentProducer.getDomainContext(), request.getServletContext().getContextPath()))
				return parentProducer.getDomainContext();
		}
		return "";
	}

	@Transient
	@JsonIgnore
	public boolean hasMenuRole(String pathVariable)
	{
		if (CommonValidator.isSetFirstNotEmpty(userRoleses))
		{
			for (IUserRoles userRole : userRoleses)
			{
				if (CommonValidator.isEqual(userRole.getRoles().getRoleId(), ERole.SuperAdminRole.name()))
					return true;

				for (MenuRole menuRole : userRole.getRoles().getMenuRoles())
				{
					if (CommonValidator.isNotNullNotEmpty(menuRole.getMenu()) && menuRole.getMenu().getActionURL().startsWith(pathVariable))
						return true;
				}
			}
		}

		return false;
	}

	@Transient
	@JsonIgnore
	public boolean isAdmin()
	{
		if (CommonValidator.isSetFirstNotEmpty(userRoleses))
		{
			for (IUserRoles userRole : userRoleses)
			{
				if (userRole.getRoles().getIsAdminRole())
					return true;
			}
		}

		return false;
	}

	@Transient
	@JsonIgnore
	public boolean isEmployee()
	{
		if (CommonValidator.isSetFirstNotEmpty(userRoleses))
		{
			for (IUserRoles usrRole : userRoleses)
			{
				if (CommonValidator.isEqual(usrRole.getRoles().getRoleType(), ERole.Employee.name()))
					return true;
			}
		}
		return false;
	}

	@Transient
	@JsonIgnore
	public boolean isSuperAdmin()
	{
		if (CommonValidator.isSetFirstNotEmpty(userRoleses))
		{
			for (IUserRoles userRole : userRoleses)
			{
				if (CommonValidator.isEqual(userRole.getRoles().getRoleId(), ERole.SuperAdminRole.name()))
					return true;
			}
		}
		return false;
	}

	@Transient
	@JsonIgnore
	public boolean isValid()
	{
		if (CommonValidator.isNotNullNotEmpty(userStatus))
		{
			String encryptText = new StringBuilder(userStatus.name()).reverse().toString();
			encryptText = Masker.decryptBase64(encryptText);
			String auth[] = encryptText.split(HYPHEN);

			if (encryptText.indexOf(HYPHEN) > 0 && auth.length == 3 && CommonValidator.isEqual(auth[0], userName))
			{
				ICommunicationMediaBase media = getMediaToDisplay(EMediaType.Primary);
				return (CommonValidator.isEqual(auth[1], media.getEmailId()) && CommonValidator.isEqual(auth[2], media.getMobileNo().toString()));
			}
		}
		return false;
	}
}
