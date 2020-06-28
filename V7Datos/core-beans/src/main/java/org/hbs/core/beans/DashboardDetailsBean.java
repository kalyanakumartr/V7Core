package org.hbs.core.beans;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DashboardDetailsBean extends APIStatus
{

	private static final long			serialVersionUID	= 521085951311877153L;
	public String						month;
	public String						day;
	public String						year;
	public String						startDate;
	public String						endDate;
	public List<DashboardStatusBean>	status				= new ArrayList<>();

	@Override
	public void clearForm()
	{
		// TODO Auto-generated method stub

	}
}
