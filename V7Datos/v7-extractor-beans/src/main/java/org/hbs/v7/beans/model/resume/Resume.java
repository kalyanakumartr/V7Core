package org.hbs.v7.beans.model.resume;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hbs.core.beans.model.Producers;
import org.hbs.v7.beans.model.ACoreDataBase;

@Entity
@Table(name = "resume")
public class Resume extends ACoreDataBase
{
	private static final long	serialVersionUID	= 5049651757005693167L;

	protected Set<Producers>	producerList		= new LinkedHashSet<Producers>(0);
	protected ResumeData		resumeData;

	public Resume()
	{
		super();
	}

	// Mapped To Multiple Producers, which means multiple branches
	@ManyToMany
	@JoinTable(name = "resume_producers", joinColumns = @JoinColumn(name = "dataURN"), inverseJoinColumns = @JoinColumn(name = "producerId"))
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

	public void setProducerList(Set<Producers> producerList)
	{
		this.producerList = producerList;
	}

	public void setResumeData(ResumeData resumeData)
	{
		this.resumeData = resumeData;
	}
}
