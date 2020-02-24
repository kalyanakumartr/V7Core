package org.hbs.core.oauth.server;

import org.springframework.security.core.userdetails.UserDetails;

public interface IOAuth2UserDetails extends UserDetails
{
	public String getFullName();

	public String getProducerId();

	public String getProducerName();

	public void setProducerName(String producerName);

	public String getParentProducerId();

	public void setParentProducerId(String parentProducerId);

	public String getParentProducerName();

	public void setParentProducerName(String parentProducerName);
}
