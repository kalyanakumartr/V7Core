package org.hbs.core.beans.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ProducersBase extends CommonDateAndStatusFields implements IProducersBase
{

	private static final long	serialVersionUID	= -2975176593095463958L;

	protected IProducers		producer;

	@ManyToOne(targetEntity = Producers.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "producerId")
	public IProducers getProducer()
	{
		return producer;
	}

	@Override
	public void setProducer(IProducers producer)
	{
		this.producer = producer;
	}
}