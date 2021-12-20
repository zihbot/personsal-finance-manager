package com.zihbot.pfm.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Account {
    private @Id @GeneratedValue Long id;
    private String name;
    private Long balance;

    private @OneToMany(mappedBy = "target") List<Transaction> transactionsIn;
    private @OneToMany(mappedBy = "source") List<Transaction> transactionsOut;
}
