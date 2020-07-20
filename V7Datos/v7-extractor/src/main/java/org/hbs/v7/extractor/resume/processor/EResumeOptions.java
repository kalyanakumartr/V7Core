package org.hbs.v7.extractor.resume.processor;

import java.util.Map;

import org.apache.commons.collections4.map.LinkedMap;
import org.hbs.core.util.EnumInterface;
import org.hbs.v7.extractor.resume.processor.filters.DefaultResumeDataFilter;
import org.hbs.v7.extractor.resume.processor.filters.GenderDataFilter;

public enum EResumeOptions implements EnumInterface
{
	PersonalInfo("", "getPersonalInfo"), //
	SkillsAndCost("", "getSkillsAndCost"), //
	Address("", "getAddress"), //
	Media("", "getMedia"), //
	Qualification("", "getQualification"), //
	ProfessionalExperience("", "getProfessionalExperience");

	String	clazz;
	String	method;

	EResumeOptions(String clazz, String method)
	{
		this.clazz = clazz;
		this.method = method;
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
					optionList.put(eOpt, toArray(DefaultResumeDataFilter.class, GenderDataFilter.class));
					break;
				}
				case SkillsAndCost :
				case Address :
				case Media :
				case Qualification :
				case ProfessionalExperience :
				{
					optionList.put(eOpt, toArray(DefaultResumeDataFilter.class));
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