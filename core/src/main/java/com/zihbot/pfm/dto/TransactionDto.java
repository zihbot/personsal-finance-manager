package com.zihbot.pfm.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private Long id;
    private Long amount;
    private Long source;
    private Long target;
    private String type;
}
