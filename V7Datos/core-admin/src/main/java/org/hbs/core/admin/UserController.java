package org.hbs.core.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hbs.core.admin.bo.UserBo;
import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.model.Users;
import org.hbs.core.beans.path.IPathAdmin;
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

/**
 * @author AnanthMalBal
 */
/**
 * @author AnanthMalBal
 */
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
	public ResponseEntity<?> blockUser(Authentication auth, @RequestBody UserFormBean userFormBean) //
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
			excep.printStackTrace();
			userFormBean.user = null;
			userFormBean.repoUser = null;
			userFormBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController blockUser ::: ", excep);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * NOT Yet Implemented...
	 */
	@Override
	public ResponseEntity<?> deleteUser(Authentication auth, @RequestBody UserFormBean userFormBean)//
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

	@Override
	public ResponseEntity<?> getUserByEmailOrMobileOrUserId(Authentication auth, @RequestBody UserFormBean userFormBean)//
	{
		try
		{
			// logger.info("Inside UserController getUserByEmailOrMobileOrUserId ::: ");
			Users users = userBo.getUserByEmailOrMobileOrUserId(userFormBean.searchParam);
			users.setCreatedDateByTimeZone(users.getCountry().getCountry());
			users.setModifiedDateByTimeZone(users.getCountry().getCountry());

			return new ResponseEntity<Users>(users, HttpStatus.OK);
		}
		catch (Exception excep)
		{
			userFormBean = new UserFormBean();
			userFormBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController getUserByEmailOrMobileOrUserId ::: ", excep);
			return new ResponseEntity<>(userFormBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCities(Authentication auth, @RequestBody UserFormBean userFormBean)//
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

	public ResponseEntity<?> getCountry(Authentication auth, @RequestBody UserFormBean userFormBean)//
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
	public ResponseEntity<?> getStates(Authentication auth, @RequestBody UserFormBean userFormBean)//
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
	public ResponseEntity<?> getUser(Authentication auth, @RequestBody UserFormBean userFormBean)//
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
	public ResponseEntity<?> getUserListByProducer(Authentication auth)//
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
	public ResponseEntity<?> resendActivationLink(Authentication auth, @RequestBody UserFormBean userFormBean)//
	{
		try
		{
			// logger.info("Inside UserController resendActivationLink ::: ");
			userBo.resendActivationLink(auth, userFormBean);
			return new ResponseEntity<>(userFormBean.messageCode, HttpStatus.OK);
		}
		catch (Exception excep)
		{
			userFormBean.user = null;
			userFormBean.messageCode = ACTIVATION_LINK_SENT_FAILED;
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
		String redirectUri = "";
		try
		{
			// logger.info("UserController validateUser starts :::");

			UserFormBean ufBean = userBo.validateUser(token);
			EFormAction formAction = ESecurity.Token.getTokenAction(token);
			if (EFormAction.PasswordChanged.name().equals(ufBean.messageCode))
				formAction = EFormAction.PasswordChanged;
			else if (EFormAction.TokenExpired.name().equals(ufBean.messageCode))
				formAction = EFormAction.TokenExpired;

			switch ( formAction )
			{
				case ForgotPassword :
					redirectUri = ServerUtilFactory.getInstance().getDomainURL(IPathAdmin.RESET_PASSWORD);
					break;
				case Verify :
					redirectUri = ServerUtilFactory.getInstance().getDomainURL(IPathAdmin.CHANGE_PASSWORD);
					break;
				case PasswordChanged :
					redirectUri = ServerUtilFactory.getInstance().getDomainURL(IPathAdmin.PASSWORD_CHANGED);
					break;
				case TokenExpired :
					redirectUri = ServerUtilFactory.getInstance().getDomainURL(IPathAdmin.TOKEN_EXPIRED);
					break;
				default :
					break;
			}
			// logger.info("UserController validateUser ends ::: ", redirectUri);
			redirectUri = redirectUri + URLEncoder.encode(token, UTF8ENCODER);

		}
		catch (InvalidKeyException excep)
		{
			try
			{
				// logger.error("InvalidKeyException in validateUser ::: ", excep);
				redirectUri = ServerUtilFactory.getInstance().getDomainURL(IPathAdmin.ERROR500);
				redirectUri += "?errorMsg=" + URLEncoder.encode(excep.getMessage(), UTF8ENCODER);
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		catch (IOException e)
		{
			// logger.error("Exception in UserController validateUser ::: ", e);
			e.printStackTrace();
		}
		finally
		{
			try
			{
				response.sendRedirect(redirectUri);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}
