package org.hbs.v7.extractor.resume.processor;

import java.util.Map;

import org.apache.commons.collections4.map.LinkedMap;
import org.hbs.core.util.EnumInterface;
import org.hbs.v7.extractor.resume.processor.filters._AddressFilter;
import org.hbs.v7.extractor.resume.processor.filters._MediaFilter;
import org.hbs.v7.extractor.resume.processor.filters._PersonalInfoFilter;
import org.hbs.v7.extractor.resume.processor.filters._PersonalInfoGenderFilter;
import org.hbs.v7.extractor.resume.processor.filters._ProfessionalExperienceFilter;
import org.hbs.v7.extractor.resume.processor.filters._QualificationFilter;
import org.hbs.v7.extractor.resume.processor.filters._SkillsAndCostFilter;

public enum EResumeOptions implements EnumInterface
{
	PersonalInfo("", "getPersonalInfo, getGender"), //
	SkillsAndCost("", "getSkillsAndCost"), //
	Address("", "getAddress"), //
	Media("", "getMedia"), //
	Qualification("", "getQualification"), //
	ProfessionalExperience("", "getProfessionalExperience");

	String	clazz;
	String	methods;

	EResumeOptions(String clazz, String method)
	{
		this.clazz = clazz;
		this.methods = method;
	}

	EResumeOptions set(Class<?> clazz)
	{
		this.clazz = clazz.getCanonicalName();
		return this;
	}

	public static Map<EResumeOptions, Class<?>[]> getProcessors()
	{
		Map<EResumeOptions, Class<?>[]> optionList = new LinkedMap<EResumeOptions, Class<?>[]>();
		for (EResumeOptions eOpt : EResumeOptions.values())
		{
			switch ( eOpt )
			{
				case PersonalInfo :
				{
					optionList.put(eOpt, toArray(_PersonalInfoFilter.class, _PersonalInfoGenderFilter.class));
					break;
				}
				case SkillsAndCost :{
					optionList.put(eOpt, toArray(_SkillsAndCostFilter.class));
					break;
				}
				case Address :{
					optionList.put(eOpt, toArray(_AddressFilter.class));
					break;
				}
				case Media :{
					optionList.put(eOpt, toArray(_MediaFilter.class));
					break;
				}
				case Qualification :{
					optionList.put(eOpt, toArray(_QualificationFilter.class));
					break;
				}
				case ProfessionalExperience :
				{
					optionList.put(eOpt, toArray(_ProfessionalExperienceFilter.class));
					break;
				}
			}
		}
		return optionList;
	}

	private static Class<?>[] toArray(Class<?>... clazz)
	{
		return clazz;
	}

}