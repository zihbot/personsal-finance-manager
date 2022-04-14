package com.zihbot.pfm.security;

import com.zihbot.pfm.dao.UserAuth;
import com.zihbot.pfm.repository.UserAuthRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthService {

  private final UserAuthRepository authRepo;
  private final PasswordEncoder passwordEncoder;

  public boolean isAuthenticationValid(String username, String password) {

    final UserAuth userAuth = authRepo.findByUsername(username);
    if (userAuth == null) return false;

    return passwordEncoder.matches(password + userAuth.getSalt(), userAuth.getPassword());
  }
}
