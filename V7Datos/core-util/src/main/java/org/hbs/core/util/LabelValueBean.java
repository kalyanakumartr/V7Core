package org.hbs.core.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LabelValueBean implements java.io.Serializable
{
	private static final long	serialVersionUID	= -2065628036344367528L;
	private String				value;
	private String				label;
	private String				key;
	private String				code;

	public enum ELabelValue implements EnumInterface
	{
		Combo;

		public List<LabelValueBean> toList(List<Object[]> lvBeanArray)
		{
			List<LabelValueBean> lvBeanList = new ArrayList<LabelValueBean>();
			for (Object[] oArray : lvBeanArray)
			{
				lvBeanList.add(new LabelValueBean(String.valueOf(oArray[0]), String.valueOf(oArray[1])));
			}
			return lvBeanList;
		}

		public String getJson(List<LabelValueBean> lvBeanList)
		{
			return getJson(lvBeanList, null, null);
		}

		public String getJson(List<LabelValueBean> lvBeanList, String headerLabel, String headerValue)
		{
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			StringBuilder sb = new StringBuilder(gson.toJson(new LabelValueCombo(lvBeanList, headerLabel, headerValue)));
			return sb.toString();
		}
	}

	public LabelValueBean()
	{
		super();
	}

	public LabelValueBean(String value, String label)
	{
		this.value = value;
		this.label = label;
	}

	public LabelValueBean(Object value, String label)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		this.value = gson.toJson(value);
		this.label = label;
	}

	public LabelValueBean(Object value, String label, String key)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		this.value = gson.toJson(value);
		this.label = label;
		this.key = key;
	}

	public LabelValueBean(Object value, String label, String key, String countryCode)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		this.value = gson.toJson(value);
		this.label = label;
		this.key = key;
		this.code = countryCode;
	}

	/**
	 * @return the label
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label)
	{
		this.label = label;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

}

class LabelValueCombo implements Serializable
{
	private static final long		serialVersionUID	= 5660689055048562000L;

	private List<LabelValueBean>	lbBeanList;

	public LabelValueCombo(List<LabelValueBean> lbBeanList, String headerLabel, String headerValue)
	{
		super();
		this.lbBeanList = lbBeanList;

		if (CommonValidator.isNotNullNotEmpty(headerLabel))
			this.lbBeanList.add(0, new LabelValueBean(headerValue, headerLabel));
	}

	public List<LabelValueBean> getLbBeanList()
	{
		return lbBeanList;
	}

	public void setLbBeanList(List<LabelValueBean> lbBeanList)
	{
		this.lbBeanList = lbBeanList;
	}

}