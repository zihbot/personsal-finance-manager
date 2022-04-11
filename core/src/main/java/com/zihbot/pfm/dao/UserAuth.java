package com.zihbot.pfm.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class UserAuth {
    private @Id @GeneratedValue Long id;
    private String username;
    private String password;
    private String salt;

}