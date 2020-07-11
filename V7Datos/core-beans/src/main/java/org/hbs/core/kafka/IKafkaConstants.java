package org.hbs.core.kafka;

import java.io.Serializable;

import org.hbs.core.util.EnumInterface;

public interface IKafkaConstants extends Serializable
{
	public String	INTERNAL_TOPIC				= "InternalTopic";
	public String	MESSAGE_TOPIC				= "MessageTopic";
	public String	DATA_EXTRACT_TOPIC			= "DataExtractTopic";
	public String	DATA_EXTRACT_REPLY_TOPIC	= "DataExtractReplyTopic";
	public String	EMAIL						= "Email";
	public String	SMS							= "SMS";
	public String	INTERNAL_GROUP				= "InternalGroup";
	public String	MESSAGE_GROUP				= "MessageGroup";
	public String	DATA_EXTRACT_GROUP			= "DataExtractGroup";

	// Grouped for Message Topic Partition
	final String	NORMAL						= "0";
	final String	EXPEDITE					= "1";

	// Grouped for Data Extract Topic Partition
	final String	DATA_IN						= "0";
	final String	DATA_IN_EXPEDITE			= "1";
	final String	EXTRACT_STEP1				= "2";
	final String	EXTRACT_STEP1_EXPEDITE		= "3";
	final String	EXTRACT_STEP2				= "4";
	final String	EXTRACT_STEP2_EXPEDITE		= "5";
	final String	EXTRACT_STEP3				= "6";
	final String	EXTRACT_STEP3_EXPEDITE		= "7";
	final String	EXTRACT_STEP4				= "8";
	final String	EXTRACT_STEP4_EXPEDITE		= "9";
	final String	EXTRACT_STEP5				= "10";
	final String	EXTRACT_STEP5_EXPEDITE		= "11";
	final String	EXTRACT_STEP6				= "12";
	final String	EXTRACT_STEP6_EXPEDITE		= "13";
	final String	EXTRACT_STEP7				= "14";
	final String	EXTRACT_STEP7_EXPEDITE		= "15";
	final String	EXTRACT_STEP8				= "16";
	final String	EXTRACT_STEP8_EXPEDITE		= "17";
	final String	EXTRACT_STEP9				= "18";
	final String	EXTRACT_STEP9_EXPEDITE		= "19";
	final String	EXTRACT_STEP10				= "20";
	final String	EXTRACT_STEP10_EXPEDITE		= "21";
	final String	EXTRACT_STEP11				= "22";
	final String	EXTRACT_STEP11_EXPEDITE		= "23";
	final String	EXTRACT_STEP12				= "24";
	final String	EXTRACT_STEP12_EXPEDITE		= "25";
	final String	EXTRACT_STEP13				= "26";
	final String	EXTRACT_STEP13_EXPEDITE		= "27";
	final String	EXTRACT_STEP14				= "28";
	final String	EXTRACT_STEP14_EXPEDITE		= "29";
	final String	EXTRACT_STEP15				= "30";
	final String	EXTRACT_STEP15_EXPEDITE		= "31";
	final String	EXTRACT_STEP16				= "32";
	final String	EXTRACT_STEP16_EXPEDITE		= "33";
	final String	EXTRACT_STEP17				= "34";
	final String	EXTRACT_STEP17_EXPEDITE		= "35";
	final String	EXTRACT_STEP18				= "36";
	final String	EXTRACT_STEP18_EXPEDITE		= "37";
	final String	EXTRACT_STEP19				= "38";
	final String	EXTRACT_STEP19_EXPEDITE		= "39";
	final String	EXTRACT_STEP20				= "40";
	final String	EXTRACT_STEP20_EXPEDITE		= "41";

	public enum ETopic implements EnumInterface
	{
		Internal(INTERNAL_TOPIC), Message(MESSAGE_TOPIC), DataExtract(DATA_EXTRACT_TOPIC), DataExtractReply(DATA_EXTRACT_REPLY_TOPIC);

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

	public enum EStep implements EnumInterface
	{
		Default, Step1, Step2, Step3, Step4, Step5, Step6, Step7, Step8, Step9, Step10, //
		Step11, Step12, Step13, Step14, Step15, Step16, Step17, Step18, Step19, Step20;
	}

	public enum EPartition implements KAFKAPartition
	{
		Message_In(NORMAL), Message_In_Expedite(EXPEDITE), //
		Data_In(DATA_IN), Data_In_Expedite(DATA_IN_EXPEDITE), //
		Step1(EXTRACT_STEP1), Step1_Expedite(EXTRACT_STEP1_EXPEDITE), //
		Step2(EXTRACT_STEP2), Step2_Expedite(EXTRACT_STEP2_EXPEDITE), //
		Step3(EXTRACT_STEP3), Step3_Expedite(EXTRACT_STEP3_EXPEDITE), //
		Step4(EXTRACT_STEP4), Step4_Expedite(EXTRACT_STEP4_EXPEDITE), //
		Step5(EXTRACT_STEP5), Step5_Expedite(EXTRACT_STEP5_EXPEDITE), //
		Step6(EXTRACT_STEP6), Step6_Expedite(EXTRACT_STEP6_EXPEDITE), //
		Step7(EXTRACT_STEP7), Step7_Expedite(EXTRACT_STEP7_EXPEDITE), //
		Step8(EXTRACT_STEP8), Step8_Expedite(EXTRACT_STEP8_EXPEDITE), //
		Step9(EXTRACT_STEP9), Step9_Expedite(EXTRACT_STEP9_EXPEDITE), //
		Step10(EXTRACT_STEP10), Step10_Expedite(EXTRACT_STEP10_EXPEDITE), //
		Step11(EXTRACT_STEP11), Step11_Expedite(EXTRACT_STEP11_EXPEDITE), //
		Step12(EXTRACT_STEP12), Step12_Expedite(EXTRACT_STEP12_EXPEDITE), //
		Step13(EXTRACT_STEP13), Step13_Expedite(EXTRACT_STEP13_EXPEDITE), //
		Step14(EXTRACT_STEP14), Step14_Expedite(EXTRACT_STEP14_EXPEDITE), //
		Step15(EXTRACT_STEP15), Step15_Expedite(EXTRACT_STEP15_EXPEDITE), //
		Step16(EXTRACT_STEP16), Step16_Expedite(EXTRACT_STEP16_EXPEDITE), //
		Step17(EXTRACT_STEP17), Step17_Expedite(EXTRACT_STEP17_EXPEDITE), //
		Step18(EXTRACT_STEP18), Step18_Expedite(EXTRACT_STEP18_EXPEDITE), //
		Step19(EXTRACT_STEP19), Step19_Expedite(EXTRACT_STEP19_EXPEDITE), //
		Step20(EXTRACT_STEP20), Step20_Expedite(EXTRACT_STEP20_EXPEDITE);

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
