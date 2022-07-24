package com.ankhang.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryProductRepository categoryProductRepository;
	

	@Override
	public boolean saveProduct(ProductInput productInput, Long cateId, String createdBy) {
	  CategoryProduct thisCategoryProduct = categoryProductRepository.findById(cateId).get();
	  InformationControl informationControl = new InformationControl();
	  informationControl.setCreatedDate(new Date());
	  informationControl.setCreatedBy(createdBy);
      Product product = new Product();
      product.setProductName(productInput.getProductName());
      product.setProductAmount(productInput.getProductAmount());
      product.setProductDescription(productInput.getProductDescription());
      product.setProductPrice(productInput.getProductPrice());
      product.setCategoryProduct(thisCategoryProduct);
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
		Product product = productRepository.findById(id).get();
		return product;
	}


	@Override
	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}



}
