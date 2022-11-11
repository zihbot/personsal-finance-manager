package com.zihbot.pfm.service;

import java.util.List;

import com.zihbot.pfm.dao.Account;
import com.zihbot.pfm.repository.AccountRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserService user;
    private final AccountRepository accountRepository;

	public List<Account> listAccounts() {
        return accountRepository.findAllByUser(user.username());
    }

    public Account insertAccount(Account account) {
        account.setId(null);
        account.setUser(user.username());
        if (account.getBalance() == null) {
            account.setBalance(0L);
        }
        return accountRepository.save(account);
    }

	public void deleteByUser(String user) {
        accountRepository.deleteAllByUser(user);
    }
}
