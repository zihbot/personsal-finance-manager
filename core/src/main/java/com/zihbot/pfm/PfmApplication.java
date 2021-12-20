package com.zihbot.pfm;

import com.zihbot.pfm.model.Account;
import com.zihbot.pfm.model.Transaction;
import com.zihbot.pfm.repository.AccountRepository;
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
    public CommandLineRunner run(TransactionRepository transactionRepository,
			AccountRepository accountRepository) throws Exception {
        return (String[] args) -> {
			Account acc = new Account();
			acc.setBalance(0L);
			acc.setName("Számla");
			accountRepository.save(acc);

			Transaction t1 = new Transaction();
			t1.setType("IN");
			t1.setAmount(1000000L);
			t1.setTarget(acc);
			transactionRepository.save(t1);
        };
    }
}
