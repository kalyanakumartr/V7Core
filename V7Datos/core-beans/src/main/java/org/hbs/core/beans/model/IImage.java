package org.hbs.core.beans.model;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.IConstProperty;

public interface IImage extends IConstProperty
{

	public enum EImage implements EnumInterface
	{
		ResourceHandler, Default;

		static final String CONTENT = "content";

		public String getRepositoryPhysicalPath(IProducers producer, String subFolderPath, String enumKey) throws Exception
		{
			if (subFolderPath == null)
			{
				subFolderPath = "";
			}

			if (enumKey == null)
			{
				enumKey = EImage.Default.name();
			}

			IProducersProperty iPP = producer.getProperty(this, enumKey);

			if (CommonValidator.isNotNullNotEmpty(iPP.getValue()))
			{
				StringBuffer sbLocalPath = new StringBuffer();
				sbLocalPath.append(iPP.getValue() + File.separator + subFolderPath);

				File file = new File(sbLocalPath.toString());
				if (file.exists() == false && file.mkdirs())
				{
					file.setWritable(true);
					file.setReadable(true);
					file.setExecutable(true);
				}

				return sbLocalPath.toString();
			}
			else
			{
				throw new Exception("Not able Create/Construct Absolute path for the Repository");
			}
		}

		public String getServerSessionPhysicalPath(HttpSession httpSession, String... externalPath) throws Exception
		{
			if (externalPath == null || externalPath.length == 0)
				externalPath = new String[] { "" };

			if (CommonValidator.isNotNullNotEmpty(httpSession))
			{
				StringBuffer sbLocalPath = new StringBuffer();
				sbLocalPath.append(httpSession.getServletContext().getRealPath(CONTENT) + File.separator);
				sbLocalPath.append(httpSession.getId() + externalPath[0]);

				File file = new File(sbLocalPath.toString());
				if (file.exists() == false)
				{
					file.mkdirs();
					file.setWritable(true);
					file.setReadable(true);
					file.setExecutable(true);
				}

				return sbLocalPath.toString();
			}
			else
			{
				throw new Exception("Not able Create/Construct Absolute path for the Session");
			}
		}

		public void getServerSessionVirtualPath(HttpServletRequest request, IProducers producer, IUploadImageOrDocuments... iDocsArr)
		{
			if (CommonValidator.isNotNullNotEmpty(iDocsArr))
			{
				Set<IUploadImageOrDocuments> iDocsSet = new LinkedHashSet<IUploadImageOrDocuments>(iDocsArr.length);
				Collections.addAll(iDocsSet, iDocsArr);
				getServerSessionVirtualPath(request, producer, iDocsSet);
			}
		}

		public void getServerSessionVirtualPath(HttpServletRequest request, IProducers producer, Set<? extends IUploadImageOrDocuments> iDocsSet)
		{
			if (CommonValidator.isNotNullNotEmpty(request) && CommonValidator.isSetFirstNotEmpty(iDocsSet))
			{
				String contextPath = null;
				if (CommonValidator.isNotNullNotEmpty(request, producer.getDomainContext()))
				{
					contextPath = producer.getDomainContext();
				}
				else
				{
					contextPath = request.getServletContext().getContextPath();
				}
				IProducersProperty iPP = null;

				for (IUploadImageOrDocuments iDoc : iDocsSet)
				{
					iPP = producer.getProperty(this, iDoc.getUploadResourceHandler());
					iDoc.setUploadFileVirtualURL(contextPath + SLASH + iPP.getProperty() + SLASH + iDoc.getUploadFileFolderURL() + SLASH + iDoc.getUploadFileName());
				}
			}
		}

		public void getServerSessionVirtualPathForLegacy(HttpServletRequest request, IProducers producer, Set<? extends IUploadImageOrDocuments> iDocsSet)
		{
			try
			{
				if (CommonValidator.isNotNullNotEmpty(request) && CommonValidator.isSetFirstNotEmpty(iDocsSet))
				{
					File destDirectory = new File(getServerSessionPhysicalPath(request.getSession()));
					File srcFile = null;
					File destFile = null;
					StringBuffer sbLocalPath = null;
					String repoFilePath = null;
					for (IUploadImageOrDocuments iDocs : iDocsSet)
					{
						try
						{
							destFile = new File(destDirectory.getAbsolutePath() + File.separator + iDocs.getUploadFileName());
							if (destFile.exists() == false)
							{
								repoFilePath = iDocs.getUploadFileFolderURL() + File.separator + iDocs.getUploadFileName();
								srcFile = new File(getRepositoryPhysicalPath(producer, repoFilePath, iDocs.getUploadResourceHandler()));

								try
								{
									FileUtils.copyFileToDirectory(srcFile, destDirectory);
								}
								catch (Exception excep)
								{

								}
							}

							sbLocalPath = new StringBuffer();
							if (CommonValidator.isNotNullNotEmpty(producer.getDomainContext()))
								sbLocalPath.append(producer.getDomainContext() + File.separator + CONTENT + File.separator);
							else
								sbLocalPath.append(request.getServletContext().getContextPath() + File.separator + CONTENT + File.separator);
							sbLocalPath.append(request.getSession().getId() + File.separator + iDocs.getUploadFileName());
							iDocs.setUploadFileVirtualURL(sbLocalPath.toString());
						}
						finally
						{
							srcFile = null;
							destFile = null;
							sbLocalPath = null;
							repoFilePath = null;

						}
					}
				}
			}
			catch (Exception excep)
			{

			}
		}

	}

	public enum EUploadType implements EnumInterface
	{
		Identification, ProducerImage, ProfileImage, Registration, TINNumber, UserImage;
	}

}
