package org.hbs.sender;

import org.hbs.core.beans.ConfigurationFormBean;
import org.hbs.core.beans.path.IPathSender;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public interface IConfigurationController extends IPathSender
{
	@PostMapping
	@RequestMapping(value = GET_CONFIGURATION_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> getConfigurationDetails(Authentication auth);

	@PostMapping
	@RequestMapping(value = SEARCH_CONFIGURATION, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> searchConfiguration(Authentication auth, ConfigurationFormBean cfBean);

	@PostMapping
	@RequestMapping(value = ADD_CONFIGURATION, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> addConfiguration(Authentication auth, ConfigurationFormBean cfBean);

	@PostMapping
	@RequestMapping(value = UPDATE_CONFIGURATION, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> updateConfiguration(Authentication auth, ConfigurationFormBean cfBean);

	@PostMapping
	@RequestMapping(value = BLOCK_CONFIGURATION, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> blockConfiguration(Authentication auth, ConfigurationFormBean cfBean);

	@PostMapping
	@RequestMapping(value = DELETE_CONFIGURATION, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_ADMINISTRATOR)
	ResponseEntity<?> deleteConfiguration(Authentication auth, ConfigurationFormBean cfBean);

}