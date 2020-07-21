package org.hbs.v7.reader.action.email;

import javax.mail.Authenticator;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.UIDFolder;

import org.hbs.core.beans.model.channel.ConfigurationEmail;
import org.hbs.core.kafka.GenericKafkaProducer;
import org.hbs.core.kafka.IKafkaConstants.ETopic;
import org.hbs.core.kafka.KAFKAPartition;
import org.hbs.core.util.CommonValidator;
import org.hbs.v7.beans.InBoxReaderTopicBean;
import org.hbs.v7.beans.model.EMessagePriority;
import org.hbs.v7.beans.model.PartitionFinder;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

public abstract class InBoxReaderBaseImpl implements InboxReaderBase
{
	private static final long	serialVersionUID	= 2428143934833300387L;

	@Autowired
	GenericKafkaProducer		gKafkaProducer;

	ConfigurationEmail			config;

	public InBoxReaderBaseImpl()
	{
		super();
	}

	protected Store authenticateMailAndConnect() throws NoSuchProviderException, MessagingException
	{
		Store store = PersistantStoreHandler.getInstance().getStore(this);
		if (store != null && store.isConnected())
		{
			return store;
		}
		else
		{
			/* Get the Session object for specific Mail Property. */
			Session session = Session.getInstance(config.getSource().getProperties(), new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(config.getUserName().trim(), config.getPassword());
				}
			});
			/* Get a Store object that implements the specified protocol. */
			store = session.getStore(config.getProtocol().trim());
			/*
			 * Connect to the current host using the specified Email id and Password.
			 */
			store.connect();
			Folder[] folderArr = store.getDefaultFolder().list();
			for (Folder folder : folderArr)
			{
				System.out.println(config.getFromId().trim() + " Inbox Connected :: Unread Message Count :: " + folder + " ---- " + folder.getUnreadMessageCount());
				if (CommonValidator.isEqual(folder.getName(), EFolder.Inbox))
				{
					break;
				}
			}
			return PersistantStoreHandler.getInstance().getStore(this, store);
		}
	}

	protected boolean pushToQueue(String producerId, UIDFolder _UIDFolder, Message... messages)
	{
		long sentDate = 0l;
		try
		{

			for (Message message : messages)
			{
				try
				{

					InBoxReaderTopicBean inBoxReaderBean = new InBoxReaderTopicBean(message.getMessageNumber(), message.getSentDate(), config);

					System.out.println(">>> " + new Gson().toJson(inBoxReaderBean));

					KAFKAPartition partition = PartitionFinder.getInstance().find(ETopic.Message, EMessagePriority.getPriority(message));

					gKafkaProducer.send(ETopic.Message, partition, inBoxReaderBean);

					message.setFlag(Flag.SEEN, true);

					sentDate = message.getSentDate().getTime();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}

			}
			return true;
		}
		finally
		{
			InBoxReaderEmailFactory.getInstance().setLastLookup(config, sentDate);
		}
	}

	public ConfigurationEmail getConfig()
	{
		return config;
	}

}