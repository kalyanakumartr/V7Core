package org.hbs.core.security.resource;

import org.springframework.security.core.userdetails.UserDetails;

public interface IOAuth2UserDetails extends UserDetails
{
	public String getEmployeeId();

	public String getFullName();

	public String getParentProducerId();

	public String getParentProducerName();

	public String getProducerId();

	public String getProducerName();

	public void setEmployeeId(String employeeId);

	public void setProducerId(String producerId);

	public void setParentProducerId(String parentProducerId);

	public void setParentProducerName(String parentProducerName);

	public void setProducerName(String producerName);
}
