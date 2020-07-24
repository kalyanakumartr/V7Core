package org.hbs.v7.extractor.resume.processor.filters;

import java.io.Serializable;

import org.hbs.v7.beans.model.resume.ResumeData;
import org.hbs.v7.extractor.resume.processor.MediatorBean;

public interface _IFilter extends Serializable
{

	void execute(MediatorBean mBean, ResumeData _RD);

}