package org.hbs.extractor.beans.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "incoming_core_data")
public class IncomingCoreData extends IncomingCoreDataBase
{

	private static final long		serialVersionUID	= -1772495396603099861L;
	public IncomingCoreData()
	{
		super();
	}

}