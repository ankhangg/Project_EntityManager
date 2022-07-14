package com.ankhang.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ankhang.config.SecurityConfigBean;
import com.ankhang.entities.Account;
import com.ankhang.entities.Account_Info;
import com.ankhang.model.AccountIF_Form;
import com.ankhang.model.Account_Register;
import com.ankhang.repository.AccountInfoRepository;
import com.ankhang.repository.AccountRepository;
import com.ankhang.service.AccountService;
@Component
@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountInfoRepository accountInfoRepository;
	
	@Autowired
	private SecurityConfigBean securityConfigBean;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Account findAccountByUsername(String userName) {
		Account account = entityManager.find(Account.class, userName);
		return account;
	}

	@Override
	public boolean checkConfirmPassword(String passWord, String passWord2) {
		if (passWord.equals(passWord2)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean saveAccount_Regist(Account_Register account_Register) {
		    Account account = new Account();
		    Account_Info account_Info = new Account_Info();
			if(checkConfirmPassword(account_Register.getPassWord(), account_Register.getPassWord2())==true) {
			account.setUserName(account_Register.getUserName());
			// translate password to BCrypt
			String BCryptpassword = securityConfigBean.passwordEncoder().encode(account_Register.getPassWord());
			account.setPassWord(BCryptpassword);	
			account.setUserRole(account_Register.getUserRole());	
			account.setActive(account_Register.isActive());	
			
			account_Info.setFirstName(account_Register.getFirstName());
			account_Info.setLastName(account_Register.getLastName());
			account_Info.setEmail(account_Register.getEmail());
			account_Info.setAccount(account);
			try {
				this.accountInfoRepository.save(account_Info);
				return  true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			}  
		return false;
		}

	@Override
	public boolean saveupdateAccountInfo(AccountIF_Form accountIF_Register) {
		try {
			this.accountInfoRepository.saveupdateAccountInfo(accountIF_Register.getFirstName(), 
					accountIF_Register.getLastName(), accountIF_Register.getAddRess(), 
					accountIF_Register.getPhoneNumber(), accountIF_Register.getEmail(), accountIF_Register.getUserName());
			        return  true;
		} catch (Exception e) {
		  e.printStackTrace();
		}
		return false;
	}	
	


}
