package org.hbs.core.beans;

import java.io.Serializable;

public class ViewCalendarBean implements Serializable
{

	/**
	 *
	 */
	private static final long	serialVersionUID	= -9012416378861313674L;

	private String				id;
	private String				start;
	private String				end;
	private String				title;
	private String				color;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getStart()
	{
		return start;
	}

	public void setStart(String start)
	{
		this.start = start;
	}

	public String getEnd()
	{
		return end;
	}

	public void setEnd(String end)
	{
		this.end = end;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

}
