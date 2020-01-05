package org.hbs.extractor.beans.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.security.resource.IPath.EMedia;
import org.hbs.core.util.EBusinessKey;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ICRUDBean;

@Entity
@Table(name = "resume_incoming_data")
public class IncomingData implements ICRUDBean, EBusinessKey
{

	public enum EIncomingStatus implements EnumInterface
	{
		New, InProcess, Completed, AttachmentReadError, InvalidAttachment, UnRecognizedError
	}

	private static final long		serialVersionUID	= 8524114488133328911L;

	protected String				incomingId;
	protected EMedia				media				= EMedia.Email;
	protected String				candidateEmail;
	protected String				subject				= "";
	protected String				description			= "";
	protected Long					sentTime;
	protected String				uniqueId;
	protected String				readerInstance;
	protected EIncomingStatus		incomingStatus		= EIncomingStatus.New;
	protected Set<DataAttachments>	attachmentList		= new LinkedHashSet<DataAttachments>();

	public IncomingData()
	{
		super();
		this.incomingId = getBusinessKey();
	}

	@OneToMany(targetEntity = DataAttachments.class, fetch = FetchType.LAZY, mappedBy = "incomingData")
	public Set<DataAttachments> getAttachmentList()
	{
		return attachmentList;
	}

	@Id
	@Column(name = "incomingId")
	public String getIncomingId()
	{
		return incomingId;
	}

	@Override
	@Transient
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	@Column(name = "candidateEmail")
	public String getCandidateEmail()
	{
		return candidateEmail;
	}

	@Column(name = "description")
	public String getDescription()
	{
		return description;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "incomingStatus")
	public EIncomingStatus getIncomingStatus()
	{
		return incomingStatus;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "media")
	public EMedia getMedia()
	{
		return media;
	}

	@Column(name = "readerInstance")
	public String getReaderInstance()
	{
		return readerInstance;
	}

	@Column(name = "sentTime")
	public Long getSentTime()
	{
		return sentTime;
	}

	@Column(name = "subject")
	public String getSubject()
	{
		return subject;
	}

	@Column(name = "uniqueId")
	public String getUniqueId()
	{
		return uniqueId;
	}

	public void setUniqueId(String uniqueId)
	{
		this.uniqueId = uniqueId;
	}

	public void setAttachmentList(Set<DataAttachments> attachmentList)
	{
		this.attachmentList = attachmentList;
	}

	public void setCandidateEmail(String candidateEmail)
	{
		this.candidateEmail = candidateEmail;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setIncomingStatus(EIncomingStatus incomingStatus)
	{
		this.incomingStatus = incomingStatus;
	}

	public void setMedia(EMedia media)
	{
		this.media = media;
	}

	public void setReaderInstance(String readerInstance)
	{
		this.readerInstance = readerInstance;
	}

	public void setSentTime(Long sentTime)
	{
		this.sentTime = sentTime;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public void setIncomingId(String incomingId)
	{
		this.incomingId = incomingId;
	}

}
