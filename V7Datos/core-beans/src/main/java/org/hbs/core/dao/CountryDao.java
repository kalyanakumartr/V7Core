package org.hbs.core.dao;

import java.util.List;

import org.hbs.core.beans.model.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDao extends CrudRepository<Country, String>
{

	@Query("select C FROM Country C WHERE C.countryName Like :countryName")
	List<Country> getCountryList(@Param("countryName") String countryName);
}
