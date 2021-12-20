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

    public TransactionDto insertTransaction(TransactionDto transaction) {
        transaction.setId(null);
        Transaction result = transactionRepository.save(this.dtoToModel(transaction));
        return this.modelToDto(result);
    }

    private TransactionDto modelToDto(Transaction model) {
        TransactionDto dto = new TransactionDto();
        dto.setId(model.getId());
        dto.setAmount(model.getAmount());
        dto.setSource(model.getSource());
        dto.setTarget(model.getTarget());
        dto.setType(model.getType());
        return dto;
    }

    private Transaction dtoToModel(TransactionDto dto) {
        Transaction model = new Transaction();
        model.setId(dto.getId());
        model.setAmount(dto.getAmount());
        model.setSource(dto.getSource());
        model.setTarget(dto.getTarget());
        model.setType(dto.getType());
        return model;
    }
}
