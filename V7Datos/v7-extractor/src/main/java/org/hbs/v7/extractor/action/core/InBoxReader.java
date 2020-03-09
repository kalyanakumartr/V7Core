package org.hbs.v7.extractor.action.core;

import java.io.Serializable;

import org.hbs.core.beans.GenericKafkaProducer;
import org.hbs.core.beans.model.IConfiguration;
import org.hbs.v7.extractor.extractor.bo.ExtractorBo;

public interface InBoxReader extends Serializable
{

	public void readDataFromChannel(IConfiguration config, GenericKafkaProducer gKafkaProducer, ExtractorBo extractorBo);

}
