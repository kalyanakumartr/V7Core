package org.hbs.v7.dao;

import org.hbs.v7.beans.model.IncomingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IncomingDao extends JpaRepository<IncomingData, String>
{

	@Query("Select MAX(ID.sentTime) From IncomingData ID where ID.producer.producerId = :producerId Order By sentTime Desc")
	public Long getLastEmailSentDate(@Param("producerId") String producerId);
}
