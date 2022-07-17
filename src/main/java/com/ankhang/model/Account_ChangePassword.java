package com.ankhang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account_ChangePassword {
   String userName;
   String oldPassword;
   String passWord;
   String passWord2;
}
