package org.hbs.core.beans.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CommonFileUpload extends CommonFileUploadBase implements ICommonFileUpload
{

	private static final long	serialVersionUID	= 3565525018590664829L;
	protected long				autoId				= 0;

	public CommonFileUpload()
	{
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "autoId")
	public long getAutoId()
	{
		return autoId;
	}

	public void setAutoId(long autoId)
	{
		this.autoId = autoId;
	}

}