package org.hbs.core.beans.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	public String getBusinessKey()
	{
		return EKey.Auto();
	}
}
