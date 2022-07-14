package com.ankhang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.ankhang.model.Account_Register;
import com.ankhang.service.AccountService;

@Controller
public class LoginController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, 
    		@RequestParam(value = "error",defaultValue = "") String error,
    		@RequestParam(value = "alert",defaultValue="") String alert) {
            model.addAttribute("error",error);
            
            
            model.addAttribute("alert",alert);
            if ("true".equals(error)) {
				model.addAttribute("message","Tài khoản hoặc mật khẩu không chính xác");
			}
            if ("danger".equals(alert)) {
            	model.addAttribute("getAlert","danger");
			}
    	return "login";
    }
	
	   @RequestMapping("403")
	    public String accessDenied(){
	        return "/403";
	        
	        
	    }
	   
	   @RequestMapping(value = "/regist", method = RequestMethod.GET)
	    public String createAcount(Model model,
	    		@RequestParam(value = "error",defaultValue = "")String error,
	    		@RequestParam(value = "alert",defaultValue = "") String alert) 
	   {
	    	model.addAttribute("error",error);
	    	model.addAttribute("alert",alert);
	    	if ("true".equals(error)) {
				model.addAttribute("alertmessage","Lỗi: Tên đăng nhập đã tồn tại---Mật khẩu không chính xác");
			}
	    	if ("danger".equals(alert)) {
				model.addAttribute("typealert","danger");
			}
	    	Account_Register account_Register = new Account_Register();
	         model.addAttribute("accountRegistForm",account_Register);	
	         return "regist_form";
	   }
	   
	   @RequestMapping(value = "/regist", method = RequestMethod.POST)
	   @Transactional(propagation = Propagation.NEVER)
	   public String createAcountPost(Model model, @ModelAttribute("accountRegistForm") @Validated Account_Register account_Register,final RedirectAttributes redirectAttributes, BindingResult result){
		   if (result.hasErrors()) {
			return "/login";
		}
		   if (accountService.saveAccount_Regist(account_Register) == true) {
			   System.out.println("Regist Success");
		} else {
			System.out.println("Regist Fail");
			return "redirect:/regist?error=true&alert=danger";
		}
		   return "login";
	   }

	    
}
