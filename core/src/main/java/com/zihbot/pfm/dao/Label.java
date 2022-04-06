package com.zihbot.pfm.dao;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Label {
    private @Id @GeneratedValue Long id;
    private String name;
    private @ManyToMany(mappedBy = "labels") Set<Transaction> connections;
    private @OneToMany(mappedBy = "name") Set<Transaction> transactions;
}
