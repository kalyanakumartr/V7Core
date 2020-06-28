package org.hbs.core.admin;

import org.hbs.core.admin.bo.OTPBo;
import org.hbs.core.beans.PasswordFormBean;
import org.hbs.core.util.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OTPController implements IOTPController
{
	private static final long	serialVersionUID	= 1355253953332836713L;

	@Autowired
	protected OTPBo				otpBo;

	public ResponseEntity<?> generateOTP(@RequestBody PasswordFormBean pfBean)
	{
		try
		{
			if (CommonValidator.isNotNullNotEmpty(pfBean.mobileNo, pfBean.media))
			{
				otpBo.generateOTP(pfBean);
				return new ResponseEntity<>(pfBean, HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			pfBean.clearForm();
			pfBean.messageCode = excep.getMessage();
			return new ResponseEntity<>(pfBean, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> validateOTP(@RequestBody PasswordFormBean pfBean)
	{
		try
		{
			if (otpBo.validateOTP(pfBean) == EReturn.Success)
			{
				pfBean.messageCode = USER_SMS_OTP_VALIDATED_SUCCESSFULLY;
				return new ResponseEntity<>(pfBean, HttpStatus.OK);
			}
			else
			{
				pfBean.clearForm();
				pfBean.messageCode = USER_SMS_OTP_INVALID_OR_EXPIRED;
				return new ResponseEntity<>(pfBean, HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception excep)
		{
			pfBean.clearForm();
			pfBean.messageCode = excep.getMessage();
			return new ResponseEntity<>(pfBean, HttpStatus.BAD_REQUEST);
		}
	}
}
