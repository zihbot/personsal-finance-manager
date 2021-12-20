package com.zihbot.pfm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.zihbot.pfm.dto.AccountDto;
import com.zihbot.pfm.model.Account;
import com.zihbot.pfm.repository.AccountRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

	public List<AccountDto> listAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
            .map(m -> modelToDto(m))
            .collect(Collectors.toList());
    }

    public AccountDto insertAccount(AccountDto account) {
        account.setId(null);
        Account result = accountRepository.save(this.dtoToModel(account));
        return this.modelToDto(result);
    }

    private AccountDto modelToDto(final Account model) {
        AccountDto dto = new AccountDto();
        dto.setId(model.getId());
        dto.setBalance(model.getBalance());
        dto.setName(model.getName());
        return dto;
    }

    private Account dtoToModel(final AccountDto dto) {
        Account model = new Account();
        model.setId(dto.getId());
        model.setBalance(dto.getBalance());
        model.setName(dto.getName());
        return model;
    }
}
