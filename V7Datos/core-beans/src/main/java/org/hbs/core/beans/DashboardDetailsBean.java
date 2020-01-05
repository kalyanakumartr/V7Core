package org.hbs.core.beans;

import java.util.ArrayList;
import java.util.List;

public class DashboardDetailsBean extends APIStatus
{

	private static final long			serialVersionUID	= 521085951311877153L;
	public String						month;
	public String						day;
	public String						year;
	public String						startDate;
	public String						endDate;
	public List<DashboardStatusBean>	status				= new ArrayList<>();
}
