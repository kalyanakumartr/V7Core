package org.hbs.v7.extractor.resume.readers;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.odf.OpenDocumentParser;
import org.apache.tika.sax.BodyContentHandler;
import org.hbs.v7.beans.DataInTopicBean;
import org.hbs.v7.beans.model.DataAttachments.EDataTrace;
import org.hbs.v7.extractor.resume.processor.MediatorBean;
import org.hbs.v7.extractor.resume.processor.ResumeDataExtractorService;

public class OpenOfficeDataExtractor extends DataExtractorBase implements IDataExtractor
{
	private static final long	serialVersionUID	= -3403166786849575868L;
	DataInTopicBean				inBean;

	public OpenOfficeDataExtractor(DataInTopicBean inBean)
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
		OpenDocumentParser openDocument = null;
		try
		{

			BodyContentHandler handler = new BodyContentHandler();
			Metadata metadata = new Metadata();
			ParseContext pcontext = new ParseContext();

			openDocument = new OpenDocumentParser();
			openDocument.parse(inBean.getInputStream(), handler, metadata, pcontext);

			MediatorBean mediatorBean = new MediatorBean(inBean.getExtension());
			mediatorBean.content = handler.toString();
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
			try
			{
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

	public static OpenOfficeDataExtractor getInstance(DataInTopicBean inBean)
	{
		return new OpenOfficeDataExtractor(inBean);
	}

}
