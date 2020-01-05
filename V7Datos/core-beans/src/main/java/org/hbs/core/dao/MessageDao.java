package org.hbs.core.dao;

import java.util.List;

import org.hbs.core.beans.model.IMessages;
import org.hbs.core.beans.model.V7Messages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends CrudRepository<V7Messages, String>
{

	@Query("From Messages where producer.producerId = ?0 and messageId = ?1 and status = true")
	List<IMessages> getByMessageId(final String producerId, final String messageId);

	@Query("From Messages where producer.producerId = ?0 and messageType = ?1 and status = true")
	List<IMessages> getByMessageType(final String producerId, final String messageType);

}
