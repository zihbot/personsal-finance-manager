package com.zihbot.pfm.model;

import java.util.List;

import lombok.Data;

@Data
public class TransactionCreateDto {
    private Long amount;
    private Long sourceId;
    private Long targetId;
    private Long category;
    private Long time;
    private String name;
    private List<String> labels;
}
