package org.hbs.v7.beans.model.resume;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hbs.core.util.EBusinessKey;
import org.hbs.core.util.ICRUDBean;

@Entity
@Table(name = "resume_professional")
public class _ProfessionalExperience implements ICRUDBean, EBusinessKey
{
	private static final long	serialVersionUID	= 9134760426797827336L;
	protected ResumeData		resumeData;
	protected String			professionalId;
	protected String			projectName;
	protected String			companyName;
	protected String			description;
	protected String			designation;
	protected String			duration;
	protected String			responsibilities;

	public _ProfessionalExperience()
	{
		super();
		this.professionalId = getBusinessKey();
	}

	@Id
	@Column(name = "professionalId")
	public String getProfessionalId()
	{
		return professionalId;
	}

	@Override
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	@Column(name = "companyName")
	public String getCompanyName()
	{
		return companyName;
	}

	@Column(name = "description")
	public String getDescription()
	{
		return description;
	}

	@Column(name = "designation")
	public String getDesignation()
	{
		return designation;
	}

	@Column(name = "duration")
	public String getDuration()
	{
		return duration;
	}

	@Column(name = "projectName")
	public String getProjectName()
	{
		return projectName;
	}

	@Column(name = "responsibilities")
	public String getResponsibilities()
	{
		return responsibilities;
	}

	@ManyToOne(targetEntity = ResumeData.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "dataId")
	public ResumeData getResumeData()
	{
		return resumeData;
	}

	public void setProfessionalId(String professionalId)
	{
		this.professionalId = professionalId;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setDesignation(String designation)
	{
		this.designation = designation;
	}

	public void setDuration(String duration)
	{
		this.duration = duration;
	}

	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}

	public void setResponsibilities(String responsibilities)
	{
		this.responsibilities = responsibilities;
	}

	public void setResumeData(ResumeData resumeData)
	{
		this.resumeData = resumeData;
	}

}
