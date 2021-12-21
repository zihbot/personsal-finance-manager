package com.zihbot.pfm.service;

import java.util.List;

import com.zihbot.pfm.dao.Account;
import com.zihbot.pfm.repository.AccountRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

	public List<Account> listAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    public Account insertAccount(Account account) {
        account.setId(null);
        if (account.getBalance() == null) {
            account.setBalance(0L);
        }
        return accountRepository.save(account);
    }
}
