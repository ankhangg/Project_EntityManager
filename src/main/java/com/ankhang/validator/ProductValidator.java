package com.ankhang.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
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
		
	}

}
