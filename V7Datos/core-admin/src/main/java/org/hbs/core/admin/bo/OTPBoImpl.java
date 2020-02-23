package org.hbs.core.admin.bo;

import java.security.InvalidKeyException;
import java.util.concurrent.ExecutionException;

import org.hbs.core.admin.OTPService;
import org.hbs.core.beans.GenericKafkaProducer;
import org.hbs.core.beans.OTPFormBean;
import org.hbs.core.beans.model.Users;
import org.hbs.core.dao.UserDao;
import org.hbs.core.security.resource.IPath.EMedia;
import org.hbs.core.security.resource.IPath.ETemplate;
import org.hbs.core.security.resource.IPath.ETopic;
import org.hbs.core.util.ServerUtilFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OTPBoImpl implements OTPBo
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

	@SuppressWarnings("unused")
	@Autowired
	private ServerUtilFactory	serverUtilFactory;

	@Override
	public String generateOTP(OTPFormBean otpFormBean) throws InvalidKeyException
	{
		Users user = userBo.getUserByEmailOrMobileOrUserId(otpFormBean.user.getUserId());
		if (user == null)
			throw new InvalidKeyException("Invalid user Id");
		otpFormBean.user = user;
		String[] otps = otpService.generate(user.getUserId(), 6);
		otpFormBean.id = otps[0];
		otpFormBean.user.setOtp(otps[1]);
		userDao.save(user);
		try
		{
			gKafkaProducer.sendMessage(ETopic.Internal, EMedia.SMS, ETemplate.SMS_OTP, otpFormBean);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return otps[0];
	}

	@Override
	public String validateOTP(OTPFormBean otpFormBean) throws ExecutionException
	{
		return otpService.validate(otpFormBean.id, otpFormBean.user.getOtp(), otpFormBean.otp).name();
	}
}
