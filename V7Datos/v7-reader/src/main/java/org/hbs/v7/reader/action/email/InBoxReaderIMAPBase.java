package org.hbs.v7.reader.action.email;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;

import com.sun.mail.imap.IMAPFolder;

public abstract class InBoxReaderIMAPBase extends InBoxReaderBaseImpl
{

	private static final long serialVersionUID = 808765927984389081L;

	public InBoxReaderIMAPBase()
	{
		super();
	}

	protected IMAPFolder getIMAPFolder(EFolder eFolder) throws NoSuchProviderException, MessagingException
	{
		IMAPFolder imapFolder = null;
		Store store = authenticateMailAndConnect();
		imapFolder = (IMAPFolder) store.getFolder(eFolder.name());

		// Open the Folder.
		if (!imapFolder.isOpen())
			imapFolder.open(Folder.READ_WRITE);
		return imapFolder;
	}
}