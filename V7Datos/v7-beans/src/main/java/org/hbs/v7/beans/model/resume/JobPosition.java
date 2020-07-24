package org.hbs.v7.beans.model.resume;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.beans.model.ProducersBase;
import org.hbs.core.util.EBusinessKey;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ICRUDBean;
import org.hbs.v7.beans.model.resume._SkillsAndCost.EAvailbility;

@Entity
@Table(name = "resume_job_position")
public class JobPosition extends ProducersBase implements ICRUDBean, EBusinessKey
{
	public enum EInstitution implements EnumInterface
	{
		Normal, Reputed, TopRated, InternationalRated;
	}

	public enum EPositionType implements EnumInterface
	{
		Temporary, Contract, Permanent;
	}

	public enum EPriority implements EnumInterface
	{
		Normal, Urgent, Expedite;
	}

	public enum ESalaryType implements EnumInterface
	{
		Hourly, Daily, Weekly, BiWeekly, Monthly, Annum, FixedBid;
	}

	public enum EWorkHours implements EnumInterface
	{
		_6_hours, _7_hours, _8_Hours, _9_Hours, _10_Hours;
	}

	public enum EWorkWeek implements EnumInterface
	{
		_5_Days, _6_Days;
	}

	private static final long	serialVersionUID	= 3270551531167460551L;

	private String				jobId;
	private String				title;
	private String				type;
	private String				level;
	private String				specialisation;
	private String				description;
	private String				requirements;
	private String				salaryMin;
	private String				salaryMax;
	private ESalaryType			salaryType			= ESalaryType.Monthly;
	private EWorkHours			workHours			= EWorkHours._8_Hours;
	private EWorkWeek			workWeek			= EWorkWeek._5_Days;
	private EPositionType		positionType		= EPositionType.Temporary;
	private EAvailbility		availability		= EAvailbility.NotSpecify;
	private EInstitution		institutionRate		= EInstitution.Normal;
	private EPriority			priority			= EPriority.Normal;
	private String				workingLocation;
	private String				workingCountry;
	private int					openPositions		= 1;
	private String				minimumQualification;
	private String				yearsOfExperience;
	private String				desiredSkills;
	private String				additionalSkills;

	public JobPosition()
	{
		super();
		this.jobId = getBusinessKey();
	}

	@Column(name = "additionalSkills")
	public String getAdditionalSkills()
	{
		return additionalSkills;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "availability")
	public EAvailbility getAvailability()
	{
		return availability;
	}

	@Column(name = "description")
	public String getDescription()
	{
		return description;
	}

	@Column(name = "desiredSkills")
	public String getDesiredSkills()
	{
		return desiredSkills;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "institutionRate")
	public EInstitution getInstitutionRate()
	{
		return institutionRate;
	}

	@Id
	@Column(name = "jobId")
	public String getJobId()
	{
		return jobId;
	}

	@Column(name = "level")
	public String getLevel()
	{
		return level;
	}

	@Column(name = "minimumQualification")
	public String getMinimumQualification()
	{
		return minimumQualification;
	}

	@Column(name = "openPositions")
	public int getOpenPositions()
	{
		return openPositions;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "positionType")
	public EPositionType getPositionType()
	{
		return positionType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "priority")
	public EPriority getPriority()
	{
		return priority;
	}

	@Column(name = "requirements")
	public String getRequirements()
	{
		return requirements;
	}

	@Column(name = "salaryMax")
	public String getSalaryMax()
	{
		return salaryMax;
	}

	@Column(name = "salaryMin")
	public String getSalaryMin()
	{
		return salaryMin;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "salaryType")
	public ESalaryType getSalaryType()
	{
		return salaryType;
	}

	@Column(name = "specialisation")
	public String getSpecialisation()
	{
		return specialisation;
	}

	@Column(name = "title")
	public String getTitle()
	{
		return title;
	}

	@Column(name = "type")
	public String getType()
	{
		return type;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "workHours")
	public EWorkHours getWorkHours()
	{
		return workHours;
	}

	@Column(name = "workingCountry")
	public String getWorkingCountry()
	{
		return workingCountry;
	}

	@Column(name = "workingLocation")
	public String getWorkingLocation()
	{
		return workingLocation;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "workWeek")
	public EWorkWeek getWorkWeek()
	{
		return workWeek;
	}

	@Column(name = "yearsOfExperience")
	public String getYearsOfExperience()
	{
		return yearsOfExperience;
	}

	public void setAdditionalSkills(String additionalSkills)
	{
		this.additionalSkills = additionalSkills;
	}

	public void setAvailability(EAvailbility availability)
	{
		this.availability = availability;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setDesiredSkills(String desiredSkills)
	{
		this.desiredSkills = desiredSkills;
	}

	public void setInstitutionRate(EInstitution institutionRate)
	{
		this.institutionRate = institutionRate;
	}

	public void setJobId(String jobId)
	{
		this.jobId = jobId;
	}

	public void setLevel(String level)
	{
		this.level = level;
	}

	public void setMinimumQualification(String minimumQualification)
	{
		this.minimumQualification = minimumQualification;
	}

	public void setOpenPositions(int openPositions)
	{
		this.openPositions = openPositions;
	}

	public void setPositionType(EPositionType positionType)
	{
		this.positionType = positionType;
	}

	public void setPriority(EPriority priority)
	{
		this.priority = priority;
	}

	public void setRequirements(String requirements)
	{
		this.requirements = requirements;
	}

	public void setSalaryMax(String salaryMax)
	{
		this.salaryMax = salaryMax;
	}

	public void setSalaryMin(String salaryMin)
	{
		this.salaryMin = salaryMin;
	}

	public void setSalaryType(ESalaryType salaryType)
	{
		this.salaryType = salaryType;
	}

	public void setSpecialisation(String specialisation)
	{
		this.specialisation = specialisation;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public void setWorkHours(EWorkHours workHours)
	{
		this.workHours = workHours;
	}

	public void setWorkingCountry(String workingCountry)
	{
		this.workingCountry = workingCountry;
	}

	public void setWorkingLocation(String workingLocation)
	{
		this.workingLocation = workingLocation;
	}

	public void setWorkWeek(EWorkWeek workWeek)
	{
		this.workWeek = workWeek;
	}

	public void setYearsOfExperience(String yearsOfExperience)
	{
		this.yearsOfExperience = yearsOfExperience;
	}

	@Override
	@Transient
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

}
