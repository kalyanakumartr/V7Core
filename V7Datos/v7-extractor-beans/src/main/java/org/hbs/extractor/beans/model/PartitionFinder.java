package org.hbs.extractor.beans.model;

import java.io.Serializable;

import org.hbs.core.kafka.IKafkaConstants.EPartition;
import org.hbs.core.kafka.IKafkaConstants.EStep;
import org.hbs.core.kafka.IKafkaConstants.ETopic;
import org.hbs.core.kafka.KAFKAPartition;

public class PartitionFinder implements Serializable
{
	private static final long		serialVersionUID	= 2676577784202651153L;
	private static PartitionFinder	partitionFinder		= null;

	private PartitionFinder()
	{

	}

	public static PartitionFinder getInstance()
	{
		if (partitionFinder == null)
		{
			partitionFinder = new PartitionFinder();
		}
		return partitionFinder;
	}

	public KAFKAPartition find(ETopic eTopic, EMessagePriority priority) throws Exception
	{
		return find(eTopic, priority, EStep.Default);
	}

	public KAFKAPartition find(ETopic eTopic, EMessagePriority priority, EStep eStep) throws Exception
	{
		switch ( eTopic )
		{
			case Message :
			{
				switch ( priority )
				{
					case Expedite :
						return EPartition.Message_In_Expedite;
					case Normal :
					default :
						return EPartition.Message_In;
				}
			}
			case DataExtract :
			{
				switch ( priority )
				{

					case Normal :
					{
						switch ( eStep )
						{
							case Default :
								return EPartition.Data_In;
							case Step1 :
							case Step2 :
							case Step3 :
							case Step4 :
							case Step5 :
							case Step6 :
							case Step7 :
							case Step8 :
							case Step9 :
							case Step10 :
							case Step11 :
							case Step12 :
							case Step13 :
							case Step14 :
							case Step15 :
							case Step16 :
							case Step17 :
							case Step18 :
							case Step19 :
							case Step20 :
								return EPartition.valueOf(eStep.name());
							default :
								throw new Exception("Not Able To Find Default Step For DataExtract");
						}
					}
					case Expedite :
					{
						switch ( eStep )
						{
							case Default :
								return EPartition.Data_In_Expedite;
							case Step1 :
							case Step2 :
							case Step3 :
							case Step4 :
							case Step5 :
							case Step6 :
							case Step7 :
							case Step8 :
							case Step9 :
							case Step10 :
							case Step11 :
							case Step12 :
							case Step13 :
							case Step14 :
							case Step15 :
							case Step16 :
							case Step17 :
							case Step18 :
							case Step19 :
							case Step20 :
								return EPartition.valueOf(eStep.name() + "_" + priority.name());
							default :
								throw new Exception("Not Able To Find Default Step For DataExtract");
						}
					}
					default :
						throw new Exception("Not Able To Find Default Priority For DataExtract");
				}
			}
			default :
				break;

		}
		return null;

	}

}
