package com.ankhang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.ankhang.entities.Account;
import com.ankhang.entities.Account_Info;
import com.ankhang.model.Account_ChangePassword;
import com.ankhang.model.Account_ForgetPassword;
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
				model.addAttribute("message","Username or Password not correct");
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
	    		model.addAttribute("alertmessage","Error: Username available pls try difference Username---Confirm Password not Correct");
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
	   
	   @RequestMapping(value = "/updateif", method = RequestMethod.GET)
	    public String updateInfoAccount(@RequestParam(value = "username",defaultValue = "") String username,Model model,
	    		@RequestParam(value = "error",defaultValue = "")String error,
	    		@RequestParam(value = "alert",defaultValue = "") String alert) 
	   {
	    	model.addAttribute("error",error);
	    	model.addAttribute("alert",alert);
	    	if ("true".equals(error)) {
				model.addAttribute("alertmessage","Error: Please Input All Information");
			}
	    	if ("danger".equals(alert)) {
				model.addAttribute("typealert","danger");
			}
	    	Account thisAccount = accountService.findAccountByUsername(username);
	    	Account_Info thisAccount_Info = null;
	    	try {
	    		thisAccount_Info = accountService.findAccountInfoByAccount(thisAccount);
	    		model.addAttribute("accountInfoForm", thisAccount_Info);
	    		model.addAttribute("accountForm", thisAccount);
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/updateif";
			}
	         return "inputAccInfo_form";
	   }
	   
	   @RequestMapping(value = "/updateif", method = RequestMethod.POST)
	   @Transactional(propagation = Propagation.NEVER)
	   public String updateInfoAccountPost(Model model, @ModelAttribute("accountInfoForm") @Validated Account_Info account_Info, final RedirectAttributes redirectAttributes, BindingResult result){
		   Long accId = account_Info.getAccinfoId();
		   if (result.hasErrors()) {
			return "/home";
		}
		   if (accountService.saveupdateAccountInfo(account_Info,accId) == true) {
			   System.out.println("Update Info Success");
		} else {
			System.out.println("Update Info Fail");
			return "redirect:/updateif?error=true&alert=danger";
		}
		   return "redirect:/home";
	   }
	   
	   @GetMapping("/updatepassword")
	   public String changePassword(Model model,
	    		@RequestParam(value = "error",defaultValue = "")String error,
	    		@RequestParam(value = "alert",defaultValue = "") String alert) 
	   {
	    	model.addAttribute("error",error);
	    	model.addAttribute("alert",alert);
	    	if ("true".equals(error)) {
	    		model.addAttribute("alertmessage","Error: Old Password Not Correct / Confirm Password Not Correct");
			}
	    	if ("danger".equals(alert)) {
				model.addAttribute("typealert","danger");
			}
	    	Account_ChangePassword account_ChangePassword = new Account_ChangePassword();
	         model.addAttribute("accountPassForm",account_ChangePassword);	
	         return "password_form";
	   }
	   

	   @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	   @Transactional(propagation = Propagation.NEVER)
	   public String changePasswordPost(Model model, @ModelAttribute("accountPassForm") @Validated Account_ChangePassword account_ChangePassword ,final RedirectAttributes redirectAttributes, BindingResult result){
		   if (result.hasErrors()) {
			return "/updatepassword";
		}
		   if (accountService.changePassword(account_ChangePassword) == true) {
			   System.out.println("Update Password Success");
		} else {
			System.out.println("Update Password Fail");
			return "redirect:/updatepassword?error=true&alert=danger";
		}
		   return "login";
	   }
	   
	   @GetMapping("/forgetpassword")
	   public String forgetPassword(Model model,
	    		@RequestParam(value = "error",defaultValue = "")String error,
	    		@RequestParam(value = "alert",defaultValue = "") String alert) 
	   {
	    	model.addAttribute("error",error);
	    	model.addAttribute("alert",alert);
	    	if ("true".equals(error)) {
	    		model.addAttribute("alertmessage","Error: Old Email Not Correct / Confirm Password Not Correct");
			}
	    	if ("danger".equals(alert)) {
				model.addAttribute("typealert","danger");
			}
	    	Account_ForgetPassword account_ForgetPassword = new Account_ForgetPassword();
	         model.addAttribute("accountForgetPassForm",account_ForgetPassword);	
	         return "forgetpassword_form";
	   }
	   
	   @RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
	   @Transactional(propagation = Propagation.NEVER)
	   public String forgetPasswordPost(Model model, @ModelAttribute("accountForgetPassForm") @Validated Account_ForgetPassword account_ForgetPassword,final RedirectAttributes redirectAttributes, BindingResult result){
		   if (result.hasErrors()) {
			return "/login";
		}
		   if (accountService.forgetPassword(account_ForgetPassword) == true) {
			   System.out.println("Take back Password Success");
		} else {
			System.out.println("Take back Password Fail");
			return "redirect:/forgetpassword?error=true&alert=danger";
		}
		   return "login";
	   }
	    
}
