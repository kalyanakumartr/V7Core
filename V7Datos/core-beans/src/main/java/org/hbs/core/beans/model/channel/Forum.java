package org.hbs.core.beans.model.channel;

import java.sql.Timestamp;
import java.util.List;

import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ICRUDBean;

class Discussion implements ICRUDBean
{
	public enum EMessageMode implements EnumInterface
	{
		Reply, Send;
	}

	private static final long	serialVersionUID	= -2067527172805348928L;

	public String				createdBy;
	public String				createdByName;
	public Timestamp			createdDate;
	public String				message;
	public EMessageMode			mode				= EMessageMode.Send;

	public String getCreatedBy()
	{
		return createdBy;
	}

	public String getCreatedByName()
	{
		return createdByName;
	}

	public Timestamp getCreatedDate()
	{
		return createdDate;
	}

	public String getMessage()
	{
		return message;
	}

	public EMessageMode getMode()
	{
		return mode;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public void setCreatedByName(String createdByName)
	{
		this.createdByName = createdByName;
	}

	public void setCreatedDate(Timestamp createdDate)
	{
		this.createdDate = createdDate;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public void setMode(EMessageMode mode)
	{
		this.mode = mode;
	}

}

public class Forum implements ICRUDBean
{

	private static final long	serialVersionUID	= 3040119580020967492L;

	private List<Discussion>	discussions;

	public List<Discussion> getDiscussions()
	{
		return discussions;
	}

	public void setDiscussions(Discussion... discussions)
	{
		for (Discussion discussion : discussions)
			this.discussions.add(discussion);
	}
}