package com.ankhang.controller;

import java.lang.StackWalker.Option;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.ankhang.entities.Account;
import com.ankhang.entities.Account_Info;
import com.ankhang.entities.CategoryProduct;
import com.ankhang.entities.Product;
import com.ankhang.entities.ProductCart;
import com.ankhang.entities.Receipt;
import com.ankhang.model.CategoryProductInput;
import com.ankhang.model.FormUsername;
import com.ankhang.model.ProductCart_Model;
import com.ankhang.model.ProductInput;
import com.ankhang.model.ReceiptCartDetailModel;
import com.ankhang.repository.AccountInfoRepository;
import com.ankhang.service.AccountService;
import com.ankhang.service.CategoryProductService;
import com.ankhang.service.ProductCartService;
import com.ankhang.service.ProductService;
import com.ankhang.service.ReceiptService;
import com.ankhang.validator.ProductCartValidator;

@Transactional
@Controller
public class ShoppingController {
	@Autowired
	private CategoryProductService categoryProductService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductCartService productCartService;
	
	@Autowired
	private ProductCartValidator productCartValidator;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ReceiptService receiptService;
	
	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		  Object target = dataBinder.getTarget();
	        if (target == null) {
	            return;
	        }
	        System.out.println("Target=" + target);
	        if (target.getClass() == ProductCart_Model.class) {
				dataBinder.setValidator(productCartValidator);
			}
	        }
	@GetMapping("/addcart")
	public String productCartDetail(Model model,@RequestParam(value = "idproduct", defaultValue = "0") Long id,
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
	    	ProductCart_Model productCart_Model = new ProductCart_Model();
	    	productCart_Model.setProductCartId(id);
	    	productCart_Model.setProductCartName(product.getProductName());
	    	productCart_Model.setProductCartPrice(product.getProductPrice());
	    	productCart_Model.setProductCartAmount(product.getProductAmount());
	    	productCart_Model.setProductCartDescription(product.getProductDescription());
	    	productCart_Model.setProductId(id);
	    	
	    	CategoryProduct categoryProduct = categoryProductService.findCategorybyId((product.getCategoryProduct().getCateprodId()));
	    	String nameCate= categoryProduct.getCateprodName();
	    	
	    	model.addAttribute("nameCate",nameCate);
	    	model.addAttribute("productcart", productCart_Model);
	    	
	    	FormUsername formUsername = new FormUsername();
			model.addAttribute("formusername", formUsername);
	    	return "detailproduct_homewebsite";
    }
		
	@PostMapping("/addcart")
	@Transactional(propagation = Propagation.NEVER)
	public String addCart(Model model,@ModelAttribute("productcart")@Validated ProductCart_Model productCart_Model,BindingResult result,final RedirectAttributes redirectAttributes) {
		if (productCart_Model.getUserNameCart().length()==0) {
       	 return "redirect:/login?error=errorcart&alert=danger";	
	        }
		if (result.hasErrors()) {
        	
	    	FormUsername formUsername = new FormUsername();
			model.addAttribute("formusername", formUsername);
			
		    Product product = productService.findByIdProduct(productCart_Model.getProductId());
		    CategoryProduct categoryProduct = categoryProductService.findCategorybyId(product.getCategoryProduct().getCateprodId());
		    String nameCate = categoryProduct.getCateprodName();
		    model.addAttribute("nameCate",nameCate);
		    
        	return "detailproduct_homewebsite";
		}
		boolean resultaddcart = productCartService.saveProductCart(productCart_Model);
		if (resultaddcart == true) {
			System.out.println("Add ProductCart Success");
			return "redirect:/home?error=false&alert=success";
		}
          return "redirect:/home?error=true&alert=danger";		
	}
		
	@GetMapping("/showcart")
	public String homePost(Model model,@ModelAttribute("formusername") FormUsername formUsername,
    		@RequestParam(value = "error",defaultValue = "")String error,
    		@RequestParam(value = "alert",defaultValue = "") String alert){
		
		if ("editcardsc".equals(error)) {
    		model.addAttribute("alertmessage","Success: Edit Cart Success");
		}
    	if ("success".equals(alert)) {
			model.addAttribute("typealert","success");
		}
    	
		if ("deletecardsc".equals(error)) {
    		model.addAttribute("alertmessage","Success: Delete Cart");
		}
    	if ("danger".equals(alert)) {
			model.addAttribute("typealert","danger");
		}
    	
		List<ProductCart> listProductCart = productCartService.FindAllCartByUsername(formUsername.getUserName());
		model.addAttribute("listcartuser",listProductCart);
		
		BigDecimal sumCart = productCartService.findSumCartUser(formUsername.getUserName());
		model.addAttribute("sumcart", sumCart);
		
		ProductCart_Model productCart_Model = new ProductCart_Model();
		model.addAttribute("procartsub",productCart_Model);
		return "cartuser";
	}
	
	@GetMapping("/editcart")
	public String editCart(Model model, @RequestParam(value = "idcart",defaultValue = "") Long id,
			@RequestParam(value = "error",defaultValue = "")String error,
    		@RequestParam(value = "alert",defaultValue = "") String alert){

		if ("true".equals(error)) {
    		model.addAttribute("alertmessage","Error: Not Enough Amount To Add Cart");
		}
    	if ("danger".equals(alert)) {
			model.addAttribute("typealert","danger");
		}
		
		ProductCart productCart = productCartService.findProductCart(id);
		
		ProductCart_Model productCart_Model = new ProductCart_Model();
		productCart_Model.setProductCartId(id);
		productCart_Model.setProductCartName(productCart.getProductCartName());
		productCart_Model.setProductCartPrice(productCart.getProductCartPrice());
		productCart_Model.setProductCartNumber(productCart.getProductCartNumber());
		productCart_Model.setProductCartSum(productCart.getProductCartSum());
		productCart_Model.setProductId(productCart.getProductId());
		
		Product product = productService.findByIdProduct(productCart.getProductId());
		productCart_Model.setProductCartAmount(product.getProductAmount());
		
		
		model.addAttribute("prodmodel",productCart_Model);
		
		
		FormUsername formUsername = new FormUsername();
		model.addAttribute("formusername", formUsername);
		return "update_cart";
	}
	
	/*------------------Option 1------------------*/
