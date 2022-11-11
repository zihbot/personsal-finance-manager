package com.zihbot.pfm.security;

import com.zihbot.pfm.dao.UserAuth;
import com.zihbot.pfm.repository.UserAuthRepository;
import com.zihbot.pfm.service.PfmUserService;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthService {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final UserAuthRepository authRepo;
  private final PasswordEncoder passwordEncoder;
  private final PfmUserService pfmUserService;

  public boolean isAuthenticationValid(String username, String password) {

    final UserAuth userAuth = authRepo.findByUsername(username);
    if (userAuth == null) return false;

    return passwordEncoder.matches(password + userAuth.getSalt(), userAuth.getPassword());
  }

  @Transactional
  public void createUser(String username, String password) {
    logger.info("Create user {}", username);

    String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
    String salt;
    try {
      Random r = SecureRandom.getInstanceStrong();
      StringBuilder buf = new StringBuilder();
      buf.append(chars.charAt(r.nextInt(26)));
      for (int i = 0; i < 16 ; i++) {
        buf.append(chars.charAt(r.nextInt(chars.length())));
      }
      salt = buf.toString();
    } catch (NoSuchAlgorithmException e) {
      logger.warn("Cannot create salt, using default");
      salt = "X0X0X0X0X0X0X0X0";
    }

    UserAuth user = new UserAuth();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password + salt));
    user.setSalt(salt);
    authRepo.save(user);
    pfmUserService.createUser(username);
  }

  @Transactional
  public void deleteUser(String username) {
    logger.info("Delete user {}", username);

    authRepo.deleteAllByUsername(username);
    pfmUserService.deleteUser(username);
  }
}
