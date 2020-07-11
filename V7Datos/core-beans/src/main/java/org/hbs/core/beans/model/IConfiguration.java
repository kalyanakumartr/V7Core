package org.hbs.core.beans.model;

import java.io.Serializable;

public interface IConfiguration extends Serializable
{
	public String getProducerId();

	public void setProducerId(String producerId);

	public String getConnectionId();

	public void setConnectionId(String connectionId);

	public String getBaseFolderPath();

}