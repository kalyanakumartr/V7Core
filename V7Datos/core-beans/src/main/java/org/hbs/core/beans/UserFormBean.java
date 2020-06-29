package org.hbs.core.beans;

import org.hbs.core.beans.model.Users;
import org.hbs.core.security.resource.IPathBase.EFormAction;
import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.util.CommonValidator;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UserFormBean extends APIStatus
{
	private static final long	serialVersionUID	= 1452795795821759203L;

	public String				country;
	public String				emailId;
	public String				media;
	public String				roleName;
	public String				searchParam;
	public String				state;
	public String				tokenURL;
	public Users				formUser;
	public Users				user;
	public EFormAction			formAction;

	public UserFormBean()
	{
		super();
	}

	public UserFormBean(String userId)
	{
		super();
		this.formUser = new Users(userId);

	}

	public UserFormBean(PasswordFormBean pfBean)
	{
		super();
		this.searchParam = CommonValidator.isEqual(pfBean.media, EMedia.Email) ? pfBean.emailId : pfBean.mobileNo;
		this.formAction = pfBean.formAction;
		this.media = pfBean.media;
	}

	public void setRepoUser(Users repoUser)
	{
		this.user = repoUser;
	}

	public void updateRepoUser(Authentication auth)
	{
		user.setCountry(formUser.getCountry());
		user.setDateOfJoin(formUser.getDateOfJoin());
		user.setDob(formUser.getDob());
		user.setFatherName(formUser.getFatherName());
		user.setLastName(formUser.getLastName());
		user.setProducer(formUser.getProducer());
		user.setSex(formUser.getSex());
		user.setUserName(formUser.getUserName());
	}

	@Override
	public void clearForm()
	{
		this.country = null;
		this.emailId = null;
		this.formUser = null;
		this.messageCode = null;
		this.state = null;
		this.roleName = null;
		this.searchParam = null;
		this.tokenURL = null;
		this.user = null;

	}

}
