package com.zihbot.pfm.repository;

import com.zihbot.pfm.dao.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUser(String user);
    void deleteAllByUser(String user);
}
