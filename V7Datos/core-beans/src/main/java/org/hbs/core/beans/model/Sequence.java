package org.hbs.core.beans.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hbs.core.util.ICRUDBean;

@Entity
@Table(name = "sequence")
public class Sequence implements ICRUDBean
{
	private static final long	serialVersionUID	= 1696926036927427862L;

	private long				autoId;
	private String				prepend;
	private IProducers			producer;
	private long				sequenceId;
	private String				sequenceKey;

	public Sequence()
	{
		super();
	}

	public Sequence(long sequenceId, String sequenceKey)
	{
		super();
		this.sequenceId = sequenceId;
		this.sequenceKey = sequenceKey;
	}

	@Id
	@JoinColumn(name = "autoId")
	public long getAutoId()
	{
		return autoId;
	}

	@JoinColumn(name = "prepend")
	public String getPrepend()
	{
		return prepend;
	}

	@ManyToOne(targetEntity = Producers.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "producerId")
	public IProducers getProducer()
	{
		return producer;
	}

	@Column(name = "sequenceId")
	public long getSequenceId()
	{
		return sequenceId;
	}

	@Column(name = "sequenceKey")
	public String getSequenceKey()
	{
		return sequenceKey;
	}

	public void setAutoId(long autoId)
	{
		this.autoId = autoId;
	}

	public void setPrepend(String prepend)
	{
		this.prepend = prepend;
	}

	public void setProducer(IProducers producer)
	{
		this.producer = producer;
	}

	public void setSequenceId(long sequenceId)
	{
		this.sequenceId = sequenceId;
	}

	public void setSequenceKey(String sequenceKey)
	{
		this.sequenceKey = sequenceKey;
	}

}
