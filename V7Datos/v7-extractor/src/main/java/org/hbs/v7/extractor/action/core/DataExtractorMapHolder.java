package org.hbs.v7.extractor.action.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.IConstProperty;
import org.hbs.core.util.LabelValueBean;
import org.hbs.extractor.beans.model.resume.DataExtractorPattern;
import org.hbs.extractor.beans.model.resume.DataExtractorPattern.RegExFor;
import org.hbs.v7.extractor.bo.ExtractorBo;

public abstract class DataExtractorMapHolder implements IConstProperty
{
	private static final long									serialVersionUID	= 8164132758260365263L;
	private Map<String, TreeMap<Integer, DataExtractorPattern>>	dataMap				= new LinkedHashMap<String, TreeMap<Integer, DataExtractorPattern>>();
	private ExtractorBo											extractorBo;
	private String												producerId;

	public DataExtractorMapHolder()
	{
		super();
	}

	public String dataFramer(RegExFor regEx4, String datum)
	{
		String inData = "";
		if (populateExtractorMap() && dataMap.containsKey(regEx4.name()))
		{
			for (DataExtractorPattern _DEP : dataMap.get(regEx4.name()).values())
			{
				Matcher matcher = Pattern.compile(_DEP.getRegExpression().trim()).matcher(datum);
				while ( matcher.find() )
				{
					inData = matcher.group();
					if (inData == null)
					{
						return "";
					}
					for (LabelValueBean _LB : _DEP.getReplacers())
					{
						if (_LB.getLabel().matches(".*[A-Za-z].*"))
						{
							Matcher matcherLBL = Pattern.compile(_LB.getLabel()).matcher(inData);
							while ( matcherLBL.find() )
							{
								_LB.setLabel(matcherLBL.group());
							}
						}
						if (CommonValidator.isNotNullNotEmpty(_LB.getValue()) && _LB.getValue().matches(".*[A-Za-z].*"))
						{
							Matcher matcherVL = Pattern.compile(_LB.getValue()).matcher(inData);
							while ( matcherVL.find() )
							{
								_LB.setValue(matcherVL.group());
							}
						}
						inData = inData.replaceAll(_LB.getLabel(), _LB.getValue()).trim();
					}

					if (_DEP.getFilters().isEmpty() || _DEP.getFilters().contains(inData.trim()) == false)
					{
						if (_DEP.isSentenceCaps())
							inData = sentenceCapsRender(regEx4, inData);
						return inData.trim();
					}
				}
			}
		}
		return inData.trim();
	}

	private boolean populateExtractorMap()
	{

		if (dataMap.isEmpty())
		{
			List<DataExtractorPattern> dataList = extractorBo.getEmailExtractors(producerId);
			if (CommonValidator.isListFirstNotEmpty(dataList))
			{
				for (DataExtractorPattern _DEP : dataList)
				{
					getTreeMap(_DEP.getRegExFor()).put(_DEP.getRegExForOrder(), _DEP);
				}
				return true;
			}
			else
			{
				return false;
			}
		}
		return true;

	}

	public Map<String, TreeMap<Integer, DataExtractorPattern>> getDataMap()
	{
		return dataMap;
	}

	public void setDataMap(Map<String, TreeMap<Integer, DataExtractorPattern>> dataMap)
	{
		this.dataMap = dataMap;
	}

	private TreeMap<Integer, DataExtractorPattern> getTreeMap(String regExFor)
	{
		if (dataMap.containsKey(regExFor.trim()) == false)
		{
			TreeMap<Integer, DataExtractorPattern> treeMap = new TreeMap<Integer, DataExtractorPattern>(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2)
				{
					return o1.compareTo(o2);
				}

			});
			dataMap.put(regExFor.trim(), treeMap);
		}

		return dataMap.get(regExFor.trim());
	}

	private String sentenceCapsRender(RegExFor regExFor, String inDatum)
	{
		char[] data = inDatum.toLowerCase().toCharArray();
		int i = 0;
		boolean capFlag = false;
		StringBuffer sbName = new StringBuffer();
		char appendChar = ' ';
		while ( i < data.length )
		{
			if (i == 0)
			{
				sbName.append(String.valueOf(data[i]).toUpperCase());
			}
			else if (data[i] == ' ' || data[i] == '-' || data[i] == '_' || data[i] == '.' || data[i] == '#' || data[i] == '(' || data[i] == '$')
			{
				appendChar = data[i];
				capFlag = true;
			}
			else if (capFlag)
			{
				sbName.append(appendChar + String.valueOf(data[i]).toUpperCase());
				appendChar = ' ';
				capFlag = false;
			}
			else
			{
				sbName.append(data[i]);
			}
			i++;
		}
		if (CommonValidator.isNotNullNotEmpty(sbName))
		{
			if (regExFor == RegExFor.Name)
			{
				List<String> shuffuleList = Arrays.asList(sbName.toString().split(","));
				if (shuffuleList.get(0).length() == 2)
				{
					shuffuleList.set(0, shuffuleList.get(0).toUpperCase());
				}
				Collections.reverse(shuffuleList);
				return shuffuleList.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\,", "");
			}
			else
			{
				return sbName.toString();
			}
		}
		else
		{
			return "";
		}
	}

	public ExtractorBo getExtractorBo()
	{
		return extractorBo;
	}

	public void setExtractorBo(ExtractorBo extractorBo)
	{
		this.extractorBo = extractorBo;
	}

	public String getProducerId()
	{
		return producerId;
	}

	public void setProducerId(String producerId)
	{
		this.producerId = producerId;
	}

}