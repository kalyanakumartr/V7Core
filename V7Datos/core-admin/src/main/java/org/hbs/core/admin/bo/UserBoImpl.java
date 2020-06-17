package org.hbs.core.admin.bo;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.common.errors.InvalidRequestException;
import org.hbs.core.beans.GenericKafkaProducer;
import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.model.IUsersMedia;
import org.hbs.core.beans.model.Users;
import org.hbs.core.beans.model.UsersMedia;
import org.hbs.core.beans.path.IErrorAdmin;
import org.hbs.core.beans.path.IPathAdmin;
import org.hbs.core.dao.SequenceDao;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ServerUtilFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
@Transactional
public class UserBoImpl extends UserBoComboBoxImpl implements UserBo, IErrorAdmin, IPathAdmin
{

	private static final long	serialVersionUID	= 7255672818512788055L;

	// private final Logger logger = LoggerFactory.getLogger(UserBoImpl.class);

	@Autowired
	GenericKafkaProducer		gKafkaProducer;

	@Autowired
	private SequenceDao			sequenceDao;

	@Value("${admin.update.delay.in.seconds:120}") // 2 minutes default update
	private int					updateDelay;

	@Override
	public EnumInterface blockUser(Authentication auth, UserFormBean ufBean) throws InvalidRequestException
	{
		if (isRecentlyUpdated(ufBean))
		{
			try
			{
				// logger.info("Inside UserBoImpl blockUser ::: ", ufBean.user.getUserId());
				ufBean.repoUser.setStatus(!ufBean.user.getStatus());// Negate Current Status
				ufBean.repoUser.modifiedUserInfo(auth);
				userDao.save(ufBean.repoUser);

				ufBean.messageCode = USER_BLOCKED_SUCCESSFULLY;
				return EReturn.Success;
			}
			finally
			{
				ufBean.tokenURL = null;
				ufBean.user = null;
			}
		}
		throw new InvalidRequestException(USER_DATA_UPDATED_RECENTLY);
	}

	@Override
	public EnumInterface deleteUser(Authentication auth, UserFormBean ufBean) throws InvalidRequestException
	{
		// logger.info("Inside UserBoImpl deleteUser ::: ", ufBean.user.getUserId());
		userDao.deleteById(ufBean.user.getUserId());
		return EReturn.Success;
	}

	@Override
	public Users getUser(UserFormBean ufBean) throws InvalidRequestException//
	{
		Users user = userDao.findById(ufBean.user.getEmployeeId()).get();
		if (CommonValidator.isNotNullNotEmpty(user))
		{
			user.createdDateByTimeZone();
			user.modifiedDateByTimeZone();
		}
		return user;
	}

	@Override
	public List<Users> getUserListByProducer(Authentication auth) throws InvalidRequestException//
	{
		List<Users> userList = userDao.findByProducerId(EAuth.User.getProducerId(auth));
		if (CommonValidator.isListFirstNotEmpty(userList))
		{
			for (Users users : userList)
			{
				users.setCreatedDateByTimeZone(users.getCountry().getCountry());
				users.setModifiedDateByTimeZone(users.getCountry().getCountry());
			}
			return userList;
		}
		else
			return new ArrayList<Users>(0);
	}

	private boolean isRecentlyUpdated(UserFormBean ufBean)
	{
		// logger.info("Inside UserBoImpl isRecentlyUpdated ::: ", ufBean.user.getUserId());
		if (CommonValidator.isNotNullNotEmpty(ufBean.user))
		{
			Users user = userDao.findById(ufBean.user.getEmployeeId()).get();
			if (CommonValidator.isNotNullNotEmpty(user))
			{
				if ((System.currentTimeMillis() - user.getModifiedDate().getTime()) > (updateDelay * 1000))
				{
					user.setProducerId(user.getProducer().getProducerId());
					user.setProducerName(user.getProducer().getProducerName());
					user.setParentProducerId(user.getParentProducer().getProducerId());
					user.setParentProducerName(user.getParentProducer().getProducerName());
					user.createdDateByTimeZone();
					user.modifiedDateByTimeZone();
					ufBean.repoUser = user;
					return true;
				}
				return false;
			}
		}
		throw new InvalidRequestException(USER_NOT_FOUND);
	}

