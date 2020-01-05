package org.hbs.core.dao;

import java.util.List;

import org.hbs.core.beans.model.channel.ChannelMessages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelMessagesDao extends CrudRepository<ChannelMessages, String>
{

	@Query("From ChannelMessages where producer.producerId = ?0 and messageid = ?1 and status = true")
	List<ChannelMessages> getByMessageId(final String producerId, final String messageId);

	@Query("From ChannelMessages where producer.producerId = ?0 and messagetype = ?1 and status = true")
	List<ChannelMessages> getByMessageType(final String producerId, final String messageType);

}
