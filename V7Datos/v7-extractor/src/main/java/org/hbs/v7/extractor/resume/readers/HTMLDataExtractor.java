package org.hbs.v7.extractor.resume.readers;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.hbs.v7.beans.DataInTopicBean;
import org.hbs.v7.beans.model.DataAttachments.EDataTrace;
import org.hbs.v7.extractor.resume.processor.MediatorBean;
import org.hbs.v7.extractor.resume.processor.ResumeDataExtractorService;
import org.jsoup.Jsoup;

public class HTMLDataExtractor extends DataExtractorBase implements IDataExtractor
{
	private static final long	serialVersionUID	= -3403166786849575868L;
	DataInTopicBean				inBean;

	public HTMLDataExtractor(DataInTopicBean inBean)
	{
		this.inBean = inBean;
	}

	@Override
	public void execute()
	{
		ResumeDataExtractorService.getInstance().execute(read(inBean));
	}

	MediatorBean read(DataInTopicBean inBean)
	{
		try
		{
			MediatorBean mediatorBean = new MediatorBean(inBean.getExtension());
			mediatorBean.setHtml(Jsoup.parse(inBean.getOutputFile(), UTF8ENCODER));
			return mediatorBean;
		}
		catch (Exception excep)
		{
			System.out.println("We had an error while reading the Open Office Document");
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
		}
		return null;
	}

	public static HTMLDataExtractor getInstance(DataInTopicBean inBean)
	{
		return new HTMLDataExtractor(inBean);
	}

}
