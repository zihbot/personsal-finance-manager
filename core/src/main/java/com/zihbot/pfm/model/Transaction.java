package com.zihbot.pfm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Transaction {
    private @Id @GeneratedValue Long id;
    private Long amount;
    private String source;
    private String target;
    private String type;
}
