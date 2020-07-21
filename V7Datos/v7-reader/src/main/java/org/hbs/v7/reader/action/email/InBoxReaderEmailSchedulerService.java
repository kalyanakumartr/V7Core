package org.hbs.v7.reader.action.email;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hbs.core.beans.model.IConfiguration;
import org.hbs.core.beans.model.channel.ConfigurationEmail;
import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.security.resource.IPathBase.EMediaMode;
import org.hbs.core.util.CommonValidator;
import org.hbs.v7.reader.action.core.InBoxReaderScheduler;
import org.hbs.v7.reader.bo.ExtractorBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author AnanthMalBal
 */
@Service
public class InBoxReaderEmailSchedulerService implements InBoxReaderScheduler
{
	private static final long	serialVersionUID	= 1958518665419239648L;

	private boolean				isRunning			= false;

	@Autowired
	ExtractorBo					extractorBo;

	@Value("${channel.email.delay}")
	private String				emailDelay;

	@Override
	@Scheduled(fixedDelayString = "${channel.email.delay}")
	public void scheduleChannel()
	{
		try
		{
			if (!isRunning)
			{
				System.out.println(">>>"+ new Date() +">>>scheduleChannel with milliseconds>>>>>>> " + emailDelay);
				isRunning = true;
				List<IConfiguration> configList = extractorBo.getConfigurationList(EMedia.Email, EMediaMode.External);

				if (CommonValidator.isListFirstNotEmpty(configList))
				{
					ExecutorService executor = Executors.newFixedThreadPool(configList.size());

					for (IConfiguration iConfig : configList)
					{
						executor.execute(new Runnable() {

							@Override
							public void run()
							{
								ConfigurationEmail config = (ConfigurationEmail) iConfig;
								try
								{
									System.out.println("Started By " + config.getFromId() + " at " + new Date());
									InBoxReaderEmailFactory.getInstance().reader(config).readDataFromChannel();
								}
								catch (Exception e)
								{
									e.printStackTrace();
								}
								finally
								{
									System.out.println("Finished By " + config.getFromId() + " at " + new Date());
								}
							}
						});
					}

					executor.shutdown();
					while ( !executor.isTerminated() )
						;
					System.out.println("Finished all threads");
				}
			}
			else
			{
				System.out.println("New Schedule Time Reached, But Earlier Still Running...");
			}
		}
		finally
		{
			isRunning = false;
		}

	}

}
