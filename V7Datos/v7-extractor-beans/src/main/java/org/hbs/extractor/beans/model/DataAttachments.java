package org.hbs.extractor.beans.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hbs.core.beans.model.CommonFileUpload;
import org.hbs.core.util.EnumInterface;

@Entity
@Table(name = "resume_attachments") // Change over DB Table name
public class DataAttachments extends CommonFileUpload
{

	public enum EDataTrace implements EnumInterface
	{
		AdditionalDocuments, MainDocument, YetToTrace
	}

	private static final long	serialVersionUID	= 3340835331638013651L;

	protected IncomingData		incomingData;
	protected String			dataURN;									// Resume
	protected EDataTrace		trace				= EDataTrace.YetToTrace;

	public DataAttachments()
	{
		super();
	}

	@ManyToOne(targetEntity = IncomingData.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "incomingId")
	public IncomingData getIncomingData()
	{
		return incomingData;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "trace")
	public EDataTrace getTrace()
	{
		return trace;
	}

	public void setIncomingData(IncomingData incomingData)
	{
		this.incomingData = incomingData;
	}

	public void setTrace(EDataTrace trace)
	{
		this.trace = trace;
	}

	@Column(name = "dataURN")
	public String getDataURN()
	{
		return dataURN;
	}

	public void setDataURN(String dataURN)
	{
		this.dataURN = dataURN;
	}

	@Override
	public String getCountryTimeZone()
	{
		return null;
	}

}
