package org.hbs.core.dao;

import java.util.List;

import org.hbs.core.beans.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends CrudRepository<City, String>
{
	@Query("From City where state.state = :state and city Like :city and status = true")
	List<City> getCityList(@Param("state") String state, @Param("city") String city);

	@Query("From City where state.state = :state and status = true")
	List<City> getCityList(@Param("state") String state);
}
