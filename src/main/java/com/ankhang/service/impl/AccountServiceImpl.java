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
import com.ankhang.model.Account_ChangePassword;
import com.ankhang.model.Account_ForgetPassword;
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
		    Long i = accountRepository.findmaxId();
		    Long accId = i + 1;
		    Account account = new Account();
		    Account_Info account_Info = new Account_Info();
			if(checkConfirmPassword(account_Register.getPassWord(), account_Register.getPassWord2())==true) {
			account.setAccId(accId);
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
	public boolean saveupdateAccountInfo(Account_Info account_Info,Long accId) {
	  String firstName = account_Info.getFirstName();
	  String lastName = account_Info.getLastName();
	  String address = account_Info.getAddRess();
	  String phoneNumber = account_Info.getPhoneNumber();
	  String email = account_Info.getEmail();
	  try {
		  accountInfoRepository.saveupdateAccountInfo(firstName, lastName, address, phoneNumber, email, accId);
		  System.out.println("Thanh cong");
		  return true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	  return false;
	}

	@Override
	public Account_Info findAccountInfoByAccount(Account account) {
		Account_Info account_Info = accountInfoRepository.findAccountInfoByAccount(account);
		return account_Info;
	}

	@Override
	public boolean changePassword(Account_ChangePassword account_ChangePassword) {
		Account thisAccount = accountRepository.findAccountByUsername(account_ChangePassword.getUserName());
		String oldPassword = thisAccount.getPassWord();
		String oldPasswordConfirm = account_ChangePassword.getOldPassword();
		try {
			if (securityConfigBean.passwordEncoder().matches(oldPasswordConfirm, oldPassword)) {
				if (account_ChangePassword.getPassWord().equals(account_ChangePassword.getPassWord2())) {
					String BCryptpassword = securityConfigBean.passwordEncoder().encode(account_ChangePassword.getPassWord());
				    accountRepository.updatePassword(BCryptpassword, account_ChangePassword.getUserName());
				    return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
        return false;
	}

	@Override
	public boolean forgetPassword(Account_ForgetPassword account_ForgetPassword) {
		Account thisAccount = accountRepository.findAccountByUsername(account_ForgetPassword.getUserName());
		Account_Info thisAccount_Info = accountInfoRepository.findAccountInfoByAccount(thisAccount);
      try {
    	  if (thisAccount_Info.getEmail().equals(account_ForgetPassword.getEmail())) {
  			if (account_ForgetPassword.getPassWord().equals(account_ForgetPassword.getPassWord2())) {
  				String BCryptpassword = securityConfigBean.passwordEncoder().encode(account_ForgetPassword.getPassWord());
  			    accountRepository.updatePassword(BCryptpassword, account_ForgetPassword.getUserName());
  			    return true;
  			}
  		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		return false;
	}


	


}
