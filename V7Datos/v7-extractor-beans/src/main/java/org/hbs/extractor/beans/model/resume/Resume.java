package org.hbs.extractor.beans.model.resume;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hbs.core.beans.model.CommonBeanFields;
import org.hbs.core.beans.model.Producers;
import org.hbs.extractor.beans.model.DataAttachments;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "resume")
public class Resume extends CommonBeanFields
{
	private static final long			serialVersionUID	= 5049651757005693167L;

	protected Set<DataAttachments>	attachmentList		= new LinkedHashSet<DataAttachments>(0);
	protected Set<Producers>			producerList		= new LinkedHashSet<Producers>(0);
	protected ResumeData				resumeData;
	protected String					resumeURN;

	public Resume()
	{
		super();
	}

	@OneToMany(targetEntity = DataAttachments.class, fetch = FetchType.LAZY, mappedBy = "resume", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JsonIgnore
	public Set<DataAttachments> getAttachmentList()
	{
		return attachmentList;
	}

	// Mapped To Multiple Producers, which means multiple branches
	@ManyToMany
	@JoinTable(name = "resume_producers", joinColumns = @JoinColumn(name = "resumeURN"), inverseJoinColumns = @JoinColumn(name = "producerId"))
	public Set<Producers> getProducerList()
	{
		return producerList;
	}

	@ManyToOne(targetEntity = ResumeData.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "dataId")
	public ResumeData getResumeData()
	{
		return resumeData;
	}

	@Id
	@Column(name = "resumeURN")
	public String getResumeURN()
	{
		return resumeURN;
	}

	public void setAttachmentList(Set<DataAttachments> attachmentList)
	{
		this.attachmentList = attachmentList;
	}

	public void setProducerList(Set<Producers> producerList)
	{
		this.producerList = producerList;
	}

	public void setResumeData(ResumeData resumeData)
	{
		this.resumeData = resumeData;
	}

	public void setResumeURN(String resumeURN)
	{
		this.resumeURN = resumeURN;
	}

}
