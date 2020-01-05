package org.hbs.v7.extractor.extractor.bo;

import java.io.Serializable;
import java.util.List;

import org.hbs.core.beans.model.IConfiguration;
import org.hbs.core.security.resource.IPath.EMedia;
import org.hbs.core.security.resource.IPath.EMediaMode;
import org.hbs.extractor.beans.model.DataExtractorPattern;

public interface ExtractorBo extends Serializable
{
	List<IConfiguration> getConfigurationList(EMedia eMedia, EMediaMode eMediaMode);

	long getLastEmailSentDate(String customerAccountMail);

	List<DataExtractorPattern> getEmailExtractors(String producerId);
}
