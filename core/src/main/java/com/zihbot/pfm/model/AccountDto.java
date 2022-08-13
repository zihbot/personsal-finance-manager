package com.zihbot.pfm.model;

import com.zihbot.pfm.dao.Account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String name;
    private Long balance;

    public AccountDto(final Account input) {
        id = input.getId();
        name = input.getName();
        balance = input.getBalance();
    }
}
