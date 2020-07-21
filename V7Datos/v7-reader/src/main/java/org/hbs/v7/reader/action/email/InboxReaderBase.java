package org.hbs.v7.reader.action.email;

import java.io.Serializable;

import org.hbs.core.beans.model.channel.ConfigurationEmail;
import org.hbs.core.util.EnumInterface;

public interface InboxReaderBase extends Serializable
{
	public enum EFolder implements EnumInterface
	{
		Inbox, Processed;
	}

	public ConfigurationEmail getConfig();
}