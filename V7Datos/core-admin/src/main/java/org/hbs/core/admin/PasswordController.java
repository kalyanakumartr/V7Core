package org.hbs.core.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.hbs.core.admin.bo.PasswordBo;
import org.hbs.core.beans.APIStatus;
import org.hbs.core.beans.PasswordFormBean;
import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.path.IPathAdmin;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.ServerUtilFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController implements IPasswordController
{
	private static final long	serialVersionUID	= 1355253953332836713L;

	private static final String	UTF8ENCODER			= "UTF-8";

	@Autowired
	protected PasswordBo		passwordBo;

	// Incoming To Server (via Email Link) and Outgoing To Browser Redirect
	@Override
	public void validateUser(@PathVariable("token") String token, HttpServletResponse response)
	{
		String redirectUri = "";
		try
		{
			// logger.info("UserController validateUser starts :::");

			UserFormBean ufBean = (UserFormBean) passwordBo.validateUser(token);

			switch ( ufBean.formAction )
			{
				case ForgotPassword :
				case Verify :
					redirectUri = ServerUtilFactory.getInstance().getDomainURL(IPathAdmin.CHANGE_PASSWORD_BASE, SLASH, token);
					break;
				case TokenExpired :
					redirectUri = ServerUtilFactory.getInstance().getDomainURL(IPathAdmin.TOKEN_EXPIRED);
					break;
				default :
					break;
			}
			// logger.info("UserController validateUser ends ::: ", redirectUri);
		}
		catch (Exception excep)
		{
			// logger.error("Exception in UserController validateUser ::: ", e);
			try
			{
				// logger.error("InvalidKeyException in validateUser ::: ", excep);
				redirectUri = ServerUtilFactory.getInstance().getDomainURL(IPathAdmin.ERROR500, "?errorMsg=", URLEncoder.encode(excep.getMessage(), UTF8ENCODER));
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
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

	// Incoming To Server (via Web) and Outgoing To Any Email Server
	@Override
	public ResponseEntity<?> sendForgotPasswordEmail(@RequestBody UserFormBean ufBean)
	{
		try
		{
			if (CommonValidator.isNotNullNotEmpty(ufBean.searchParam, ufBean.media))
			{
				return new ResponseEntity<>(passwordBo.forgotPassword(ufBean), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			return new ResponseEntity<>(EReturn.Failure, HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	// Incoming To Server (Redirect) and Outgoing To Browser
	public ResponseEntity<?> changePassword(@PathVariable("token") String token, HttpServletResponse response)
	{
		PasswordFormBean pfBean = new PasswordFormBean();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(token))
			{
				token = new StringBuffer(token).reverse().toString();
				if (token.trim().startsWith(DOUBLE_EQUAL_TO))
				{
					token = token.substring(2) + DOUBLE_EQUAL_TO;
				}
				String tokenInfo[] = new String(Base64.decodeBase64(token)).split(HASH);

				if (CommonValidator.isArrayFirstNotNull(tokenInfo) && tokenInfo.length == 3)
				{
					pfBean.emailId = tokenInfo[0];
					pfBean.$passwordToken = new BCryptPasswordEncoder().encode(pfBean.emailId);
					pfBean.formAction = EFormAction.valueOf(tokenInfo[2]);
					return new ResponseEntity<>((APIStatus) pfBean, HttpStatus.OK);
				}
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			pfBean.messageCode = excep.getMessage();
			return new ResponseEntity<>(pfBean, HttpStatus.BAD_REQUEST);
		}
	}

	// Incoming To Server (via Web) and Outgoing To Browser
	public ResponseEntity<?> updatePassword(Authentication auth, @RequestBody PasswordFormBean pfBean)
	{
		try
		{
			if (pfBean.validatePasswordToken())
			{
				if (passwordBo.updatePassword(auth, pfBean) == EReturn.Success)
					return new ResponseEntity<>((APIStatus) pfBean, HttpStatus.OK);
				else
					return new ResponseEntity<>((APIStatus) pfBean, HttpStatus.BAD_REQUEST);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			pfBean.messageCode = excep.getMessage();
			return new ResponseEntity<>(pfBean, HttpStatus.BAD_REQUEST);
		}
	}
}
