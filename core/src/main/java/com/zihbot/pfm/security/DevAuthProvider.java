package com.zihbot.pfm.security;

import java.util.ArrayList;

import com.zihbot.pfm.configuration.ApplicationConstants;
import com.zihbot.pfm.dao.UserAuth;
import com.zihbot.pfm.repository.UserAuthRepository;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@Profile(ApplicationConstants.PROFILE_DEV)
@RequiredArgsConstructor
public class DevAuthProvider implements AuthenticationProvider {

    private final UserAuthRepository authRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final String username = auth.getName();
        final String password = auth.getCredentials().toString();
        final UserAuth userAuth = authRepo.findByUsername(username);

        if (userAuth == null) return null;

        boolean valid = passwordEncoder.matches(password + userAuth.getSalt(), userAuth.getPassword());
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
