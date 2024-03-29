package com.zihbot.pfm.repository;

import com.zihbot.pfm.dao.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUser(String user);
    void deleteAllByUser(String user);
}
