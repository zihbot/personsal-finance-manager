package com.zihbot.pfm.service;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zihbot.pfm.dao.Account;
import com.zihbot.pfm.dao.Label;
import com.zihbot.pfm.dao.Transaction;
import com.zihbot.pfm.repository.AccountRepository;
import com.zihbot.pfm.repository.TransactionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UserService user;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    private final LabelService labelService;

	public List<Transaction> listTransactions() {
        return transactionRepository.findAllByUser(user.username());
    }

    public Transaction insertTransaction(Transaction transaction, Long sourceId, Long targetId) {
        if (transaction.getAmount() == null || transaction.getAmount() < 0) {
            throw new InvalidParameterException();
        }

        transaction.setId(null);
        transaction.setUser(user.username());

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
        if (mainLabel != null) {
            Label name = labelService.createOrGetLabel(mainLabel);
            transaction.setName(name);
        }

        if (labels != null) {
            Set<Label> newLabels = new HashSet<>();
            labels.forEach(label -> newLabels.add(labelService.createOrGetLabel(label)));
            transaction.setLabels(newLabels);
        }

        return transactionRepository.save(transaction);
    }

	public void deleteByUser(String user) {
        logger.info("Delete transactions for user {}", user);

        transactionRepository.deleteAllByUser(user);
    }
}
