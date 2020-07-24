package org.hbs.v7.beans.model.resume;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hbs.core.beans.model.Producers;
import org.hbs.core.beans.model.ProducersAddress;
import org.hbs.core.util.EnumInterface;

@Entity(name = "CustomerProducer")
// @DiscriminatorValue("RezoomProducer")
public class CustomerProducer extends Producers
{
	public enum EProducerStatus implements EnumInterface
	{
		Pending, Activated, Blocked;
	}

	private static final long		serialVersionUID	= -3890542462537008480L;

	private BillingMedia			billingMedia;
	private Set<ProducersAddress>	address;
	private String					description;
	private String					registrationDate;
	private Set<Credits>			credits				= new LinkedHashSet<Credits>(0);
	private EProducerStatus			customerStatus		= EProducerStatus.Pending;

	public CustomerProducer()
	{
		super();
	}

	@OneToMany(targetEntity = ProducersAddress.class, fetch = FetchType.EAGER, mappedBy = "producer", cascade = CascadeType.ALL)
	@OrderBy("createdDate ASC")
	public Set<ProducersAddress> getAddress()
	{
		return address;
	}

	@Embedded
	public BillingMedia getBillingMedia()
	{
		return billingMedia;
	}

	@OneToMany(targetEntity = Credits.class, fetch = FetchType.EAGER, mappedBy = "producer", cascade = CascadeType.ALL)
	@OrderBy("createdDate ASC")
	public Set<Credits> getCredits()
	{
		return credits;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "customerStatus")
	public EProducerStatus getProducerStatus()
	{
		return customerStatus;
	}

	@Column(name = "description")
	public String getDescription()
	{
		return description;
	}

	@Column(name = "registrationDate")
	public String getRegistrationDate()
	{
		return registrationDate;
	}

	public void setAddress(Set<ProducersAddress> address)
	{
		this.address = address;
	}

	public void setBillingMedia(BillingMedia billingMedia)
	{
		this.billingMedia = billingMedia;
	}

	public void setCredits(Set<Credits> credits)
	{
		this.credits = credits;
	}

	public void setProducerStatus(EProducerStatus customerStatus)
	{
		this.customerStatus = customerStatus;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setRegistrationDate(String registrationDate)
	{
		this.registrationDate = registrationDate;
	}

}
