package com.ankhang.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ankhang.entities.CategoryProduct;
import com.ankhang.entities.Product;
import com.ankhang.model.Account_Register;
import com.ankhang.model.CategoryProductInput;
import com.ankhang.model.ProductInput;
import com.ankhang.service.CategoryProductService;
import com.ankhang.service.ProductService;
import com.ankhang.validator.ProductValidator;

@Controller
@Transactional
public class AdminController {

	@Autowired
	private CategoryProductService categoryProductService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductValidator productValidator;
	

	
	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		  Object target = dataBinder.getTarget();
	        if (target == null) {
	            return;
	        }
	        System.out.println("Target=" + target);
	        if (target.getClass() == ProductInput.class) {
				dataBinder.setValidator(productValidator);
				// For upload Image.
				//dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
			}
	        }
	
	@GetMapping("/admin")
	public String admin() {
		return "body_adminwebsite";
	}
	
	@RequestMapping(value = "/addcategory", method = RequestMethod.GET)
	public String category(Model model,
    		@RequestParam(value = "error",defaultValue = "")String error,
    		@RequestParam(value = "alert",defaultValue = "") String alert) 
   {
    	model.addAttribute("error",error);
    	model.addAttribute("alert",alert);
    	if ("true".equals(error)) {
    		model.addAttribute("alertmessage","Error: Fields can not be blank");
		}
    	if ("danger".equals(alert)) {
			model.addAttribute("typealert","danger");
		}
    	CategoryProductInput categoryProductInput = new CategoryProductInput();
         model.addAttribute("categoryForm",categoryProductInput);	
         return "add_categoryform";
   }
	
	 @RequestMapping(value = "/addcategory", method = RequestMethod.POST)
	   @Transactional(propagation = Propagation.NEVER)
	   public String createAcountPost(Model model, @ModelAttribute("categoryForm") @Validated  CategoryProductInput categoryProductInput,final RedirectAttributes redirectAttributes, BindingResult result){
		   if (result.hasErrors()) {
			return "body_adminwebsite";
		}
		   if (categoryProductService.saveCategoryProd(categoryProductInput) == true) {
			   System.out.println("Add Category Success");
		} else {
			System.out.println("Add Category Fail");
			return "redirect:/addcategory?error=true&alert=danger";
		}
		   return "redirect:/showcate";
	 }
	 
	 @GetMapping("/showcate")
	 public String showallCategory(Model model) {
		 List<CategoryProduct> CategoryProducts = categoryProductService.findAllCategory();
		 model.addAttribute("listCategoryProducts",CategoryProducts);
		 return "show_categoryform";
	 }
 
	 @GetMapping("/deletecate")
	 public String deleteCategory(Model model, @RequestParam(value = "idcategory", defaultValue = "0") Long id,
	    		@RequestParam(value = "error",defaultValue = "")String error,
	    		@RequestParam(value = "alert",defaultValue = "") String alert) 
	   {
	    	model.addAttribute("error",error);
	    	model.addAttribute("alert",alert);
	    	if ("true".equals(error)) {
	    		model.addAttribute("alertmessage","Error: This value have connect with Product - Pls delete the connect Product first");
			}
	    	if ("danger".equals(alert)) {
				model.addAttribute("typealert","danger");
			}
		 CategoryProduct categoryProduct = categoryProductService.findCategorybyId(id);
		 model.addAttribute("categorydelete",categoryProduct);
		 return "delete_categoryform";
	 }
	 
	 @PostMapping("/deletecate")
	 public String deleteCategoryPost(Model model, @ModelAttribute("categorydelete")@Validated CategoryProduct categoryProduct,final RedirectAttributes redirectAttributes,BindingResult result) {
		   if (result.hasErrors()) {
			return "body_adminwebsite";
		}
		   if (categoryProductService.deleteCategory(categoryProduct) == true) {
			   System.out.println("Delete Success");
		} else {
			System.out.println("Delete Fail");
			return "redirect:/deletecate?error=true&alert=danger";
		}
		   return "redirect:/showcate";
	 }
	 
	 @RequestMapping(value = "/updatecategory", method = RequestMethod.GET)
		public String updatecategory(Model model, @RequestParam(value = "idcategory", defaultValue = "0") Long id,
	    		@RequestParam(value = "error",defaultValue = "")String error,
	    		@RequestParam(value = "alert",defaultValue = "") String alert) 
	   {
	    	model.addAttribute("error",error);
	    	model.addAttribute("alert",alert);
	    	if ("true".equals(error)) {
	    		model.addAttribute("alertmessage","Error: Fields can not be blank");
			}
	    	if ("danger".equals(alert)) {
				model.addAttribute("typealert","danger");
			}
	    	 CategoryProduct categoryProduct = categoryProductService.findCategorybyId(id);
			 model.addAttribute("categoryupdate",categoryProduct);	
	         return "update_categoryform";
	   }
	 
	 @PostMapping("/updatecategory")
	 public String updatecategoryPost(Model model, @ModelAttribute("categoryupdate")@Validated CategoryProduct categoryProduct,final RedirectAttributes redirectAttributes,BindingResult result) {
		   if (result.hasErrors()) {
			return "body_adminwebsite";
		}
           Long cateId = categoryProduct.getCateprodId();
           String modifiedBy = categoryProduct.getInformationControl().getModifiedBy();
		   if (categoryProductService.updateCategoryProduct(categoryProduct,cateId, modifiedBy) == true) {
			   System.out.println("Update Success");
		} else {
			System.out.println("Update Fail");
			return "redirect:/updatecategory?error=true&alert=danger";
		}
		   return "redirect:/showcate";
	 }
	 
	 @RequestMapping(value = "/addproduct", method = RequestMethod.GET)
		public String saveproduct(Model model,
	    		@RequestParam(value = "error",defaultValue = "")String error,
	    		@RequestParam(value = "alert",defaultValue = "") String alert) 
	   {
	    	model.addAttribute("error",error);
	    	model.addAttribute("alert",alert);
	    	if ("true".equals(error)) {
	    		model.addAttribute("alertmessage","Error: Fields can not be blank");
			}
	    	if ("danger".equals(alert)) {
				model.addAttribute("typealert","danger");
			}
	    	 List<CategoryProduct> CategoryProducts = categoryProductService.findAllCategory();
			 model.addAttribute("listCategoryProducts",CategoryProducts);
             ProductInput productInput = new ProductInput();
             model.addAttribute("productinputform",productInput);
	         return "add_productform";
	   }
		
		 @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
//		  @Transactional(propagation = Propagation.NEVER)
		   public String saveproductPost(Model model, @ModelAttribute("productinputform") @Validated  ProductInput productInput,final RedirectAttributes redirectAttributes, BindingResult result){
			   if (result.hasErrors()) {
				return "body_adminwebsite";
			}
			   Long cateId = productInput.getIdCategory();
			   String createdBy = productInput.getCreatedBy();
			   if ( productService.saveProduct(productInput,cateId,createdBy)== true) {
				   System.out.println("Add Product Success");
			} else {
				System.out.println("Add Product Fail");
				return "redirect:/addproduct?error=true&alert=danger";
			}
			   return "redirect:/showcate";
		 }
		 
		 
		 @RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
		 public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
				 @RequestParam("id") Long id) throws IOException {
			 Product product = null;
			 if (id != null) {
				product = productService.findByIdProduct(id);
			}
			 if (product != null && product.getProductThumbnail() != null) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(product.getProductThumbnail());
			}
			 response.getOutputStream().close();
		 }
	    
}
