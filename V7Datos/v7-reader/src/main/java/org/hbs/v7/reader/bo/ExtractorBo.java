package org.hbs.v7.reader.bo;

import java.io.Serializable;
import java.util.List;

import org.hbs.core.beans.model.IConfiguration;
import org.hbs.core.beans.model.channel.ConfigurationEmail;
import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.security.resource.IPathBase.EMediaMode;
import org.hbs.v7.beans.model.resume.DataExtractorPattern;

public interface ExtractorBo extends Serializable
{
	List<IConfiguration> getConfigurationList(EMedia eMedia, EMediaMode eMediaMode);

	long getLastEmailSentDate(ConfigurationEmail config);

	List<DataExtractorPattern> getEmailExtractors(String producerId);

	void updateProducerProperty(IConfiguration config) throws ClassNotFoundException;
}
