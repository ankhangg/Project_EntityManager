package com.ankhang.service;

import org.springframework.stereotype.Component;

import com.ankhang.entities.Account;

@Component
public interface AccountService {
 
	public Account saveAccount(Account account);
	
	public Account getAccount(int accountId);
}
