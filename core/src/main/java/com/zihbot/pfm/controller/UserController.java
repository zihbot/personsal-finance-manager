package com.zihbot.pfm.controller;

import com.zihbot.pfm.security.JwtService;
import com.zihbot.pfm.security.UserAuthService;
import com.zihbot.pfm.service.UserService;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final JwtService jwtService;
	private final UserAuthService userAuthService;
	private final UserService authoritationService;

	@PostMapping("")
	public void createUser(@RequestBody CreateUserRequest account) {
		if (!authoritationService.isRoot()) {
			throw new AccessDeniedException("Forbidden");
		}
		userAuthService.createUser(account.getUsername(), account.getPassword());
	}

	@DeleteMapping("/{username}")
	public void createUser(@PathVariable String username) {
		if (!authoritationService.isRoot()) {
			throw new AccessDeniedException("Forbidden");
		}
		userAuthService.deleteUser(username);
	}

	@PostMapping("login")
	public String login(@RequestBody LoginPostDto account) {
		if (!userAuthService.isAuthenticationValid(account.getUsername(), account.getPassword())) {
			throw new BadCredentialsException("Invalid credentials");
		}
		return jwtService.createToken(account.getUsername());
	}

	@Data
	public static class LoginPostDto {
		private String username;
		private String password;
	}

	@Data
	public static class CreateUserRequest {
		private String username;
		private String password;
	}
}
