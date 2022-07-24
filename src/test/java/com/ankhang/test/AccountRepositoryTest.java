package com.ankhang.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ankhang.entities.Account;
import com.ankhang.entities.Account_Info;
import com.ankhang.repository.AccountInfoRepository;
import com.ankhang.repository.AccountRepository;

@SpringBootTest
public class AccountRepositoryTest {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountInfoRepository accountInfoRepository;
	
	@Test
	public void save() {
		Account account = Account.builder()
				.accId(1L)
				.userName("admin")
				.passWord("$2a$10$NLEH/bLG2crqGrxp6UaZ5utkxRe0LiYvDjxtgbjpp6aNdJNslpG26")
				.userRole("ROLE_ADMIN")
				.active(true)
				.build();
		
		Account_Info account_Info = Account_Info.builder()
				.firstName("admin")
				.lastName("admin")
//				.addRess("ADMIN")
//				.phoneNumber("0767143103")
				.email("admin@gmail.com")
				.account(account)
				.build();
		accountInfoRepository.save(account_Info);
	}
	
//	@Test
//	public void findAccountByUsername() {
//		Account account = accountRepository.findByAccId(2L);
//		System.out.println(account);
//	}
	
//	@Test
//	public void findAccountInfo() {
//		Account account = accountRepository.findAccountByUsername("ankhang");
//		String firstName = "Bin";
//		String lastName = "Bin";
//		String address = "Bin";
//		String phoneNumber = "123";
//		String email = "Bin";
//		accountInfoRepository.saveupdateAccountInfo(firstName, lastName, address, phoneNumber, email, account);
//		//System.out.println("Ket qua:" + account_Info);
//	}
	

	

}
