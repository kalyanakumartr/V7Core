package org.hbs.core.dao;

import java.io.Serializable;
import java.security.InvalidKeyException;

public interface SequenceDao extends Serializable
{
	long getPrimaryKey(String keyName) throws InvalidKeyException;
}