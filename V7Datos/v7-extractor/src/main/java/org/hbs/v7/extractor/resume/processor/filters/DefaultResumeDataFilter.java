package org.hbs.v7.extractor.resume.processor.filters;

import org.hbs.v7.beans.model.resume.ResumeData;
import org.hbs.v7.beans.model.resume._Address;
import org.hbs.v7.beans.model.resume._Media;
import org.hbs.v7.beans.model.resume._PersonalInfo;
import org.hbs.v7.beans.model.resume._ProfessionalExperience;
import org.hbs.v7.beans.model.resume._Qualification;
import org.hbs.v7.beans.model.resume._SkillsAndCost;
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
		_PersonalInfo personal = new _PersonalInfo();
		_RD.setPersonal(personal);
		System.out.println(">>>DefaultResumeDataFilter>>>>>>>getPersonalInfo>>>>>>mBean>>>> " );
		
	}

	public void getSkillsAndCost(ResumeData _RD)
	{
		_SkillsAndCost skillsCost = new _SkillsAndCost();
		_RD.setSkillsCost(skillsCost);
		System.out.println(">>>DefaultResumeDataFilter>>>>>>>getSkillsAndCost>>>>>>mBean>>>> " );
	}

	public void getAddress(ResumeData _RD)
	{
		_Address address = new _Address(_RD);
		_RD.setAddressList(address);
		System.out.println(">>>DefaultResumeDataFilter>>>>>>>getAddress>>>>>>mBean>>>> " );
	}

	public void getMedia(ResumeData _RD)
	{
		_Media media = new _Media(_RD);
		_RD.setMediaList(media);
		System.out.println(">>>DefaultResumeDataFilter>>>>>>>getMedia>>>>>>mBean>>>> " );
	}

	public void getQualification(ResumeData _RD)
	{
		_Qualification qualification = new _Qualification(_RD);
		_RD.setQualificationList(qualification);
		System.out.println(">>>DefaultResumeDataFilter>>>>>>>getQualification>>>>>>mBean>>>> " );
	}

	public void getProfessionalExperience(ResumeData _RD)
	{
		_ProfessionalExperience professional = new _ProfessionalExperience();
		_RD.setProfessionalList(professional);
		System.out.println(">>>DefaultResumeDataFilter>>>>>>>getProfessionalExperience>>>>>>mBean>>>> " );
	}
}
