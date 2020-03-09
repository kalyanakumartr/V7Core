package org.hbs.core.beans.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hbs.core.security.resource.IPathBase.EMediaType;

@MappedSuperclass
public abstract class CommunicationMedia extends CommunicationMediaBase implements ICommunicationMedia
{
	private static final long	serialVersionUID	= 2628421462831892696L;
	protected String			mediaId;
	protected EMediaType		mediaType			= EMediaType.Primary;

	public CommunicationMedia()
	{
		super();
		this.mediaId = getBusinessKey();
	}

	@Id
	@Column(name = "mediaId")
	public String getMediaId()
	{
		return mediaId;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "mediaType")
	public EMediaType getMediaType()
	{
		return mediaType;
	}

	public void setMediaId(String mediaId)
	{
		this.mediaId = mediaId;
	}

	public void setMediaType(EMediaType mediaType)
	{
		this.mediaType = mediaType;
	}

	@Override
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}
}