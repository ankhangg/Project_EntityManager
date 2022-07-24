package com.ankhang.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ankhang.entities.Product;
import com.ankhang.model.ProductInput;

@Component
public interface ProductService {
  boolean saveProduct(ProductInput productInput, Long cateId, String createdBy);
  Product findByIdProduct(Long id);
  List<Product> findAllProduct();
}
