package org.hbs.core.admin.bo;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.common.errors.InvalidRequestException;
import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.model.IUsersMedia;
import org.hbs.core.beans.model.Users;
import org.hbs.core.beans.model.UsersMedia;
import org.hbs.core.beans.path.IErrorAdmin;
import org.hbs.core.beans.path.IPathAdmin;
import org.hbs.core.dao.SequenceDao;
import org.hbs.core.kafka.GenericKafkaProducer;
import org.hbs.core.kafka.IKafkaConstants.ETopic;
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
		if (isRecentlyUpdated(auth, ufBean))
		{
			try
			{
				// logger.info("Inside UserBoImpl blockUser ::: ", ufBean.user.getUserId());
				ufBean.user.setStatus(!ufBean.formUser.getStatus());// Negate Current Status
				userDao.save(ufBean.user);
				ufBean.clearForm();
				ufBean.messageCode = USER_BLOCKED_SUCCESSFULLY;
				return EReturn.Success;
			}
			finally
			{
				ufBean.tokenURL = null;
				ufBean.formUser = null;
			}
		}
		throw new InvalidRequestException(USER_DATA_UPDATED_RECENTLY);
	}

	@Override
	public EnumInterface deleteUser(Authentication auth, UserFormBean ufBean) throws InvalidRequestException
	{
		// logger.info("Inside UserBoImpl deleteUser ::: ", ufBean.user.getUserId());
		userDao.deleteById(ufBean.formUser.getUserId());
		ufBean.clearForm();
		return EReturn.Success;
	}

	@Override
	public Users getUser(UserFormBean ufBean) throws InvalidRequestException//
	{
		Users user = userDao.findById(ufBean.formUser.getEmployeeId()).get();
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
				users.createdDateByTimeZone();
				users.modifiedDateByTimeZone();
			}
			return userList;
		}
		else
			return new ArrayList<Users>(0);
	}

	@Override
	public boolean isRecentlyUpdated(Authentication auth, UserFormBean ufBean)
	{
		// logger.info("Inside UserBoImpl isRecentlyUpdated ::: ", ufBean.user.getUserId());
		if (CommonValidator.isNotNullNotEmpty(ufBean.formUser))
		{
			ufBean.user = userDao.findById(ufBean.formUser.getEmployeeId()).get();
		}
		else if (CommonValidator.isNotNullNotEmpty(ufBean.searchParam))
		{
			ufBean.user = getUserByEmailOrMobileOrUserId(ufBean.searchParam);
		}

		if (CommonValidator.isNotNullNotEmpty(ufBean.user))
		{
			if ((System.currentTimeMillis() - ufBean.user.getModifiedDate().getTime()) > (updateDelay * 1000))
			{
				ufBean.user.updateDisplayInfoOfProducersAndDateTime();
				ufBean.user.modifiedUserInfo(auth);
				return true;
			}
			return false;
		}
		throw new InvalidRequestException(USER_NOT_FOUND);
	}

	@Override
	public EnumInterface saveUser(Authentication auth, UserFormBean ufBean) throws InvalidRequestException, InvalidKeyException
	{

		if (EAuth.User.verifyProducer(auth, ufBean.formUser.getProducer().getProducerId()))
		{
			// logger.info("UserBoImpl saveUser starts ::: ");
			IUsersMedia uMedia = ufBean.formUser.getPrimaryMedia();
			List<String> userNameList = userDao.checkUserNameEmailIdOrMobileNo(ufBean.formUser.getProducer().getProducerId(), uMedia.getEmailId(), uMedia.getMobileNo());
			if (userNameList.isEmpty())
			{
				ufBean.formUser.createdUserAndProducerInfo(auth);

				ufBean.tokenURL = ServerUtilFactory.getInstance().getDomainURL(ESecurity.Token.generate(ufBean.formUser, EFormAction.Verify));
				ufBean.formUser.setStatus(false);
				ufBean.formUser.setUserPwdModFlag(true);
				ufBean.formUser.getBusinessKey();// Initialize Primary Key

				for (UsersMedia _UM : ufBean.formUser.getMediaList())
				{
					_UM.setUsers(ufBean.formUser);
				}
				ufBean.formUser.setUserId(sequenceDao.getPrimaryKey(Users.class.getSimpleName(), ufBean.formUser.getProducer().getProducerId()));

				// ufBean.user = userDao.save(ufBean.user);

				if (CommonValidator.isNotNullNotEmpty(ufBean.formUser, ufBean.tokenURL))
				{
					try
					{
						gKafkaProducer.send(ETopic.Internal, EMedia.Email, ETemplate.Create_User_Admin, ufBean);
						gKafkaProducer.send(ETopic.Internal, EMedia.Email, ETemplate.Create_User_Employee, ufBean);
						// gKafkaProducer.sendMessage(ETopic.Internal, EMedia.SMS,
						// ETemplate.Create_User_Employee_SMS, ufBean);
						ufBean.clearForm();
						ufBean.messageCode = USER_CREATED_SUCCESSFULLY;
						return EReturn.Success;
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
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
		if (isRecentlyUpdated(auth, ufBean))
		{
			// logger.info("UserBoImpl updateUser starts ::: ");
			ufBean.updateRepoUser(auth);
			userDao.save(ufBean.user);
			ufBean.clearForm();
			ufBean.messageCode = USER_UPDATED_SUCCESSFULLY;
			// logger.info("UserBoImpl updateUser ends ::: ");
			return EReturn.Success;
		}
		throw new InvalidRequestException(USER_DATA_UPDATED_RECENTLY);
	}

	@Override
	public EnumInterface resendActivationLink(Authentication auth, UserFormBean ufBean) throws InvalidKeyException, JsonProcessingException
	{

		if (isRecentlyUpdated(auth, ufBean))
		{
			ufBean.tokenURL = ServerUtilFactory.getInstance().getDomainURL(ESecurity.Token.generate(ufBean.user, EFormAction.Verify));
			ufBean.user.setStatus(false);
			userDao.save(ufBean.user);

			// Putting User Details in KAFKA Topics
			gKafkaProducer.send(ETopic.Internal, EMedia.Email, ETemplate.Create_User_Admin, ufBean);
			// gKafkaProducer.sendMessage(ETopic.Internal, EMedia.Email,
			// ETemplate.Create_User_Employee, ufBean);
			// gKafkaProducer.sendMessage(ETopic.Internal, EMedia.SMS,
			// ETemplate.Create_User_Employee_SMS, ufBean);
			ufBean.clearForm();
			ufBean.messageCode = ACTIVATION_LINK_SENT_SUCCESSFULLY;
			return EReturn.Success;
		}
		else
			throw new InvalidKeyException(ACTIVATION_LINK_SENT_RECENTLY);

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
