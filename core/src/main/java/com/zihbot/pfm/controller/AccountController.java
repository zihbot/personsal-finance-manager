package com.zihbot.pfm.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.zihbot.pfm.dao.Account;
import com.zihbot.pfm.model.AccountDto;
import com.zihbot.pfm.service.AccountService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
	private final AccountService accountService;

	@GetMapping()
	public List<AccountDto> listAccounts() {
		List<Account> accounts = accountService.listAccounts();
        return accounts.stream()
            .map(m -> new AccountDto(m))
            .collect(Collectors.toList());
	}

	@PostMapping()
	public AccountDto insertAccount(@RequestBody AccountDto account) {
        Account result = accountService.insertAccount(convertToEntity(account));
		return new AccountDto(result);
	}

    private Account convertToEntity(final AccountDto input) {
        Account result = new Account();
        result.setId(input.getId());
        result.setBalance(input.getBalance());
        result.setName(input.getName());
        return result;
    }
}
