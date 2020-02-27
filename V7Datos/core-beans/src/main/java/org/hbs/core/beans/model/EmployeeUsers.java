package org.hbs.core.beans.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("Employee")
public class EmployeeUsers extends Users
{

	private static final long serialVersionUID = 8708144241732401029L;

	public EmployeeUsers()
	{
		super();
		this.employeeId = getBusinessKey();
		this.userType = EUserType.Employee;
	}

	@Transient
	@JsonIgnore
	public String getBusinessKey()
	{
		return EKey.Auto();
	}
}
