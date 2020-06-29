package org.hbs.core.beans.path;

import java.sql.Timestamp;
import java.util.UUID;

import javax.management.openmbean.InvalidKeyException;

import org.apache.commons.codec.binary.Base64;
import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.model.Users;
import org.hbs.core.dao.UserDao;
import org.hbs.core.security.resource.EnumResourceInterface;
import org.hbs.core.security.resource.IPath;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;

public interface IPathAdmin extends IPath, IErrorAdmin
{
	public String	LOGIN						= "/login";
	public String	SWAGGER_UI					= "/swagger-ui.html";
	public String	SWAGGER_WEBJARS				= "/webjars/**";
	public String	ERROR404					= "/404Error";
	public String	ERROR500					= "/500Error";

	// User Admin Module
	public String	PRESEARCH_USER				= "/preSearchUser";
	public String	GET_USERS_LIST_BY_ROLE		= "/getUsersListByRoleName";
	public String	GET_ACTIVE_USER				= "/getActiveUser";
	public String	SEARCH_COUNTRY				= "/searchCountry";
	public String	SEARCH_STATE				= "/searchState";
	public String	SEARCH_CITY					= "/searchCity";
	public String	SEARCH_USER					= "/searchUser";
	public String	SEARCH_GROUP_USER			= "/searchGroupUser";
	public String	PREADD_USER					= "/preAddUser";
	public String	ADD_USER					= "/addUser";
	public String	PREUPDATE_USER				= "/preUpdateUser";
	public String	UPDATE_USER					= "/updateUser";
	public String	BLOCK_USER					= "/blockUser";
	public String	DELETE_USER					= "/deleteUser";
	public String	VALIDATE_USER_BASE			= "/validateUser";
	public String	VALIDATE_USER				= VALIDATE_USER_BASE + "/{token}";
	public String	RESEND_ACTIVATION_LINK		= "/resendActivationLink";
	public String	GET_ALL_USERS				= "getAllUsers";
	public String	GET_USERS_BY_PRODUCER		= "/getUserByProducer";
	// Group
	public String	GET_GROUP_LIST				= "/getGroupList";
	public String	GET_GROUP					= "/getGroup";
	public String	GET_GROUP_BY_CUST_ID		= "/getGroupByProducer";
	public String	ADD_GROUP					= "/addGroup";
	public String	UPDATE_GROUP				= "/updateGroup";
	public String	DELETE_GROUP				= "/deleteGroup";
	public String	BLOCK_GROUP					= "/blockGroup";
	public String	SEARCH_GROUP				= "/searchGroup";
	public String	CHECK_GROUP_EXISTS			= "/checkGroupExists";

	// Group
	public String	GET_GROUPMEMBER_LIST		= "/getGroupMemberList";
	public String	GET_GROUPMEMBERS_BY_GROUP	= "/getGroupMembersByGroup";
	public String	GET_GROUPMEMBERS			= "/getGroupMembers";
	public String	ADD_GROUPMEMBERS			= "/addGroupMembers";
	public String	UPDATE_GROUPMEMBERS			= "/updateGroupMembers";
	public String	DELETE_GROUPMEMBERS			= "/deleteGroupMembers";
	public String	BLOCK_GROUPMEMBERS			= "/deleteGroupMemberDataById";

	// Producer
	public String	PRESEARCH_CUSTOMER			= "/preSearchProducer";
	public String	SEARCH_CUSTOMER				= "/searchProducer";
	public String	ADD_CUSTOMER				= "/addProducer";
	public String	PREUPDATE_CUSTOMER			= "/getProducer";
	public String	UPDATE_CUSTOMER				= "/updateProducer";
	public String	CUSTOMER_LIST				= "/getProducerList";
	public String	BLOCK_CUSTOMER				= "/blockProducer";
	public String	DELETE_CUSTOMER				= "/deleteProducer";
	public String	CHECK_CUSTOMER_EXIST		= "/checkProducerExist";

