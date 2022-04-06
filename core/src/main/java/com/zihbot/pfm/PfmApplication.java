package com.zihbot.pfm;

import java.util.Set;

import com.zihbot.pfm.dao.Account;
import com.zihbot.pfm.dao.Label;
import com.zihbot.pfm.dao.Transaction;
import com.zihbot.pfm.repository.AccountRepository;
import com.zihbot.pfm.repository.LabelRepository;
import com.zihbot.pfm.repository.TransactionRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PfmApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfmApplication.class, args);
	}

	// TODO: Only for testing
	@Bean
	public CommandLineRunner run(
		TransactionRepository transactionRepository,
		AccountRepository accountRepository,
		LabelRepository labelRepository
	) throws Exception {
		return (String[] args) -> {
			Account acc = new Account();
			acc.setBalance(0L);
			acc.setName("Számla");
			accountRepository.save(acc);

			Label lab1 = new Label();
			lab1.setName("Megtakarítás");
			labelRepository.save(lab1);
			Label lab2 = new Label();
			lab2.setName("Értékpapírszámla");
			labelRepository.save(lab2);

			Transaction t1 = new Transaction();
			t1.setType("IN");
			t1.setAmount(1000000L);
			t1.setTarget(acc);
			t1.setName(lab1);
			t1.setLabels(Set.of(lab2));
			transactionRepository.save(t1);
		};
	}
}
