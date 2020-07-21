package org.hbs.v7.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.IConstProperty;
import org.hbs.v7.beans.model.IncomingData.EExtension;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AttachmentInfoBean implements IConstProperty
{

	private static final long	serialVersionUID	= -3434772327583545293L;

	private String				fileFolderURL		= "";

	private String				fileName			= "";

	private String				subFolderPath;

	private EExtension			extension			= EExtension.Invalid;

	private boolean				compressed			= false;				// Represents_ZIPPED_File

	public AttachmentInfoBean()
	{
		super();
	}

	public boolean isCompressed()
	{
		return compressed;
	}

	public void setCompressed(boolean compressed)
	{
		this.compressed = compressed;
	}

	public String getFileFolderURL()
	{
		return fileFolderURL;
	}

	public String getFileName()
	{
		return CommonValidator.isNotNullNotEmpty(fileName) ? fileName : "";
	}

	@JsonIgnore
	public ZipInputStream getZIPInputStream() throws FileNotFoundException
	{
		return new ZipInputStream(new FileInputStream(new File(getOutputPath() + File.separator + getFileName())));
	}

	@JsonIgnore
	public SevenZFile getSevenZFile() throws IOException
	{
		return new SevenZFile(new File(getOutputPath() + File.separator + getFileName()));
	}

	@JsonIgnore
	public FileInputStream getInputStream() throws FileNotFoundException 
	{
		return new FileInputStream(getOutputFile());
	}
	
	@JsonIgnore
	public FileOutputStream getOutputStream() throws FileNotFoundException
	{
		File attFileDir = new File(getOutputPath());

		if (!attFileDir.exists())
		{
			attFileDir.mkdirs();
		}

		return new FileOutputStream(attFileDir + File.separator + getFileName());
	}

	@JsonIgnore
	public String getOutputPath()
	{
		return (getFileFolderURL() + getSubFolderPath()).replace(BACKSLASH, SLASH);
	}

	@JsonIgnore
	public String getOutputPath(String fileName)
	{
		this.setFileName(fileName);
		return getOutputPath() + SLASH + getFileName();
	}

	@JsonIgnore
	public File getOutputFile()
	{
		return new File(getOutputPath() + SLASH + getFileName());
	}

	@JsonIgnore
	private String getSubFolderPath()
	{
		return CommonValidator.isNotNullNotEmpty(subFolderPath) ? SLASH + subFolderPath : "";
	}

	public void setFileFolderURL(String fileFolderURL)
	{
		this.fileFolderURL = fileFolderURL;
	}

	public void setFileName(String fileName)
	{
		if (fileName != null)
		{
			if (fileName.indexOf(File.separator) > 0)
				fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
			else if (fileName.indexOf(SLASH) > 0)
				fileName = fileName.substring(fileName.lastIndexOf(SLASH) + 1);

			this.setExtension(EExtension.isValid(fileName));

			this.compressed = (this.extension == EExtension.Zip || this.extension == EExtension._7z);
		}
		this.fileName = fileName;

	}

	public void setSubFolderPath(String subFolderPath)
	{
		this.subFolderPath = subFolderPath;
	}

	public EExtension getExtension()
	{
		return extension;
	}

	public void setExtension(EExtension extension)
	{
		this.extension = extension;
	}

}