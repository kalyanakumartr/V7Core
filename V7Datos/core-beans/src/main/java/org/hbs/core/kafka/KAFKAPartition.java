package org.hbs.core.kafka;

import java.io.Serializable;

public interface KAFKAPartition extends Serializable
{
	public int getPartition();
}