	@Override
	public EnumInterface saveUser(Authentication auth, UserFormBean ufBean) throws InvalidRequestException, InvalidKeyException
	{

		if (EAuth.User.verifyProducer(auth, ufBean.user.getProducer().getProducerId()))
		{
			// logger.info("UserBoImpl saveUser starts ::: ");
			IUsersMedia uMedia = ufBean.user.getPrimaryMedia();
			List<String> userNameList = userDao.checkUserNameEmailIdOrMobileNo(ufBean.user.getProducer().getProducerId(), uMedia.getEmailId(), uMedia.getMobileNo());
			if (userNameList.isEmpty())
			{
				ufBean.user.createdUserProducerInfo(auth);

				ufBean.tokenURL = ServerUtilFactory.getInstance().getDomainURL(ESecurity.Token.generate(ufBean.user, EFormAction.Verify));
				ufBean.user.setStatus(false);
				ufBean.user.setUserPwdModFlag(true);
				ufBean.user.getBusinessKey();// Initialize Primary Key

				for (UsersMedia _UM : ufBean.user.getMediaList())
				{
					_UM.setUsers(ufBean.user);
				}
				ufBean.user.setUserId(sequenceDao.getPrimaryKey(Users.class.getSimpleName(), ufBean.user.getProducer().getProducerId()));

				// ufBean.user = userDao.save(ufBean.user);

				if (CommonValidator.isNotNullNotEmpty(ufBean.user, ufBean.tokenURL))
				{
					try
					{
						gKafkaProducer.sendMessage(ETopic.Internal, EMedia.Email, ETemplate.Create_User_Admin, ufBean);
						gKafkaProducer.sendMessage(ETopic.Internal, EMedia.Email, ETemplate.Create_User_Employee, ufBean);
						// gKafkaProducer.sendMessage(ETopic.Internal, EMedia.SMS,
						// ETemplate.Create_User_Employee_SMS, ufBean);

						ufBean.messageCode = USER_CREATED_SUCCESSFULLY;
						return EReturn.Success;
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
					finally
					{
						ufBean.tokenURL = null;
						ufBean.user = null;
					}
				}
				throw new InvalidKeyException(USER_TOKEN_KEY_GENERATE_ISSUE);
			}
			throw new InvalidKeyException(USER_ALREADY_EXISTS);
		}
		// logger.error("UserBoImpl saveUser ::: Producer Not Matched");
		throw new InvalidKeyException(USER_CREATED_FAILED);

	}

	@Override
	public List<Users> searchUser(Authentication auth, UserFormBean ufBean) throws InvalidRequestException
	{
		// logger.info("Inside UserController getAllUsers ::: ");
		List<Users> userList = userDao.searchUser(ufBean.searchParam);
		if (CommonValidator.isListFirstNotEmpty(userList))
		{
			for (Users users : userList)
			{
				users.setCreatedDateByTimeZone(users.getCountry().getCountry());
				users.setModifiedDateByTimeZone(users.getCountry().getCountry());
			}
			return userList;
		}
		else
			return new ArrayList<Users>(0);
	}

	@Override
	public EnumInterface updateUser(Authentication auth, UserFormBean ufBean) throws InvalidRequestException
	{
		if (isRecentlyUpdated(ufBean))
		{
			try
			{
				// logger.info("UserBoImpl updateUser starts ::: ");
				ufBean.updateRepoUser(auth);
				userDao.save(ufBean.repoUser);

				ufBean.messageCode = USER_UPDATED_SUCCESSFULLY;
				// logger.info("UserBoImpl updateUser ends ::: ");
				return EReturn.Success;
			}
			finally
			{
				ufBean.tokenURL = null;
				ufBean.user = null;
			}
		}
		throw new InvalidRequestException(USER_DATA_UPDATED_RECENTLY);
	}

	@Override
	public UserFormBean validateUser(String tokenKey) throws InvalidKeyException
	{
		if (CommonValidator.isNotNullNotEmpty(tokenKey))
		{
			// logger.info("Inside UserBoImpl validateUser ::: ");
			UserFormBean ufBean = ESecurity.Token.validate(userDao, tokenKey, TOKEN_EXPIRY_DURATION);

			if (CommonValidator.isNotNullNotEmpty(ufBean))
			{
				return ufBean;
			}
		}
		throw new InvalidKeyException(USER_TOKEN_KEY_NOT_AVAILABLE_IN_REQUEST);
	}

	@Override
	public EnumInterface resendActivationLink(Authentication auth, UserFormBean ufBean) throws InvalidKeyException, JsonProcessingException
	{

		try
		{
			if (isRecentlyUpdated(ufBean))
			{
				ufBean.tokenURL = ServerUtilFactory.getInstance().getDomainURL(ESecurity.Token.generate(ufBean.repoUser, EFormAction.Verify));
				// ufBean.repoUser.modifiedUserInfo(auth);
				ufBean.repoUser.setStatus(false);
				userDao.save(ufBean.repoUser);
				if (CommonValidator.isNotNullNotEmpty(ufBean.repoUser, ufBean.tokenURL))
				{
					ufBean.user = ufBean.repoUser;
					// ufBean.user.setParentProducerId(EAuth.User.getParentProducerId(auth));
					// ufBean.user.setParentProducerName(EAuth.User.getParentProducerName(auth));
					ufBean.repoUser = null;
					gKafkaProducer.sendMessage(ETopic.Internal, EMedia.Email, ETemplate.Create_User_Admin, ufBean);
					// gKafkaProducer.sendMessage(ETopic.Internal, EMedia.Email,
					// ETemplate.Create_User_Employee, ufBean);
					// gKafkaProducer.sendMessage(ETopic.Internal, EMedia.SMS,
					// ETemplate.Create_User_Employee_SMS, ufBean);

					ufBean.messageCode = ACTIVATION_LINK_SENT_SUCCESSFULLY;
				}
			}
			else
				throw new InvalidKeyException(ACTIVATION_LINK_SENT_RECENTLY);
		}
		catch (Exception excep)
		{
			excep.printStackTrace();
		}
		finally
		{
			ufBean.tokenURL = null;
			ufBean.repoUser = null;
		}
		return EReturn.Success;

	}

	@Override
	public Users getUserByEmailOrMobileOrUserId(String searchParam)//
	{
		Object object = userDao.findByEmailOrMobileOrUserId(searchParam);
		if (object == null)
		{
			throw new UsernameNotFoundException("User Info " + searchParam + " not found");
		}
		Object[] userDetail = (Object[]) object;

		Users user = (Users) userDetail[0];
		user.setProducerId(user.getProducer().getProducerId());
		user.setProducerName((String) userDetail[1]);
		user.setParentProducerId(user.getParentProducer().getProducerId());
		user.setParentProducerName((String) userDetail[2]);
		user.createdDateByTimeZone();
		user.modifiedDateByTimeZone();

		return user;
	}
}
