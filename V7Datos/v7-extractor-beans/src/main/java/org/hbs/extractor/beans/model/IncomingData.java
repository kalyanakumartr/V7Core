package org.hbs.extractor.beans.model;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.beans.model.IProducers;
import org.hbs.core.beans.model.ProducersProperty;
import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.util.EBusinessKey;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ICRUDBean;
import org.hbs.extractor.beans.model.resume.CustomerProducer;

@Entity
@Table(name = "resume_incoming_data")
public class IncomingData implements ICRUDBean, EBusinessKey
{

	public enum EIncomingStatus implements EnumInterface
	{
		New, Ready, InProcess, Completed, AttachmentReadError, InvalidAttachment, UnRecognizedError, Timeout
	}

	private static final long		serialVersionUID	= 8524114488133328911L;

	protected String				incomingId;
	protected String				_URN;
	protected EMedia				media				= EMedia.Email;
	protected String				candidateEmail;
	protected String				subject				= "";
	protected String				description			= "";
	protected Long					sentTime;
	protected String				uniqueId;
	protected String				readerInstance;
	protected EIncomingStatus		incomingStatus		= EIncomingStatus.New;
	protected Set<DataAttachments>	attachmentList		= new LinkedHashSet<DataAttachments>();
	protected IProducers			producer;
	protected ProducersProperty		producerProperty;
	protected Timestamp				createdDate;

	public IncomingData()
	{
		super();
		this.incomingId = getBusinessKey();
	}

	@OneToMany(targetEntity = DataAttachments.class, fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "incomingData")
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

	@ManyToOne(targetEntity = CustomerProducer.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "producerId")
	public IProducers getProducer()
	{
		return producer;
	}

	@ManyToOne(targetEntity = ProducersProperty.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "propertyId")
	public ProducersProperty getProducerProperty()
	{
		return producerProperty;
	}

	@Column(name = "createdDate")
	public Timestamp getCreatedDate()
	{
		return createdDate;
	}

	public void setProducerProperty(ProducersProperty producerProperty)
	{
		this.producerProperty = producerProperty;
	}

	public void setProducer(IProducers producer)
	{
		this.producer = producer;
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

	@Column(name = "_URN")
	public String get_URN()
	{
		return _URN;
	}

	public void set_URN(String _URN)
	{
		this._URN = _URN;
	}

	public void setCreatedDate(Timestamp createdDate)
	{
		this.createdDate = createdDate;
	}
}
