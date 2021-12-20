package com.zihbot.pfm.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private Long id;
    private Long amount;
    private String source;
    private String target;
    private String type;
}
