package org.hbs.v7.extractor.resume.readers;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.hbs.v7.beans.DataInTopicBean;
import org.hbs.v7.beans.model.DataAttachments.EDataTrace;
import org.hbs.v7.extractor.resume.processor.MediatorBean;
import org.hbs.v7.extractor.resume.processor.ResumeDataExtractorService;

public class CSVDataExtractor extends DataExtractorBase implements IDataExtractor
{
	private static final long	serialVersionUID	= -3403166786849575868L;
	DataInTopicBean				inBean;

	public CSVDataExtractor(DataInTopicBean inBean)
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
			CSVParser csvParser = new CSVParser(new FileReader(inBean.getOutputFile()), CSVFormat.DEFAULT);
			MediatorBean mediatorBean = new MediatorBean(inBean.getExtension());
			mediatorBean.setCsv(csvParser);
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

	public static CSVDataExtractor getInstance(DataInTopicBean inBean)
	{
		return new CSVDataExtractor(inBean);
	}

}
