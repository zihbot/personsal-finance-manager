package com.zihbot.pfm.security;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final String username = auth.getName();
        final String password = auth.getCredentials().toString();

        if (username == null) {
            return null;
        }

        return new PreAuthenticatedAuthenticationToken(username, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }

}
