package org.hbs.core.dao;

import java.security.InvalidKeyException;

import org.hbs.core.beans.model.Sequence;
import org.hbs.core.util.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SequenceDaoImpl implements SequenceDao
{
	private static final long	serialVersionUID	= 1722249372045340263L;

	@Autowired
	PrimaryDao					primaryDao;

	public String getPrimaryKey(String keyName, String producerId) throws InvalidKeyException
	{
		Sequence sequence = primaryDao.findBySequenceKey(keyName, producerId);
		if (CommonValidator.isNotNullNotEmpty(sequence) && sequence.getSequenceId() > 0L)
		{
			sequence.setSequenceId(sequence.getSequenceId() + 1);
			primaryDao.save(sequence);
			return sequence.getPrepend() + sequence.getSequenceId();
		}
		throw new InvalidKeyException("Sequence Id NOT received for " + keyName);
	}
}
