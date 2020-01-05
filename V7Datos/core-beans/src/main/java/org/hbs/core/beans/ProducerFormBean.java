package org.hbs.core.beans;

import org.hbs.core.beans.model.Producers;
import org.springframework.security.core.Authentication;

public class ProducerFormBean extends APIStatus
{

	private static final long	serialVersionUID	= 5862342185767193951L;

	public Producers		producer			= new Producers();

	public Producers		repoProducer;

	public void updateRepoProducer(Authentication auth)
	{
	}
}
