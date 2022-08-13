package com.zihbot.pfm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zihbot.pfm.model.AccountDto;
import com.zihbot.pfm.model.CategoryDto;
import com.zihbot.pfm.model.UserDataDto;
import com.zihbot.pfm.service.AccountService;
import com.zihbot.pfm.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/configs")
@RequiredArgsConstructor
public class ConfigController {
	private final AccountService accountService;
	private final CategoryService categoryService;

	@GetMapping("userdata")
	public UserDataDto getUserData() {
		List<AccountDto> accounts = accountService.listAccounts().stream()
                .map(m -> new AccountDto(m))
                .toList();
        List<CategoryDto> categories = categoryService.listCategories().stream()
                .map(m -> new CategoryDto(m))
                .toList();
        
        UserDataDto result = new UserDataDto();
        result.setAccounts(accounts);
        result.setCategories(categories);
        return result;
	}
}
