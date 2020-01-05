package org.hbs.core.dao;

import java.util.List;

import org.hbs.core.beans.model.channel.EmailAttachments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAttachmentDao extends CrudRepository<EmailAttachments, String>
{
	@Query("From EmailAttachments where message.messageId = ?0")
	List<EmailAttachments> getByMessageId(final String messageId);

	@Query("select count(*) from EmailAttachments where message.messageId = ?0")
	long countByMessageId(final String messageId);

}
