package org.hbs.core.beans;

import org.hbs.core.beans.model.IConfiguration;
import org.hbs.core.beans.model.ProducersProperty;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ConfigurationFormBean extends APIStatus
{

	private static final long	serialVersionUID	= 2490152380120347818L;

	public IConfiguration		configuration;

	public String				to;

	public String				messageCode;

	public ProducersProperty	producerProperty;

	public ProducersProperty	repoProducerProperty;

	public String				groupName;

	public String				autoId;

	public ConfigurationFormBean()
	{
		super();
	}

	public ConfigurationFormBean(IConfiguration configuration)
	{
		super();
		this.configuration = configuration;
	}

	public void updateRepoConfiguration(Authentication auth)
	{

	}

	@Override
	public void clearForm()
	{
		// TODO Auto-generated method stub

	}

}
