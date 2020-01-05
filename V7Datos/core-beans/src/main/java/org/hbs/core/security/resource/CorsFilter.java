package org.hbs.core.security.resource;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter, OAuth2Constants
{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
	{
		final HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, ORIGINS);
		response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, REQUEST_TYPES);
		response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, HEADERS);
		response.setHeader(ACCESS_CONTROL_MAX_AGE, "3600");
		if (HttpMethod.OPTIONS.name().equalsIgnoreCase(((HttpServletRequest) req).getMethod()))
		{
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else
		{
			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy()
	{
	}

	@Override
	public void init(FilterConfig config) throws ServletException
	{
	}
}