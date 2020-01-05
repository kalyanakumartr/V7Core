package org.hbs.v7.extractor.action.core;

public class DataExtractor extends DataExtractorMapHolder
{
	private static final long		serialVersionUID	= 2522630191746946677L;
	private static DataExtractor	dataExtractor		= null;

	private DataExtractor()
	{
	}

	public static DataExtractor getInstance(String producerId)
	{
		if (dataExtractor == null)
		{
			dataExtractor = new DataExtractor();
			dataExtractor.setProducerId(producerId);
		}
		return dataExtractor;
	}

}