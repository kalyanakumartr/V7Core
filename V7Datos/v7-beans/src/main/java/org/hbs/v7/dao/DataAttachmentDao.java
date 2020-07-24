package org.hbs.v7.dao;

import java.util.List;

import org.hbs.v7.beans.model.DataAttachments;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataAttachmentDao extends CrudRepository<DataAttachments, Long>
{
	@Query("From DataAttachments where autoId = ?0")
	List<DataAttachments> getByMessageId(final long autoId);

	@Query("select count(*) from DataAttachments where autoId = ?0")
	long countByMessageId(final long autoId);

	@Modifying
	@Query("update DataAttachments DAT set DAT.trace = :trace, DAT.description = :description Where DAT.autoId = :autoId")
	void updateReadStatus(@Param("autoId") long autoId, @Param("trace") String trace, @Param("description") String description);
}
