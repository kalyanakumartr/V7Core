package org.hbs.core.oauth.server;

import org.hbs.core.beans.model.IUsers;
import org.hbs.core.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class OAuth2UserDetailsService implements UserDetailsService
{
	@Autowired
	UserDao userDao;

	@Override
	public OAuth2UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
	{
		IUsers user = userDao.findByEmailOrMobileOrUserId(userName);
		if (user == null)
		{
			throw new UsernameNotFoundException("Login Info " + userName + " not found");
		}
		return new OAuth2UserDetails(user);
	}
}
