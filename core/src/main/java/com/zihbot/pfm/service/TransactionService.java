package com.zihbot.pfm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.zihbot.pfm.dto.TransactionDto;
import com.zihbot.pfm.model.Transaction;
import com.zihbot.pfm.repository.TransactionRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

	public List<TransactionDto> listTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
            .map(m -> modelToDto(m))
            .collect(Collectors.toList());
    }

    private TransactionDto modelToDto(Transaction model) {
        TransactionDto dto = new TransactionDto();
        dto.setAmount(model.getAmount());
        dto.setSource(model.getSource());
        dto.setTarget(model.getTarget());
        dto.setType(model.getType());
        return dto;
    }
}
