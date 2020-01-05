package org.hbs.core.util.io;

public class IOUtilException
{

	public static class ZipFileConversionException extends RuntimeException
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 2189960999890017047L;

		public ZipFileConversionException(String message)
		{
			super(message);
		}

		public ZipFileConversionException(String message, Throwable throwable)
		{
			super(message, throwable);
		}

	}

}
