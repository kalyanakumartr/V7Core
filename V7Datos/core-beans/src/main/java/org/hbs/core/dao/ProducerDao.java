package org.hbs.core.dao;

import java.util.List;

import org.hbs.core.beans.model.Producers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerDao extends JpaRepository<Producers, String>
{
	@Query("Select P From Producers P where P.status = true And P.producerId = :producerId")
	public Producers findByProducerId(@Param("producerId") String producerId);

	public int countByProducerName(String producerName);

	public List<Producers> findByProducerName(String producerName);

	@Query("Select P From Producers P where P.producerName like %:producerName% ")
	public List<Producers> findLikeProducerName(@Param("producerName") String producerName);

	@Query("Select P.producerName From Producers P where P.producerName like %:producerName% ")
	public Object[] checkProducerName(@Param("producerName") String producerName);

	@Query("Select P From Producers P where P.status = :status")
	public List<Producers> findByStatus(@Param("status") Boolean status);

}
