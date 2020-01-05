package org.hbs.core.beans.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("Consumer")
public class ConsumerUsers extends Users
{
	private static final long serialVersionUID = -5264387759409829408L;

	public ConsumerUsers()
	{
		super();
		this.employeeId = getBusinessKey();
		this.userType = EUserType.Consumer;
	}

	@Transient
	public String getBusinessKey()
	{
		return EKey.Auto();
	}
}
