package org.hbs.core.beans.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class ProducersBase extends CommonDateAndStatusFields implements IProducersBase
{

	private static final long	serialVersionUID	= -2975176593095463958L;

	protected Producers			producer;

	@ManyToOne(targetEntity = Producers.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "producerId")
	@JsonIgnore
	public Producers getProducer()
	{
		return producer;
	}

	@Override
	public void setProducer(Producers producer)
	{
		this.producer = producer;
	}

	@Override
	@Transient
	@JsonIgnore
	public String getCountryTimeZone()
	{
		return null;
	}
}