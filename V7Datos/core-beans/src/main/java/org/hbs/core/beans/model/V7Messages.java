package org.hbs.core.beans.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hbs.core.security.resource.IPath.ETemplate;

@Entity
@Table(name = "messages")
public class V7Messages extends V7MessagesBase
{

	private static final long serialVersionUID = 684883062828693721L;

	public V7Messages()
	{
		super();
	}

	public V7Messages(ETemplate eTemplate)
	{
		this.messageId = eTemplate.name();
	}

}