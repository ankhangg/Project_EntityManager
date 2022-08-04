package com.ankhang.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.validator.routines.checkdigit.ISBN10CheckDigit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ankhang.entities.CategoryProduct;
import com.ankhang.entities.InformationControl;
import com.ankhang.entities.Product;
import com.ankhang.model.CategoryProductInput;
import com.ankhang.repository.CategoryProductRepository;
import com.ankhang.service.CategoryProductService;

@Component
@Service
public class CategoryProductServiceImpl implements CategoryProductService {

	@Autowired
	private CategoryProductRepository categoryProductRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean saveCategoryProd(CategoryProductInput categoryProductInput) {
		InformationControl informationControl = new InformationControl();
		informationControl.setCreatedBy(categoryProductInput.getCreatedBy());
		informationControl.setCreatedDate(new Date());
		
		List<Product> listProduct = new ArrayList<Product>();
		CategoryProduct categoryProduct = new CategoryProduct();
		categoryProduct.setCateprodCode(categoryProductInput.getCateprodCode());
		categoryProduct.setCateprodName(categoryProductInput.getCateprodName());
		categoryProduct.setInformationControl(informationControl);
		categoryProduct.setListProduct(listProduct);
		try {
			categoryProductRepository.save(categoryProduct);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}

	@Override
	public List<CategoryProduct> findAllCategory() {
       return categoryProductRepository.findAllCategory();
	}

	
	@Override
	//@Transactional
	public CategoryProduct findCategorybyId(Long categoryId) {
		CategoryProduct categoryProduct = categoryProductRepository.findCateById(categoryId);
		//CategoryProduct categoryProduct = entityManager.find(CategoryProduct.class, categoryId); //dung dc
		return categoryProduct;
	}
	
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
	
	@Override
	public boolean deleteCategory(CategoryProduct categoryProduct,Long cateIdtest) {
		CategoryProduct categoryProductTest = categoryProductRepository.findCateByIdCheckProduct(cateIdtest);
		List<Product> thislist = categoryProductTest.getListProduct();
		boolean checkIsEmptyList = isEmpty(thislist);
		if (checkIsEmptyList) {
				categoryProductRepository.delete(categoryProduct);
				return true;
		}
		return false;
	}

	@Override
	public boolean updateCategoryProduct(CategoryProductInput categoryProductInput) {
           String cateCode = categoryProductInput.getCateprodCode();
           String cateName = categoryProductInput.getCateprodName();
           Long cateId = categoryProductInput.getCateprodId();
           String modifiedBy = categoryProductInput.getModifiedBy();
           Date modifiedDate = new Date();
		try {
            categoryProductRepository.updateCategory(cateCode, cateName, modifiedBy, modifiedDate, cateId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}



}

