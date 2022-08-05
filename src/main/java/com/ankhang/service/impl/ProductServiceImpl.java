package com.ankhang.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.ankhang.entities.CategoryProduct;
import com.ankhang.entities.InformationControl;
import com.ankhang.entities.Product;
import com.ankhang.model.ProductInput;
import com.ankhang.repository.CategoryProductRepository;
import com.ankhang.repository.ProductRepository;
import com.ankhang.service.ProductService;

@Component
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryProductRepository categoryProductRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Transactional
	@Override
	public boolean saveProduct(ProductInput productInput) {	  
	  InformationControl informationControl = new InformationControl();
	  informationControl.setCreatedDate(new Date());
	  informationControl.setCreatedBy(productInput.getCreatedBy());
      Product product = new Product();
      product.setProductName(productInput.getProductName());
      product.setProductAmount(productInput.getProductAmount());
      product.setProductDescription(productInput.getProductDescription());
      product.setProductPrice(productInput.getProductPrice());
      
      if (productInput.getFileData() != null) {
		byte[] image = null;
		try {
			image = productInput.getFileData().getBytes();
		} catch (Exception e) {
		}
		if (image != null && image.length>0) {
			product.setProductThumbnail(image);
		}
	}
      product.setInformationControl(informationControl);
      CategoryProduct categoryProduct = categoryProductRepository.findCateById(productInput.getIdCategory());
      product.setCategoryProduct(categoryProduct);
      try {
   	 productRepository.save(product);
    	  return true;
	} catch (Exception e) {
		e.printStackTrace();
	}

      
      return false;
	}


	@Override
	public Product findByIdProduct(Long id) {
		Product product = productRepository.findProbyId(id);
		return product;
	}


	@Override
	public List<Product> findAllProduct() {
		return productRepository.findAllProduct();
	}


	@Override
	public boolean deleteProduct(Product product) {
		try {
			productRepository.delete(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public List<Product> findAllProductWithCate() {
		return productRepository.findAllProductWithCate();
	}


	@Override
	public Product findByIdProductWithCate(Long id) {
		Product product = productRepository.findProbyIdWithCate(id);
		return product;
	}


	@Override
	public boolean updateProduct(ProductInput productInput) {
	String prodName = productInput.getProductName();
	Long prodAmount = productInput.getProductAmount();
	BigDecimal productPrice = productInput.getProductPrice();
	Date modifiDate = new Date();
    String productDescription = productInput.getProductDescription();
    
    String modifiedBy = productInput.getModifiedBy();
    Long cateId = productInput.getIdCategory();
    Long productId = productInput.getProductId();
	byte[] image = null;
	
    if (productInput.getFileDataUpdateCanNull() != null) {
    	byte[] test = null;
		try {
			test = productInput.getFileDataUpdateCanNull().getBytes();
		} catch (Exception e) {
		}
		if (test != null && test.length>0) {
			image = test;
		}
		else {
			image = productInput.getProductThumbnail();
		}
    }

		
		
    CategoryProduct categoryProduct = categoryProductRepository.findCateById(cateId);
		try {
			productRepository.updateProduct(prodName, prodAmount, productPrice, image, categoryProduct, modifiedBy, modifiDate, productDescription, productId);
			return true;
		} catch (Exception e) {
		e.printStackTrace();
		}
		return false;
	}



}
