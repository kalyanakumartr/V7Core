package org.hbs.v7.beans.model.resume;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hbs.core.beans.model.CommonBeanFields;

@Entity
@Table(name = "resume_comments")
public class ResumeComments extends CommonBeanFields
{
	private static final long	serialVersionUID	= 8853493372625882791L;
	protected String			commentId;
	protected ResumeData		resumeData;
	protected JobPosition		jobPosition;
	protected String			comments;

	public ResumeComments()
	{
		super();
	}

	public ResumeComments(String commentId, ResumeData resumeData, JobPosition jobPosition, String comments)
	{
		super();
		this.commentId = commentId;
		this.resumeData = resumeData;
		this.jobPosition = jobPosition;
		this.comments = comments;
	}

	@Id
	@Column(name = "commentId")
	public String getCommentId()
	{
		return commentId;
	}

	@Column(name = "comments")
	public String getComments()
	{
		return comments;
	}

	@ManyToOne(targetEntity = JobPosition.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "jobId")
	public JobPosition getJobPosition()
	{
		return jobPosition;
	}

	@ManyToOne(targetEntity = ResumeData.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "dataId")
	public ResumeData getResumeData()
	{
		return resumeData;
	}

	public void setCommentId(String commentId)
	{
		this.commentId = commentId;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public void setJobPosition(JobPosition jobPosition)
	{
		this.jobPosition = jobPosition;
	}

	public void setResumeData(ResumeData resumeData)
	{
		this.resumeData = resumeData;
	}

}
