package org.hbs.core.beans.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hbs.core.util.ICRUDBean;

@Entity
@Table(name = "state")
public class State implements ICRUDBean, Comparable<State>
{
	private static final long	serialVersionUID	= 1372105679878287793L;
	protected Country			country;
	protected String			state;
	protected boolean			status;

	public State()
	{
		super();
	}

	public State(Country country, String state)
	{
		super();
		this.country = country;
		this.state = state;
	}

	@ManyToOne(targetEntity = Country.class)
	@JoinColumn(name = "country", nullable = false)
	public Country getCountry()
	{
		return country;
	}

	@Id
	@Column(name = "state")
	public String getState()
	{
		return state;
	}

	@Column(name = "status")
	public boolean isStatus()
	{
		return status;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

	public void setCountry(Country country)
	{
		this.country = country;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	@Override
	public int compareTo(State state)
	{
		return this.state.compareTo(state.getState());
	}
}
