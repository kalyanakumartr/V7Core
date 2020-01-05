package org.hbs.extractor.dao;

import java.util.List;

import org.hbs.extractor.beans.model.DataAttachments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataAttachmentDao extends CrudRepository<DataAttachments, String>
{
	@Query("From DataAttachments where message.messageId = ?0")
	List<DataAttachments> getByMessageId(final String messageId);

	@Query("select count(*) from DataAttachments where message.messageId = ?0")
	long countByMessageId(final String messageId);

}
