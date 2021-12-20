package com.zihbot.pfm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.zihbot.pfm.dto.TransactionDto;
import com.zihbot.pfm.model.Account;
import com.zihbot.pfm.model.Transaction;
import com.zihbot.pfm.repository.AccountRepository;
import com.zihbot.pfm.repository.TransactionRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

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
        if (model.getSource() != null) {
            dto.setSource(model.getSource().getId());
        }
        if (model.getTarget() != null) {
            dto.setTarget(model.getTarget().getId());
        }
        dto.setType(model.getType());
        return dto;
    }

    private Transaction dtoToModel(TransactionDto dto) {
        Transaction model = new Transaction();
        model.setId(dto.getId());
        model.setAmount(dto.getAmount());
        if (dto.getSource() != null) {
            final Account source = accountRepository.findById(dto.getSource())
                .orElseThrow(() -> new RuntimeException());
            model.setSource(source);
        }
        if (dto.getTarget() != null) {
            final Account target = accountRepository.findById(dto.getTarget())
                .orElseThrow(() -> new RuntimeException());
            model.setTarget(target);
        }
        model.setType(dto.getType());
        return model;
    }
}
