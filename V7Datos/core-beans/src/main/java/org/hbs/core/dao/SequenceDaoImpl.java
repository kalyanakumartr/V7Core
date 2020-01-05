package org.hbs.core.dao;

import java.security.InvalidKeyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SequenceDaoImpl implements SequenceDao
{
	private static final long	serialVersionUID	= 1722249372045340263L;

	@Autowired
	PrimaryDao					primaryDao;

	public long getPrimaryKey(String keyName) throws InvalidKeyException
	{
		long primaryId = primaryDao.findBySequenceKey(keyName);
		if (primaryId > 0L)
		{
			primaryId = primaryId + 1;
			primaryDao.updateSequenceIdByOne(primaryId, keyName);
			return primaryId;
		}
		throw new InvalidKeyException("Sequence Id NOT received for " + keyName);
	}
}
