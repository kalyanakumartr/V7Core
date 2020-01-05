package org.hbs.core.beans.model.channel;

import java.sql.Timestamp;

import org.hbs.core.beans.model.ICommonDateAndStatusFields;
import org.hbs.core.beans.model.IMessages;
import org.hbs.core.beans.model.IProducersBase;
import org.hbs.core.util.ICRUDBean;

public interface IMessagesChannel extends ICommonDateAndStatusFields, IProducersBase, ICRUDBean, IMessages
{
	public String getExpiryDate();

	public Timestamp getNextDeliveryDate();

	public String getScheduledDate();

	public void setExpiryDate(String expiryDate);

	public void setNextDeliveryDate(Timestamp nextDeliveryDate);

}