package org.hbs.core.dao;

import java.util.List;

import org.hbs.core.beans.model.Users;
import org.hbs.core.beans.model.UsersMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<Users, String>
{
	@Query("select UM FROM UsersMedia UM where UM.users.employeeId = :employeeId")
	List<UsersMedia> fetchMediaByEmployeeId(@Param("employeeId") String employeeId);

	@Query("select M.users, M.users.producer.producerName, M.users.parentProducer.producerName FROM UsersMedia M Where M.mediaType = 'Primary' AND (M.emailId = :loginBy OR M.mobileNo = :loginBy OR M.users.userId = :loginBy )")
	Object findByEmailOrMobileOrUserId(@Param("loginBy") String emailOrMobileOrUserId);

	@Query("select U from Users U where U.producer.producerId = :producerId")
	List<Users> findByProducerId(@Param("producerId") String producerId);

	@Query("select Distinct(U.employeeId), U.userName FROM Users U JOIN U.mediaList M WHERE U.status = true AND U.userStatus = 'Activated' AND (U.userName Like :searchParam OR M.emailId Like :searchParam) AND U.producer.producerId = :producerId")
	List<Object[]> fetchUsersByUserNameOrEmailId(@Param("producerId") String producerId, @Param("searchParam") String searchParam);

	@Query("select U.userName FROM Users U JOIN U.mediaList M WHERE ( M.emailId = :emailId OR M.mobileNo = :mobileNo ) AND U.producer.producerId = :producerId")
	List<String> checkUserNameEmailIdOrMobileNo(@Param("producerId") String producerId, @Param("emailId") String emailId, @Param("mobileNo") String mobileNo);

	@Query("select U FROM Users U JOIN U.mediaList M WHERE M.emailId Like %:searchParam% OR M.mobileNo Like %:searchParam% OR M.whatsAppNo Like %:searchParam% OR U.userId Like %:searchParam% OR U.userName Like %:searchParam% OR U.dateOfJoin Like %:searchParam% OR U.fatherName Like %:searchParam% ")
	List<Users> searchUser(@Param("searchParam") String searchParam);
}
