package org.hbs.core.security.resource;

import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.ServerUtilFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

public abstract class OAuth2ResourceServerConfigBase extends ResourceServerConfigurerAdapter implements IPath, OAuth2Constants
{
	private static final long			serialVersionUID	= -177116146310386350L;

	@Autowired
	private CustomAccessTokenConverter	customAccessTokenConverter;

	public void configure(HttpSecurity http, EnumResourceInterface... resourcePath) throws Exception
	{
		for (EnumResourceInterface ePath : resourcePath)
		{
			for (ERole eRole : ePath.getRoles())
			{
				if (CommonValidator.isNotNullNotEmpty(eRole.name()))
					http.authorizeRequests().antMatchers(ePath.getPath()).hasAuthority(eRole.name()).anyRequest().authenticated();
				else
					http.authorizeRequests().antMatchers(ePath.getPath()).authenticated();
			}
		}
		http.csrf().disable().authorizeRequests()//
				.and()//
				.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices()
	{
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

	@Bean
	public TokenStore tokenStore()
	{
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	protected JwtAccessTokenConverter accessTokenConverter()
	{
		// KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new
		// ClassPathResource("jwt.jks"), "mySecretKey".toCharArray());
		// JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		// converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));

		// -- for the simple demo purpose, used the secret for signing.
		// -- for production, it is recommended to use public/private key pair

		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setAccessTokenConverter(customAccessTokenConverter);
		converter.setSigningKey(HBS_APPLICATION);

		return converter;

	}
	
	//DoNOT remove this initialization
	@Bean
	public ServerUtilFactory getServerUtilFactory()
	{
		return ServerUtilFactory.getInstance();
	}
}
