package com.ankhang.service;

import org.springframework.stereotype.Component;

import com.ankhang.entities.Account;
import com.ankhang.model.AccountIF_Form;
import com.ankhang.model.Account_Register;

@Component
public interface AccountService {

	 Account findAccountByUsername(String userName);
	 boolean saveAccount_Regist(Account_Register account_Register);
	 boolean checkConfirmPassword(String passWord, String passWord2);
	 boolean saveupdateAccountInfo(AccountIF_Form accountIF_Register);
}