//	@PostMapping("/editcart")
//	@Transactional(propagation = Propagation.NEVER)
//	public RedirectView editCartPost(Model model, @ModelAttribute("prodmodel") ProductCart_Model productCart_Model,final RedirectAttributes redirectAttributes){
//        if (productCartService.updateProductCart(productCart_Model)==true) {
//			System.out.println("Update Success");
//		}
//        String userName = productCart_Model.getUserNameCart();
//        redirectAttributes.addFlashAttribute("flashAttribute", userName);
//        redirectAttributes.addAttribute("userName",userName);
//		return new RedirectView("/showcart");
//	}
	
	/*------------------Option 2------------------*/
	@PostMapping("/editcart")
	@Transactional(propagation = Propagation.NEVER)
	public ModelAndView  editCartPost(Model model, @ModelAttribute("prodmodel") ProductCart_Model productCart_Model,ModelMap modelMap){  
		boolean result = productCartService.updateProductCart(productCart_Model);
        if (result==true) {
            String userNameCart = productCart_Model.getUserNameCart();
            modelMap.addAttribute("userName",userNameCart);
            modelMap.addAttribute("error","editcardsc");
            modelMap.addAttribute("alert","success");
            return new ModelAndView("redirect:/showcart",modelMap);
		}
            model.addAttribute("idcart",productCart_Model.getProductCartId());
	        modelMap.addAttribute("error","true");
	        modelMap.addAttribute("alert","danger");
	        return new ModelAndView("redirect:/editcart",modelMap);

	}
	

	
	@GetMapping("/deletecart")
	public ModelAndView deleteCartPost(@RequestParam(value = "idcart",defaultValue = "") Long id,
			ModelMap modelMap){
		 ProductCart productCart = productCartService.findProductCart(id);
		 String userNameCart = productCart.getUserNameCart();
		 productCartService.deleteProductCart(productCart);
		 
		 
         modelMap.addAttribute("userName",userNameCart);
         modelMap.addAttribute("error","deletecardsc");
         modelMap.addAttribute("alert","danger");
         return new ModelAndView("redirect:/showcart",modelMap);
	}
	
	//@ModelAttribute("fullNameUser")
	@GetMapping("/showcartselect")
	public String submitCartpost(@RequestParam(value = "selectcart") Long[] selectcart,
			@RequestParam(value = "username") String userName, Model model) {
		FormUsername formUsername = new FormUsername();
		model.addAttribute("formusername", formUsername);
		
		BigDecimal sumresult = new BigDecimal("0") ;
		List<ProductCart> listCartofSelect = new ArrayList<ProductCart>();
		for(Long valueL: selectcart) {
			ProductCart productCart = productCartService.findProductCartUserSelect(userName, valueL);
			BigDecimal sumCart = productCart.getProductCartSum();
			listCartofSelect.add(productCart);
			sumresult = sumresult.add(sumCart);
		}
		
		Account account = accountService.findAccountByUsername(userName);
		String fullNameUser = account.getAccount_Info().getLastName() + account.getAccount_Info().getFirstName();
		
		
		ReceiptCartDetailModel pCartDetailModel = new ReceiptCartDetailModel();
		pCartDetailModel.setUserName(userName);
		pCartDetailModel.setListCartofSelect(listCartofSelect);
		pCartDetailModel.setFullName(fullNameUser);
		pCartDetailModel.setSumTotal(sumresult);
		pCartDetailModel.setAddRess(account.getAccount_Info().getAddRess());
		pCartDetailModel.setPhoneNumber(account.getAccount_Info().getPhoneNumber());
		pCartDetailModel.setListidProductCart(selectcart);
		model.addAttribute("receiptdetail",pCartDetailModel);
		model.addAttribute("sumcartselect",sumresult);
		return "cartdetail";
	}
	
//	@PostMapping("/showcartselect")
//	@Transactional(propagation = Propagation.NEVER)
//	public String addReceipt(Model model,@ModelAttribute("cartselect")  List<ProductCart> listProductCart01,
//			@ModelAttribute("sumcartselect") BigDecimal sumTotal,
//			@ModelAttribute("account") Account account,
//			@ModelAttribute("fullnameuser") String fullName
//			) {
//		receiptService.saveReceipt(listProductCart01, sumTotal, account, fullName);
//		return "";
//	}
	
	@PostMapping("/showcartselect")
	@Transactional(propagation = Propagation.NEVER)
	public String addReceipt(Model model,
			@ModelAttribute("receiptdetail") ReceiptCartDetailModel rDetailModel) {
		receiptService.saveReceipt(rDetailModel);
		return "";
	}

	

	
	
}
