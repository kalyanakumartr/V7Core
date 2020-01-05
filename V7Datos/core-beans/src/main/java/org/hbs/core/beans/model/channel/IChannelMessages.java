package org.hbs.core.beans.model.channel;

import java.sql.Timestamp;

import org.hbs.core.beans.model.IMessages;

public interface IChannelMessages extends IMessages
{
	public String getExpiryDate();

	public String getMessageId();

	public Timestamp getNextDeliveryDate();

	public String getScheduledDate();

	public void setExpiryDate(String expiryDate);

	public void setMessageId(String messageId);

	public void setNextDeliveryDate(Timestamp nextDeliveryDate);

	public void setScheduledDate(String scheduledDate);
}
