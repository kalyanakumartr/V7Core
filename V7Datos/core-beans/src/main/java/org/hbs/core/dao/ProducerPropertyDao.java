package org.hbs.core.dao;

import java.util.List;

import org.hbs.core.beans.model.ProducersProperty;
import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.security.resource.IPathBase.EMediaMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerPropertyDao extends JpaRepository<ProducersProperty, String>
{

	@Query("Select PP.producer.producerId, PP.value From ProducersProperty PP where PP.status = true and PP.media = :eMedia and PP.mediaMode = :eMediaMode")
	public List<Object[]> getProperty(@Param("eMedia") EMedia eMedia, @Param("eMediaMode") EMediaMode eMediaMode);

	@Query("Select PP From ProducersProperty PP where PP.producer.producerId = :producerId AND PP.status = true and PP.media = :eMedia and PP.mediaMode = :eMediaMode")
	public ProducersProperty getProducerProperty(@Param("producerId") String producerId, @Param("eMedia") EMedia eMedia, @Param("eMediaMode") EMediaMode eMediaMode);
}
