package com.ankhang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.ankhang.entities.AccountDeomo;
import com.ankhang.entities.CategoryProduct;
import com.ankhang.entities.Product;
import com.ankhang.entities.ProductCart;
import com.ankhang.model.Account_Register;
import com.ankhang.model.FormUsername;
import com.ankhang.service.AccountDemoService;
import com.ankhang.service.ProductCartService;
import com.ankhang.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductCartService productCartService;
	

	
	@GetMapping("/home")
	public String home(Model model,@RequestParam(value = "error",defaultValue = "")String error,
    		@RequestParam(value = "alert",defaultValue = "") String alert)  {
		    model.addAttribute("error",error);
	    	model.addAttribute("alert",alert);
	    	if ("true".equals(error)) {
	    		model.addAttribute("alertmessage","Fail: Not Enough Amount To Add More Card");
			}
	    	if ("false".equals(error)) {
	    		model.addAttribute("alertmessage","Success: Add product Success");
			}
	    	if ("successlogin".equals(error)) {
	    		model.addAttribute("alertmessage","Success: Login Success");
			}
	    	if ("falseudcart".equals(error)) {
	    		model.addAttribute("alertmessage","Success: Update Cart Success");
			}
	    	if ("success".equals(alert)) {
				model.addAttribute("typealert","success");
			}
	    	
	    	if ("successudinfo".equals(error)) {
	    		model.addAttribute("alertmessage","Success: Update Info");
			}
	    	if ("primary".equals(alert)) {
				model.addAttribute("typealert","primary");
			}
	    	if ("falsenumber".equals(error)) {
	    		model.addAttribute("alertmessage","Fail: Not enough amount to add cart");
			}
	    	if ("danger".equals(alert)) {
				model.addAttribute("typealert","danger");
			}
        String message = "Hello Spring Boot + JSP";
        model.addAttribute("message", message);
        List<Product> listProducts = productService.findAllProduct();
        model.addAttribute("listProductForm",listProducts);
        
        
		FormUsername formUsername = new FormUsername();
		model.addAttribute("formusername", formUsername);
		return "body_homewebsite";
	}
	
}
