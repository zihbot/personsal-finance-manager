package com.zihbot.pfm;

import com.zihbot.pfm.model.Transaction;
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
    public CommandLineRunner run(TransactionRepository transactionRepository) throws Exception {
        return (String[] args) -> {
			Transaction t1 = new Transaction();
			t1.setType("IN");
			t1.setAmount(1000000L);
			t1.setTarget("Cél");
			transactionRepository.save(t1);
        };
    }
}
