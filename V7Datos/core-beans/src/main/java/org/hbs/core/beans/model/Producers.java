
package org.hbs.core.beans.model;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.beans.model.IUsersBase.EUserType;
import org.hbs.core.security.resource.IPath.EAuth;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EBusinessKey;
import org.hbs.core.util.EnumInterface;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.Authentication;

/**
 * @author ananth Here Producers Plays the Role of Customer in Magnet Project
 */
@Entity(name = "Producers")
@Table(name = "producers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "producerType", discriminatorType = DiscriminatorType.STRING)
public class Producers extends CommonDateAndStatusFields implements IProducers, EBusinessKey
{
	private static final long			serialVersionUID		= -5798203939392344587L;

	private String						activeProducerId;
	private String						domainContext;
	private Set<IProducersAttachments>	producerAttachmentList	= new LinkedHashSet<IProducersAttachments>(0);
	private String						producerId;
	private String						producerName;
	private EUserType					producerType			= EUserType.Producer;
	private Set<IProducersProperty>		propertyList			= new LinkedHashSet<IProducersProperty>(0);
	private Timestamp					pwdExpiryDays;
	private IUsers						users;
	private boolean						primary					= true;
	private CreatedModifiedUsers		byUser					= new CreatedModifiedUsers();

	public Producers()
	{
		super();
		this.producerId = getBusinessKey();
	}

	public Producers(String producerId)
	{
		super();
		this.producerId = producerId;
	}

	@Column(name = "primary")
	public boolean isPrimary()
	{
		return primary;
	}

	public void setPrimary(boolean primary)
	{
		this.primary = primary;
	}

	@Column(name = "producerType", insertable = false, updatable = false)
	public EUserType getProducerType()
	{
		return producerType;
	}

	public void setProducerType(EUserType producerType)
	{
		this.producerType = producerType;
	}

	@Transient
	public String getActiveProducerId()
	{
		return activeProducerId == null ? producerId : activeProducerId;
	}

	@Transient
	public IProducersAttachments getAttachment(EnumInterface documentType)
	{
		if (CommonValidator.isSetFirstNotEmpty(producerAttachmentList))
		{
			for (IProducersAttachments attachments : producerAttachmentList)
			{
				if (CommonValidator.isEqual(attachments.getUploadDocumentForType(), documentType))
					return attachments;
			}
		}
		return null;
	}

	@Transient
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	@Column(name = "domainContext")
	public String getDomainContext()
	{
		return domainContext == null ? "" : domainContext;
	}

	@OneToMany(targetEntity = ProducersAttachments.class, fetch = FetchType.LAZY, mappedBy = "producer")
	@Fetch(FetchMode.JOIN)
	public Set<IProducersAttachments> getProducerAttachmentList()
	{
		return producerAttachmentList;
	}

	@Id
	@Column(name = "producerId", nullable = false)
	public String getProducerId()
	{
		return producerId;
	}

	@Column(name = "producerName")
	public String getProducerName()
	{
		return producerName;
	}

	@Transient
	public IProducersProperty getProperty(EnumInterface media, String key)
	{
		List<IProducersProperty> iPPList = propertyList.stream().filter(p -> (p.getMedia() == media && p.getProperty().equals(key))).collect(Collectors.toList());

		if (CommonValidator.isListFirstNotEmpty(iPPList))
			return iPPList.iterator().next();
		return null;
	}

	@Transient
	public List<IProducersProperty> getProperty(EnumInterface media)
	{
		return propertyList.stream().filter(p -> (p.getMedia() == media)).collect(Collectors.toList());
	}

	@OneToMany(targetEntity = ProducersProperty.class, fetch = FetchType.LAZY, mappedBy = "producer")
	@Fetch(FetchMode.JOIN)
	public Set<IProducersProperty> getPropertyList()
	{
		return propertyList;
	}

	@Transient
	public List<IProducersProperty> getPropertyList(EnumInterface media)
	{
		return propertyList.stream().filter(p -> p.getMedia() == media).collect(Collectors.toList());
	}

	@Column(name = "pwdExpiryDays")
	public Timestamp getPwdExpiryDays()
	{
		return pwdExpiryDays;
	}

	@ManyToOne(targetEntity = ProducerUsers.class)
	@JoinColumn(name = "employeeId", nullable = false)
	public IUsers getUsers()
	{
		return users;
	}

	public void setActiveProducerId(String activeProducerId)
	{
		this.activeProducerId = activeProducerId;
	}

	public void setDomainContext(String domainContext)
	{
		this.domainContext = domainContext;
	}

	public void setProducerAttachmentList(Set<IProducersAttachments> producerAttachmentList)
	{
		this.producerAttachmentList = producerAttachmentList;
	}

	public void setProducerId(String producerId)
	{
		this.producerId = producerId;
	}

	public void setProducerName(String producerName)
	{
		this.producerName = producerName;
	}

	public void setPropertyList(Set<IProducersProperty> propertyList)
	{
		this.propertyList = propertyList;
	}

	public void setPwdExpiryDays(Timestamp pwdExpiryDays)
	{
		this.pwdExpiryDays = pwdExpiryDays;
	}

	public void setUsers(IUsers users)
	{
		this.users = users;
	}

	@Embedded
	public CreatedModifiedUsers getByUser()
	{
		return byUser;
	}

	public void setByUser(CreatedModifiedUsers byUser)
	{
		this.byUser = byUser;
	}

	public void createdUserProducerInfo(Authentication auth)
	{
		this.byUser.createdUser = EAuth.User.getUser(auth);
		this.createdDate = new Timestamp(System.currentTimeMillis());
		this.byUser.modifiedUser = EAuth.User.getUser(auth);
		this.modifiedDate = new Timestamp(System.currentTimeMillis());
	}

	public void modifiedUserInfo(Authentication auth)
	{
		this.byUser.modifiedUser = EAuth.User.getUser(auth);
		this.modifiedDate = new Timestamp(System.currentTimeMillis());
	}

}
