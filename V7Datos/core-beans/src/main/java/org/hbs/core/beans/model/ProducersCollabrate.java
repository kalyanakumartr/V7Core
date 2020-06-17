package org.hbs.core.beans.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "producerscollabrate")
public class ProducersCollabrate extends ProducersBase implements IProducersCollabrate
{
	private static final long		serialVersionUID	= 8950252686467081208L;

	protected long					autoId;
	protected IProducers			collabrater;
	protected CreatedModifiedUsers	byUser;

	public ProducersCollabrate()
	{
		super();
	}

	@Id
	@Column(name = "professionalId")
	public long getAutoId()
	{
		return autoId;
	}

	@ManyToOne(targetEntity = Producers.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "collabraterId")
	public IProducers getCollabrater()
	{
		return collabrater;
	}

	public void setAutoId(long autoId)
	{
		this.autoId = autoId;
	}

	@Override
	public void setCollabrater(IProducers collabrater)
	{
		this.collabrater = collabrater;
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

	@Override
	@Transient
	@JsonIgnore
	public String getCountryTimeZone()
	{
		if (this.byUser.createdUser != null && this.byUser.createdUser.getCountry() != null && this.byUser.modifiedUser == null)
		{
			return this.byUser.createdUser.getCountry().getCountry();
		}
		else if (this.byUser.modifiedUser.getCountry() != null)
		{
			return this.byUser.modifiedUser.getCountry().getCountry();
		}
		return null;
	}
}
