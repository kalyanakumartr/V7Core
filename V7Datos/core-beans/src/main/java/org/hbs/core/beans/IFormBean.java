package org.hbs.core.beans;

import java.io.Serializable;
import java.util.Map;

public interface IFormBean extends Serializable
{

	IFormBean getStatusBean();

	IFormBean getStatusBeanWithMessage(String messageCode);

	Map<String, Object> updateObjectAsMap();

	void clearForm();
}