package org.hbs.sender.bo;

import java.security.InvalidKeyException;
import java.util.List;

import org.hbs.core.beans.ConfigurationFormBean;
import org.hbs.core.beans.model.IConfiguration;
import org.hbs.core.beans.model.ProducersProperty;
import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.security.resource.IPathBase.EMediaMode;
import org.hbs.core.security.resource.IPathBase.EMediaType;
import org.hbs.core.util.EnumInterface;
import org.springframework.security.core.Authentication;

public interface ConfigurationBo
{
	EnumInterface blockConfiguration(Authentication auth, ConfigurationFormBean cfBean);

	int checkConfigurationExists(Authentication auth, ConfigurationFormBean cfBean);

	EnumInterface deleteConfiguration(Authentication auth, ConfigurationFormBean cfBean);

	ProducersProperty getConfigurationByAutoId(Authentication auth, ConfigurationFormBean cfBean);

	IConfiguration getConfigurationByType(String producerId, EMedia eMedia, EMediaType eMediaType, EMediaMode eMediaMode) throws ClassNotFoundException;

	IConfiguration getConfigurationByType(Authentication auth, EMedia eMedia, EMediaType eMediaType, EMediaMode eMediaMode) throws ClassNotFoundException;

	EnumInterface saveConfiguration(Authentication auth, ConfigurationFormBean cfBean) throws InvalidKeyException;

	List<ProducersProperty> searchByConfigurationName(Authentication auth, ConfigurationFormBean cfBean);

	EnumInterface updateConfiguration(Authentication auth, ConfigurationFormBean cfBean);

}
