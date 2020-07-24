package org.hbs.v7.extractor.resume.processor.filters;

import org.hbs.v7.beans.model.resume.ResumeData;
import org.hbs.v7.extractor.resume.processor.MediatorBean;

public class DefaultResumeDataFilter extends ResumeDataFilterBase
{

	private static final long serialVersionUID = -4518388491913217656L;

	public DefaultResumeDataFilter(MediatorBean mBean)
	{
		this.mBean = mBean;
	}

	public void getPersonalInfo(ResumeData _RD)
	{
		_PersonalInfoFilter.getInstance().execute(mBean, _RD);
		
		System.out.println(">>>DefaultResumeDataFilter>>>>>>>getPersonalInfo>>>>>>mBean>>>> " );
		
	}

	public void getSkillsAndCost(ResumeData _RD)
	{
		_SkillsAndCostFilter.getInstance().execute(mBean, _RD);
		System.out.println(">>>DefaultResumeDataFilter>>>>>>>getSkillsAndCost>>>>>>mBean>>>> " );
	}

	public void getAddress(ResumeData _RD)
	{
		_AddressFilter.getInstance().execute(mBean, _RD);
		System.out.println(">>>DefaultResumeDataFilter>>>>>>>getAddress>>>>>>mBean>>>> " );
	}

	public void getMedia(ResumeData _RD)
	{
		_MediaFilter.getInstance().execute(mBean, _RD);
		System.out.println(">>>DefaultResumeDataFilter>>>>>>>getMedia>>>>>>mBean>>>> " );
	}

	public void getQualification(ResumeData _RD)
	{
		_QualificationFilter.getInstance().execute(mBean, _RD);
		System.out.println(">>>DefaultResumeDataFilter>>>>>>>getQualification>>>>>>mBean>>>> " );
	}

	public void getProfessionalExperience(ResumeData _RD)
	{
		_ProfessionalExperienceFilter.getInstance().execute(mBean, _RD);
		System.out.println(">>>DefaultResumeDataFilter>>>>>>>getProfessionalExperience>>>>>>mBean>>>> " );
	}
}
