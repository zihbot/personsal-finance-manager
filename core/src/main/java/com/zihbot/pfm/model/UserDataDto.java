package com.zihbot.pfm.model;

import java.util.List;

import lombok.Data;

@Data
public class UserDataDto {
    private List<AccountDto> accounts;
    private List<CategoryDto> categories;
}
