package org.hbs.core.util;

import java.io.Serializable;
import java.util.UUID;

public interface EBusinessKey extends Serializable
{

	public enum EKey implements EnumInterface
	{
		Primary;

		public static String Auto()
		{
			try
			{
				Thread.sleep(1);
				return UUID.randomUUID().toString();
			}
			catch (Exception excep)
			{
				return UUID.randomUUID().toString();
			}
		}

		public static String AutoUnMask(String code)
		{
			try
			{
				Thread.sleep(1);
				return code + System.currentTimeMillis();
			}
			catch (Exception excep)
			{
				return code + System.currentTimeMillis();
			}
		}

		public static String Custom(String... codes)
		{
			String business = "";
			try
			{
				for (String code : codes)
				{
					business += code;
				}
				return Masker.generateMD5(business);
			}
			catch (Exception e)
			{
				return business + System.currentTimeMillis();
			}
		}
	}

	public String getBusinessKey(String... combination);
}
