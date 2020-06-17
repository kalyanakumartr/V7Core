package org.hbs.core.beans.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.beans.model.IUsersBase.EResource;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usersattachments")
public class UsersAttachments extends CommonFileUpload implements IUsersAttachments
{
	private static final long	serialVersionUID	= 917678364001988324L;

	protected IUsers			users;

	public UsersAttachments()
	{
		super();
	}

	public UsersAttachments(int autoId, MultipartFile multiPartFile, String uploadSubFolderPath, String uploadDocumentForType)
	{
		super();
		if (autoId > 0)
			this.autoId = autoId;
		this.uploadResourceHandler = EResource.Profile.name();
		this.uploadMultiPartFile = multiPartFile;
		this.uploadSubFolderPath = uploadSubFolderPath;
		this.uploadDocumentForType = uploadDocumentForType;

	}

	@ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	public IUsers getUsers()
	{
		return users;
	}

	public void setUsers(IUsers users)
	{
		this.users = users;
	}

	@Override
	@Transient
	@JsonIgnore
	public String getCountryTimeZone()
	{
		if (this.users.getByUser().createdUser != null && this.users.getByUser().createdUser.getCountry() != null && this.users.getByUser().modifiedUser == null)
		{
			return this.users.getByUser().createdUser.getCountry().getCountry();
		}
		else if (this.users.getByUser().modifiedUser.getCountry() != null)
		{
			return this.users.getByUser().modifiedUser.getCountry().getCountry();
		}
		return null;
	}
}
