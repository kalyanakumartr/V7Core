package org.hbs.security.resource;

import org.hbs.core.beans.path.IPathSender;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityAnonymousAccessConfig extends WebSecurityConfigurerAdapter implements IPathSender
{

	/**
	 *
	 */
	private static final long serialVersionUID = 2813257051745683712L;

	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().mvcMatchers(PASSWORD_RESET_MAIL, SEND_SMS_OTP, SMS_STATUS_CALLBACK);
	}
}