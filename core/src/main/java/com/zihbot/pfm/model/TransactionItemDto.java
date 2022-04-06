package com.zihbot.pfm.model;

import java.util.List;

import lombok.Data;

@Data
public class TransactionItemDto {
    private Long id;
    private Long amount;
    private Long source;
    private Long target;
    private String type;
    private Long time;
    private String name;
    private List<String> labels;
}
