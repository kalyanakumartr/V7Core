package org.hbs.v7.extractor.resume.readers;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hbs.v7.beans.DataInTopicBean;
import org.hbs.v7.beans.model.DataAttachments.EDataTrace;
import org.hbs.v7.extractor.resume.processor.MediatorBean;
import org.hbs.v7.extractor.resume.processor.ResumeDataExtractorService;

public class ExcelDataExtractor extends DataExtractorBase implements IDataExtractor
{
	private static final long	serialVersionUID	= -3403166786849575868L;
	DataInTopicBean				inBean;

	public ExcelDataExtractor(DataInTopicBean inBean)
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
		XSSFWorkbook book = null;
		try
		{
			book = new XSSFWorkbook(inBean.getInputStream());
			MediatorBean mediatorBean = new MediatorBean(inBean.getExtension());
			mediatorBean.setExcel(book.getSheetAt(0));
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
				if (book != null)
				{
					book.close();
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

	public static ExcelDataExtractor getInstance(DataInTopicBean inBean)
	{
		return new ExcelDataExtractor(inBean);
	}

}
