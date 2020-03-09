package org.hbs.v7.extractor.action.email;

import java.util.Date;

import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import org.hbs.core.beans.GenericKafkaProducer;
import org.hbs.core.beans.model.IConfiguration;
import org.hbs.core.beans.model.channel.ConfigurationEmail;
import org.hbs.core.util.CommonValidator;
import org.hbs.v7.extractor.extractor.bo.ExtractorBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sun.mail.imap.IMAPFolder;


@Component
public class InBoxReaderIMAP extends InBoxReaderBase
{

	private static final long	serialVersionUID	= 2521396768665593925L;
	private final Logger		logger				=  LoggerFactory.getLogger(InBoxReaderIMAP.class);

	@Override
	public void readDataFromChannel(IConfiguration iConfig, GenericKafkaProducer gKafkaProducer, ExtractorBo extractorBo)
	{
		
		logger.info("InSide IMAP");
		if (CommonValidator.isNotNullNotEmpty(iConfig))
		{
			ConfigurationEmail config = (ConfigurationEmail) iConfig;
			this.gKafkaProducer=gKafkaProducer;
			Store store = null;
			IMAPFolder imapFolder = null;
			try
			{
				store = authenticateMailAndConnect(config);

				imapFolder = (IMAPFolder) store.getFolder("inbox");

				// Open the Folder.
				if (!imapFolder.isOpen())
					imapFolder.open(Folder.READ_ONLY);

				Date initDateTime = null, startTime = null, endTime = null;

				SearchTerm searchTerm = createBaseSearchTerm();
				ReceivedDateTerm minDateTerm, maxDateTerm = null;
				
				endTime = new Date();
				long lastDateTime =  extractorBo.getLastEmailSentDate(config.getProducerId());
				startTime =initDateTime = (lastDateTime == 0) ? imapFolder.getMessage(1).getReceivedDate() : new Date(lastDateTime);
				if (config.reverseStart) {
					maxDateTerm = new ReceivedDateTerm(ComparisonTerm.LE, endTime);
					minDateTerm = new ReceivedDateTerm(ComparisonTerm.GE, startTime);
					pushToQueue(config.getProducerId(), imapFolder, searchTerm, minDateTerm, maxDateTerm);
				}
				else if (config.reverseStart)
				{
					
					while ( config.startDate.isReached(initDateTime, endTime) )
					{
						maxDateTerm = new ReceivedDateTerm(ComparisonTerm.LE, startTime); // Going_Backward...

						//endTime = config.readEvery.getReversedDate(startTime); // Reverse_Going_By_5_minutes_Default

						minDateTerm = new ReceivedDateTerm(ComparisonTerm.GE, endTime);

 						pushToQueue(config.getProducerId(), imapFolder, searchTerm, minDateTerm, maxDateTerm);

						//startTime = endTime;
					}
				}
				else
				{
					// Get the Last Received Mail Date from DB
					 lastDateTime = 0l;// extractorBo.getLastEmailSentDate(config.getFromId());
					startTime = (lastDateTime == 0) ? new Date(System.currentTimeMillis() - config.readEvery.getDateTime()) : new Date(lastDateTime);
					endTime = new Date();

					do
					{
						minDateTerm = new ReceivedDateTerm(ComparisonTerm.GT, startTime);

						maxDateTerm = new ReceivedDateTerm(ComparisonTerm.LE, endTime);

						startTime = config.readEvery.getForwardDate(startTime); // Forward_Going_By_5_minutes_Default

						pushToQueue(config.getProducerId(), imapFolder, searchTerm, minDateTerm, maxDateTerm);

					}
					while ( startTime.getTime() < endTime.getTime() );
				}
			}
			catch (FolderClosedException excep)
			{
				logger.info("Folder Closed ");
				excep.printStackTrace();
			}
			catch (MessagingException excep)
			{
				logger.info("Messaging Exception ");
				excep.printStackTrace();
			}
			finally
			{
				try
				{
					try
					{
						Thread.sleep(100000);
					}
					catch (InterruptedException ie)
					{
					}
					if (imapFolder != null && imapFolder.isOpen())
					{
						imapFolder.close(true);
					}
					if (store != null)
					{
						store.close();
					}
				}
				catch (MessagingException e)
				{
					logger.info("Message Exception ");
					e.printStackTrace();
				}
			}

		}
	}

	private void pushToQueue(String producerId, IMAPFolder imapFolder, SearchTerm searchTerm, ReceivedDateTerm minDateTerm, ReceivedDateTerm maxDateTerm) throws MessagingException
	{
		searchTerm = new AndTerm(searchTerm, minDateTerm);
		searchTerm = new AndTerm(searchTerm, maxDateTerm);
 
		pushToQueue(producerId, imapFolder, (Message[]) imapFolder.search(searchTerm));
	}

	@SuppressWarnings("serial")
	private SearchTerm createBaseSearchTerm()
	{
		SearchTerm searchTerm = new SearchTerm() {

			@Override
			public boolean match(Message message)
			{
				try
				{
					return message.isMimeType("multipart/mixed") || message.isMimeType("multipart/alternative") || message.isMimeType("multipart/related") || message.isMimeType("multipart/signed")
							|| message.isMimeType("multipart/encrypted");
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

}
