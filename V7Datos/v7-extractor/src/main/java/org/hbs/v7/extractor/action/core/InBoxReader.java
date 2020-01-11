package org.hbs.v7.extractor.action.core;

import java.io.Serializable;

import org.hbs.core.beans.GenericKafkaProducer;
import org.hbs.core.beans.model.IConfiguration;

public interface InBoxReader extends Serializable
{

	public void readDataFromChannel(IConfiguration config, GenericKafkaProducer gKafkaProducer);

}
