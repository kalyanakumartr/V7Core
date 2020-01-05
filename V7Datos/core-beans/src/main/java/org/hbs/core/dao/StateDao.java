package org.hbs.core.dao;

import java.util.List;

import org.hbs.core.beans.model.State;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateDao extends CrudRepository<State, String>
{

	@Query("From State where country.country = ?0 and state Like ?1 and status = true")
	List<State> getStateList(String country, String statename);

	@Query("From State where country.country = ?0 and status = true")
	List<State> getStateList(String country);

}
