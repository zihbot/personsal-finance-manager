package com.zihbot.pfm.model;

import lombok.Data;

@Data
public class TransactionCreateDto {
    private Long amount;
    private Long sourceId;
    private Long targetId;
    private String type;
}
