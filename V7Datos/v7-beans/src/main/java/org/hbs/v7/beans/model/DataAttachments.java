package org.hbs.v7.beans.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.beans.model.CommonFileUpload;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.IConstProperty;
import org.hbs.v7.beans.DataInTopicBean;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "resume_attachments") // Change over DB Table name
public class DataAttachments extends CommonFileUpload implements IConstProperty
{

	public enum EDataTrace implements EnumInterface
	{
		AdditionalDocuments, MainDocument, YetToTrace, UnableToRead
	}

	private static final long	serialVersionUID	= 3340835331638013651L;

	protected String			dataURN;										// Resume
	protected IncomingData		incomingData;
	protected EMessagePriority	priority			= EMessagePriority.Normal;
	protected EDataTrace		trace				= EDataTrace.YetToTrace;
	protected String			description;

	public DataAttachments()
	{
		super();
	}

	@Override
	@Transient
	@JsonIgnore
	public String getCountryTimeZone()
	{
		return null;
	}

	@Column(name = "dataURN")
	public String getDataURN()
	{
		return dataURN;
	}

	@ManyToOne(targetEntity = IncomingData.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "incomingId")
	public IncomingData getIncomingData()
	{
		return incomingData;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "priority")
	public EMessagePriority getPriority()
	{
		return priority;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "trace")
	public EDataTrace getTrace()
	{
		return trace;
	}

	public void setDataURN(String dataURN)
	{
		this.dataURN = dataURN;
	}

	public void setIncomingData(IncomingData incomingData)
	{
		this.incomingData = incomingData;
	}

	public void setPriority(EMessagePriority priority)
	{
		this.priority = priority;
	}

	public void setTrace(EDataTrace trace)
	{
		this.trace = trace;
	}

	@Column(name = "description")
	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Transient
	public DataInTopicBean getDataInTopicBean()
	{
		DataInTopicBean inBean = new DataInTopicBean();
		inBean.setAttachmentAutoId(this.getAutoId());
		inBean.setExternal(false);
		inBean.setFileFolderURL(this.getUploadFileFolderURL());
		inBean.setFileName(this.getUploadFileName());
		inBean.setPriority(this.getPriority());
		return inBean;
	}

}
