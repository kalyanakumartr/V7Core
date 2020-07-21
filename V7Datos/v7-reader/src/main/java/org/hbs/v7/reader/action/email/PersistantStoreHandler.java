package org.hbs.v7.reader.action.email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.mail.MessagingException;
import javax.mail.Store;

import org.hbs.core.util.CommonValidator;
import org.hbs.v7.reader.action.email.InboxReaderBase.EFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistantStoreHandler implements Serializable
{

	private static final long				serialVersionUID	= 3799251398652568882L;
	private final Logger					logger				= LoggerFactory.getLogger(PersistantStoreHandler.class);
	private static PersistantStoreHandler	connectionHandler	= null;

	Map<String, Store>						connectionMap		= null;
	Map<String, ArrayList<String>>			isStoreUsingByOther	= null;

	public static PersistantStoreHandler getInstance()
	{
		if (connectionHandler == null)
		{
			connectionHandler = new PersistantStoreHandler();
		}
		return connectionHandler;
	}

	private PersistantStoreHandler()
	{
		connectionMap = new ConcurrentHashMap<String, Store>();
		isStoreUsingByOther = new ConcurrentHashMap<String, ArrayList<String>>();

	}

	private String key(InboxReaderBase reader)
	{
		return reader.getConfig().getProducerId() + reader.getConfig().getFromId();
	}

	public void removeStore(InboxReaderBase reader)
	{
		try
		{
			synchronized ( connectionMap )
			{
				Store store = getStore(reader);

				ArrayList<String> activeQueue = isStoreUsingByOther.get(key(reader));

				if (store != null && activeQueue != null && activeQueue.contains(reader.getClass().getSimpleName()))
				{
					activeQueue.remove(reader.getClass().getSimpleName());
					if (activeQueue.isEmpty())
					{
						isStoreUsingByOther.remove(key(reader));
						if (store.getFolder(EFolder.Inbox.name()).isOpen())
							store.getFolder(EFolder.Inbox.name()).close();

						store.close();
						connectionMap.remove(key(reader));
					}
					else
						isStoreUsingByOther.put(key(reader), activeQueue);
				}
				else
				{
					connectionMap.clear();
					isStoreUsingByOther.clear();
				}
			}
		}
		catch (MessagingException excep)
		{
			logger.info("Message Exception ");
			excep.printStackTrace();
		}
	}

	public Store getStore(InboxReaderBase reader)
	{
		Store store = connectionMap.get(key(reader));

		if (store != null)
		{
			ArrayList<String> activeQueue = isStoreUsingByOther.get(key(reader));
			if (CommonValidator.isListFirstNotEmpty(activeQueue) && activeQueue.contains(reader.getClass().getSimpleName()) == false)
			{
				activeQueue.add(reader.getClass().getSimpleName());
				isStoreUsingByOther.put(key(reader), activeQueue);
			}
		}
		// System.out.println(">>>>>Get Store 1 >>>>>>connectionMap>>>>>>>>>>>> " + connectionMap);
		// System.out.println(">>>>>Get Store 1 >>>>>>isStoreUsingByOther>>>>>> " +
		// isStoreUsingByOther);
		return store;
	}

	public Store getStore(InboxReaderBase reader, Store store)
	{
		synchronized ( store )
		{
			ArrayList<String> activeQueue = new ArrayList<>();
			activeQueue.add(reader.getClass().getSimpleName());
			isStoreUsingByOther.put(key(reader), activeQueue);
			connectionMap.put(key(reader), store);
			return getStore(reader);
		}
	}
}
