package org.hbs.v7.reader.action.email;

import java.util.Date;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.CustomException;
import org.hbs.v7.reader.action.core.InBoxReader;
import org.hbs.v7.reader.bo.ExtractorBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.mail.imap.IMAPFolder;

@Service
public class InBoxReaderIMAPProducer extends InBoxReaderIMAPBase implements InBoxReader
{

	private static final long	serialVersionUID	= 2521396768665593925L;

	@Autowired
	ExtractorBo					extractorBo;

	private final Logger		logger				= LoggerFactory.getLogger(InBoxReaderIMAPProducer.class);

	@SuppressWarnings("serial")
	private SearchTerm createBaseSearchTerm()
	{
		SearchTerm searchTerm = new SearchTerm() {

			@Override
			public boolean match(Message message)
			{
				try
				{
					return (message.isMimeType(MULTIPART_MIXED) || //
					message.isMimeType(MULTIPART_ALTERNATIVE) || //
					message.isMimeType(MULTIPART_RELATED) || //
					message.isMimeType(MULTIPART_SIGNED) || //
					message.isMimeType(MULTIPART_ENCRYPTED));
				}
				catch (MessagingException e)
				{
					e.printStackTrace();
					return false;
				}
			}
		};
		return searchTerm;
	}

	private boolean pushToQueue(String producerId, IMAPFolder imapFolder, SearchTerm searchTerm, ReceivedDateTerm minDateTerm, ReceivedDateTerm maxDateTerm) throws MessagingException
	{
		searchTerm = new AndTerm(searchTerm, minDateTerm);
		searchTerm = new AndTerm(searchTerm, maxDateTerm);
		searchTerm = new AndTerm(searchTerm, new FlagTerm(new Flags(Flags.Flag.SEEN), false)); // Read-Only-UnRead-Messages
		Message[] messages = (Message[]) imapFolder.search(searchTerm);
		if (messages.length > 0)
			return pushToQueue(producerId, imapFolder, messages);
		else
		return false;
	}

	@Override
	public void readDataFromChannel() throws CustomException
	{

		if (CommonValidator.isNotNullNotEmpty(config))
		{
			try
			{
				IMAPFolder imapFolder = getIMAPFolder(EFolder.Inbox);

				Date initDateTime = null, startTime = null, endTime = new Date();

				SearchTerm searchTerm = createBaseSearchTerm();
				ReceivedDateTerm minDateTerm, maxDateTerm = null;

				//Lookup Initiates Here
				long lastDateTime = extractorBo.getLastEmailSentDate(config);
				startTime = initDateTime = (lastDateTime == 0) ? new Date() : new Date(lastDateTime);
				
				if (config.reverseStart)
				{
					System.out.println(">>>>>>>>>>>>>>>>>>>>>Reverse Start Started<<<<<<<<<<<<<<<<<<<<<<<<<<<");

					// Trying Backwards and loading latest first

					while ( config.startDate.isReached(initDateTime, endTime) )
					{
						maxDateTerm = new ReceivedDateTerm(ComparisonTerm.LE, startTime); // Going_Backward...

						endTime = config.readEvery.getReversedDate(startTime); //
						// Reverse_Going_By_5_minutes_Default

						minDateTerm = new ReceivedDateTerm(ComparisonTerm.GE, endTime);

						pushToQueue(config.getProducerId(), imapFolder, searchTerm, minDateTerm, maxDateTerm);

						startTime = endTime;
					}

					config.reverseStart = false;
					extractorBo.updateProducerProperty(config);
					System.out.println(">>>>>>>>>>>>>>>>>>>>>Reverse Start Completed<<<<<<<<<<<<<<<<<<<<<<<<<<");
				}
				else
				{
					System.out.println(">>>>>>>>>>>>>>>>>>>>>Forward Start Started<<<<<<<<<<<<<<<<<<<<<<<<<<<");
					// Get the Last Received Mail Date from DB
					// Start with 5 Minutes earlier
					startTime = (lastDateTime == 0) ? new Date(System.currentTimeMillis() - config.readEvery.getDateTime()) : new Date(lastDateTime);
					endTime = new Date();
					System.out.println(">>>>>>>>>>>>>>>>>>>>>startTime<<<<<<<<<<<<<<<<<<<<<<<<<<< " + startTime);
					
					boolean hasPushed = false;
					do
					{
						minDateTerm = new ReceivedDateTerm(ComparisonTerm.GT, startTime);

						maxDateTerm = new ReceivedDateTerm(ComparisonTerm.LE, endTime);

						hasPushed = pushToQueue(config.getProducerId(), imapFolder, searchTerm, minDateTerm, maxDateTerm);

						startTime = config.readEvery.getForwardDate(startTime); // Forward_Going_By_5_minutes_Default

					}
					while ( startTime.getTime() < endTime.getTime() );
					
					if(!hasPushed)
						InBoxReaderEmailFactory.getInstance().setLastLookup(config, endTime.getTime());

					System.out.println(">>>>>>>>>>>>>>>>>>>>>Forward Start Completed<<<<<<<<<<<<<<<<<<<<<<<<<<");
				}
			}
			catch (MessagingException | ClassNotFoundException excep)
			{
				excep.printStackTrace();
			}
			finally
			{
				try
				{
					Thread.sleep(10000);
				}
				catch (InterruptedException ie)
				{
				}
				PersistantStoreHandler.getInstance().removeStore(this);
			}
		}
		else
			throw new CustomException("Configuration NOT available");
	}

}
