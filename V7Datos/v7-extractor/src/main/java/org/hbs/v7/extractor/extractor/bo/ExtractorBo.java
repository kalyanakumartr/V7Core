package org.hbs.v7.extractor.extractor.bo;

import java.io.Serializable;
import java.util.List;

import org.hbs.core.beans.model.IConfiguration;
import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.security.resource.IPathBase.EMediaMode;
import org.hbs.extractor.beans.model.resume.DataExtractorPattern;

public interface ExtractorBo extends Serializable
{
	List<IConfiguration> getConfigurationList(EMedia eMedia, EMediaMode eMediaMode);

	long getLastEmailSentDate(String customerAccountMail);

	List<DataExtractorPattern> getEmailExtractors(String producerId);
}
