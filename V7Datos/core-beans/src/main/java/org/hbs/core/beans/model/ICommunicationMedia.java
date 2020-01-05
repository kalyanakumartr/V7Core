package org.hbs.core.beans.model;

import org.hbs.core.security.resource.IPath.EMediaType;
import org.hbs.core.util.EBusinessKey;

public interface ICommunicationMedia extends EBusinessKey, ICommunicationMediaBase
{

	String getMediaId();

	EMediaType getMediaType();

	void setMediaId(String mediaId);

	void setMediaType(EMediaType mediaType);

}