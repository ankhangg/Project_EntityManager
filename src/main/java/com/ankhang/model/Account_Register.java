package com.ankhang.model;

import com.ankhang.entities.Account;
import com.ankhang.entities.Account_Info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account_Register {
	
	private String userName;
	private String passWord;
	private String passWord2;
	private boolean active;
	private String userRole;
	private String firstName;
	private String lastName;
	private String email;
	private boolean newAccount = false;
}
