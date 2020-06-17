package org.hbs.core.beans.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.hbs.core.beans.model.IUsersBase.EUserType;
import org.hbs.core.security.resource.IPathBase.EMediaMode;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ICRUDBean;

public interface IProducers extends ICRUDBean
{
	public String getActiveProducerId();

	public IProducersAttachments getAttachment(EnumInterface documentType);

	public String getDomainContext();

	public Set<IProducersAttachments> getProducerAttachmentList();

	public String getProducerId();

	public String getProducerName();

	public EUserType getProducerType();

	public List<IProducersProperty> getProperty(EnumInterface media);

	public IProducersProperty getProperty(EnumInterface media, String key);

	public IProducersProperty getProperty(EnumInterface media, EMediaMode mediaMode, String key);

	public Set<ProducersProperty> getPropertyList();

	public List<IProducersProperty> getPropertyList(EnumInterface group);

	public Timestamp getPwdExpiryDays();

	public IUsers getUsers();

	public boolean isPrimary();

	public void setActiveProducerId(String producerId);

	public void setDomainContext(String domainContext);

	public void setPrimary(boolean primary);

	public void setProducerAttachmentList(Set<IProducersAttachments> producerAttachmentList);

	public void setProducerId(String producerId);

	public void setProducerName(String producerName);

	public void setProducerType(EUserType producerType);

	public void setPropertyList(Set<ProducersProperty> propertyList);

	public void setPwdExpiryDays(Timestamp csPwdExpiryDays);

	public void setUsers(IUsers users);

}
