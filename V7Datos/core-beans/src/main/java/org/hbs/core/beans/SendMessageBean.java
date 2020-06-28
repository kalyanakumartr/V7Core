package org.hbs.core.beans;

import org.hbs.core.beans.model.Users;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SendMessageBean extends APIStatus
{

	private static final long	serialVersionUID	= 2490152380120347818L;

	public String				type;

	public Users				user;

	public String				contact;

	@Override
	public void clearForm()
	{
		// TODO Auto-generated method stub

	}

}
