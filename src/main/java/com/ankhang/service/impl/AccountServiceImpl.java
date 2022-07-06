package com.ankhang.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ankhang.entities.Account;
import com.ankhang.repository.AccountRepository;
import com.ankhang.service.AccountService;

@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
    
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Account saveAccount(Account account) {
		Account acc_response = accountRepository.save(account);
		return acc_response;
	}

	@Transactional
	public Account getAccount(int accountId) {
        Account account = entityManager.find(Account.class, accountId);
		return account;
	}
	
	
}
