package com.zihbot.pfm.model;

import lombok.Data;

@Data
public class TransactionItemDto {
    private Long id;
    private Long amount;
    private Long source;
    private Long target;
    private String type;
    private Long time;
}
