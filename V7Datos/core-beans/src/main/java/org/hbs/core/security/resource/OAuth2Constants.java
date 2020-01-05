package org.hbs.core.security.resource;

public interface OAuth2Constants
{
	String	ACCESS_CONTROL_ALLOW_HEADERS	= "Access-Control-Allow-Headers";
	String	ACCESS_CONTROL_ALLOW_METHODS	= "Access-Control-Allow-Methods";
	String	ACCESS_CONTROL_ALLOW_ORIGIN		= "Access-Control-Allow-Origin";
	String	ACCESS_CONTROL_MAX_AGE			= "Access-Control-Max-Age";
	String	AUTHENTICATION_MANAGER_BEAN		= "authenticationManagerBean";
	String	AUTHORIZATION					= "Authorization";
	String	BEARER							= "Bearer ";
	String	HEADERS							= "Authorization, Content-Type,headers,lazyupdate,normalizednames";
	String	OAUTH_TOKEN						= "/oauth/token";
	String	OAUTH_TOKEN_REVOKE				= "/oauth/token_revoke";
	String	ORIGINS							= "*";
	String	PASSWORD						= "password";
	String	READ							= "read";
	String	REALM							= "SOM_REALM";
	String	REFRESH_TOKEN					= "refresh_token";
	String	REQUEST_TYPES					= "POST, PUT, GET, OPTIONS, DELETE";
	String	SOM_APPLICATION					= "SOMApplication";
	String	SOM_SECRET						= "SOMSecret";
	String	TRUST							= "trust";
	String	WRITE							= "write";
	int		THIRTY_DAYS						= 60 * 60 * 24 * 30;
	int		SIXTY_MINUTES					= 60 * 60;
	int		MAX_LOGIN_ATTEMPT				= 3;
}
