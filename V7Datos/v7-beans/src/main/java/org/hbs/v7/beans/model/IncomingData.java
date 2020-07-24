package org.hbs.v7.beans.model;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
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
import org.hbs.core.util.IConstProperty;
import org.hbs.v7.beans.model.resume.CustomerProducer;

@Entity
@Table(name = "resume_incoming_data")
public class IncomingData implements ICRUDBean, EBusinessKey, IConstProperty
{
	public enum EExtension implements EnumInterface
	{
		// RAR we are not supporting due to 3rd party license
		Invalid, Zip, _7z, Doc, Docx, ODT, XLS, XLSX, PDF, HTML, HTM, Json, Csv;

		public static EExtension isValid(String fileName)
		{
			if (fileName.indexOf(DOT) > 0)
			{
				for (EExtension EE : EExtension.values())
				{
					if (EE.name().toLowerCase().endsWith(fileName.substring(fileName.lastIndexOf(DOT) + 1).toLowerCase()))
						return EE;
				}
			}
			return EExtension.Invalid;
		}

		public static String format()
		{
			return EWrap.Brace.enclose((Object[]) EExtension.values());
		}
	}

	public enum EIncomingStatus implements EnumInterface
	{
		New, Ready, InProcess, Completed, AttachmentReadError, InvalidAttachment, UnRecognizedError, Timeout
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
	protected IProducers			producer;
	protected ProducersProperty		producerProperty;
	protected Timestamp				createdDate;
	protected boolean				bulkAttachment		= false;
	protected EMessagePriority		priority			= EMessagePriority.Normal;

	public IncomingData()
	{
		super();
	}

	public IncomingData(Message message) throws MessagingException
	{
		super();
		this.incomingId = getBusinessKey();
		this.priority = EMessagePriority.getPriority(message);
	}

	public IncomingData(String incomingId, EMedia media, String candidateEmail, String subject, String description, Long sentTime, String uniqueId, String readerInstance,
			EIncomingStatus incomingStatus, Set<DataAttachments> attachmentList, IProducers producer, ProducersProperty producerProperty, Timestamp createdDate, boolean bulkAttachment,
			EMessagePriority priority)
	{
		super();
		this.incomingId = incomingId;
		this.media = media;
		this.candidateEmail = candidateEmail;
		this.subject = subject;
		this.description = description;
		this.sentTime = sentTime;
		this.uniqueId = uniqueId;
		this.readerInstance = readerInstance;
		this.incomingStatus = incomingStatus;
		this.attachmentList = attachmentList;
		this.producer = producer;
		this.producerProperty = producerProperty;
		this.createdDate = createdDate;
		this.bulkAttachment = bulkAttachment;
		this.priority = priority;
	}

	@Transient
	public EMessagePriority getPriority()
	{
		return priority;
	}

	public void setPriority(EMessagePriority priority)
	{
		this.priority = priority;
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

	@Column(name = "bulkAttachment")
	public boolean isBulkAttachment()
	{
		return bulkAttachment;
	}

	public void setBulkAttachment(boolean bulkAttachment)
	{
		this.bulkAttachment = bulkAttachment;
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

	public void setCreatedDate(Timestamp createdDate)
	{
		this.createdDate = createdDate;
	}
}
