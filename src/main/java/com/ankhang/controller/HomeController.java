package com.ankhang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.ankhang.entities.Account;
import com.ankhang.service.AccountService;

@Controller
public class HomeController {
    
	@Autowired
	private AccountService accountService;
	
	@GetMapping({"/home", "/"})
	public String homew(Model model) {
        String message = "Hello Spring Boot + JSP";

        model.addAttribute("message", message);
		return "body_homewebsite";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "body_adminwebsite";
	}
	
	@GetMapping("/account")
	public String account(@ModelAttribute("accountForm")Account account) {
		return "account_demo";
	}
	
	@PostMapping("/account")
	public String accountPost(Model model, @ModelAttribute("accountForm") @Validated Account account, final RedirectAttributes redirectAttributes, BindingResult result) {
		if (result.hasErrors()) {
			return "body_homewebsite";
		}
		try {
			accountService.saveAccount(account);
			System.out.println("Them thanh cong");
		} catch (Exception e) {
			System.out.println("That bai");
		}
		return "redirect:/home";
	}
	
	
}
