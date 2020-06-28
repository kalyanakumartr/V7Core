package org.hbs.security.resource;

import org.hbs.core.beans.path.IPathAdmin;
import org.hbs.core.security.resource.OAuth2ResourceServerConfigBase;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends OAuth2ResourceServerConfigBase implements IPathAdmin
{
	private static final long serialVersionUID = -177116146310386350L;

	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		configure(http, EPathAdmin.values());
		http.formLogin().loginPage(LOGIN).permitAll()//
				.and().logout().permitAll();
	}
}
