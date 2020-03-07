package org.hbs.core.dao;

import java.io.Serializable;
import java.security.InvalidKeyException;

public interface SequenceDao extends Serializable
{
	String getPrimaryKey(String keyName, String producerId) throws InvalidKeyException;
}