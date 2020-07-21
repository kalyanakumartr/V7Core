package org.hbs.v7.reader.action.email;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.hbs.core.beans.model.channel.ConfigurationEmail;
import org.hbs.v7.reader.action.core.InBoxReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InBoxReaderEmailFactory implements Serializable
{
	private static final long				serialVersionUID	= 4519947259430658342L;

	private static InBoxReaderEmailFactory	readerFactory		= null;

	@Autowired
	InBoxReaderPop3							$InBoxReaderPop3;

	@Autowired
	InBoxReaderIMAPProducer					$InBoxReaderIMAP;

	Map<String, Long>						lookupMap			= new HashMap<>();

	private InBoxReaderEmailFactory()
	{

	}

	public static InBoxReaderEmailFactory getInstance()
	{
		if (readerFactory == null)
		{
			readerFactory = new InBoxReaderEmailFactory();
		}
		return readerFactory;
	}

	public InBoxReader reader(ConfigurationEmail config)
	{
		switch ( config.getSource() )
		{
			case Gmail_Pop3 :
				$InBoxReaderPop3.config = config;
				return $InBoxReaderPop3;
			case Gmail_IMAP :
			default :
				$InBoxReaderIMAP.config = config;
				return $InBoxReaderIMAP;

		}
	}
	
	public long getLastLookup(ConfigurationEmail config)
	{
		Long lastLookup = this.lookupMap.get(config.getConnectionId());
		
		return (lastLookup == null ? 0L : lastLookup);
	}
	
	public void setLastLookup(ConfigurationEmail config, long lastTime)
	{
		this.lookupMap.put(config.getConnectionId(), lastTime);
	}

}
