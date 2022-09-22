package com.ankhang.service;

import java.math.BigDecimal;
import java.util.List;

import com.ankhang.entities.ProductCart;
import com.ankhang.model.ProductCart_Model;

public interface ProductCartService {
   boolean saveProductCart(ProductCart_Model productCart_Model);
   BigDecimal findSumCartUser(String userName);
   List<ProductCart> FindAllCartByUsername(String userName);
   ProductCart findProductCart(Long id);
   boolean updateProductCart(ProductCart_Model productCart_Model);
   void deleteProductCart(ProductCart productCart);
   ProductCart findProductCartUserSelect(String userName, Long idCart);
}
