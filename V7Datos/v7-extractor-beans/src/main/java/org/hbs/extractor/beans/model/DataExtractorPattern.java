package org.hbs.extractor.beans.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.IConstProperty;
import org.hbs.core.util.LabelValueBean;

@Entity
@Table(name = "resume_data_extractor_pattern")
public class DataExtractorPattern implements IConstProperty
{
	public enum RegExFor implements EnumInterface
	{
		Address, Address1, Address2, Age, DOB, Email, EmailFromFile, JobName, JobPostion, MobileNo, Name, NameFrom, PhoneNo, PostalCode;
	}

	private static final long		serialVersionUID	= 1304474771085726423L;
	protected int					autoId;
	protected String				dataFilters			= null;
	protected String				description			= null;
	protected List<String>			filters				= new ArrayList<String>(0);
	protected String				fromColonColonTo	= null;
	protected String				regExFor			= null;
	protected int					regExForOrder		= 1;
	protected String				regExpression		= null;
	protected List<LabelValueBean>	replacers			= new ArrayList<LabelValueBean>(0);
	protected boolean				sentenceCaps		= false;

	public DataExtractorPattern()
	{
		super();
	}

	public DataExtractorPattern(int autoId, int regExForOrder, String regExFor, String description, String regExpression, String fromColonColonTo, String dataFilters, boolean sentenceCaps,
			List<LabelValueBean> replacers, List<String> filters)
	{
		super();
		this.autoId = autoId;
		this.regExForOrder = regExForOrder;
		this.regExFor = regExFor;
		this.description = description;
		this.regExpression = regExpression;
		this.fromColonColonTo = fromColonColonTo;
		this.dataFilters = dataFilters;
		this.sentenceCaps = sentenceCaps;
		this.replacers = replacers;
		this.filters = filters;
	}

	@Id
	@Column(name = "autoId")
	public int getAutoId()
	{
		return autoId;
	}

	@Column(name = "dataFilters")
	public String getDataFilters()
	{
		return dataFilters;
	}

	@Column(name = "description")
	public String getDescription()
	{
		return description;
	}

	@Transient
	public List<String> getFilters()
	{
		if (CommonValidator.isNotNullNotEmpty(dataFilters) && filters.isEmpty())
		{
			filters = Arrays.asList(dataFilters.split(","));
		}
		return filters;
	}

	@Column(name = "fromColonColonTo")
	public String getFromColonColonTo()
	{
		return fromColonColonTo;
	}

	@Column(name = "regExFor")
	public String getRegExFor()
	{
		return regExFor;
	}

	@Column(name = "regExForOrder")
	public int getRegExForOrder()
	{
		return regExForOrder;
	}

	@Column(name = "regExpression")
	public String getRegExpression()
	{
		return regExpression;
	}

	@Transient
	public List<LabelValueBean> getReplacers()
	{
		if (replacers.isEmpty() && CommonValidator.isNotNullNotEmpty(fromColonColonTo))
		{
			fromColonColonTo = fromColonColonTo.replaceAll("\\\\,", "###");

			String[] _Transformers = fromColonColonTo.split(",");
			for (String _Transformer : _Transformers)
			{
				if (_Transformer.indexOf("::") > 0)
				{
					String[] lbFromTo = _Transformer.split("::");
					if (lbFromTo.length == 1)
						replacers.add(new LabelValueBean("", lbFromTo[0]));
					else
						replacers.add(new LabelValueBean(lbFromTo[1], lbFromTo[0]));
				}
			}
		}
		return replacers;
	}

	@Column(name = "sentenceCaps")
	public boolean isSentenceCaps()
	{
		return sentenceCaps;
	}

	public void setAutoId(int autoId)
	{
		this.autoId = autoId;
	}

	public void setDataFilters(String dataFilters)
	{
		this.dataFilters = dataFilters;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setFilters(List<String> filters)
	{
		this.filters = filters;
	}

	public void setFromColonColonTo(String fromColonColonTo)
	{
		this.fromColonColonTo = fromColonColonTo;
	}

	public void setRegExFor(String regExFor)
	{
		this.regExFor = regExFor;
	}

	public void setRegExForOrder(int regExForOrder)
	{
		this.regExForOrder = regExForOrder;
	}

	public void setRegExpression(String regExpression)
	{
		this.regExpression = regExpression;
	}

	public void setReplacers(List<LabelValueBean> replacers)
	{
		this.replacers = replacers;
	}

	public void setSentenceCaps(boolean sentenceCaps)
	{
		this.sentenceCaps = sentenceCaps;
	}

}
