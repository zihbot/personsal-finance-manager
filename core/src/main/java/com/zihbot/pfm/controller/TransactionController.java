package com.zihbot.pfm.controller;

import java.util.List;

import com.zihbot.pfm.dto.TransactionDto;
import com.zihbot.pfm.service.TransactionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
	private final TransactionService transactionService;

	@GetMapping()
	public List<TransactionDto> listTransactions() {
		return transactionService.listTransactions();
	}

	@PostMapping()
	public String insertTransaction() {
		return "OK";
	}
}
