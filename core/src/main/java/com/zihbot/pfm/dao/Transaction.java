package com.zihbot.pfm.dao;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Transaction {
    private @Id @GeneratedValue Long id;
    private String user;
    private Long amount;
    private @ManyToOne Account source;
    private @ManyToOne Account target;
    private @ManyToOne Category category;
    private Long time;
    private @ManyToOne Label name;
    private @ManyToMany Set<Label> labels;
}
