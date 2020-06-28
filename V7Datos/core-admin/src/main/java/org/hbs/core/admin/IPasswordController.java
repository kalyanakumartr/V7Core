package org.hbs.core.admin;

import javax.servlet.http.HttpServletResponse;

import org.hbs.core.beans.PasswordFormBean;
import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.path.IPathAdmin;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public interface IPasswordController extends IPathAdmin
{

	@GetMapping
	@RequestMapping(value = CHANGE_PASSWORD)
	ResponseEntity<?> changePassword(@PathVariable("token") String token, HttpServletResponse response);

	@GetMapping
	@RequestMapping(value = VALIDATE_USER)
	void validateUser(@PathVariable("token") String token, HttpServletResponse response);

	@PostMapping
	@RequestMapping(value = UPDATE_PASSWORD)
	ResponseEntity<?> updatePassword(Authentication auth, @RequestBody PasswordFormBean pfBean);

	@GetMapping
	@RequestMapping(value = FORGOT_PASSWORD)
	ResponseEntity<?> sendForgotPasswordEmail(@RequestBody UserFormBean ufBean);

}