package com.ankhang.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
 
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = "admin";
        System.out.println("1:" + encodedPassword);
        
        String encodedPassword2 = passwordEncoder.encode("admin");
        System.out.println("2:" +encodedPassword2);
        
     if (passwordEncoder.matches(encodedPassword, encodedPassword2)) {
    	 System.out.println("True");
	}else {
		System.out.println("False");	
	}

    }
 
}