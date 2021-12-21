package com.zihbot.pfm.controller;

import java.util.List;
import java.util.Optional;

import com.zihbot.pfm.dao.Transaction;
import com.zihbot.pfm.model.TransactionCreateDto;
import com.zihbot.pfm.model.TransactionItemDto;
import com.zihbot.pfm.service.TransactionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
	private final TransactionService transactionService;

	@GetMapping()
	public List<TransactionItemDto> listTransactions() {
		List<Transaction> transactions = transactionService.listTransactions();
		return transactions.stream().map(this::convertToItemDto).toList();
	}

	@PostMapping()
	public TransactionItemDto insertTransaction(@RequestBody TransactionCreateDto transactionDto) {
		Transaction transaction = new Transaction();
		transaction.setAmount(transactionDto.getAmount());
		transaction.setType(transactionDto.getType());
		transaction = transactionService.insertTransaction(
			transaction,
			transactionDto.getSourceId(),
			transactionDto.getTargetId());
		return this.convertToItemDto(transaction);
	}

	private TransactionItemDto convertToItemDto(final Transaction input) {
		TransactionItemDto result = new TransactionItemDto();
		result.setId(input.getId());
		result.setAmount(input.getAmount());
		result.setSource(Optional.ofNullable(input.getSource())
			.map(s -> s.getId()).orElse(null));
		result.setTarget(Optional.ofNullable(input.getTarget())
			.map(t -> t.getId()).orElse(null));
		result.setType(input.getType());
		return result;
	}
}
