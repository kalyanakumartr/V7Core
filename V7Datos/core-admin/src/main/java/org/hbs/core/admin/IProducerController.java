package org.hbs.core.admin;

import org.hbs.core.beans.ProducerFormBean;
import org.hbs.core.beans.path.IPathAdmin;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public interface IProducerController extends IPathAdmin
{
	@PostMapping
	@RequestMapping(value = ADD_CUSTOMER)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> addProducer(Authentication auth, @RequestBody ProducerFormBean customerForm);

	@PostMapping
	@RequestMapping(value = BLOCK_CUSTOMER)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> blockProducer(Authentication auth, @RequestBody ProducerFormBean customerForm);

	@PostMapping
	@RequestMapping(value = DELETE_CUSTOMER)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> deleteProducer(Authentication auth, @RequestBody ProducerFormBean customerForm);

	@PostMapping
	@RequestMapping(value = CHECK_CUSTOMER_EXIST)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> checkProducerExist(Authentication auth, @RequestBody ProducerFormBean customerForm);

	@PostMapping
	@RequestMapping(value = PREUPDATE_CUSTOMER)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> getProducer(Authentication auth, @RequestBody ProducerFormBean customerForm);

	@PostMapping
	@RequestMapping(value = SEARCH_CUSTOMER)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> getProducerByName(Authentication auth, @RequestBody ProducerFormBean customerForm);

	@PostMapping
	@RequestMapping(value = UPDATE_CUSTOMER)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> updateProducer(Authentication auth, @RequestBody ProducerFormBean customerForm);

	@PostMapping
	@RequestMapping(value = CUSTOMER_LIST)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> getProducerList(Authentication auth);
}