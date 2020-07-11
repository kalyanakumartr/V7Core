package org.hbs.extractor.beans;

public class DataInTopicBean extends AttachmentInfoBean
{
	private static final long	serialVersionUID	= 3504792085335973094L;
	private long				attachmentAutoId	= 0L;
	private boolean				external			= false;				// Will_Be_Used_If_We_Expose_DataExtraction_As_Service

	public DataInTopicBean()
	{
		super();
	}

	public long getAttachmentAutoId()
	{
		return attachmentAutoId;
	}

	public void setAttachmentAutoId(long attachmentAutoId)
	{
		this.attachmentAutoId = attachmentAutoId;
	}

	public boolean isExternal()
	{
		return external;
	}

	public void setExternal(boolean external)
	{
		this.external = external;
	}

}
