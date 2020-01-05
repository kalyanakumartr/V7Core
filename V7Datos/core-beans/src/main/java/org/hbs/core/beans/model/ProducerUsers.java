package org.hbs.core.beans.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("Producer")
public class ProducerUsers extends Users implements IProducerUsers
{

	private static final long serialVersionUID = -7077340071970448073L;

	public ProducerUsers()
	{
		super();
		this.employeeId = getBusinessKey();
		this.userType = EUserType.Producer;
	}

	@Transient
	public String getBusinessKey()
	{
		return EKey.Auto();
	}
}
