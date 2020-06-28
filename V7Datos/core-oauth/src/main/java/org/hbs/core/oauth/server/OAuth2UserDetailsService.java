package org.hbs.core.oauth.server;

import org.hbs.core.beans.model.Users;
import org.hbs.core.dao.UserDao;
import org.hbs.core.security.resource.OAuth2UserDetails;
import org.hbs.core.util.ServerUtilFactory;
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
		System.out.println(">>>>>>>>>>>>>>>>>>>>>_DomainURL>>>>>>>>>>>>>>>>>>>>>>>> " + ServerUtilFactory.getInstance().getDomainURL());
		Object object = userDao.findByEmailOrMobileOrUserId(userName);
		if (object == null)
		{
			throw new UsernameNotFoundException("Login Info " + userName + " not found");
		}
		Object[] userDetail = (Object[]) object;
		return new OAuth2UserDetails((Users) userDetail[0], (String) userDetail[1], (String) userDetail[2]);
	}
}
