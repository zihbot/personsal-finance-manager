package com.zihbot.pfm.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
    System.out.println("token " + token);
    if (token == null) {
      filterChain.doFilter(request, response);
      return;
    }

    System.out.println("token " + token);
    final String username = jwtService.validateToken(token.replace("Bearer ", ""));
    if (username == null) {
      filterChain.doFilter(request, response);
      return;
    }

    Authentication auth = new PreAuthenticatedAuthenticationToken(username, token);
    SecurityContextHolder.getContext().setAuthentication(auth);
    filterChain.doFilter(request, response);
  }
}
