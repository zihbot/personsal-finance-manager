package com.zihbot.pfm;

import java.util.Set;

import com.zihbot.pfm.configuration.ApplicationConstants;
import com.zihbot.pfm.dao.Account;
import com.zihbot.pfm.dao.Label;
import com.zihbot.pfm.dao.Transaction;
import com.zihbot.pfm.dao.UserAuth;
import com.zihbot.pfm.repository.AccountRepository;
import com.zihbot.pfm.repository.LabelRepository;
import com.zihbot.pfm.repository.TransactionRepository;
import com.zihbot.pfm.repository.UserAuthRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class PfmApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfmApplication.class, args);
	}

	@Bean
	@Profile(ApplicationConstants.PROFILE_DEV)
	public CommandLineRunner run(
		TransactionRepository transactionRepository,
		AccountRepository accountRepository,
		LabelRepository labelRepository,
		UserAuthRepository userAuthRepository
	) throws Exception {
		return (String[] args) -> {
			UserAuth userAuth = new UserAuth();
			userAuth.setUsername("user");
			userAuth.setPassword("BD2D2EA7F21928FC7FF7C0F1D246B9BE");
			userAuth.setSalt("FA8DA7D7049131A2");
			userAuthRepository.save(userAuth);

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
