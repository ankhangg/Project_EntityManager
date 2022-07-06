package com.ankhang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


import com.ankhang.entities.Account;
import com.ankhang.service.AccountService;

@Controller
public class HomeController {
    
//	@Autowired
//	private AccountService accountService;
	
	@GetMapping({"/home", "/"})
	public String home(Model model) {
        String message = "Hello Spring Boot + JSP";

        model.addAttribute("message", message);
		return "body_homewebsite";
	}
}
