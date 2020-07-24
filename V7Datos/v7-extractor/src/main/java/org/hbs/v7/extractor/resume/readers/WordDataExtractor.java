package org.hbs.v7.extractor.resume.readers;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.hbs.v7.beans.DataInTopicBean;
import org.hbs.v7.beans.model.DataAttachments.EDataTrace;
import org.hbs.v7.extractor.resume.processor.MediatorBean;
import org.hbs.v7.extractor.resume.processor.ResumeDataExtractorService;

public class WordDataExtractor extends DataExtractorBase implements IDataExtractor
{
	private static final long	serialVersionUID	= -3403166786849575868L;
	DataInTopicBean				inBean;

	public WordDataExtractor(DataInTopicBean inBean)
	{
		this.inBean = inBean;
	}

	@Override
	public void execute()
	{
		ResumeDataExtractorService.getInstance().execute(read(inBean));
	}

	@SuppressWarnings("resource")
	public MediatorBean read(DataInTopicBean inBean)
	{
		XWPFDocument document = null;
		try
		{
			document = new XWPFDocument(inBean.getInputStream());
			MediatorBean mediatorBean = new MediatorBean(inBean.getExtension());
			mediatorBean.content = new XWPFWordExtractor(document).getText();
			return mediatorBean; 
		}
		catch (Exception excep)
		{
			System.out.println("We had an error while reading the Word Doc");
			if (inBean.isExternal() == false) // Internal Flow
			{
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				excep.printStackTrace(pw);
				datDao.updateReadStatus(inBean.getAttachmentAutoId(), EDataTrace.UnableToRead.name(), sw.toString());
			}
		}
		finally
		{
			try
			{
				if (document != null)
				{
					document.close();
				}
				if (inBean.getInputStream() != null)
				{
					inBean.getInputStream().close();
				}
			}
			catch (Exception ex)
			{
			}
		}
		return null;
	}

	public static WordDataExtractor getInstance(DataInTopicBean inBean)
	{
		return new WordDataExtractor(inBean);
	}

}
