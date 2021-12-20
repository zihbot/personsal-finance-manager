package com.zihbot.pfm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Account {
    private @Id @GeneratedValue Long id;
    private String name;
    private Long balance;
}
