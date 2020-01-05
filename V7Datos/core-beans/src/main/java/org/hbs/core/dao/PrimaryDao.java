package org.hbs.core.dao;

import org.hbs.core.beans.model.Sequence;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimaryDao extends CrudRepository<Sequence, String>
{
	@Query("select sequenceId from Sequence where tablename = ?0")
	long findBySequenceKey(String sequencekey);

	@Modifying
	@Query("update Sequence set sequenceId = ?0 where sequenceKey = ?1")
	void updateSequenceIdByOne(long sequenceId, String sequencekey);
}
