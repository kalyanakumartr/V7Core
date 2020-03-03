package org.hbs.core.oauth.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hbs.core.beans.model.IUserRoles;
import org.hbs.core.beans.model.IUsers;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.IConstProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class OAuth2UserDetails implements IOAuth2UserDetails
{

	private static final long						serialVersionUID	= 7116369654223628650L;
	private Collection<? extends GrantedAuthority>	authorities;
	private String									employeeId;
	private String									countryId;
	private String									password;
	private String									username;
	private String									fullName;
	private String									producerId;
	private String									producerName;
	private String									parentProducerId;
	private String									parentProducerName;

	public OAuth2UserDetails(IUsers user, String producerName, String parentProducerName)
	{
		this.employeeId = user.getEmployeeId();
		this.username = user.getUserId();
		this.password = user.getUserPwd();
		this.countryId = user.getCountry().getCountry();
		this.fullName = CommonValidator.isNullOrEmpty(user.getLastName()) ? "" : user.getLastName() + IConstProperty.COMMA_SPACE + user.getUserName();
		// Dont Change Order
		this.producerId = user.getProducer().getProducerId();
		this.parentProducerId = user.getParentProducer().getProducerId();

		this.producerName = producerName;
		this.parentProducerName = parentProducerName;
		this.authorities = translate(user.getUserRoleses());
		
	}

	private Collection<? extends GrantedAuthority> translate(Set<IUserRoles> userRoles)
	{
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (IUserRoles uRole : userRoles)
		{
			authorities.add(new SimpleGrantedAuthority(uRole.getRoles().getRoleId().toUpperCase()));
		}
		return authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return authorities;
	}

	@Override
	public String getPassword()
	{
		return password;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getProducerId()
	{
		return producerId;
	}

	public void setProducerId(String producerId)
	{
		this.producerId = producerId;
	}

	public String getProducerName()
	{
		return producerName;
	}

	public void setProducerName(String producerName)
	{
		this.producerName = producerName;
	}

	public String getParentProducerId()
	{
		return parentProducerId;
	}

	public void setParentProducerId(String parentProducerId)
	{
		this.parentProducerId = parentProducerId;
	}

	public String getParentProducerName()
	{
		return parentProducerName;
	}

	public void setParentProducerName(String parentProducerName)
	{
		this.parentProducerName = parentProducerName;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	@Override
	public String getUsername()
	{
		return username;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	public String getCountryId()
	{
		return countryId;
	}

	public void setCountryId(String countryId)
	{
		this.countryId = countryId;
	}
	@Override
	public String getEmployeeId()
	{
		return employeeId;
	}

	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}

}
