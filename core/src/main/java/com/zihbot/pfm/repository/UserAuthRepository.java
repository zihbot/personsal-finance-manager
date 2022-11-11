package com.zihbot.pfm.repository;

import com.zihbot.pfm.dao.UserAuth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    public UserAuth findByUsername(String username);
    void deleteAllByUsername(String username);
}
