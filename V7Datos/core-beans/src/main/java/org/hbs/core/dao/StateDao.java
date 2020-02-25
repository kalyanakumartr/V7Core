package org.hbs.core.dao;

import java.util.List;

import org.hbs.core.beans.model.State;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StateDao extends CrudRepository<State, String>
{

	@Query("From State S where S.country.country = :country and S.state Like :statename and S.status = true")
	List<State> getStateList(@Param("country") String country, @Param("statename") String statename);

	@Query("From State S where S.country.country = :country and S.status = true")
	List<State> getStateList(@Param("country") String country);

}
