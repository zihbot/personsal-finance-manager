package com.zihbot.pfm.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Category {
    private @Id @GeneratedValue Long id;
    private String name;
    private String icon;
    private String color;
}
