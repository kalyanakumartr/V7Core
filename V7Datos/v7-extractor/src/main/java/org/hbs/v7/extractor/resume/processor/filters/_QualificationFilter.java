package org.hbs.v7.extractor.resume.processor.filters;

import java.io.Serializable;

import org.hbs.v7.beans.model.resume.ResumeData;
import org.hbs.v7.beans.model.resume._Qualification;
import org.hbs.v7.extractor.resume.processor.MediatorBean;

public class _QualificationFilter implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5298987922486205403L;
	private static _QualificationFilter	filterFactory		= null;

	private _QualificationFilter()
	{

	}

	public static _QualificationFilter getInstance()
	{
		if (filterFactory == null)
		{
			filterFactory = new _QualificationFilter();
		}
		return filterFactory;
	}

	public void getQualification(MediatorBean mBean, ResumeData _RD)
	{
		switch ( mBean.extension )
		{
			case Docx :
			case Doc :
			case ODT :
			case PDF :
			{
				_Qualification qualification = new _Qualification(_RD);
				_RD.setQualificationList(qualification);
				break;
			}
			case HTM :
			case HTML :
			{
				_Qualification qualification = new _Qualification(_RD);
				_RD.setQualificationList(qualification);
				break;
			}
			case Json :
			{
				_Qualification qualification = new _Qualification(_RD);
				_RD.setQualificationList(qualification);
				break;
			}
			case XLS :
			case XLSX :
			{
				_Qualification qualification = new _Qualification(_RD);
				_RD.setQualificationList(qualification);
				break;
			}
			case Csv :
			{
				_Qualification qualification = new _Qualification(_RD);
				_RD.setQualificationList(qualification);
				break;
			}
			default :
				break;

		}
	}
}
