package org.hbs.v7.beans.model.resume;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.beans.model.IProducers;
import org.hbs.core.beans.model.Producers;
import org.hbs.core.util.EBusinessKey;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ICRUDBean;

@Entity
@Table(name = "resume_job_position_mapping")
public class ResumeJobPositions implements ICRUDBean, EBusinessKey
{
	public enum EResumeStatus implements EnumInterface
	{
		New, Open, Completed, View, Blocked, Expired;
	}

	private static final long	serialVersionUID	= -9029724986744850629L;

	protected String			autoId;
	protected Resume			resume;
	protected JobPosition		jobPosition;
	protected EResumeStatus		resumeStatus		= EResumeStatus.New;
	protected IProducers		producer;

	public ResumeJobPositions()
	{
		super();
	}

	@Id
	@Column(name = "autoId")
	public String getAutoId()
	{
		return autoId;
	}

	@Override
	@Transient
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	@ManyToOne(targetEntity = JobPosition.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "jobId")
	public JobPosition getJobPosition()
	{
		return jobPosition;
	}

	@ManyToOne(targetEntity = Producers.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "producerId")
	public IProducers getProducer()
	{
		return producer;
	}

	@ManyToOne(targetEntity = Resume.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "resumeURN")
	public Resume getResume()
	{
		return resume;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "resumeStatus")
	public EResumeStatus getResumeStatus()
	{
		return resumeStatus;
	}

	public void setAutoId(String autoId)
	{
		this.autoId = autoId;
	}

	public void setJobPosition(JobPosition jobPosition)
	{
		this.jobPosition = jobPosition;
	}

	public void setProducer(IProducers producer)
	{
		this.producer = producer;
	}

	public void setResume(Resume resume)
	{
		this.resume = resume;
	}

	public void setResumeStatus(EResumeStatus resumeStatus)
	{
		this.resumeStatus = resumeStatus;
	}

}
