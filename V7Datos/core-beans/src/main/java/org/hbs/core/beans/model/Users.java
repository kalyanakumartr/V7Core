package org.hbs.core.beans.model;

import java.sql.Timestamp;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.security.resource.IPath.EAuth;
import org.hibernate.annotations.DiscriminatorOptions;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "userType")
@DiscriminatorOptions(insert = false, force = true)
public class Users extends CommonUsers
{
	private static final long		serialVersionUID	= 8724774711417136041L;

	protected CreatedModifiedUsers	byUser				= new CreatedModifiedUsers();

	public Users()
	{
		super();
		getBusinessKey();
	}

	public Users(EUserType userType)
	{
		super();
		getBusinessKey();
		this.userType = userType;
	}

	public Users(String employeeId)
	{
		super();
		this.employeeId = employeeId;
	}

	@Transient
	@JsonIgnore
	public String getBusinessKey(String... combination)
	{
		this.employeeId = EKey.Auto();
		return this.employeeId;
	}

	@Embedded
	public CreatedModifiedUsers getByUser()
	{
		return byUser;
	}

	public void setByUser(CreatedModifiedUsers byUser)
	{
		this.byUser = byUser;
	}

	public void createdUserAndProducerInfo(Authentication auth)
	{
		this.byUser.createdUser = EAuth.User.getUser(auth);
		this.createdDate = new Timestamp(System.currentTimeMillis());
		this.byUser.modifiedUser = EAuth.User.getUser(auth);
		this.modifiedDate = new Timestamp(System.currentTimeMillis());
		this.parentProducer = EAuth.User.getParentProducer(auth);
		this.producer = EAuth.User.getProducer(auth);
	}

	public void modifiedUserInfo(Authentication auth)
	{
		if (auth == null)
			this.byUser.modifiedUser = this; // Self update any personal details / password change
		else
			this.byUser.modifiedUser = EAuth.User.getUser(auth);
		this.modifiedDate = new Timestamp(System.currentTimeMillis());
	}

	public void updateDisplayInfoOfProducersAndDateTime()
	{
		this.producerId = this.producer.getProducerId();
		this.producerName = this.producer.getProducerName();
		this.parentProducerId = this.parentProducer.getProducerId();
		this.parentProducerName = this.parentProducer.getProducerName();
		createdDateByTimeZone();
		modifiedDateByTimeZone();
	}

	@Override
	@Transient
	@JsonIgnore
	public String getCountryTimeZone()
	{
		return this.byUser.getCountryId();
	}

}
