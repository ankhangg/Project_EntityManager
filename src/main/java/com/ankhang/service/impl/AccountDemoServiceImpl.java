package com.ankhang.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ankhang.entities.AccountDeomo;
import com.ankhang.repository.AccountDemoRepository;
import com.ankhang.service.AccountDemoService;

@Service("accountDemoServiceImpl")
public class AccountDemoServiceImpl implements AccountDemoService {

	@Autowired
	private AccountDemoRepository accountRepository;
    
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public AccountDeomo saveAccountDemo(AccountDeomo account) {
		AccountDeomo acc_response = accountRepository.save(account);
		return acc_response;
	}

	@Transactional
	public AccountDeomo findAccountDemo(int accountId) {
        AccountDeomo account = entityManager.find(AccountDeomo.class, accountId);
		return account;
	}
	
	
}
