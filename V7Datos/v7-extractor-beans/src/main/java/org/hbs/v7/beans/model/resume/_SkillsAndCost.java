package org.hbs.v7.beans.model.resume;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ICRUDBean;

@Embeddable
public class _SkillsAndCost implements ICRUDBean
{
	public enum EAvailbility implements EnumInterface
	{
		NotSpecify, Immediate, TwoWeeks, OneMonth, TwoMonths, ThreeMonths;
	}

	public enum ENotice implements EnumInterface
	{
		NotApplicable, OneMonth, TwoMonths, ThreeMonths;
	}

	private static final long	serialVersionUID	= -3158653937908067775L;

	protected String			minSalaryExpected;
	protected String			maxSalaryExpected;
	protected String			currentSalary;
	protected String			skills;
	protected ENotice			noticePeriod		= ENotice.NotApplicable;
	protected EAvailbility		availability		= EAvailbility.NotSpecify;

	public _SkillsAndCost()
	{
		super();
	}

	public _SkillsAndCost(String minSalaryExpected, String maxSalaryExpected, String currentSalary, String skills, ENotice noticePeriod, EAvailbility availability)
	{
		super();
		this.minSalaryExpected = minSalaryExpected;
		this.maxSalaryExpected = maxSalaryExpected;
		this.currentSalary = currentSalary;
		this.skills = skills;
		this.noticePeriod = noticePeriod;
		this.availability = availability;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "availability")
	public EAvailbility getAvailability()
	{
		return availability;
	}

	@Column(name = "currentSalary")
	public String getCurrentSalary()
	{
		return currentSalary;
	}

	@Column(name = "maxSalaryExpected")
	public String getMaxSalaryExpected()
	{
		return maxSalaryExpected;
	}

	@Column(name = "minSalaryExpected")
	public String getMinSalaryExpected()
	{
		return minSalaryExpected;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "noticePeriod")
	public ENotice getNoticePeriod()
	{
		return noticePeriod;
	}

	@Column(name = "skills")
	public String getSkills()
	{
		return skills;
	}

	public void setAvailability(EAvailbility availability)
	{
		this.availability = availability;
	}

	public void setCurrentSalary(String currentSalary)
	{
		this.currentSalary = currentSalary;
	}

	public void setMaxSalaryExpected(String maxSalaryExpected)
	{
		this.maxSalaryExpected = maxSalaryExpected;
	}

	public void setMinSalaryExpected(String minSalaryExpected)
	{
		this.minSalaryExpected = minSalaryExpected;
	}

	public void setNoticePeriod(ENotice noticePeriod)
	{
		this.noticePeriod = noticePeriod;
	}

	public void setSkills(String skills)
	{
		this.skills = skills;
	}

}
