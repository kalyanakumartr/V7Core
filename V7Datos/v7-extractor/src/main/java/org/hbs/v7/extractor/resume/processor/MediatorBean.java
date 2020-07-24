package org.hbs.v7.extractor.resume.processor;

import java.io.Serializable;

import org.apache.commons.csv.CSVParser;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.hbs.v7.beans.model.IncomingData.EExtension;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;

public class MediatorBean implements Serializable
{

	private static final long	serialVersionUID	= 6909459362905584030L;
	public String				content;
	private XSSFSheet			excel;
	private Document			html;
	private CSVParser			csv;
	private JSONObject			json;
	public EExtension			extension;

	public MediatorBean(EExtension extension)
	{
		this.extension = extension;
	}

	public JSONObject jsonInstance()
	{
		try
		{
			return this.json;
		}
		finally
		{
			this.excel = null;
			this.csv = null;
			this.html = null;
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
			this.excel = null;
			this.html = null;
			this.json = null;
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
			this.csv = null;
			this.html = null;
			this.json = null;
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
			this.excel = null;
			this.csv = null;
			this.json = null;
		}
	}

	public void setExcel(XSSFSheet excel)
	{
		this.excel = excel;
	}

	public void setHtml(Document html)
	{
		this.html = html;
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
