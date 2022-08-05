package com.ankhang.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ankhang.model.CategoryProductInput;
import com.ankhang.service.CategoryProductService;

@Component
public class CategoryValidator implements Validator{
	
	@Autowired
	private CategoryProductService categoryProductService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == CategoryProductInput.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		CategoryProductInput categoryProductInput = (CategoryProductInput) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cateprodCode", "NotEmpty.cate.cateprodCode");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cateprodName", "NotEmpty.cate.cateprodName");
		
		if (categoryProductInput.getCateprodCode().length()>4) {
			errors.rejectValue("cateprodCode", "Length.cate.cateprodCode");
		}
	}

}
