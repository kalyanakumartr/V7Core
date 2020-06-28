package org.hbs.core.admin.bo;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.util.concurrent.ExecutionException;

import org.hbs.core.beans.IFormBean;
import org.hbs.core.beans.PasswordFormBean;
import org.hbs.core.beans.UserFormBean;
import org.hbs.core.util.EnumInterface;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface PasswordBo extends Serializable
{
	IFormBean validateUser(String tokenKey) throws InvalidKeyException;

	EnumInterface updatePassword(Authentication auth, PasswordFormBean password) throws InvalidKeyException, ExecutionException;

	EnumInterface forgotPassword(UserFormBean user) throws InvalidKeyException, JsonProcessingException;

	/**
	 * Should contain at least one digit Should contain at least one lower case letter Should
	 * contain at least one upper case letter Should contain at least one special character length
	 * should be between 6 to 20
	 */
	public static String PASSWORD_VALIDATION_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,20}$";

}
