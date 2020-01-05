package org.hbs.core.dao;

import org.hbs.core.beans.model.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesDao extends CrudRepository<Roles, String>
{

}
