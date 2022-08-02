package com.zihbot.pfm;

import java.util.Set;

import javax.persistence.EntityManager;

import com.zihbot.pfm.configuration.ApplicationConstants;
import com.zihbot.pfm.dao.Account;
import com.zihbot.pfm.dao.Category;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
		UserAuthRepository userAuthRepository,
		EntityManager em,
		PasswordEncoder passwordEncoder
	) throws Exception {
		return (String[] args) -> {
			UserAuth userAuth = new UserAuth();
			userAuth.setUsername("user");
			userAuth.setPassword(passwordEncoder.encode("user" + "FA8DA7D7049131A2"));
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

			Category cat = new Category();
			cat.setColor("#348923");
			cat.setIcon("arrow");
			cat.setId(1L);
			cat.setName("Kategória");
			em.persist(cat);

			Transaction t1 = new Transaction();
			t1.setCategory(cat);
			t1.setAmount(1000000L);
			t1.setTarget(acc);
			t1.setName(lab1);
			t1.setLabels(Set.of(lab2));
			transactionRepository.save(t1);
		};
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
