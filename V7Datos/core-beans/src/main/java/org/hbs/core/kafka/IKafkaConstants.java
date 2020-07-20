package org.hbs.core.kafka;

import java.io.Serializable;

import org.hbs.core.util.EnumInterface;

public interface IKafkaConstants extends Serializable
{
	public String	INTERNAL_TOPIC		= "InternalTopic";
	public String	MESSAGE_TOPIC		= "MessageTopic";
	public String	DATA_EXTRACT_TOPIC	= "DataExtractTopic";

	public String	EMAIL				= "Email";
	public String	SMS					= "SMS";

	public String	INTERNAL_GROUP		= "InternalGroup";
	public String	MESSAGE_GROUP		= "MessageGroup";
	public String	DATA_EXTRACT_GROUP	= "DataExtractGroup";

	// Grouped for Message Topic Partition
	final String	NORMAL				= "0";
	final String	EXPEDITE			= "1";

	// Grouped for Data Extract Topic Partition
	final String	DOCUMENT			= "0";
	final String	DOCUMENT_EXPEDITE	= "1";
	final String	OPENOFFICE			= "2";
	final String	OPENOFFICE_EXPEDITE	= "3";
	final String	EXCEL				= "4";
	final String	EXCEL_EXPEDITE		= "5";
	final String	PDF					= "6";
	final String	PDF_EXPEDITE		= "7";
	final String	JSON				= "8";
	final String	JSON_EXPEDITE		= "9";
	final String	CSV					= "10";
	final String	CSV_EXPEDITE		= "11";
	final String	HTML				= "12";
	final String	HTML_EXPEDITE		= "13";

	public enum ETopic implements EnumInterface
	{
		Internal(INTERNAL_TOPIC), Message(MESSAGE_TOPIC), DataExtract(DATA_EXTRACT_TOPIC);

		String topic;

		ETopic(String topic)
		{
			this.topic = topic;
		}

		public String getTopic()
		{
			return this.topic;
		}
	}

	public enum EPartition implements KAFKAPartition
	{

		Message_In(NORMAL), Message_In_Expedite(EXPEDITE), //
		Document(DOCUMENT), Document_Expedite(DOCUMENT_EXPEDITE), //
		OpenOffice(OPENOFFICE), OpenOffice_Expedite(OPENOFFICE_EXPEDITE), //
		Excel(EXCEL), Excel_Expedite(EXCEL_EXPEDITE), //
		Pdf(PDF), Pdf_Expedite(PDF_EXPEDITE), //
		Json(JSON), Json_Expedite(JSON_EXPEDITE), //
		Csv(CSV), Csv_Expedite(CSV_EXPEDITE), //
		Html(HTML), Html_Expedite(HTML_EXPEDITE);

		String partition;

		EPartition(String partition)
		{
			this.partition = partition;
		}

		public int getPartition()
		{
			return Integer.parseInt(this.partition);
		}
	}

}
