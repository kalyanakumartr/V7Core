package org.hbs.core.admin.bo;

import java.security.InvalidKeyException;
import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import org.hbs.core.beans.PasswordFormBean;
import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.model.IUsersBase.EUserStatus;
import org.hbs.core.beans.path.IErrorAdmin;
import org.hbs.core.beans.path.IPathAdmin;
import org.hbs.core.dao.UserDao;
import org.hbs.core.kafka.GenericKafkaProducer;
import org.hbs.core.kafka.IKafkaConstants.ETopic;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ServerUtilFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
@Transactional
public class PasswordBoImpl implements PasswordBo, IErrorAdmin, IPathAdmin
{
	private static final long	serialVersionUID	= 1949352771664273090L;

	@Autowired
	GenericKafkaProducer		gKafkaProducer;
	@Autowired
	protected UserBo			userBo;

	@Autowired
	protected UserDao			userDao;

	@Autowired
	OTPBo						otpBo;

	@SuppressWarnings("unused")
	@Autowired
	private ServerUtilFactory	serverUtil;

	@Override
	public UserFormBean validateUser(String tokenKey) throws InvalidKeyException
	{
		if (CommonValidator.isNotNullNotEmpty(tokenKey))
		{
			// logger.info("Inside UserBoImpl validateUser ::: ");
			return ESecurity.Token.validate(userDao, tokenKey, TOKEN_EXPIRY_DURATION);

		}
		throw new InvalidKeyException(USER_TOKEN_KEY_NOT_AVAILABLE_IN_REQUEST);
	}

	@Override
	public EnumInterface updatePassword(Authentication auth, PasswordFormBean pfBean) throws InvalidKeyException, ExecutionException
	{
		UserFormBean ufBean = new UserFormBean(pfBean); // Transform PasswordFormBean To
														// UserFormBean
		try
		{
			if (userBo.isRecentlyUpdated(auth, ufBean))
			{
				switch ( pfBean.formAction )
				{
					case ChangePassword :
					case ForgotPassword :
					case Verify :
					{
						if (EReturn.Success == validatePassword(pfBean, ufBean))
						{
							ufBean.user.setUserPwd(new BCryptPasswordEncoder().encode(pfBean.newPassword));
							ufBean.user.setUserPwdModFlag(false);
							ufBean.user.setStatus(true);
							ufBean.user.setUserPwdModDate(new Timestamp(System.currentTimeMillis()));
							ufBean.user.setOtp(null);
							ufBean.user.setToken(null);
							ufBean.user.setTokenExpiryDate(null);
							ufBean.user.setUserStatus(EUserStatus.Activated);
							userDao.save(ufBean.user);
							pfBean.messageCode = EReturn.Success.name();
							return EReturn.Success;
						}
					}
					default :
						break;
				}
			}
			else
				throw new InvalidKeyException(PASSWORD_UPDATED_RECENTLY);
		}
		finally
		{
			ufBean = null;
		}
		return EReturn.Failure;
	}

	private EReturn validatePassword(PasswordFormBean pfBean, UserFormBean ufBean) throws ExecutionException
	{
		if (!Pattern.compile(PASSWORD_VALIDATION_REGEX).matcher(pfBean.newPassword).matches())
		{
			pfBean.messageCode = PASSWORD_STRENGTH_FAILURE;
			return EReturn.Failure;
		}
		else if (pfBean.formAction == EFormAction.ChangePassword && new BCryptPasswordEncoder().matches(pfBean.newPassword, ufBean.user.getUserPwd()))
		{
			pfBean.messageCode = PASSWORD_SAME_AS_OLD_PASSWORD;
			return EReturn.Failure;
		}
		return EReturn.Success;
	}

	@Override
	public EnumInterface forgotPassword(@RequestBody UserFormBean ufBean) throws InvalidKeyException, JsonProcessingException
	{
		try
		{
			if (userBo.isRecentlyUpdated(null, ufBean))
			{
				ufBean.tokenURL = ServerUtilFactory.getInstance().getDomainURL(ESecurity.Token.generate(ufBean.user, EFormAction.ForgotPassword));
				ufBean.user.modifiedUserInfo(null);
				ufBean.user.setUserStatus(EUserStatus.ResetPassword);
				userDao.save(ufBean.user);
				if (CommonValidator.isNotNullNotEmpty(ufBean.user, ufBean.tokenURL))
				{
					gKafkaProducer.send(ETopic.Internal, EMedia.Email, ETemplate.User_Reset_Password, ufBean);

					ufBean.messageCode = FORGOT_PASSWORD_EMAIL_SENT_SUCCESSFULLY;
				}
			}
			else
				throw new InvalidKeyException(USER_DATA_UPDATED_RECENTLY);
		}
		finally
		{
			ufBean.tokenURL = null;
			ufBean.formUser = null;
			ufBean.user = null;
		}
		return EReturn.Success;
	}

}
