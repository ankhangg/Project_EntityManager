package com.ankhang.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ankhang.entities.Account;
import com.ankhang.repository.AccountRepository;

@SpringBootTest
public class AccountRepository_Test {

	@Autowired
	private AccountRepository accountRepository;
	
	@Test
	public void saveAccount() {
		Account account = Account.builder()
				.userName("ankhang")
				.passWord("an@khang")
				.build();
		accountRepository.save(account);
	}
}
