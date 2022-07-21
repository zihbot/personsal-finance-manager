package com.zihbot.pfm.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zihbot.pfm.configuration.ApplicationConstants;

import lombok.RequiredArgsConstructor;

@Component
@Profile(ApplicationConstants.PROFILE_DEV)
@RequiredArgsConstructor
public class DevAuthenticationFilter extends OncePerRequestFilter implements PfmAuthFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null || !token.equals("XXXPFMXXX")) {
            filterChain.doFilter(request, response);
            return;
        }
    
        Authentication auth = new PreAuthenticatedAuthenticationToken("demouser", token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);                
    }

}
