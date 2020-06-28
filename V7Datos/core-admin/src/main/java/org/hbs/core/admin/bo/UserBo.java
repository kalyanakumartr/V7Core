package org.hbs.core.admin.bo;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.util.Collection;
import java.util.List;

import org.apache.kafka.common.errors.InvalidRequestException;
import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.model.Users;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.LabelValueBean;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserBo extends Serializable
{
	EnumInterface blockUser(Authentication auth, UserFormBean ufBean) throws InvalidRequestException;

	EnumInterface deleteUser(Authentication auth, UserFormBean ufBean) throws InvalidRequestException;

	List<LabelValueBean> getCityList(Authentication auth, UserFormBean userFormBean);

	List<LabelValueBean> getCountryList(Authentication auth, UserFormBean userFormBean);

	List<LabelValueBean> getStateList(Authentication auth, UserFormBean userFormBean);

	Users getUser(UserFormBean ufBean) throws InvalidRequestException;

	List<Users> getUserListByProducer(Authentication auth);

	Collection<LabelValueBean> getUsersBySearchParam(Authentication auth, UserFormBean gmfBean);

	EnumInterface resendActivationLink(Authentication auth, UserFormBean userFormBean) throws InvalidKeyException, JsonProcessingException;

	EnumInterface saveUser(Authentication auth, UserFormBean ufBean) throws InvalidRequestException, InvalidKeyException;

	List<Users> searchUser(Authentication auth, UserFormBean ufBean) throws InvalidRequestException;

	EnumInterface updateUser(Authentication auth, UserFormBean ufBean) throws InvalidRequestException;

	Users getUserByEmailOrMobileOrUserId(String searchParam);

	boolean isRecentlyUpdated(Authentication auth, UserFormBean ufBean);

}
