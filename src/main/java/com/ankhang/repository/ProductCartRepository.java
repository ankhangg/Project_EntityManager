package com.ankhang.repository;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ankhang.entities.ProductCart;

@Repository
public interface ProductCartRepository extends CrudRepository<ProductCart, Long> {
	@Query("Select count (p.productCartId) from ProductCart p where p.productCartName = ?1 and p.userNameCart = ?2")
	int checkCartExist(String productCartName,String userNameCart);
	
	@Query("Select p from ProductCart p where productCartId= ?1")
	ProductCart findProCartbyId(Long prodcartId);
	
	@Query("Select p.productCartId from ProductCart p where p.productCartName = ?1 and p.userNameCart = ?2")
	Long findProductCartId(String productCartName,String userNameCart);
	
	@Modifying
	@Transactional
    @Query("update ProductCart p set p.productCartNumber = ?1, p.productCartSum = ?2 where p.productCartId = ?3")
	void updateProductCart(Long productCartNumber, BigDecimal productCartSum, Long productCartId);
	
	@Query("Select sum (p.productCartSum) from ProductCart p where p.userNameCart = ?1")
	BigDecimal findSumProductCart(String userNameCart);
	
	@Query("select p from ProductCart p where p.userNameCart = ?1 ")
	List<ProductCart> findCartByUserName(String userName);
	
	@Query("select p from ProductCart p where p.userNameCart = ?1 and p.productCartId = ?2")
	ProductCart findCartByUserNameandSelect(String userName, Long idcart);
	
}
