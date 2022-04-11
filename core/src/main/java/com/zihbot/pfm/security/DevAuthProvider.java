package com.zihbot.pfm.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import com.zihbot.pfm.configuration.ApplicationConstants;
import com.zihbot.pfm.dao.UserAuth;
import com.zihbot.pfm.repository.UserAuthRepository;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@Profile(ApplicationConstants.PROFILE_DEV)
@RequiredArgsConstructor
public class DevAuthProvider implements AuthenticationProvider {

    private final UserAuthRepository authRepo;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final String username = auth.getName();
        final String password = auth.getCredentials().toString();
        final UserAuth userAuth = authRepo.findByUsername(username);

        if (userAuth == null) return null;

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        md.update((password + userAuth.getSalt()).getBytes());
        final String hashedIncomingPass = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
        if (!userAuth.getPassword().equals(hashedIncomingPass)) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(username, hashedIncomingPass, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
