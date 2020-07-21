package org.hbs.v7.extractor.resume.readers;

import org.hbs.v7.beans.DataInTopicBean;
import org.hbs.v7.dao.DataAttachmentDao;
import org.hbs.v7.extractor.resume.processor.MediatorBean;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DataExtractorBase 
{

	@Autowired
	protected DataAttachmentDao datDao;

	public DataExtractorBase()
	{
		super();
	}
	
	abstract MediatorBean read(DataInTopicBean inBean);

}