package org.hbs.core.beans;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class APIStatus implements IFormBean
{
	private static final long serialVersionUID = 7838070576020990903L;

	public APIStatus()
	{
		super();
	}

	public String messageCode;

	@Override
	@JsonIgnore
	public APIStatus getStatusBean()
	{
		return this;
	}

	@Override
	@JsonIgnore
	public APIStatus getStatusBeanWithMessage(String messageCode)
	{
		this.messageCode = messageCode;
		return getStatusBean();
	}

	@SuppressWarnings("unchecked")
	@Transient
	@JsonIgnore
	public Map<String, String> updateObjectAsMap()
	{
		Map<String, String> dataMap = new LinkedHashMap<String, String>();

		Map<String, Object> objectMap = new ObjectMapper().convertValue(this, Map.class);

		for (String key : objectMap.keySet())
		{
			Object object = objectMap.get(key);
			if (object == null || object.getClass().isArray())
				continue;

			if (object instanceof String || object.getClass().isPrimitive())
			{
				dataMap.put(key, String.valueOf(object));
			}
			else
			{
				if (object instanceof LinkedHashMap)
				{
					LinkedHashMap<String, Object> _LHMap = (LinkedHashMap<String, Object>) object;
					for (String mKey : _LHMap.keySet())
					{
						dataMap.put(key + "-" + mKey, String.valueOf(_LHMap.get(mKey)));
					}
				}
			}
		}

		return dataMap;

	}

}