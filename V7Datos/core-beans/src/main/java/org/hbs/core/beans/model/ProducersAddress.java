package org.hbs.core.beans.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producersaddress")
public class ProducersAddress extends CommonAddress
{
	private static final long	serialVersionUID	= 5556344583215813395L;
	protected IProducers		producer;

	public ProducersAddress()
	{
		super();
		this.addressId = getBusinessKey();
	}

	public ProducersAddress(AddressType addressType)
	{
		super();
		this.addressId = getBusinessKey();
		this.addressType = addressType;
	}

	public ProducersAddress(IProducers producer)
	{
		super();
		this.addressId = getBusinessKey();
		this.producer = producer;
	}

	@ManyToOne(targetEntity = Producers.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "producerId")
	public IProducers getProducer()
	{
		return producer;
	}

	public void setProducer(IProducers producer)
	{
		this.producer = producer;
	}
}
