package org.hbs.v7.extractor.resume.processor.filters;

import org.hbs.v7.beans.model.resume.ResumeData;
import org.hbs.v7.beans.model.resume._PersonalInfo;
import org.hbs.v7.extractor.resume.processor.MediatorBean;

public class _PersonalInfoFilter implements _IFilter
{
	/**
	 * 
	 */
	private static final long			serialVersionUID	= -3930496576019385191L;
	private static _IFilter	filterFactory		= null;

	private _PersonalInfoFilter()
	{

	}

	public static _IFilter getInstance()
	{
		if (filterFactory == null)
		{
			filterFactory = new _PersonalInfoFilter();
		}
		return filterFactory;
	}

	@Override
	public void execute(MediatorBean mBean, ResumeData _RD)
	{
		switch ( mBean.extension )
		{
			case Docx :
			case Doc :
			case ODT :
			case PDF :
			{
				_PersonalInfo personal = new _PersonalInfo();
				System.out.println(mBean.content);
				_RD.setPersonal(personal);
				break;
			}
			case HTM :
			case HTML :
			{
				_PersonalInfo personal = new _PersonalInfo();
				_RD.setPersonal(personal);
				break;
			}
			case Json :
			{
				_PersonalInfo personal = new _PersonalInfo();
				_RD.setPersonal(personal);
				break;
			}
			case XLS :
			case XLSX :
			{
				_PersonalInfo personal = new _PersonalInfo();
				_RD.setPersonal(personal);
				break;
			}
			case Csv :
			{
				_PersonalInfo personal = new _PersonalInfo();
				_RD.setPersonal(personal);
				break;
			}
			default :
				break;

		}
	}
}
