package com.ankhang.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ankhang.entities.CategoryProduct;
import com.ankhang.entities.InformationControl;
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
		
		CategoryProduct categoryProduct = new CategoryProduct();
		categoryProduct.setCateprodCode(categoryProductInput.getCateprodCode());
		categoryProduct.setCateprodName(categoryProductInput.getCateprodName());
		categoryProduct.setInformationControl(informationControl);
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
       return categoryProductRepository.findAll();
	}

	@Override
	public CategoryProduct findCategorybyId(Long categoryId) {
		CategoryProduct categoryProduct = categoryProductRepository.findById(categoryId).get();
		return categoryProduct;
	}

	@Override
	public boolean deleteCategory(CategoryProduct categoryProduct) {
		try {
			categoryProductRepository.delete(categoryProduct);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCategoryProduct(CategoryProduct categoryProduct, Long cateId, String modifiedBy) {
           String cateCode = categoryProduct.getCateprodCode();
           String cateName = categoryProduct.getCateprodName();
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

