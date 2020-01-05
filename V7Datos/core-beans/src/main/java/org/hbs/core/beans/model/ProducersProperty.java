package org.hbs.core.beans.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.security.resource.IPath.EAuth;
import org.hbs.core.security.resource.IPath.EMedia;
import org.hbs.core.security.resource.IPath.EMediaMode;
import org.hbs.core.security.resource.IPath.EMediaType;
import org.hbs.core.util.EBusinessKey;
import org.springframework.security.core.Authentication;

import com.google.gson.Gson;

@Entity(name = "ProducersProperty")
@Table(name = "producersproperty")
public class ProducersProperty extends ProducersBase implements IProducersProperty, EBusinessKey
{

	private static final long		serialVersionUID	= -4704236838326830638L;
	private String					autoId;
	private CreatedModifiedUsers	byUser				= new CreatedModifiedUsers();
	private String					comments;
	private String					enumKey;
	private String					groupName;
	private EMedia					media;												// Channel
	private EMediaMode				mediaMode;
	private EMediaType				mediaType			= EMediaType.Primary;
	private String					property;
	private boolean					status				= true;
	private String					value;

	public ProducersProperty()
	{
		super();
		this.autoId = getBusinessKey();
	}

	public ProducersProperty(String autoId, EMedia media, String enumKey, String groupName, String comments, String property, boolean status, String value)
	{
		super();
		this.autoId = autoId;
		this.media = media;
		this.groupName = groupName;
		this.enumKey = enumKey;
		this.comments = comments;
		this.property = property;
		this.status = status;
		this.value = value;
	}

	public void createdUserProducerInfo(Authentication auth)
	{
		this.byUser.createdUser = EAuth.User.getUser(auth);
		this.createdDate = new Timestamp(System.currentTimeMillis());
		this.byUser.modifiedUser = EAuth.User.getUser(auth);
		this.modifiedDate = new Timestamp(System.currentTimeMillis());
		this.producer = EAuth.User.getProducer(auth);
	}

	@Id
	@Column(name = "professionalId")
	public String getAutoId()
	{
		return autoId;
	}

	@Override
	@Transient
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	@Embedded
	public CreatedModifiedUsers getByUser()
	{
		return byUser;
	}

	@Column(name = "comments")
	public String getComments()
	{
		return comments;
	}

	@Column(name = "enumKey")
	public String getEnumKey()
	{
		return enumKey;
	}

	@Column(name = "groupName")
	public String getGroupName()
	{
		return groupName;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "media")
	public EMedia getMedia()
	{
		return media;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "mediaMode")
	public EMediaMode getMediaMode()
	{
		return mediaMode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "mediaType")
	public EMediaType getMediaType()
	{
		return mediaType;
	}

	@Column(name = "property")
	public String getProperty()
	{
		if (property == null)
			return property;
		return property.trim();
	}

	@SuppressWarnings("unchecked")
	@Transient
	public IConfiguration getPropertyAsConfiguration() throws ClassNotFoundException
	{
		Class<IConfiguration> clazz = (Class<IConfiguration>) Class.forName(property);
		return new Gson().fromJson(value, clazz);
	}

	@Column(name = "value")
	public String getValue()
	{
		if (value == null)
			return value;
		return value.trim();
	}

	@Column(name = "status")
	public boolean isStatus()
	{
		return status;
	}

	public void modifiedUserInfo(Authentication auth)
	{
		this.byUser.modifiedUser = EAuth.User.getUser(auth);
		this.modifiedDate = new Timestamp(System.currentTimeMillis());
	}

	public void setAutoId(String autoId)
	{
		this.autoId = autoId;
	}

	public void setByUser(CreatedModifiedUsers byUser)
	{
		this.byUser = byUser;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public void setEnumKey(String enumKey)
	{
		this.enumKey = enumKey;
	}

	public void setGroupName(String name)
	{
		this.groupName = name;
	}

	public void setMedia(EMedia media)
	{
		this.media = media;
	}

	public void setMediaMode(EMediaMode mediaMode)
	{
		this.mediaMode = mediaMode;
	}

	public void setMediaType(EMediaType mediaType)
	{
		this.mediaType = mediaType;
	}

	public void setProperty(String property)
	{
		this.property = property;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}
