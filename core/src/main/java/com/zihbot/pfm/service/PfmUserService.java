package com.zihbot.pfm.service;

import org.springframework.stereotype.Service;

import com.zihbot.pfm.dao.PfmUser;
import com.zihbot.pfm.repository.PfmUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PfmUserService {
    private final PfmUserRepository pfmUserRepository;
    private final AccountService accountService;
    private final CategoryService categoryService;
    private final LabelService labelService;
    private final TransactionService transactionService;

	public PfmUser createUser(String username) {
        PfmUser user = new PfmUser();
        user.setUsername(username);
        return pfmUserRepository.save(user);
    }

	public void deleteUser(String username) {
        labelService.deleteByUser(username);
        transactionService.deleteByUser(username);
        accountService.deleteByUser(username);
        categoryService.deleteByUser(username);
        pfmUserRepository.deleteByUsername(username);
    }
}
