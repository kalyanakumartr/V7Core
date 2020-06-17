package org.hbs.core.beans;

import java.util.LinkedHashMap;
import java.util.Map;

import org.hbs.core.util.CommonValidator;
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
	public Map<String, Object> updateObjectAsMap()
	{
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();

		Map<String, Object> objectMap = new ObjectMapper().convertValue(this, Map.class);

		extractNestedObjectAsMap("", dataMap, objectMap);

		return dataMap;

	}

	@SuppressWarnings("unchecked")
	private void extractNestedObjectAsMap(String parentKey, Map<String, Object> dataMap, Map<String, Object> objectMap)
	{
		for (String key : objectMap.keySet())
		{
			Object object = objectMap.get(key);
			if (object == null || object.getClass().isArray())
				continue;

			if (object instanceof String || object.getClass().isPrimitive())
			{
				if (CommonValidator.isNotNullNotEmpty(parentKey))
					key = parentKey + "_" + key;
				dataMap.put(key, String.valueOf(object));
			}
			else
			{
				if (object instanceof Map)
				{
					if (CommonValidator.isNotNullNotEmpty(parentKey, key))
						key = parentKey + "_" + key;

					extractNestedObjectAsMap(key, dataMap, (Map<String, Object>) object);
					// Map<String, Object> _LHMap = (Map<String, Object>) object;
					// for (String mKey : _LHMap.keySet())
					// {
					// dataMap.put(key + "-" + mKey, String.valueOf(_LHMap.get(mKey)));
					// }
				}
			}
		}
	}

}