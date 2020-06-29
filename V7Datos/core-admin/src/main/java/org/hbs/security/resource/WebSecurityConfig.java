package org.hbs.security.resource;

import org.hbs.core.beans.path.IPathAdmin;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements IPathAdmin
{
	private static final long serialVersionUID = -7281928899149367733L;

	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().mvcMatchers(SWAGGER_UI, FORGOT_PASSWORD, VALIDATE_USER_BASE + SLASH_STARS, CHANGE_PASSWORD_BASE + SLASH_STARS, UPDATE_PASSWORD, GENERATE_OTP, VALIDATE_OTP);
	}

}