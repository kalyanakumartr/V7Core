package org.hbs.core.admin.bo;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.util.concurrent.ExecutionException;

import org.hbs.core.beans.PasswordFormBean;
import org.hbs.core.security.resource.IPathBase.EReturn;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface OTPBo extends Serializable
{
	void generateOTP(PasswordFormBean pfBean) throws InvalidKeyException, JsonProcessingException;

	EReturn validateOTP(PasswordFormBean pfBean) throws ExecutionException;
}
