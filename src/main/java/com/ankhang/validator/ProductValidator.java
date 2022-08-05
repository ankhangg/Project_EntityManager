package com.ankhang.validator;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ankhang.model.ProductInput;
import com.ankhang.repository.ProductRepository;
import com.ankhang.service.ProductService;

@Component
public class ProductValidator implements Validator {
	
	@Autowired
	private ProductService productService;

	@Override
	public boolean supports(Class<?> clazz) {
         return clazz == ProductInput.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductInput productInput = (ProductInput) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "NotEmpty.prod.productName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productDescription", "NotEmpty.prod.productDescription");


		
		
		BigDecimal pricecheck = productInput.getProductPrice();
		if (pricecheck.compareTo(BigDecimal.ZERO) == 0) {
			errors.rejectValue("productPrice", "productPrice.length");
		}
	
		Long cateIdCheck = productInput.getIdCategory();
		if (cateIdCheck  == 0) {
			errors.rejectValue("idCategory", "idCategory.length");
		}
	
		// Check null Image
		 if (productInput.getFileData() != null) {
		    	byte[] test = null;
				try {
					test = productInput.getFileData().getBytes();
				} catch (Exception e) {
				}
				if (test != null && test.length>0) {
				}
				else {
					errors.rejectValue("fileData", "NotEmpty.prod.fileData");
				}
		    }
		
	}

}
