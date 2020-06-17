package org.hbs.v7.extractor.action.email;

import org.hbs.core.beans.GenericKafkaProducer;
import org.hbs.core.beans.model.IConfiguration;
import org.hbs.v7.extractor.extractor.bo.ExtractorBo;

public class InBoxReaderPop3 extends InBoxReaderBase
{

	private static final long serialVersionUID = 4813150068221933347L;

	@Override
	public void readDataFromChannel(IConfiguration config, GenericKafkaProducer gKafkaProducer, ExtractorBo extractorBo)
	{

	}

}
