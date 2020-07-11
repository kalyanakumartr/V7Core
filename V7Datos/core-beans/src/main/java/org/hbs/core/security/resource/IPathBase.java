package org.hbs.core.security.resource;

import org.hbs.core.kafka.KAFKAPartition;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.IConstProperty;

public interface IPathBase extends IConstProperty
{
	public enum EFormAction implements EnumInterface
	{
		Default, Add, Update, Search, SoftDelete, PermanentDelete, ChangePassword, ForgotPassword, Verify, TokenExpired, OTP_Generate
	}

	public enum EMedia implements EnumInterface, KAFKAPartition
	{
		Email, SMS, WhatsApp, Manual, Web;

		@Override
		public int getPartition()
		{
			return this.ordinal();
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