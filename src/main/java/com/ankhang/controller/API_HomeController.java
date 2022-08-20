package com.ankhang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ankhang.entities.AccountDeomo;
import com.ankhang.service.AccountDemoService;



@RestController
public class API_HomeController {

	
	@Autowired
	private AccountDemoService accountService;
	
	@PostMapping(value = "/accountapi")
	@ResponseBody
	public void saveAPI(@RequestBody AccountDeomo account) {
	  try {
		  accountService.saveAccountDemo(account);
		  System.out.println("Them thanh cong"); //hoho03
		  System.out.println("Thông tin account: "+account); //hoho
	} catch (Exception e) {
		System.out.println("That bai"); 
	}
	}
}
