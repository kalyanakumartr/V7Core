package org.hbs.extractor.dao;

import org.hbs.extractor.beans.model.IncomingData;
import org.springframework.data.repository.CrudRepository;

public interface IncomingDao extends CrudRepository<IncomingData, String>
{

}
