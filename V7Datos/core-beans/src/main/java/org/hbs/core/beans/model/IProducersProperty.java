package org.hbs.core.beans.model;

import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.security.resource.IPathBase.EMediaMode;

public interface IProducersProperty extends IProducersBase, ICommonDateAndStatusFields
{
	public IConfiguration getPropertyAsConfiguration() throws ClassNotFoundException;

	public String getAutoId();

	public CreatedModifiedUsers getByUser();

	public String getComments();

	public String getEnumKey();

	public String getGroupName();

	public EMedia getMedia();

	public EMediaMode getMediaMode();

	public void setMediaMode(EMediaMode mediaMode);

	public String getProperty();

	public String getValue();

	public boolean isStatus();

	public void setAutoId(String autoId);

	public void setByUser(CreatedModifiedUsers byUser);

	public void setComments(String comments);

	public void setEnumKey(String enumKey);

	public void setGroupName(String name);

	public void setMedia(EMedia media);

	public void setProperty(String property);

	public void setStatus(boolean status);

	public void setValue(String value);
}
