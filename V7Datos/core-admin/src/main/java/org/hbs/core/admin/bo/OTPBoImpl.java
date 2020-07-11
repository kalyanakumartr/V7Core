package org.hbs.core.admin.bo;

import java.security.InvalidKeyException;
import java.util.concurrent.ExecutionException;

import org.hbs.core.admin.OTPService;
import org.hbs.core.beans.PasswordFormBean;
import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.path.IErrorAdmin;
import org.hbs.core.dao.UserDao;
import org.hbs.core.kafka.GenericKafkaProducer;
import org.hbs.core.kafka.IKafkaConstants.ETopic;
import org.hbs.core.security.resource.IPath.ETemplate;
import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.security.resource.IPathBase.EReturn;
import org.hbs.core.util.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
@Transactional
public class OTPBoImpl implements OTPBo, IErrorAdmin
{
	private static final long	serialVersionUID	= 1949352771664273090L;

	@Autowired
	protected OTPService		otpService;

	@Autowired
	GenericKafkaProducer		gKafkaProducer;

	@Autowired
	protected UserBo			userBo;

	@Autowired
	protected UserDao			userDao;

	@Override
	public void generateOTP(PasswordFormBean pfBean) throws InvalidKeyException, JsonProcessingException
	{
		UserFormBean ufBean = new UserFormBean(pfBean);
		try
		{
			// Authentication object, we can't get at this request call
			if (userBo.isRecentlyUpdated(null, ufBean))
			{
				// First 3 Characters are ANY Alphabets and Next 6 Characters are Numbers
				// opts = ["REZ","REZ123456"]
				String[] otps = otpService.generate(ufBean.user.getUserId(), 6);

				// We are using Cache for OTP Validation and we are NOT persist otp in User table
				// column otp, may use in future
				// ufBean.user.modifiedUserInfo(null);
				// ufBean.user.setUserStatus(EUserStatus.OTPEnabled);
				// userDao.save(ufBean.user);

				if (CommonValidator.isArrayFirstNotNull(otps))
				{
					ufBean.user.setOtp(otps[1]); // ["REZ123456"]
					gKafkaProducer.send(ETopic.Internal, EMedia.SMS, ETemplate.SMS_OTP, ufBean);
					pfBean.messageCode = USER_SMS_OTP_GENERATE_SUCCESSFULLY;
					pfBean.$OTPPrefix = otps[0]; // ["REZ"]
					pfBean.userId = ufBean.user.getUserId();
				}
				else
					throw new InvalidKeyException(USER_SMS_OTP_GENERATE_ISSUE);
			}
			else
				throw new InvalidKeyException(USER_DATA_UPDATED_RECENTLY);
		}
		finally
		{
			ufBean = null;
		}

	}

	@Override
	public EReturn validateOTP(PasswordFormBean pfBean) throws ExecutionException
	{
		return otpService.validate(pfBean.$OTPPrefix, pfBean.userId, pfBean.$OTP);
	}
}
