package org.hbs.v7.extractor.resume.processor.filters;

import org.hbs.extractor.beans.model.resume.ResumeData;
import org.hbs.extractor.beans.model.resume._PersonalInfo;
import org.hbs.v7.extractor.resume.processor.MediatorBean;

public class GenderDataFilter extends ResumeDataFilterBase
{
	private static final long	serialVersionUID	= 3647416351476088188L;

	public GenderDataFilter(MediatorBean mBean)
	{
		this.mBean = mBean;
	}

	public void getPersonalInfo(ResumeData _RD)
	{
		_PersonalInfo personal = new _PersonalInfo();
		_RD.setPersonal(personal);
		System.out.println(">>>GenderDataFilter>>>>>>>getPersonalInfo>>>>>>mBean>>>> " );
	}

}
