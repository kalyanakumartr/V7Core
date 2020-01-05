package org.hbs.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public final class Masker
{
	private static final String	SHA_256	= "SHA-256";
	private static final String	SHA_1	= "SHA-1";
	private static final String	MD5		= "MD5";
	private static final String	UTF_8	= "UTF-8";

	public static synchronized String decrypt(String encryptText, String decoding)
	{
		try
		{
			byte[] decodedBytes = Base64.decodeBase64(encryptText.getBytes(decoding));
			return new String(decodedBytes);
		}
		catch (UnsupportedEncodingException excep)
		{

		}
		return null;
	}

	public static synchronized String decryptBase64(String decryptText)
	{
		byte[] decodedBytes = Base64.decodeBase64(decryptText.getBytes());
		return new String(decodedBytes);
	}

	public static synchronized String encrypt(String plaintext, String algorithm)
	{
		try
		{
			MessageDigest msgDigest = MessageDigest.getInstance(algorithm);
			msgDigest.update(plaintext.getBytes(UTF_8));
			byte[] encodedBytes = Base64.encodeBase64(msgDigest.digest());
			return new String(encodedBytes);
		}
		catch (NoSuchAlgorithmException excep)
		{
			excep.printStackTrace();

		}
		catch (UnsupportedEncodingException excep)
		{
			excep.printStackTrace();

		}
		return null;
	}

	public static synchronized String encryptBase64(String encryptText)
	{
		byte[] encodedBytes = Base64.encodeBase64(encryptText.getBytes());
		return new String(encodedBytes);
	}

	public static String generateMD5(String message) throws Exception
	{
		return hashString(message, MD5);
	}

	public static String generateSHA1(String message) throws Exception
	{
		return hashString(message, SHA_1);
	}

	public static String generateSHA256(String message) throws Exception
	{
		return hashString(message, SHA_256);
	}

	private static String hashString(String message, String algorithm) throws Exception
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashedBytes = digest.digest(message.getBytes(UTF_8));

			return convertByteArrayToHexString(hashedBytes);
		}
		catch (NoSuchAlgorithmException | UnsupportedEncodingException ex)
		{
			throw new Exception("Could not generate hash from String", ex);
		}
	}

	private static String convertByteArrayToHexString(byte[] arrayBytes)
	{
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++)
		{
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}

}
