package org.hbs.v7.extractor.action.email;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.mail.Store;

public class EmailConnectionHandler implements Serializable
{

	private static final long				serialVersionUID	= 3799251398652568882L;
	private static EmailConnectionHandler	connectionHandler	= null;

	Map<String, Store>						connectionMap		= new LinkedHashMap<String, Store>();

	public static EmailConnectionHandler getInstance()
	{
		if (connectionHandler == null)
		{
			connectionHandler = new EmailConnectionHandler();
		}
		return connectionHandler;
	}

	private EmailConnectionHandler()
	{
	}

	public Store getStore(String connectionKey)
	{
		return connectionMap.get(connectionKey);
	}

	public void putStore(String connectionKey, Store store)
	{
		connectionMap.put(connectionKey, store);
	}
}
