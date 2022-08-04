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
import com.ankhang.service.AccountDemoService;
import com.ankhang.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;
	
	@GetMapping({"/home", "/"})
	public String home(Model model) {
        String message = "Hello Spring Boot + JSP";
        model.addAttribute("message", message);
        List<Product> listProducts = productService.findAllProduct();
        model.addAttribute("listProductForm",listProducts);
		return "body_homewebsite";
	}
	
	@GetMapping("/showproduct")
	public String showproduct(Model model, @RequestParam(value = "idproduct", defaultValue = "0") Long id ) {
		Product product = productService.findByIdProduct(id);
		model.addAttribute("detailProduct",product);
		return "detailproduct_homewebsite";
	}
	
}
