package org.hbs.core.admin.bo;

import java.security.InvalidKeyException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.kafka.common.errors.InvalidRequestException;
import org.hbs.core.beans.ProducerFormBean;
import org.hbs.core.beans.model.Producers;
import org.hbs.core.beans.path.IErrorAdmin;
import org.hbs.core.dao.ProducerDao;
import org.hbs.core.dao.SequenceDao;
import org.hbs.core.security.resource.IPathBase.EReturn;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.IConstProperty.EWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProducerBoImpl implements ProducerBo, IErrorAdmin
{
	private static final long	serialVersionUID	= 6078462669851402422L;
	// private final Logger logger = LoggerFactory.getLogger(ProducerBoImpl.class);

	@Autowired
	protected ProducerDao		producerDao;

	@Autowired
	SequenceDao					sequenceDao;

	@Override
	public EnumInterface blockProducer(Authentication auth, ProducerFormBean cfBean)
	{
		if (isRecentlyUpdated(cfBean))
		{
			try
			{
				cfBean.repoProducer.setStatus(!cfBean.producer.getStatus());// Negate Current Status
				cfBean.repoProducer.modifiedUserInfo(auth);
				producerDao.save(cfBean.repoProducer);

				cfBean.messageCode = CUSTOMER_BLOCKED_SUCCESSFULLY;
				return EReturn.Success;
			}
			finally
			{
				cfBean.producer = null;
			}
		}
		throw new InvalidRequestException(CUSTOMER_DATA_UPDATED_RECENTLY);

	}

	@Override
	public EnumInterface blockProducerById(Authentication auth, ProducerFormBean cfBean) throws InvalidRequestException
	{
		if (isRecentlyUpdated(cfBean))
		{
			try
			{
				cfBean.repoProducer.setStatus(!cfBean.producer.getStatus());// Negate Current Status
				cfBean.repoProducer.modifiedUserInfo(auth);
				producerDao.save(cfBean.repoProducer);

				cfBean.messageCode = USER_BLOCKED_SUCCESSFULLY;
				return EReturn.Success;
			}
			finally
			{
				cfBean.repoProducer = null;
				cfBean.producer = null;
			}
		}
		throw new InvalidRequestException(USER_DATA_UPDATED_RECENTLY);
	}

	@Override
	public EnumInterface deleteProducer(Authentication auth, ProducerFormBean cfBean) throws InvalidRequestException
	{
		// logger.info("Inside ProducerBoImpl deleteProducer ::: ",
		// cfBean.producer.getProducerId());
		// producerDao.deleteById(cfBean.producer.getProducerId());
		cfBean.repoProducer = producerDao.findByProducerId(cfBean.producer.getProducerId());
		cfBean.repoProducer.setStatus(!cfBean.repoProducer.getStatus());// Negate Current Status

		cfBean.repoProducer.modifiedUserInfo(auth);
		producerDao.save(cfBean.repoProducer);
		return EReturn.Success;
	}

	@Override
	public EnumInterface checkProducerExist(ProducerFormBean cfBean)
	{
		int row = producerDao.countByProducerName(cfBean.producer.getProducerName());
		return row > 0 ? EReturn.Exists : EReturn.Not_Exists;
	}

	@Override
	public Producers getProducer(ProducerFormBean cfBean)
	{
		return producerDao.findByProducerId(cfBean.producer.getProducerId());
	}

	@Override
	public List<Producers> getProducerByName(ProducerFormBean cfBean)
	{
		return producerDao.findByProducerName(EWrap.Percent.enclose(cfBean.producer.getProducerName()));
	}

	private boolean isRecentlyUpdated(ProducerFormBean cfBean)
	{
		if (CommonValidator.isNotNullNotEmpty(cfBean, cfBean.producer))
		{
			Producers producer = producerDao.findByProducerId(cfBean.producer.getProducerId());
			if (CommonValidator.isNotNullNotEmpty(producer))
			{
				if (ChronoUnit.NANOS.between(cfBean.producer.getModifiedDate().toLocalDateTime(), producer.getModifiedDate().toLocalDateTime()) == 0)
				{
					cfBean.repoProducer = producer;
					return true;
				}
				return false;
			}
		}
		throw new InvalidRequestException(CUSTOMER_NOT_FOUND);
	}

	@Override
	public EnumInterface saveProducer(Authentication auth, ProducerFormBean cfBean) throws InvalidKeyException
	{

		Object[] row = producerDao.checkProducerName(cfBean.producer.getProducerName());
		if (CommonValidator.isNullOrEmpty(row))
		{
			cfBean.producer.setStatus(true);
			cfBean.producer.createdUserProducerInfo(auth);
			cfBean.producer = producerDao.save(cfBean.producer);

			if (CommonValidator.isNotNullNotEmpty(cfBean.producer))
			{
				try
				{
					cfBean.messageCode = CUSTOMER_CREATED_SUCCESSFULLY;
					return EReturn.Success;
				}
				finally
				{
					cfBean.producer = null;
					cfBean.repoProducer = null;
				}
			}
		}
		throw new InvalidRequestException(CUSTOMER_ALREADY_EXISTS);

	}

	@Override
	public EnumInterface updateProducer(Authentication auth, ProducerFormBean cfBean)
	{

		if (isRecentlyUpdated(cfBean))
		{
			try
			{
				cfBean.updateRepoProducer(auth);
				producerDao.save(cfBean.repoProducer);

				cfBean.messageCode = CUSTOMER_UPDATED_SUCCESSFULLY;
				return EReturn.Success;
			}
			finally
			{
				cfBean.producer = null;
			}
		}
		throw new InvalidRequestException(CUSTOMER_DATA_UPDATED_RECENTLY);

	}

	@Override
	public List<Producers> getProducerList()
	{
		return producerDao.findByStatus(true);
	}
}
