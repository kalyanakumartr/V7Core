package org.hbs.v7.extractor.action.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.IConstProperty;

public abstract class AttachmentInfo implements IConstProperty
{

	private static final long	serialVersionUID	= -3434772327583545293L;

	private String				fileFolderURL		= "C:\\Users\\HP\\AttachmentPath\\";

	private String				fileName;

	private String				subFolderPath;

	public AttachmentInfo()
	{
		super();
	}

	private String getFileFolderURL()
	{
		return CommonValidator.isNotNullNotEmpty(fileFolderURL) ? SLASH + fileFolderURL : "";
	}

	private String getFileName()
	{
		return CommonValidator.isNotNullNotEmpty(fileName) ? SLASH + fileName : "";
	}

	public FileOutputStream getOutputStream() throws FileNotFoundException
	{
		File attFileDir = new File(getOutputPath());

		if (!attFileDir.exists())
		{
			attFileDir.mkdirs();
		}

		return new FileOutputStream(attFileDir + getFileName());
	}

	public String getOutputPath()
	{
		return getFileFolderURL() + getSubFolderPath();
	}

	public File getOutputFile()
	{
		return new File(getFileFolderURL() + getSubFolderPath() + getFileName());
	}

	public String getSubFolderPath()
	{
		return CommonValidator.isNotNullNotEmpty(subFolderPath) ? SLASH + subFolderPath : "";
	}

	public void setFileFolderURL(String fileFolderURL)
	{
		this.fileFolderURL = fileFolderURL;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;

	}

	public void setSubFolderPath(String subFolderPath)
	{
		this.subFolderPath = subFolderPath;
	}

}