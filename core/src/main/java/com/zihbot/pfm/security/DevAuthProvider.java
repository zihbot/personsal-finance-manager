package com.zihbot.pfm.security;

import java.util.ArrayList;

import com.zihbot.pfm.configuration.ApplicationConstants;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

//@Component
//@Profile(ApplicationConstants.PROFILE_DEV)
@RequiredArgsConstructor
public class DevAuthProvider implements AuthenticationProvider {
    private final UserAuthService userAuthService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final String username = auth.getName();
        final String password = auth.getCredentials().toString();

        boolean valid = userAuthService.isAuthenticationValid(username, password);
        if (!valid) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
