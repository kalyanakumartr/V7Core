package org.hbs.core.beans;

import org.hbs.core.security.resource.IPathBase.EFormAction;
import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.util.CommonValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PasswordFormBean extends APIStatus
{
	private static final long	serialVersionUID	= 8292008040523676703L;

	public EFormAction			formAction			= EFormAction.ChangePassword;

	public String				media				= EMedia.Email.name();

	public String				newPassword;

	public String				oldPassword;

	public String				userId;

	public String				emailId;

	public String				mobileNo;

	public String				$OTPPrefix;

	public String				$OTP;

	public String				$passwordToken;

	@JsonIgnore
	public boolean validatePasswordToken()
	{
		return new BCryptPasswordEncoder().matches(emailId, $passwordToken) && CommonValidator.isNotNullNotEmpty(newPassword);
	}

	public void clearForm()
	{
		this.newPassword = null;
		this.oldPassword = null;
		this.userId = null;
		this.emailId = null;
		this.mobileNo = null;
		this.$OTPPrefix = null;
		this.$OTP = null;
		this.$passwordToken = null;
		this.messageCode = null;
	}

}
