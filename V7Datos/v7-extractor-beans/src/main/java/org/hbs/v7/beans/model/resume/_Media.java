package org.hbs.v7.beans.model.resume;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hbs.core.beans.model.CommunicationMedia;

@Entity
@Table(name = "resume_media")
public class _Media extends CommunicationMedia
{
	private static final long	serialVersionUID	= -3116818679900586377L;

	protected ResumeData		resumeData;

	public _Media()
	{
		super();
	}

	public _Media(ResumeData resumeData)
	{
		super();
		this.resumeData = resumeData;
	}

	@ManyToOne(targetEntity = ResumeData.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "dataId")
	public ResumeData getResumeData()
	{
		return resumeData;
	}

	public void setResumeData(ResumeData resumeData)
	{
		this.resumeData = resumeData;
	}

}
