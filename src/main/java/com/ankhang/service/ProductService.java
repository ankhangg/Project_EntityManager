package com.ankhang.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ankhang.entities.CategoryProduct;
import com.ankhang.entities.Product;
import com.ankhang.model.ProductInput;

@Component
public interface ProductService {
  boolean saveProduct(ProductInput productInput);
  Product findByIdProduct(Long id);
  List<Product> findAllProduct();
  List<Product> findAllProductWithCate();
  boolean deleteProduct(Product product);
  Product findByIdProductWithCate(Long id);
  boolean updateProduct(ProductInput productInput);
}
