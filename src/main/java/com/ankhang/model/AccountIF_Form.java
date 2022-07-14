package com.ankhang.model;

import com.ankhang.entities.Account;
import com.ankhang.entities.Account_Info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountIF_Form {
	private String firstName;
	private String lastName;
	private String addRess;
	private String email;
	private String phoneNumber;
	private String userName;
	
}
