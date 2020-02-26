package org.hbs.core.admin;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hbs.core.admin.bo.UserBo;
import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.model.Users;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ServerUtilFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserController
{
	private static final long	serialVersionUID	= -1046242631792313825L;

	private static final String	UTF8ENCODER			= "UTF-8";

	// private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserBo						userBo;

	@Override
	public ResponseEntity<?> addUser(Authentication auth, @RequestBody UserFormBean userFormBean)
	{
		try
		{
			// logger.info("UserController addUser starts ::: ");
			userBo.saveUser(auth, userFormBean);
			// logger.info("addUser ends ::: ");
			return new ResponseEntity<>(userFormBean, HttpStatus.OK);
		}
		catch (Exception excep)
		{
			userFormBean.user = null;
			userFormBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController addUser ::: ", excep);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> blockUser(Authentication auth, @RequestBody UserFormBean userFormBean)
	{
		try
		{
			// logger.info("UserController blockUser starts ::: ", userFormBean.user.getUserId());
			if (CommonValidator.isNotNullNotEmpty(userFormBean.user))
			{
				return new ResponseEntity<EnumInterface>(userBo.blockUser(auth, userFormBean), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			userFormBean.user = null;
			userFormBean.repoUser = null;
			userFormBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController blockUser ::: ", excep);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> deleteUser(Authentication auth, @RequestBody UserFormBean userFormBean)
	{
		try
		{
			// logger.info("UserController deleteUser starts ::: ", userFormBean.user.getUserId());
			if (CommonValidator.isNotNullNotEmpty(userFormBean.user))
			{
				return new ResponseEntity<EnumInterface>(userBo.deleteUser(auth, userFormBean), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);

		}
		catch (Exception excep)
		{
			userFormBean.user = null;
			userFormBean.repoUser = null;
			userFormBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController deleteUser ::: ", excep);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> getAllUsers(Authentication auth, @RequestBody UserFormBean userFormBean)
	{
		try
		{
			// logger.info("Inside UserController getAllUsers ::: ");
			List<Users> userList = userBo.searchUser(auth, userFormBean);
			if (CommonValidator.isListFirstNotEmpty(userList)) {
				for(Users users : userList)
				{
					users.setCreatedDateByTimeZone(users.getCountry().getCountry());
					users.setModifiedDateByTimeZone(users.getCountry().getCountry());
				}
				return new ResponseEntity<List<Users>>(userList, HttpStatus.OK);
			}
			else
				return new ResponseEntity<List<Users>>(new ArrayList<Users>(0), HttpStatus.OK);
		}
		catch (Exception e)
		{
			userFormBean = new UserFormBean();
			userFormBean.messageCode = e.getMessage();
			// logger.error("Exception in UserController getAllUsers ::: ", e);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getUserByEmailOrMobileOrUserId(Authentication auth, @RequestBody UserFormBean userFormBean)
	{
		try
		{
			System.out.println(">>>>>>>>>>>>>>>>>>>>>_DomainURL>>>>>>>>>>>>>>>>>>>>>>>> " + ServerUtilFactory.getInstance().getDomainURL());
			// logger.info("Inside UserController getUserByEmailOrMobileOrUserId ::: ");
			Users users = userBo.getUserByEmailOrMobileOrUserId(userFormBean.searchParam);
			users.setCreatedDateByTimeZone(users.getCountry().getCountry());
			users.setModifiedDateByTimeZone(users.getCountry().getCountry());

			return new ResponseEntity<Users>(users, HttpStatus.OK);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			userFormBean = new UserFormBean();
			userFormBean.messageCode = e.getMessage();
			// logger.error("Exception in UserController getUserByEmailOrMobileOrUserId ::: ", e);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCities(Authentication auth, @RequestBody UserFormBean userFormBean)
	{
		try
		{
			// logger.info("Inside UserController getCities ::: ");
			return new ResponseEntity<>(userBo.getCityList(auth, userFormBean), HttpStatus.OK);
		}
		catch (Exception excep)
		{
			userFormBean.user = null;
			userFormBean.repoUser = null;
			userFormBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController getCities ::: ", excep);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> getCountry(Authentication auth, @RequestBody UserFormBean userFormBean)
	{
		try
		{
			// logger.info("Inside UserController getCountry ::: ");
			return new ResponseEntity<>(userBo.getCountryList(auth, userFormBean), HttpStatus.OK);
		}
		catch (Exception excep)
		{
			userFormBean.user = null;
			userFormBean.repoUser = null;
			userFormBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController getCountry ::: ", excep);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getStates(Authentication auth, @RequestBody UserFormBean userFormBean)
	{
		try
		{
			// logger.info("Inside UserController getStates ::: ");
			return new ResponseEntity<>(userBo.getStateList(auth, userFormBean), HttpStatus.OK);
		}
		catch (Exception excep)
		{
			userFormBean.user = null;
			userFormBean.repoUser = null;
			userFormBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController getStates ::: ", excep);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getUser(Authentication auth, @RequestBody UserFormBean userFormBean)
	{
		try
		{
			// logger.info("UserController getUser starts ::: ", userFormBean.user.getUserId());
			if (CommonValidator.isNotNullNotEmpty(userFormBean.user))
			{
				return new ResponseEntity<Users>(userBo.getUser(userFormBean), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);

		}
		catch (Exception excep)
		{
			userFormBean.user = null;
			userFormBean.repoUser = null;
			userFormBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController getUser ::: ", excep);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getUserListByProducer(Authentication auth)
	{
		try
		{
			// logger.info("Inside UserController getUserByProducer ::: ");
			return new ResponseEntity<List<Users>>(userBo.getUserListByProducer(auth), HttpStatus.OK);
		}
		catch (Exception excep)
		{
			UserFormBean userFormBean = new UserFormBean();
			userFormBean.user = null;
			userFormBean.repoUser = null;
			userFormBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController getUserByProducer ::: ", excep);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> resendActivationLink(Authentication auth, @RequestBody UserFormBean userFormBean)
	{
		try
		{
			// logger.info("Inside UserController resendActivationLink ::: ");
			return new ResponseEntity<>(userBo.resendActivationLink(auth, userFormBean), HttpStatus.OK);
		}
		catch (Exception e)
		{
			userFormBean.user = null;
			userFormBean.messageCode = e.getMessage();
			// logger.error("Exception in UserController resendActivationLink ::: ", e);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> search(Authentication auth, @RequestBody UserFormBean userFormBean)
	{
		try
		{
			// logger.info("Inside UserController search ::: ");
			return new ResponseEntity<List<Users>>(userBo.searchUser(auth, userFormBean), HttpStatus.OK);
		}
		catch (Exception excep)
		{
			userFormBean.user = null;
			userFormBean.repoUser = null;
			userFormBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController search ::: ", excep);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> updateUser(Authentication auth, @RequestBody UserFormBean userFormBean)
	{
		try
		{
			// logger.info("Inside UserController updateUser ::: ");
			if (CommonValidator.isNotNullNotEmpty(userFormBean, userFormBean.user))
			{
				userBo.updateUser(auth, userFormBean);
				return new ResponseEntity<>(userFormBean, HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);

		}
		catch (Exception excep)
		{
			userFormBean.user = null;
			userFormBean.repoUser = null;
			userFormBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController updateUser ::: ", excep);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void validateUser(@PathVariable("token") String token, HttpServletResponse response)
	{
		try
		{
			// logger.info("UserController validateUser starts :::");
			String redirectUri = null;
			UserFormBean ufBean = userBo.validateUser(token);
			EFormAction formAction = ESecurity.Token.getTokenAction(token);
			if (EFormAction.PasswordChanged.name().equals(ufBean.messageCode))
				formAction = EFormAction.PasswordChanged;
			else if (EFormAction.TokenExpired.name().equals(ufBean.messageCode))
				formAction = EFormAction.TokenExpired;

			switch ( formAction )
			{
//				case ForgotPassword :
//					redirectUri = MicroServices.UI.getUrl(IPathAdmin.RESET_PASSWORD) + URLEncoder.encode(token, UTF8ENCODER);
//					break;
//				case Verify :
//					redirectUri = MicroServices.UI.getUrl(IPathAdmin.CHANGE_PASSWORD) + URLEncoder.encode(token, UTF8ENCODER);
//					break;
//				case PasswordChanged :
//					redirectUri = MicroServices.UI.getUrl(IPathAdmin.PASSWORD_CHANGED) + URLEncoder.encode(token, UTF8ENCODER);
//					break;
//				case TokenExpired :
//					redirectUri = MicroServices.UI.getUrl(IPathAdmin.TOKEN_EXPIRED) + URLEncoder.encode(token, UTF8ENCODER);
//					break;
				default :
					break;
			}
			// logger.info("UserController validateUser ends ::: ", redirectUri);
			response.sendRedirect(redirectUri);

		}
		catch (InvalidKeyException excep)
		{
//			try
//			{
//				// logger.error("InvalidKeyException in validateUser ::: ", excep);
//				response.sendRedirect(MicroServices.UI.getUrl(IPathAdmin.ERROR500) + "?errorMsg=" + URLEncoder.encode(excep.getMessage(), UTF8ENCODER));
//			}
//			catch (UnsupportedEncodingException e)
//			{
//				e.printStackTrace();
//			}
//			catch (IOException e)
//			{
//				e.printStackTrace();
//			}
		}
		catch (IOException e)
		{
			// logger.error("Exception in UserController validateUser ::: ", e);
			e.printStackTrace();
		}
	}

}
