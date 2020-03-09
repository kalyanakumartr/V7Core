package org.hbs.sender.bo;

import java.security.InvalidKeyException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.kafka.common.errors.InvalidRequestException;
import org.hbs.core.beans.ConfigurationFormBean;
import org.hbs.core.beans.model.IConfiguration;
import org.hbs.core.beans.model.ProducersProperty;
import org.hbs.core.beans.path.IErrorSender;
import org.hbs.core.dao.ConfigurationDao;
import org.hbs.core.dao.SequenceDao;
import org.hbs.core.security.resource.IPath.EAuth;
import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.security.resource.IPathBase.EMediaMode;
import org.hbs.core.security.resource.IPathBase.EMediaType;
import org.hbs.core.security.resource.IPathBase.EReturn;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.IConstProperty.EWrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationBoImpl implements ConfigurationBo, IErrorSender
{
	private static final long	serialVersionUID	= -3286833424714620583L;

	private final Logger		logger				= LoggerFactory.getLogger(ConfigurationBoImpl.class);

	@Autowired
	ConfigurationDao			configurationDao;

	@Autowired
	SequenceDao					sequenceDao;

	@Override
	public EnumInterface blockConfiguration(Authentication auth, ConfigurationFormBean cfBean)
	{
		if (isRecentlyUpdated(auth, cfBean))
		{
			try
			{
				logger.info("Inside ConfigurationBoImpl blockUser ::: ", cfBean.producerProperty.getGroupName());
				cfBean.repoProducerProperty.setStatus(!cfBean.producerProperty.getStatus());// Negate
																							// Current
																							// Status
				cfBean.repoProducerProperty.modifiedUserInfo(auth);
				configurationDao.save(cfBean.repoProducerProperty);

				cfBean.messageCode = CONFIGURATION_BLOCKED_SUCCESSFULLY;
				return EReturn.Success;
			}
			finally
			{
				cfBean.repoProducerProperty = null;
			}
		}
		throw new InvalidRequestException(CONFIGURATION_DATA_UPDATED_RECENTLY);
	}

	@Override
	public int checkConfigurationExists(Authentication auth, ConfigurationFormBean cfBean)
	{
		return configurationDao.checkConfigurationExists(EAuth.User.getProducerId(auth), cfBean.groupName);
	}

	@Override
	public EnumInterface deleteConfiguration(Authentication auth, ConfigurationFormBean cfBean)
	{
		configurationDao.deleteById(cfBean.autoId);
		return EReturn.Success;
	}

	@Override
	public List<ProducersProperty> searchByConfigurationName(Authentication auth, ConfigurationFormBean cfBean)
	{
		return configurationDao.searchByConfigurationName(EAuth.User.getProducerId(auth), EWrap.Percent.enclose(cfBean.groupName));
	}

	@Override
	public ProducersProperty getConfigurationByAutoId(Authentication auth, ConfigurationFormBean cfBean)
	{
		return configurationDao.fetchByAutoId(EAuth.User.getProducerId(auth), cfBean.autoId);
	}

	@Override
	public IConfiguration getConfigurationByType(String producerId, EMedia eMedia, EMediaType eMediaType, EMediaMode eMediaMode) throws ClassNotFoundException
	{
		String media = eMedia == null ? "%%" : eMedia.name();
		String mediaType = eMediaType == null ? "%%" : eMediaType.name();
		String mediaMode = eMediaMode == null ? "%%" : eMediaMode.name();

		ProducersProperty _PP = configurationDao.getConfigurationByType(producerId, media, mediaType, mediaMode).iterator().next();

		return _PP.getPropertyAsConfiguration();
	}

	@Override
	public IConfiguration getConfigurationByType(Authentication auth, EMedia eMedia, EMediaType eMediaType, EMediaMode eMediaMode) throws ClassNotFoundException
	{
		return getConfigurationByType(EAuth.User.getProducerId(auth), eMedia, eMediaType, eMediaMode);
	}

	@Override
	public synchronized EnumInterface saveConfiguration(Authentication auth, ConfigurationFormBean cfBean) throws InvalidKeyException
	{
		logger.info("ConfigurationBoImpl saveConfiguration starts ::: ");
		int count = configurationDao.checkConfigurationExists(EAuth.User.getProducerId(auth), cfBean.producerProperty.getGroupName());
		if (count > 0)
		{
			cfBean.producerProperty.createdUserProducerInfo(auth);

			cfBean.producerProperty = configurationDao.save(cfBean.producerProperty);

			return EReturn.Success;
		}
		throw new InvalidRequestException(CONFIGURATION_ALREADY_EXISTS);
	}

	@Override
	public EnumInterface updateConfiguration(Authentication auth, ConfigurationFormBean cfBean)
	{
		if (isRecentlyUpdated(auth, cfBean))
		{
			try
			{
				logger.info("ConfigurationBoImpl updateConfiguration starts ::: ");
				cfBean.updateRepoConfiguration(auth);
				configurationDao.save(cfBean.repoProducerProperty);

				cfBean.messageCode = CONFIGURATION_UPDATED_SUCCESSFULLY;
				logger.info("ConfigurationBoImpl updateConfiguration ends ::: ");
				return EReturn.Success;
			}
			finally
			{
				cfBean.repoProducerProperty = null;
			}
		}
		throw new InvalidRequestException(CONFIGURATION_DATA_UPDATED_RECENTLY);
	}

	private boolean isRecentlyUpdated(Authentication auth, ConfigurationFormBean cfBean)
	{
		logger.info("Inside ConfigurationBoImpl isRecentlyUpdated ::: ", cfBean.producerProperty.getAutoId());
		if (CommonValidator.isNotNullNotEmpty(cfBean.producerProperty))
		{
			ProducersProperty iPP = configurationDao.fetchByAutoId(EAuth.User.getProducerId(auth), cfBean.autoId);
			if (CommonValidator.isNotNullNotEmpty(iPP))
			{
				if (ChronoUnit.NANOS.between(cfBean.producerProperty.getModifiedDate().toLocalDateTime(), iPP.getModifiedDate().toLocalDateTime()) == 0)
				{
					cfBean.producerProperty = iPP;
					return true;
				}
				return false;
			}
		}
		throw new InvalidRequestException(CONFIGURATION_NOT_FOUND);
	}
}
