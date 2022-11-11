package com.zihbot.pfm.repository;

import com.zihbot.pfm.dao.PfmUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PfmUserRepository extends JpaRepository<PfmUser, Long> {
    PfmUser findByUsername(String username);
    void deleteByUsername(String username);
}
