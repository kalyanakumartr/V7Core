package org.hbs.v7.extractor.resume.processor;

import java.io.Serializable;

import org.apache.commons.csv.CSVParser;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.tika.sax.BodyContentHandler;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;

public class MediatorBean implements Serializable
{

	private static final long	serialVersionUID	= 6909459362905584030L;
	public String				content;
	private XSSFSheet			excel;
	private BodyContentHandler	openOffice;
	private XWPFWordExtractor	word;
	private Document			html;
	private CSVParser			csv;
	private BodyContentHandler	pdf;
	private JSONObject			json;

	public JSONObject jsonInstance()
	{
		try
		{
			return this.json;
		}
		finally
		{
			this.word = null;
			this.excel = null;
			this.pdf = null;
			this.csv = null;
			this.html = null;
			this.openOffice = null;
		}
	}

	public CSVParser csvInstance()
	{
		try
		{
			return this.csv;
		}
		finally
		{
			this.word = null;
			this.excel = null;
			this.pdf = null;
			this.html = null;
			this.json = null;
			this.openOffice = null;
		}
	}

	public XSSFSheet excelInstance()
	{
		try
		{
			return this.excel;
		}
		finally
		{
			this.word = null;
			this.pdf = null;
			this.csv = null;
			this.html = null;
			this.json = null;
			this.openOffice = null;
		}
	}

	public BodyContentHandler openOfficeInstance()
	{
		try
		{
			return this.openOffice;
		}
		finally
		{
			this.word = null;
			this.excel = null;
			this.pdf = null;
			this.csv = null;
			this.html = null;
			this.json = null;
		}
	}

	public XWPFWordExtractor wordInstance()
	{
		try
		{
			return this.word;
		}
		finally
		{
			this.excel = null;
			this.pdf = null;
			this.csv = null;
			this.html = null;
			this.json = null;
			this.openOffice = null;
		}
	}

	public Document htmlInstance()
	{
		try
		{
			return this.html;
		}
		finally
		{
			this.word = null;
			this.excel = null;
			this.pdf = null;
			this.csv = null;
			this.json = null;
			this.openOffice = null;
		}
	}

	public BodyContentHandler pdfInstance()
	{
		try
		{
			return this.pdf;
		}
		finally
		{
			this.word = null;
			this.excel = null;
			this.csv = null;
			this.html = null;
			this.json = null;
			this.openOffice = null;
		}
	}

	public void setExcel(XSSFSheet excel)
	{
		this.excel = excel;
	}

	public void setOpenOffice(BodyContentHandler openOffice)
	{
		this.openOffice = openOffice;
	}

	public void setWord(XWPFWordExtractor word)
	{
		this.word = word;
	}

	public void setHtml(Document html)
	{
		this.html = html;
	}

	public void setPdf(BodyContentHandler pdf)
	{
		this.pdf = pdf;
	}

	public void setCsv(CSVParser csv)
	{
		this.csv = csv;
	}

	public void setJson(JSONObject json)
	{
		this.json = json;
	}

}