	// Password
	public String	GENERATE_OTP				= "/generateOTP";
	public String	VALIDATE_OTP				= "/validateOTP";
	public String	CHANGE_PASSWORD_BASE		= "/changePassword";
	public String	CHANGE_PASSWORD				= CHANGE_PASSWORD_BASE + "/{token}";
	public String	FORGOT_PASSWORD				= "/forgotPassword";
	public String	UPDATE_PASSWORD				= "/updatePassword";
	public String	TOKEN_EXPIRED				= "/tokenExpired";

	public long		TOKEN_EXPIRY_DURATION		= 86400000l;

	// Department
	public String	UPDATE_DEPARTMENT			= "/updateDepartment";
	public String	BLOCK_DEPARTMENT			= "/blockDepartment";
	public String	CHECK_DEPARTMENT_EXIST		= "/checkDepartmentExist";
	public String	ADD_DEPARTMENT				= "/addDepartment";
	public String	GET_DEPARTMENT_LIST			= "/getDepartmentList";
	public String	GET_DEPARTMENT				= "/getDepartment";

	public enum EPathAdmin implements EnumResourceInterface
	{
		PreSearchProducer(PRESEARCH_CUSTOMER, ERole.Administrator), //
		SearchProducer(SEARCH_CUSTOMER, ERole.Administrator), //
		AddProducer(ADD_CUSTOMER, ERole.Administrator), //
		PreUpdateProducer(PREUPDATE_CUSTOMER, ERole.Administrator), //
		UpdateProducer(UPDATE_CUSTOMER, ERole.Administrator), //
		BlockProducer(BLOCK_CUSTOMER, ERole.Administrator), //
		CheckProducerExist(CHECK_CUSTOMER_EXIST, ERole.Administrator), //
		// GenerateOTP(GENERATE_OTP, ERole.Administrator, ERole.Employee, ERole.User), //
		// ValidateOTP(VALIDATE_OTP, ERole.Administrator, ERole.Employee, ERole.User), //
		// ChangePassword(CHANGE_PASSWORD, ERole.Administrator, ERole.Employee, ERole.User), //

