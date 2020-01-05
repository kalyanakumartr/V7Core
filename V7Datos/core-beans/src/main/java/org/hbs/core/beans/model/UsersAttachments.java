package org.hbs.core.beans.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hbs.core.beans.model.IUsersBase.EResource;
import org.springframework.web.multipart.MultipartFile;

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
}
