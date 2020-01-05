package org.hbs.core.util.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.hbs.core.util.io.IOUtilException.ZipFileConversionException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

@Component
public class IOUtil
{

	public void closeIOSilently(Closeable closeable)
	{
		if (closeable != null)
		{
			try
			{
				closeable.close();
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}

	public static Resource loadFileAsResource(String filePathUrl) throws FileNotFoundException
	{
		try
		{
			Path filePath = Paths.get(filePathUrl).toAbsolutePath().normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists())
			{
				return resource;
			}
			else
			{
				throw new FileNotFoundException("File not found " + filePathUrl);
			}
		}
		catch (Exception ex)
		{
			throw new FileNotFoundException("File not found " + filePathUrl);
		}
	}

	public ZipOutputStream convertAsZipStream(OutputStream outputStream, File... files) throws ZipFileConversionException
	{
		try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream))
		{
			for (File file : files)
			{
				zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
				FileInputStream fileInputStream = new FileInputStream(file);
				IOUtils.copy(fileInputStream, zipOutputStream);
				closeIOSilently(fileInputStream);
				zipOutputStream.closeEntry();
			}
			return zipOutputStream;
		}
		catch (Exception e)
		{
			throw new ZipFileConversionException(e.getMessage(), e);
		}

	}

}
