package org.hbs.core.admin;

import java.util.List;

import org.hbs.core.admin.bo.PasswordBo;
import org.hbs.core.admin.bo.UserBo;
import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.model.Users;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AnanthMalBal
 */
@RestController
public class UserController implements IUserController
{
	private static final long	serialVersionUID	= -1046242631792313825L;

	// private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserBo						userBo;

	@Autowired
	PasswordBo					passwordBo;

	// Postman Implemented
	@Override
	public ResponseEntity<?> addUser(Authentication auth, @RequestBody UserFormBean ufBean)
	{
		try
		{
			// logger.info("UserController addUser starts ::: ");
			userBo.saveUser(auth, ufBean);
			// logger.info("addUser ends ::: ");
			return new ResponseEntity<>(ufBean, HttpStatus.OK);
		}
		catch (Exception excep)
		{
			ufBean.formUser = null;
			ufBean.messageCode = excep.getMessage();
			ufBean = new UserFormBean();
			// logger.error("Exception in UserController addUser ::: ", excep);
			return new ResponseEntity<>(ufBean, HttpStatus.BAD_REQUEST);
		}
	}

	// Postman Implemented
	@Override
	public ResponseEntity<?> blockUser(Authentication auth, @RequestBody UserFormBean ufBean) //
	{
		try
		{
			// logger.info("UserController blockUser starts ::: ", ufBean.user.getUserId());
			if (CommonValidator.isNotNullNotEmpty(ufBean.formUser))
			{
				return new ResponseEntity<EnumInterface>(userBo.blockUser(auth, ufBean), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			excep.printStackTrace();
			ufBean.formUser = null;
			ufBean.user = null;
			ufBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController blockUser ::: ", excep);
			return new ResponseEntity<>(ufBean, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * NOT Yet Implemented...
	 */
	@Override
	public ResponseEntity<?> deleteUser(Authentication auth, @RequestBody UserFormBean ufBean)//
	{
		try
		{
			// logger.info("UserController deleteUser starts ::: ", ufBean.user.getUserId());
			if (CommonValidator.isNotNullNotEmpty(ufBean.formUser))
			{
				return new ResponseEntity<EnumInterface>(userBo.deleteUser(auth, ufBean), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);

		}
		catch (Exception excep)
		{
			ufBean.formUser = null;
			ufBean.user = null;
			ufBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController deleteUser ::: ", excep);
			return new ResponseEntity<>(ufBean, HttpStatus.BAD_REQUEST);
		}
	}

	// Postman Implemented
	@Override
	public ResponseEntity<?> getCities(Authentication auth, @RequestBody UserFormBean ufBean)//
	{
		try
		{
			// logger.info("Inside UserController getCities ::: ");
			return new ResponseEntity<>(userBo.getCityList(auth, ufBean), HttpStatus.OK);
		}
		catch (Exception excep)
		{
			ufBean.formUser = null;
			ufBean.user = null;
			ufBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController getCities ::: ", excep);
			return new ResponseEntity<>(ufBean, HttpStatus.BAD_REQUEST);
		}
	}

	// Postman Implemented
	public ResponseEntity<?> getCountry(Authentication auth, @RequestBody UserFormBean ufBean)
	{
		try
		{
			// logger.info("Inside UserController getCountry ::: ");
			return new ResponseEntity<>(userBo.getCountryList(auth, ufBean), HttpStatus.OK);
		}
		catch (Exception excep)
		{
			ufBean.formUser = null;
			ufBean.user = null;
			ufBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController getCountry ::: ", excep);
			return new ResponseEntity<>(ufBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getStates(Authentication auth, @RequestBody UserFormBean ufBean)//
	{
		try
		{
			// logger.info("Inside UserController getStates ::: ");
			return new ResponseEntity<>(userBo.getStateList(auth, ufBean), HttpStatus.OK);
		}
		catch (Exception excep)
		{
			ufBean.formUser = null;
			ufBean.user = null;
			ufBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController getStates ::: ", excep);
			return new ResponseEntity<>(ufBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getUser(Authentication auth, @RequestBody UserFormBean ufBean)//
	{
		try
		{
			// logger.info("UserController getUser starts ::: ", ufBean.user.getUserId());
			if (CommonValidator.isNotNullNotEmpty(ufBean.formUser))
			{
				return new ResponseEntity<Users>(userBo.getUser(ufBean), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);

		}
		catch (Exception excep)
		{
			ufBean.formUser = null;
			ufBean.user = null;
			ufBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController getUser ::: ", excep);
			return new ResponseEntity<>(ufBean, HttpStatus.BAD_REQUEST);
		}
	}

	// Postman Implemented
	@Override
	public ResponseEntity<?> getUserByEmailOrMobileOrUserId(Authentication auth, @RequestBody UserFormBean ufBean)//
	{
		try
		{
			// logger.info("Inside UserController getUserByEmailOrMobileOrUserId ::: ");
			Users users = userBo.getUserByEmailOrMobileOrUserId(ufBean.searchParam);

			return new ResponseEntity<Users>(users, HttpStatus.OK);
		}
		catch (Exception excep)
		{
			ufBean = new UserFormBean();
			ufBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController getUserByEmailOrMobileOrUserId ::: ",
			// excep);
			return new ResponseEntity<>(ufBean, HttpStatus.BAD_REQUEST);
		}
	}

	// Postman Implemented
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
			UserFormBean ufBean = new UserFormBean();
			ufBean.formUser = null;
			ufBean.user = null;
			ufBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController getUserByProducer ::: ", excep);
			return new ResponseEntity<>(ufBean, HttpStatus.BAD_REQUEST);
		}
	}

	// Postman Implemented
	@Override
	public ResponseEntity<?> resendActivationLink(Authentication auth, @RequestBody UserFormBean ufBean)//
	{
		try
		{
			// logger.info("Inside UserController resendActivationLink ::: ");
			userBo.resendActivationLink(auth, ufBean);
			return new ResponseEntity<>(ufBean, HttpStatus.OK);
		}
		catch (Exception excep)
		{
			ufBean.formUser = null;
			ufBean.messageCode = ACTIVATION_LINK_SENT_FAILED;
			// logger.error("Exception in UserController resendActivationLink ::: ", e);
			return new ResponseEntity<>(ufBean, HttpStatus.BAD_REQUEST);
		}
	}

	// Postman Implemented
	@Override
	public ResponseEntity<?> search(Authentication auth, @RequestBody UserFormBean ufBean)
	{
		try
		{
			// logger.info("Inside UserController search ::: ");
			return new ResponseEntity<List<Users>>(userBo.searchUser(auth, ufBean), HttpStatus.OK);
		}
		catch (Exception excep)
		{
			ufBean.formUser = null;
			ufBean.user = null;
			ufBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController search ::: ", excep);
			return new ResponseEntity<>(ufBean, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Need To Implement UserMedia and User Address Stuffs
	 */
	// Postman Implemented
	@Override
	public ResponseEntity<?> updateUser(Authentication auth, @RequestBody UserFormBean ufBean)
	{
		try
		{
			// logger.info("Inside UserController updateUser ::: ");
			if (CommonValidator.isNotNullNotEmpty(ufBean, ufBean.formUser))
			{
				userBo.updateUser(auth, ufBean);
				return new ResponseEntity<>(ufBean, HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);

		}
		catch (Exception excep)
		{
			ufBean.formUser = null;
			ufBean.user = null;
			ufBean.messageCode = excep.getMessage();
			// logger.error("Exception in UserController updateUser ::: ", excep);
			return new ResponseEntity<>(ufBean, HttpStatus.BAD_REQUEST);
		}
	}

}
