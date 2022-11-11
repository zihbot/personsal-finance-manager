package com.zihbot.pfm.repository;

import com.zihbot.pfm.dao.Label;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    Label getByUserAndName(String user, String name);
    List<Label> findAllByUser(String user);
    void deleteAllByUser(String user);
}
