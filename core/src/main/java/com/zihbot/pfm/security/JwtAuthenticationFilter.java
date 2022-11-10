package com.zihbot.pfm.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter implements PfmAuthFilter {
  private final JwtService jwtService;

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (token == null) {
      filterChain.doFilter(request, response);
      return;
    }

    final String username = jwtService.validateToken(token.replace("Bearer ", ""));
    if (username == null) {
      filterChain.doFilter(request, response);
      return;
    }

    log.info("Token for {}", username);
    Authentication auth = new PreAuthenticatedAuthenticationToken(username, token);
    SecurityContextHolder.getContext().setAuthentication(auth);
    filterChain.doFilter(request, response);
  }
}
