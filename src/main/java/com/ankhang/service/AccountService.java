package com.ankhang.service;

import org.springframework.stereotype.Component;

import com.ankhang.entities.Account;
import com.ankhang.entities.Account_Info;
import com.ankhang.model.Account_ChangePassword;
import com.ankhang.model.Account_ForgetPassword;
import com.ankhang.model.Account_Register;

@Component
public interface AccountService {

	 Account findAccountByUsername(String userName);
	 Account_Info findAccountInfoByAccount(Account account);
	 boolean saveAccount_Regist(Account_Register account_Register);
	 boolean checkConfirmPassword(String passWord, String passWord2);
	 boolean saveupdateAccountInfo(Account_Info account_Info , Long accId);
	 boolean changePassword(Account_ChangePassword account_ChangePassword );
	 boolean forgetPassword(Account_ForgetPassword account_ForgetPassword);
}
