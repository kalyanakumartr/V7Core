package org.hbs.core.admin;

import javax.servlet.http.HttpServletResponse;

import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.path.IPathAdmin;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public interface IUserController extends IPathAdmin
{
	@PostMapping
	@RequestMapping(value = ADD_USER, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_SUPERADMIN_OR_ADMIN)
	public ResponseEntity<?> addUser(Authentication auth, UserFormBean ufBean);

	@PostMapping
	@RequestMapping(value = GET_ACTIVE_USER, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	public ResponseEntity<?> getUserByEmailOrMobileOrUserId(Authentication auth, @RequestBody UserFormBean userFormBean);

	@PostMapping
	@RequestMapping(value = PRESEARCH_USER, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	public ResponseEntity<?> getUser(Authentication auth, UserFormBean ufBean);

	@PostMapping
	@RequestMapping(value = BLOCK_USER, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	public ResponseEntity<?> blockUser(Authentication auth, UserFormBean ufBean);

	@PostMapping
	@RequestMapping(value = UPDATE_USER, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	public ResponseEntity<?> updateUser(Authentication auth, UserFormBean ufBean);

	@PostMapping
	@RequestMapping(value = SEARCH_USER, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	public ResponseEntity<?> search(Authentication auth, UserFormBean ufBean);

	@PostMapping
	@RequestMapping(value = DELETE_USER, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	public ResponseEntity<?> deleteUser(Authentication auth, UserFormBean ufBean);

	@GetMapping
	@RequestMapping(value = VALIDATE_USER)
	public void validateUser(@PathVariable("token") String token, HttpServletResponse response);

	@PostMapping
	@RequestMapping(value = SEARCH_COUNTRY, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	ResponseEntity<?> getCountry(Authentication auth, UserFormBean userFormBean);

	@PostMapping
	@RequestMapping(value = SEARCH_STATE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	ResponseEntity<?> getStates(Authentication auth, UserFormBean userFormBean);

	@PostMapping
	@RequestMapping(value = SEARCH_CITY, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	ResponseEntity<?> getCities(Authentication auth, UserFormBean userFormBean);

	@GetMapping
	@RequestMapping(value = GET_ALL_USERS, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	public ResponseEntity<?> getAllUsers(Authentication auth, UserFormBean userFormBean);

	@GetMapping
	@RequestMapping(value = GET_USER_BY_CUSTID, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	public ResponseEntity<?> getUserListByProducer(Authentication auth);

	@PostMapping
	@RequestMapping(value = RESEND_ACTIVATION_LINK, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_BOTH)
	public ResponseEntity<?> resendActivationLink(Authentication auth, UserFormBean userFormBean);

}