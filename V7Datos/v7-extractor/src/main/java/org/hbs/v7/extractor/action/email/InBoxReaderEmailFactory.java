package org.hbs.v7.extractor.action.email;

import java.io.Serializable;

import org.hbs.core.beans.model.channel.ConfigurationEmail;
import org.hbs.v7.extractor.action.core.InBoxReader;
import org.springframework.stereotype.Component;

@Component
public class InBoxReaderEmailFactory implements Serializable
{
	private static final long				serialVersionUID	= 4519947259430658342L;

	private static InBoxReaderEmailFactory	readerFactory		= null;

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
				return new InBoxReaderPop3();
			case Gmail_IMAP :
			default :
				return new InBoxReaderIMAP();

		}
	}

}
