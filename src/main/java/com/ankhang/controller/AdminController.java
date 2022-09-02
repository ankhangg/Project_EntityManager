package com.ankhang.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
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
import com.ankhang.model.ProductCart_Model;
import com.ankhang.model.ProductInput;
import com.ankhang.service.CategoryProductService;
import com.ankhang.service.ProductService;
import com.ankhang.validator.CategoryValidator;
import com.ankhang.validator.ProductCartValidator;
import com.ankhang.validator.ProductValidator;

@Transactional
@Controller
public class AdminController {

	@Autowired
	private CategoryProductService categoryProductService;
	
	@Autowired
	private ProductService productService;
	

	@Autowired
	private ProductValidator productValidator;
	
	@Autowired
	private CategoryValidator categoryValidator;
	
	
	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		  Object target = dataBinder.getTarget();
	        if (target == null) {
	            return;
	        }
	        System.out.println("Target=" + target);
	        if (target.getClass() == ProductInput.class) {
				dataBinder.setValidator(productValidator);
			}
	        if (target.getClass() == CategoryProductInput.class) {
				dataBinder.setValidator(categoryValidator);
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
    		model.addAttribute("alertmessage","Error: Category Code Value Already Exists");
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
	   public String createAcountPost(Model model, @ModelAttribute("categoryForm") @Validated  CategoryProductInput categoryProductInput,BindingResult result,final RedirectAttributes redirectAttributes){
		   if (result.hasErrors()) {
			   return "add_categoryform";
		}
		   if (categoryProductService.saveCategoryProd(categoryProductInput) == true) {
			   System.out.println("Add Category Success");
		} else {
			System.out.println("Add Category Fail");
			return "redirect:/addcategory?error=true&alert=danger";
		}
		   return "redirect:/showcate?error=scaddcate&alert=success";
	 }
	 
	 @GetMapping("/showcate")
	 public String showallCategory(Model model,    		
			    @RequestParam(value = "error",defaultValue = "")String error,
	    		@RequestParam(value = "alert",defaultValue = "") String alert) {
	    	if ("scaddcate".equals(error)) {
	    		model.addAttribute("alertmessage","Success: Add Category Success");
			}
	    	if ("success".equals(alert)) {
				model.addAttribute("typealert","success");
			}
	    	
	    	if ("scdeletecate".equals(error)) {
	    		model.addAttribute("alertmessage","Success: Delete Category Success");
			}
	    	if ("dark".equals(alert)) {
				model.addAttribute("typealert","dark");
			}
	    	
	    	if ("scupdatecate".equals(error)) {
	    		model.addAttribute("alertmessage","Success: Update Category Success");
			}
	    	if ("primary".equals(alert)) {
				model.addAttribute("typealert","primary");
			}
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
	    	if ("true".equals(error) ) {
	    		model.addAttribute("alertmessage","Error: This value have connect with Product - Pls delete the connect Product first");
			}
	    	if ("danger".equals(alert)) {
				model.addAttribute("typealert","danger");
			}
		 CategoryProduct categoryProduct = categoryProductService.findCategorybyId(id);
    	 
		 CategoryProductInput categoryProductInput = new CategoryProductInput();
    	 categoryProductInput.setCateprodCode(categoryProduct.getCateprodCode());
    	 categoryProductInput.setCateprodName(categoryProduct.getCateprodName());
    	 categoryProductInput.setCateprodId(id);
    	 Long cateId = id;

		 model.addAttribute("categorydelete", categoryProductInput);
		 model.addAttribute("cateId", cateId);
		 return "delete_categoryform";
	 }
	 
	 @PostMapping("/deletecate")
	 @Transactional(propagation = Propagation.NEVER)
	 public String deleteCategoryPost(Model model, @ModelAttribute("categorydelete")@Validated CategoryProduct categoryProduct,BindingResult result,final RedirectAttributes redirectAttributes) {
		   if (result.hasErrors()) {
			   return "redirect:/deletecate?error=true&alert=danger";
		}
		   Long cateIdTest = categoryProduct.getCateprodId();
		   if (categoryProductService.deleteCategory(categoryProduct,cateIdTest) == true) {
			   System.out.println("Delete Success");
		} else {
			System.out.println("Delete Fail");
			return "redirect:/deletecate?error=true&alert=danger";
		}
		   return "redirect:/showcate?error=scdeletecate&alert=dark";
	 }
	 
	 @RequestMapping(value = "/updatecategory", method = RequestMethod.GET)
		public String updatecategory(Model model, @RequestParam(value = "idcategory", defaultValue = "0") Long id,
	    		@RequestParam(value = "error",defaultValue = "")String error,
	    		@RequestParam(value = "alert",defaultValue = "") String alert) 
	   {
	    	model.addAttribute("error",error);
	    	model.addAttribute("alert",alert);
	    	if ("true".equals(error)) {
	    		model.addAttribute("alertmessage","Error: Category Code Value Already Exists");
			}
	    	if ("danger".equals(alert)) {
				model.addAttribute("typealert","danger");
			}
	    	 CategoryProduct categoryProduct = categoryProductService.findCategorybyId(id);
	    	 
	    	 CategoryProductInput categoryProductInput = new CategoryProductInput();
	    	 categoryProductInput.setCateprodCode(categoryProduct.getCateprodCode());
	    	 categoryProductInput.setCateprodName(categoryProduct.getCateprodName());
	    	 categoryProductInput.setCateprodId(id);

			 model.addAttribute("categoryupdate", categoryProductInput);
	         return "update_categoryform";
	   }
	 
	 @RequestMapping(value = "/updatecategory", method = RequestMethod.POST)
	 @Transactional(propagation = Propagation.NEVER)
	 public String updatecategoryPost(Model model, @ModelAttribute("categoryupdate")@Validated CategoryProductInput categoryProductInput,BindingResult result,final RedirectAttributes redirectAttributes) {
		   if (result.hasErrors()) {
			return "update_categoryform";
		}
		   if (categoryProductService.updateCategoryProduct(categoryProductInput) == true) {
			   System.out.println("Update Success");
		} else {
			System.out.println("Update Fail");
		}
		   return "redirect:/showcate?error=scupdatecate&alert=primary";
	 }
	 
	 @RequestMapping(value = "/addproduct", method = RequestMethod.GET)
		public String saveproduct(Model model,
	    		@RequestParam(value = "error",defaultValue = "")String error,
	    		@RequestParam(value = "alert",defaultValue = "") String alert) 
	   {
	    	model.addAttribute("error",error);
	    	model.addAttribute("alert",alert);
	    	if ("true".equals(error)) {
	    		model.addAttribute("alertmessage","Error: Product Name Value Already Exists");
			}
	    	if ("danger".equals(alert)) {
				model.addAttribute("typealert","danger");
			}
	    	 List<CategoryProduct> CategoryProducts = categoryProductService.findAllCategory();
			 model.addAttribute("listCategoryProducts",CategoryProducts);
             ProductInput productInput = new ProductInput();
             model.addAttribute("productinputform",productInput);
             
             Long value= 0L;
             BigDecimal proudPrice = BigDecimal.valueOf(value);
             model.addAttribute("proudPrice",proudPrice);
             
             Long prodAmount = 0L;
             model.addAttribute("prodAmount",prodAmount);
             
	         return "add_productform";
	   }
		
		 @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
		   public String saveproductPost(Model model, @ModelAttribute("productinputform") @Validated  ProductInput productInput,BindingResult result,final RedirectAttributes redirectAttributes){
			   if (result.hasErrors()) {
			    	 List<CategoryProduct> CategoryProducts = categoryProductService.findAllCategory();
					 model.addAttribute("listCategoryProducts",CategoryProducts);
				   return "add_productform";
			}
			   if ( productService.saveProduct(productInput)== true) {
				   System.out.println("Add Product Success");
			} else {
				System.out.println("Add Product Fail");
				return "redirect:/addproduct?error=true&alert=danger";
			}
			   return "redirect:/showproductadmin?error=scaddpro&alert=success";
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
		 
		 @GetMapping("/showproductadmin")
		 public String showlistProduct(Model model,
				 @RequestParam(value = "error",defaultValue = "")String error,
		    		@RequestParam(value = "alert",defaultValue = "") String alert){
		    	if ("scaddpro".equals(error)) {
		    		model.addAttribute("alertmessage","Success: Add Product Success");
				}
		    	if ("success".equals(alert)) {
					model.addAttribute("typealert","success");
				}
		    	
		    	if ("scdeletepro".equals(error)) {
		    		model.addAttribute("alertmessage","Success: Delete Product Success");
				}
		    	if ("dark".equals(alert)) {
					model.addAttribute("typealert","dark");
				}
		    	
		    	if ("scupdatecate".equals(error)) {
		    		model.addAttribute("alertmessage","Success: Update Product Success");
				}
		    	if ("primary".equals(alert)) {
					model.addAttribute("typealert","primary");
				}
			 List<Product> listProducts = productService.findAllProductWithCate();
		        model.addAttribute("listProductForm",listProducts);
		        return "show_productform";
		 }
		 
		 @GetMapping("/deleteproduct")
		 public String deleteCProduct(Model model, @RequestParam(value = "idproduct", defaultValue = "0") Long id,
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
                 Product product = productService.findByIdProduct(id);
                 
                 ProductInput productInput = new ProductInput();
                 productInput.setProductId(id);
                 productInput.setProductName(product.getProductName());
                 productInput.setProductPrice(product.getProductPrice());
                 productInput.setProductThumbnail(product.getProductThumbnail());
                 
                 model.addAttribute("productId",id);
                 model.addAttribute("productdelete",productInput);
			 return "delete_productform";
		 }
		 
		 @PostMapping("/deleteproduct")
		 public String deleteCProductPost(Model model, @ModelAttribute("productdelete")@Validated Product product,final RedirectAttributes redirectAttributes,BindingResult result) {
			   if (result.hasErrors()) {
				return "redirect:/deleteproduct?error=true&alert=danger";
			}
			   if (productService.deleteProduct(product) == true) {
				   System.out.println("Delete Success");
			} else {
				System.out.println("Delete Fail");
				return "redirect:/deleteproduct?error=true&alert=danger";
			}
			   return "redirect:/showproductadmin?error=scdeletepro&alert=dark";
		 }
	    
		 @GetMapping("/updateproduct")
		 public String updateProduct(Model model,@RequestParam(value = "idproduct", defaultValue = "0") Long id,
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
		    	Product product = productService.findByIdProductWithCate(id);
		    	ProductInput productInput = new ProductInput();
		    	productInput.setProductId(id);
		    	productInput.setProductName(product.getProductName());
		    	productInput.setProductDescription(product.getProductDescription());
		    	productInput.setProductPrice(product.getProductPrice());
		    	productInput.setIdCategory(product.getCategoryProduct().getCateprodId());
		    	productInput.setProductAmount(product.getProductAmount());
		    	productInput.setProductThumbnail(product.getProductThumbnail());

		    	CategoryProduct categoryProduct = categoryProductService.findCategorybyId((product.getCategoryProduct().getCateprodId()));
		    	String nameCate= categoryProduct.getCateprodName();
		    	
		    	model.addAttribute("idCate",product.getCategoryProduct().getCateprodId());
		    	model.addAttribute("nameCate",nameCate);
		    	
		    	 List<CategoryProduct> CategoryProducts = categoryProductService.findAllCategory();
				 model.addAttribute("listCategoryProducts",CategoryProducts);

				 model.addAttribute("productupdate", productInput);
			 return "update_productform";
		 }
		 
		 @PostMapping("/updateproduct")
		 @Transactional(propagation = Propagation.NEVER)
		 public String updateProductPost(Model model, @ModelAttribute("productupdate")@Validated ProductInput productInput,BindingResult result,final RedirectAttributes redirectAttributes) {
			   if (result.hasErrors()) {
				   CategoryProduct categoryProduct = categoryProductService.findCategorybyId(productInput.getIdCategory());
			    	String nameCate= categoryProduct.getCateprodName();
			    	model.addAttribute("idCate",productInput.getIdCategory());
			    	model.addAttribute("nameCate",nameCate);
			    	
			    List<CategoryProduct> CategoryProducts = categoryProductService.findAllCategory();
			    model.addAttribute("listCategoryProducts",CategoryProducts);
				return "update_productform";
			}
			   if (productService.updateProduct(productInput) == true) {
				   System.out.println("Update Success");
			} else {
				System.out.println("Update Fail");
			}
			   return "redirect:/showproductadmin?error=scupdatecate&alert=primary";
		 }
}
