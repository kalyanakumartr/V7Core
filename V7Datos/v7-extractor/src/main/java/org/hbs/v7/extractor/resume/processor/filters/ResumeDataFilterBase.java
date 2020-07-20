package org.hbs.v7.extractor.resume.processor.filters;

import org.hbs.v7.extractor.resume.processor.MediatorBean;

public abstract class ResumeDataFilterBase implements IDataFilter
{
	private static final long	serialVersionUID	= 1774704020215690452L;
	protected MediatorBean		mBean;

	public ResumeDataFilterBase()
	{
		super();
	}

}