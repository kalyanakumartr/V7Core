package org.hbs.core.beans.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hbs.core.beans.model.IAddress.AddressType;
import org.hbs.core.security.resource.IPathBase.EMediaType;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@MappedSuperclass
public abstract class CommonUsersBase extends UsersBase implements IUsers
{
	private static final long			serialVersionUID	= -7527216318945401365L;

	protected Set<UsersMedia>			mediaList			= new LinkedHashSet<UsersMedia>(0);

	protected Set<IUsersAddress>		addressList			= new LinkedHashSet<IUsersAddress>(0);

	protected Set<IUsersAttachments>	attachmentList		= new LinkedHashSet<IUsersAttachments>(0);

	protected Set<IUserRoles>			userRoleses			= new LinkedHashSet<IUserRoles>(0);

	protected Set<IUserPortlets>		userPorlets			= new LinkedHashSet<IUserPortlets>(0);

	protected IUsersAddress				communicationAddress;

	protected IUsersMedia				primaryMedia;

	public CommonUsersBase()
	{
		super();
	}

	@OneToMany(targetEntity = UsersAddress.class, fetch = FetchType.LAZY, mappedBy = "users", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@Where(clause = "addressType = 'CommunicationAddress'")
	@JsonIgnore
	public Set<IUsersAddress> getAddressList()
	{
		return addressList;
	}

	@Transient
	public IUsersAttachments getAttachment(EnumInterface documentType)
	{
		if (CommonValidator.isSetFirstNotEmpty(attachmentList))
		{
			for (IUsersAttachments attachments : attachmentList)
			{
				if (CommonValidator.isEqual(attachments.getUploadDocumentForType(), documentType))
					return attachments;
			}
		}
		return null;
	}

	@OneToMany(targetEntity = UsersAttachments.class, fetch = FetchType.LAZY, mappedBy = "users", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@Where(clause = "uploadDocumentForType = 'ProfileImage'")
	@JsonIgnore
	public Set<IUsersAttachments> getAttachmentList()
	{
		return attachmentList;
	}

	@Transient
	@JsonIgnore
	public IUsersMedia getPrimaryMedia()
	{
		if (primaryMedia == null && CommonValidator.isSetFirstNotEmpty(getMediaList()))
		{
			for (IUsersMedia media : getMediaList())
			{
				if (CommonValidator.isEqual(media.getMediaType(), EMediaType.Primary))
					primaryMedia = media;
			}
		}
		return primaryMedia;
	}

	@Transient
	@JsonIgnore
	public IUsersAddress getCommunicationAddress()
	{
		if (communicationAddress == null && CommonValidator.isSetFirstNotEmpty(getAddressList()))
		{
			for (IUsersAddress address : getAddressList())
			{
				if (CommonValidator.isEqual(address.getAddressType(), AddressType.CommunicationAddress))
					communicationAddress = address;
			}
		}
		return communicationAddress;
	}

	@OneToMany(targetEntity = UsersMedia.class, fetch = FetchType.LAZY, mappedBy = "users", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	// @JsonIgnore
	@JsonDeserialize(as = LinkedHashSet.class)
	@JsonManagedReference
	public Set<UsersMedia> getMediaList()
	{
		return mediaList;
	}

	@OneToMany(targetEntity = UserPortlets.class, fetch = FetchType.LAZY, mappedBy = "users", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@OrderBy("displayOrder ASC")
	@JsonIgnore
	public Set<IUserPortlets> getUserPorlets()
	{
		return userPorlets;
	}

	@OneToMany(targetEntity = UserRoles.class, fetch = FetchType.LAZY, mappedBy = "users", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JsonIgnore
	public Set<IUserRoles> getUserRoleses()
	{
		return userRoleses;
	}

	public void setAddressList(Set<IUsersAddress> addressList)
	{
		this.addressList = addressList;
	}

	public void setAttachmentList(Set<IUsersAttachments> attachmentList)
	{
		this.attachmentList = attachmentList;
	}

	public void setMediaList(Set<UsersMedia> mediaList)
	{
		this.mediaList = mediaList;
	}

	public void setUserPorlets(Set<IUserPortlets> userPorlets)
	{
		this.userPorlets = userPorlets;
	}

	public void setUserRoleses(Set<IUserRoles> userRoleses)
	{
		this.userRoleses = userRoleses;
	}
}