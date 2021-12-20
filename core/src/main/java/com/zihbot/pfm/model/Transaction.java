package com.zihbot.pfm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Transaction {
    private @Id @GeneratedValue Long id;
    private Long amount;
    private @ManyToOne Account source;
    private @ManyToOne Account target;
    private String type;
}
