package org.hbs.security.resource;

import org.hbs.core.beans.path.IPathAdmin;
import org.hbs.core.util.ServerUtilFactory;
import org.springframework.context.annotation.Bean;
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
		web.ignoring().mvcMatchers(FORGOT_PASSWORD, RESET_PASSWORD, VALIDATE_USER + SLASH_STARS, GENERATE_OTP, VALIDATE_OTP, CHANGE_PASSWORD);
	}

	@Bean
	public ServerUtilFactory getServerUtilFactory()
	{
		return ServerUtilFactory.getInstance();
	}

}