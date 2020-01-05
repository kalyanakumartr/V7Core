package org.hbs.core.beans;

import org.hbs.core.beans.model.IMessages.EMessageStatus;

public class DashboardStatusBean
{

	public DashboardStatusBean(String status)
	{
		this.status = status;
	}

	public DashboardStatusBean(EMessageStatus status)
	{
		this.status = status.name();
	}

	public String	status;
	public long		value;

}
