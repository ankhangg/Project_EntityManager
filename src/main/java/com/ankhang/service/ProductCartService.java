package com.ankhang.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ankhang.entities.ProductCart;
import com.ankhang.model.ProductCart_Model;

public interface ProductCartService {
   boolean saveProductCart(ProductCart_Model productCart_Model);
   
   
   
   //@PreAuthorize("#userName == authentication.principal.username")
   //Just authen when the user have login
   @PreAuthorize("isAuthenticated() and (#userName == authentication.principal.username)")
   BigDecimal findSumCartUser(String userName);
   
   //@PreAuthorize("#userName == authentication.principal.username")
   @PreAuthorize("isAuthenticated() and (#userName == authentication.principal.username)")
   List<ProductCart> FindAllCartByUsername(String userName);
   
   ProductCart findProductCart(Long id);
   ProductCart findProductCartAuthen(Long id, String userName);
   boolean updateProductCart(ProductCart_Model productCart_Model);
   void deleteProductCart(ProductCart productCart);
   ProductCart findProductCartUserSelect(String userName, Long idCart);
}
