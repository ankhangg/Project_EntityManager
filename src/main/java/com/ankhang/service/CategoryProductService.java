package com.ankhang.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ankhang.entities.CategoryProduct;
import com.ankhang.model.CategoryProductInput;


public interface CategoryProductService {
  boolean saveCategoryProd(CategoryProductInput categoryProductInput);
  List<CategoryProduct> findAllCategory();
  CategoryProduct findCategorybyId(Long categoryId);
  boolean deleteCategory(CategoryProduct categoryProduct,Long cateIdtest); 
  boolean updateCategoryProduct(CategoryProductInput categoryProductInput);


}
