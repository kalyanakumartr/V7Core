package org.hbs.core.beans.path;

import org.hbs.core.security.resource.EnumResourceInterface;
import org.hbs.core.security.resource.IPath;

public interface IPathSender extends IPath, IErrorSender
{
	// Configuration
	public String	CHECK_CONFIGURATION_EXISTS		= "/checkConfigurationExists";
	public String	GET_CONFIGURATION_LIST			= "/getConfigurationList";
	public String	GET_CONFIGURATION				= "/getConfiguration";
	public String	GET_CONFIGURATION_BY_CUSTOMER	= "/getConfigurationByProducer";
	public String	PRESEARCH_CONFIGURATION			= "/preSearchConfiguration";
	public String	SEARCH_CONFIGURATION			= "/searchConfiguration";
	public String	PREADD_CONFIGURATION			= "/preAddConfiguration";
	public String	ADD_CONFIGURATION				= "/addConfiguration";
	public String	PREUPDATE_CONFIGURATION			= "/preUpdateConfiguration";
	public String	UPDATE_CONFIGURATION			= "/updateConfiguration";
	public String	BLOCK_CONFIGURATION				= "/blockConfiguration";
	public String	DELETE_CONFIGURATION			= "/deleteConfiguration";
	public String	TEST_CONFIGURATION				= "/testConfiguration";
	public String	SEND_MAIL						= "/sendMail";
	public String	SAVE_MESSAGE					= "/saveMessage";
	public String	SEND_SMS						= "/sendSMS";
	public String	SMS_STATUS_CALLBACK				= "/smsStatusCallBack";
	public String	CREATE_USER_MAIL				= "/createUserMail";
	public String	GET_ACTIVE_EMAIL				= "/getActiveEmailConfiguration";
	public String	GET_ACTIVE_SMS					= "/getActiveSMSConfiguration";
	public String	PASSWORD_RESET_MAIL				= "/passwordResetMail";
	public String	SEND_SMS_OTP					= "/sendSMSForResetPassword";
	public String	GET_SCHEDULE_LIST				= "/getScheduleList";
	public String	GET_SCHEDULE					= "/getSchedule";
	public String	GET_SCHEDULE_BY_CUSTOMER		= "/getScheduleByProducer";
	public String	UPDATE_SCHEDULE					= "/updateSchedule";
	public String	BLOCK_SCHEDULE					= "/blockSchedule";
	public String	DELETE_SCHEDULE					= "/deleteSchedule";
	public String	ADD_SCHEDULE					= "/addSchedule";
	public String	VIEW_CALENDAR					= "/viewCalendar";
	public String	ACKNOWLEDGE_MESSAGE_STATUS		= "/ackMessageStatus";
	public String	SEND_USER_BLOCK_MAIL			= "/sendUserBlockMail";
	public String	DOWNLOAD_ATTACHMENT				= "/downloadAttachment";

	public enum EPathSender implements EnumResourceInterface
	{
		GetConfigurationList(GET_CONFIGURATION_LIST, ERole.Administrator), //
		GetConfiguration(GET_CONFIGURATION, ERole.Administrator, ERole.Employee), //
		PreSearchConfiguration(PRESEARCH_CONFIGURATION, ERole.Administrator, ERole.Employee), //
		SearchConfiguration(SEARCH_CONFIGURATION, ERole.Administrator, ERole.Employee), //
		PreAddConfiguration(PREADD_CONFIGURATION, ERole.Administrator), //
		AddConfiguration(ADD_CONFIGURATION, ERole.Administrator), //
		PreUpdateConfiguration(PREUPDATE_CONFIGURATION, ERole.Administrator), //
		UpdateConfiguration(UPDATE_CONFIGURATION, ERole.Administrator), //
		BlockConfiguration(BLOCK_CONFIGURATION, ERole.Administrator), //
		DeleteConfiguration(DELETE_CONFIGURATION, ERole.Administrator), //
		TestConfiguration(TEST_CONFIGURATION, ERole.Administrator), //
		SendMail(SEND_MAIL, ERole.Administrator), //
		SaveMessage(SAVE_MESSAGE, ERole.Administrator), //
		SendSMS(SEND_SMS, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		SMSStatusCallback(SEND_SMS, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		CreateUserMail(CREATE_USER_MAIL, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		GetActiveEmail(GET_ACTIVE_EMAIL, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		GetActiveSMS(GET_ACTIVE_SMS, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		GetScheduleList(GET_SCHEDULE_LIST, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		GetSchedule(GET_SCHEDULE, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		GetScheduleByProducer(GET_SCHEDULE_BY_CUSTOMER, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		PasswordResetEmail(PASSWORD_RESET_MAIL, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		SendSMSOTP(SEND_SMS_OTP, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		UpdateSchedule(UPDATE_SCHEDULE, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		BlockSchedule(BLOCK_SCHEDULE, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		DownloadAttachment(DOWNLOAD_ATTACHMENT, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		DeleteSchedule(DELETE_SCHEDULE, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		SaveSchedule(ADD_SCHEDULE, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		ViewCalendar(VIEW_CALENDAR, ERole.Administrator, ERole.Employee, ERole.Consumer);

		String	path;

		ERole	roles[];

		EPathSender(String path, ERole... roles)
		{
			this.path = path;
			this.roles = roles;
		}

		public String getPath()
		{
			return this.path;
		}

		public ERole[] getRoles()
		{
			return this.roles;
		}
	}
}
