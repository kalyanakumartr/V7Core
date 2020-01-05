package org.hbs.core.oauth.server;

public interface OAuth2Constants
{
	String	ACCESS_CONTROL_ALLOW_HEADERS	= "Access-Control-Allow-Headers";
	String	ACCESS_CONTROL_ALLOW_METHODS	= "Access-Control-Allow-Methods";
	String	ACCESS_CONTROL_ALLOW_ORIGIN		= "Access-Control-Allow-Origin";
	String	ACCESS_CONTROL_MAX_AGE			= "Access-Control-Max-Age";
	String	AUTHENTICATION_MANAGER_BEAN		= "authenticationManagerBean";
	String	AUTHORIZATION					= "Authorization";
	String	BEARER							= "Bearer ";
	String	HEADERS							= "Authorization, Content-Type";
	String	OAUTH_TOKEN						= "/oauth/token";
	String	OAUTH_TOKEN_REVOKE				= "/oauth/token_revoke";
	String	ORIGINS							= "*";
	String	PASSWORD						= "password";
	String	READ							= "read";
	String	REALM							= "KALAM_REALM";
	String	REFRESH_TOKEN					= "refresh_token";
	String	REQUEST_TYPES					= "POST, PUT, GET, OPTIONS, DELETE";
	String	HBS_APPLICATION					= "HBSAPPLICATION";
	String	HBS_SECRET						= "Kalam@151031";
	String	TRUST							= "trust";
	String	WRITE							= "write";
	int		THIRTY_DAYS						= 60 * 60 * 24 * 30;
}
