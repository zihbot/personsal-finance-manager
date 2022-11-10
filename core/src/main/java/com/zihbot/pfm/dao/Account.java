package com.zihbot.pfm.dao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Account {
    private @Id @GeneratedValue Long id;
    private @ManyToOne PfmUser user;
    private String name;
    private Long balance;

    private @OneToMany(mappedBy = "target") List<Transaction> transactionsIn;
    private @OneToMany(mappedBy = "source") List<Transaction> transactionsOut;
}
