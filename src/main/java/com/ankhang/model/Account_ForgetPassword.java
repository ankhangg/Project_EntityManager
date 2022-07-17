package com.ankhang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account_ForgetPassword {
	   String userName;
	   String email;
	   String passWord;
	   String passWord2;
}
