package org.hbs.extractor.dao;

import java.util.List;

import org.hbs.extractor.beans.model.DataAttachments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataAttachmentDao extends CrudRepository<DataAttachments, Long>
{
	@Query("From DataAttachments where autoId = ?0")
	List<DataAttachments> getByMessageId(final long autoId);

	@Query("select count(*) from DataAttachments where autoId = ?0")
	long countByMessageId(final long autoId);

}
