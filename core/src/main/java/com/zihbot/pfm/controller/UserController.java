package com.zihbot.pfm.controller;

import com.zihbot.pfm.security.JwtService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final JwtService jwtService;

	@GetMapping("login")
	public String login() {
        final String user = SecurityContextHolder.getContext().getAuthentication().getName();
		return jwtService.createToken(user);
	}
}
