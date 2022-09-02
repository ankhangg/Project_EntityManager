package com.ankhang.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ankhang.entities.Product;
import com.ankhang.model.ProductCart_Model;
import com.ankhang.service.ProductCartService;
import com.ankhang.service.ProductService;

@Component
public class ProductCartValidator implements Validator {

	@Autowired
	private ProductCartService productCartService;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ProductCart_Model.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductCart_Model productCart_Model = (ProductCart_Model) target;
	
		Product product = productService.findByIdProduct(productCart_Model.getProductId());
		Long amountcheck = product.getProductAmount();
			
		Long productCartNumberCheck = productCart_Model.getProductCartNumber();
		if (productCartNumberCheck<=0 ) {
			errors.rejectValue("productCartNumber", "productCartNumber.length");
		}
		
		if (productCartNumberCheck>amountcheck) {
			errors.rejectValue("productCartNumber", "productCartNumber.length02");
		}
		
	}

}
