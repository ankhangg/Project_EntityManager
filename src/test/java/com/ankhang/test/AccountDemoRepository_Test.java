package com.ankhang.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ankhang.entities.AccountDeomo;
import com.ankhang.repository.AccountDemoRepository;

@SpringBootTest
public class AccountDemoRepository_Test {

	@Autowired
	private AccountDemoRepository accountRepository;
	
	@Test
	public void saveAccount() {
		AccountDeomo account = AccountDeomo.builder()
				.userName("ankhang")
				.passWord("an@khang")
				.build();
		accountRepository.save(account);
	}
}
