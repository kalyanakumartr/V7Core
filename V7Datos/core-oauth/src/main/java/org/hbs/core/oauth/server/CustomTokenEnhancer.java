package org.hbs.core.oauth.server;

import java.util.HashMap;
import java.util.Map;

import org.hbs.core.security.resource.IPath;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer, IPath
{

	private static final long serialVersionUID = 2333742099695592008L;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication)
	{
		OAuth2UserDetails user = (OAuth2UserDetails) authentication.getPrincipal();
		final Map<String, Object> additionalInfo = new HashMap<>();

		additionalInfo.put(USER_FULL_NAME, user.getFullName());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

		return accessToken;
	}

}
