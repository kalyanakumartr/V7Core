package org.hbs.core.security.resource;

import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.IConstProperty;

public interface IPathBase extends IConstProperty
{
	public String	INTERNAL_TOPIC		= "InternalTopic";
	public String	EXTERNAL_TOPIC		= "ExternalTopic";
	public String	ATTACHMENT_TOPIC	= "AttachmentTopic";
	public String	EMAIL				= "Email";
	public String	SMS					= "SMS";

	public enum ETopic implements EnumInterface
	{
		Internal(INTERNAL_TOPIC), External(EXTERNAL_TOPIC), Attachment(ATTACHMENT_TOPIC);

		String topic;

		ETopic(String topic)
		{
			this.topic = topic;
		}

		public String getTopic()
		{
			return this.topic;
		}
	}

	public enum EFormAction implements EnumInterface
	{
		Add, Update, Search, SoftDelete, PermanentDelete, ChangePassword, ForgotPassword, Verify, PasswordChanged, TokenExpired
	}

	public enum EMedia implements EnumInterface
	{
		Email, SMS, WhatsApp, Manual, Web;

		public String order()
		{
			return this.ordinal() + "";
		}
	}

	public enum EMediaMode implements EnumInterface
	{
		NoReply, Internal, External;
	}

	public enum EMediaType implements EnumInterface
	{
		Primary, Secondary, Alternate
	}

	public enum EReturn implements EnumInterface
	{
		Success, Failure, Exists, Not_Exists
	}

	public enum ERole implements EnumInterface
	{
		Administrator, Consumer, Dummy, Employee, Producer, SuperAdminRole;
	}

}