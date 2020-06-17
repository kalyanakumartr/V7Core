package org.hbs.core.beans.model;

import java.io.Serializable;
import java.sql.Timestamp;

public interface ICommonDateAndStatusFields extends Serializable
{
	public Timestamp getCreatedDate();

	public Timestamp getModifiedDate();

	public Boolean getStatus();

	public void setCreatedDate(Timestamp createdDate);

	public void setModifiedDate(Timestamp modifiedDate);

	public void setStatus(Boolean status);

	public String getCountryTimeZone();

}
