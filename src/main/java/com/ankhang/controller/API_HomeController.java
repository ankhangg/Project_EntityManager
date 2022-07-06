package com.ankhang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ankhang.entities.Account;
import com.ankhang.service.AccountService;



@RestController
public class API_HomeController {

	
	@Autowired
	private AccountService accountService;
	
	@PostMapping(value = "/accountapi")
	@ResponseBody
	public void saveAPI(@RequestBody Account account) {
	  try {
		  accountService.saveAccount(account);
		  System.out.println("Them thanh cong");
	} catch (Exception e) {
		System.out.println("That bai");
	}
	}
}
