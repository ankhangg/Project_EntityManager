package com.ankhang.service;

import org.springframework.stereotype.Component;

import com.ankhang.entities.AccountDeomo;


public interface AccountDemoService {
 
	public AccountDeomo saveAccountDemo(AccountDeomo account);
	
	public AccountDeomo findAccountDemo(int accountId);
}
