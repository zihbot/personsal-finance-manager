package com.zihbot.pfm.controller;

import com.zihbot.pfm.security.JwtService;
import com.zihbot.pfm.security.UserAuthService;

import org.springframework.security.authentication.BadCredentialsException;
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
}
