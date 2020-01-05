package org.hbs.core.beans;

import org.hbs.core.beans.model.Users;

public class OTPFormBean extends APIStatus
{
	private static final long	serialVersionUID	= 2473153333582197660L;

	public String				id;

	public String				otp;

	public Users				user;

	public String				tokenUrl;
}
