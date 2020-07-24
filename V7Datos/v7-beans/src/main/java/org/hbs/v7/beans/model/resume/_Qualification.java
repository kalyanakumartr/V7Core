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
@Table(name = "resume_qualification")
public class _Qualification implements ICRUDBean, EBusinessKey
{

	private static final long	serialVersionUID	= -2100990137830123105L;
	protected String			qualificationId;
	protected ResumeData		resumeData;
	protected String			institutionName;
	protected String			course;
	protected String			grade;
	protected String			passedOutYear;
	protected String			weightage;
	protected String			weightageDescription;
	protected Boolean			isHighest			= false;

	public _Qualification()
	{
		super();
	}

	public _Qualification(ResumeData resumeData)
	{
		super();
		this.resumeData = resumeData;
	}

	@Id
	@Column(name = "qualificationId")
	public String getQualificationId()
	{
		return qualificationId;
	}

	@Override
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	@Column(name = "course")
	public String getCourse()
	{
		return course;
	}

	@Column(name = "isHighest")
	public Boolean getIsHighest()
	{
		return isHighest;
	}

	@ManyToOne(targetEntity = ResumeData.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "dataId")
	public ResumeData getResumeData()
	{
		return resumeData;
	}

	@Column(name = "weightage")
	public String getWeightage()
	{
		return weightage;
	}

	@Column(name = "weightageDescription")
	public String getWeightageDescription()
	{
		return weightageDescription;
	}

	public void setQualificationId(String qualificationId)
	{
		this.qualificationId = qualificationId;
	}

	public void setCourse(String course)
	{
		this.course = course;
	}

	public void setIsHighest(Boolean isHighest)
	{
		this.isHighest = isHighest;
	}

	public void setResumeData(ResumeData resumeData)
	{
		this.resumeData = resumeData;
	}

	public void setWeightage(String weightage)
	{
		this.weightage = weightage;
	}

	public void setWeightageDescription(String weightageDescription)
	{
		this.weightageDescription = weightageDescription;
	}

}
