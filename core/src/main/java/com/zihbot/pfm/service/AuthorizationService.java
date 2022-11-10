package com.zihbot.pfm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
	@Value("${pfm.auth.root:root}") private String rootUsername;

	public String username() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public boolean isRoot() {
        return rootUsername.equals(username());
    }
}
