package com.zihbot.pfm.service;

import java.security.InvalidParameterException;
import java.util.List;

import com.zihbot.pfm.dao.Account;
import com.zihbot.pfm.dao.Label;
import com.zihbot.pfm.dao.Transaction;
import com.zihbot.pfm.repository.AccountRepository;
import com.zihbot.pfm.repository.TransactionRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    private final LabelService labelService;

	public List<Transaction> listTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions;
    }

    public Transaction insertTransaction(Transaction transaction, Long sourceId, Long targetId) {
        if (transaction.getAmount() == null || transaction.getAmount() < 0) {
            throw new InvalidParameterException();
        }

        transaction.setId(null);

        if (sourceId != null) {
            Account source = accountRepository.getById(sourceId);
            source.setBalance(source.getBalance() - transaction.getAmount());
            source = accountRepository.save(source);

            transaction.setSource(source);
        } else {
            transaction.setSource(null);
        }

        if (targetId != null) {
            Account target = accountRepository.getById(targetId);
            target.setBalance(target.getBalance() + transaction.getAmount());
            target = accountRepository.save(target);

            transaction.setTarget(target);
        } else {
            transaction.setSource(null);
        }

        transaction = transactionRepository.save(transaction);

        return transaction;
    }

    public Transaction updateTransactionLabels(Transaction transaction, String mainLabel, List<String> labels) {
        if (transaction.getName() != null
        && !transaction.getName().getName().equals(mainLabel)) {
            Label name = labelService.createLabel(mainLabel);
            transaction.setName(name);
        }

        return transactionRepository.save(transaction);
    }
}
