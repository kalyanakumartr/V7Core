package org.hbs.core.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hbs.core.beans.model.ProducersProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationDao extends CrudRepository<ProducersProperty, String>
{

	@Query("From ProducersProperty where producerid = ?0")
	List<ProducersProperty> fetchByProducerId(String producerId);

	@Query("From ProducersProperty where producerid = ?0 and autoid = ?1")
	ProducersProperty fetchByAutoId(String producerId, String autoId);

	@Query("From ProducersProperty where status = true and producer.producerId = ?0 and media like ?1 and mediatype like ?2 and mediamode like ?3")
	List<ProducersProperty> getConfigurationByType(String producerId, String media, String mediaType, String mediaMode);

	@Query("From ProducersProperty where status = true and producerid = ?0 and groupname like ?1 ")
	List<ProducersProperty> searchByConfigurationName(String producerId, String groupName);

	@Query("select count(1) from ProducersProperty where producerid = ?0 and groupname = ?1")
	int checkConfigurationExists(String producerId, String groupName);

	@Query("update ProducersProperty set status = ?0,  modifiedby = ?1 ,modifieddate = ?2 where autoid = ?3 ")
	void blockConfiguration(boolean status, String modifiedBy, Timestamp modifiedDate, String autoId);

	@Query("delete from ProducersProperty where producerid = ?0 and autoid=?1 ")
	void deleteConfiguration(String producerId, String autoId);

}
