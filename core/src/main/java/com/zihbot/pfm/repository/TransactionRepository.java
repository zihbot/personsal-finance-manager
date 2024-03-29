package com.zihbot.pfm.repository;

import com.zihbot.pfm.dao.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUser(String user);
    void deleteAllByUser(String user);
}