		PreSearchUser(PRESEARCH_USER, ERole.Administrator, ERole.Employee), //
		GetActiveUser(GET_ACTIVE_USER, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		SearchUser(SEARCH_USER, ERole.Administrator, ERole.Employee), //
		SearchGroupUser(SEARCH_GROUP_USER, ERole.Administrator, ERole.Employee), //
		PreAddUser(PREADD_USER, ERole.Administrator), //
		AddUser(ADD_USER, ERole.Administrator), //
		PreUpdateUser(PREUPDATE_USER, ERole.Administrator), //
		UpdateUser(UPDATE_USER, ERole.Administrator), //
		BlockUser(BLOCK_USER, ERole.Administrator, ERole.Employee), //
		DeleteUser(DELETE_USER, ERole.Administrator), //
		GetAllUsers(GET_ALL_USERS, ERole.Administrator, ERole.Employee), //
		GetUsersByProducer(GET_USERS_BY_PRODUCER, ERole.Administrator, ERole.Employee), SearchCountry(SEARCH_COUNTRY, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		SearchStates(SEARCH_STATE, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		SearchCities(SEARCH_CITY, ERole.Administrator, ERole.Employee, ERole.Consumer), //

		// Group
		getGroupList(GET_GROUP_LIST, ERole.Administrator), // Dummy To Delete
		GetGroup(GET_GROUP, ERole.Administrator, ERole.Employee), //
		GetGroupByProducer(GET_GROUP_BY_CUST_ID, ERole.Administrator, ERole.Employee), //
		SaveGroup(ADD_GROUP, ERole.Administrator), //
		UpdateGroup(UPDATE_GROUP, ERole.Administrator), //
		BlockGroup(BLOCK_GROUP, ERole.Administrator), //
		SearchGroup(SEARCH_GROUP, ERole.Administrator), //
		CheckGroupExists(CHECK_GROUP_EXISTS, ERole.Administrator, ERole.Employee), //

		// Group Member
		getGroupMemberList(GET_GROUPMEMBER_LIST, ERole.Administrator), // Dummy To Delete
		GetGroupMembers(GET_GROUPMEMBERS, ERole.Administrator, ERole.Employee), //
		GetGroupMembersByGroup(GET_GROUPMEMBERS_BY_GROUP, ERole.Administrator, ERole.Employee), //
		SaveGroupMembers(ADD_GROUPMEMBERS, ERole.Administrator), //
		UpdateGroupMembers(UPDATE_GROUPMEMBERS, ERole.Administrator), //
		BlockGroupMembers(BLOCK_GROUPMEMBERS, ERole.Administrator), //
		DeleteGroupMembers(DELETE_GROUPMEMBERS, ERole.Administrator), //

		// Department
		updateDepartment(UPDATE_DEPARTMENT, ERole.Administrator), //
		blockDepartment(BLOCK_DEPARTMENT, ERole.Administrator), //
		checkDepartmentExist(CHECK_DEPARTMENT_EXIST, ERole.Administrator), //
		getDepartmentList(GET_DEPARTMENT_LIST, ERole.Administrator), //
		addDepartment(ADD_DEPARTMENT, ERole.Administrator); //

		String	path;

		ERole	roles[];

		EPathAdmin(String path, ERole... roles)
		{
			this.path = path;
			this.roles = roles;
		}

		@Override
		public String getPath()
		{
			return this.path;
		}

		@Override
		public ERole[] getRoles()
		{
			return this.roles;
		}
	}

	public enum ESecurity implements EnumInterface
	{
		Token;

		public String generate(Users user, EFormAction eFormAction)
		{
			String tokenKey = UUID.randomUUID().toString();
			user.setToken(tokenKey);
			user.setTokenExpiryDate(new Timestamp(System.currentTimeMillis()));
			user.setUserPwdModFlag(false);
			tokenKey = Base64.encodeBase64String((user.getPrimaryMedia().getEmailId() + HASH + user.getToken() + HASH + eFormAction.name()).getBytes());
			if(tokenKey.trim().endsWith(DOUBLE_EQUAL_TO))
			{
				tokenKey = DOUBLE_EQUAL_TO + tokenKey.substring(0, tokenKey.indexOf(DOUBLE_EQUAL_TO));
			}
			StringBuffer tokenURL = new StringBuffer();
			tokenURL.append(VALIDATE_USER_BASE + SLASH);
			tokenURL.append(new StringBuffer(tokenKey).reverse());
			return tokenURL.toString();
		}

		public UserFormBean validate(UserDao userDao, String tokenKey, long expiryDuration)
		{
			UserFormBean ufBean = new UserFormBean();

			tokenKey = new StringBuffer(tokenKey).reverse().toString();
			if(tokenKey.trim().startsWith(DOUBLE_EQUAL_TO))
			{
				tokenKey = tokenKey.substring(2) + DOUBLE_EQUAL_TO;
			}
			
			String tokenInfo[] = new String(Base64.decodeBase64(tokenKey)).split(HASH);

			if (CommonValidator.isArrayFirstNotNull(tokenInfo) && tokenInfo.length == 3)
			{
				Object object = userDao.findByEmailOrMobileOrUserId(tokenInfo[0]);

				if (object != null && CommonValidator.isArrayFirstNotNull((Object[]) object))
				{
					ufBean.formUser = (Users) ((Object[]) object)[0];
					if (CommonValidator.isNotNullNotEmpty(ufBean.formUser.getTokenExpiryDate()))
					{
						if (CommonValidator.isEqual(tokenInfo[2], EFormAction.ForgotPassword) || CommonValidator.isEqual(tokenInfo[2], EFormAction.Verify))
						{
							long difference = System.currentTimeMillis() - ufBean.formUser.getTokenExpiryDate().getTime();

							if (difference <= expiryDuration)
							{
								ufBean.formAction = EFormAction.valueOf(tokenInfo[2]);
								return ufBean;
							}
							else
							{
								ufBean.formAction = EFormAction.TokenExpired;
								return ufBean;
							}
						}
					}
					throw new InvalidKeyException(USER_TOKEN_KEY_EXPIRED);
				}
			}
			throw new InvalidKeyException(USER_TOKEN_KEY_NOT_AVAILABLE_IN_REQUEST);
		}

	}
}
