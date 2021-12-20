package com.zihbot.pfm.controller;

import java.util.List;

import com.zihbot.pfm.dto.AccountDto;
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
	public List<AccountDto> listTransactions() {
		return accountService.listAccounts();
	}

	@PostMapping()
	public AccountDto insertTransaction(@RequestBody AccountDto account) {
		return accountService.insertAccount(account);
	}
}
