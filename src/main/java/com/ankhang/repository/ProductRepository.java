package com.ankhang.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ankhang.entities.CategoryProduct;
import com.ankhang.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {


	@Query("select c from Product c")
	List<Product> findAllProduct();
	
	@Query("select c from Product c left join fetch c.categoryProduct")
	List<Product> findAllProductWithCate();
	
	//@Query("select p from Product p left join fetch p.categoryProduct where p.productId = ?1")
	@Query("select p from Product p where p.productId = ?1")
	Product findProbyId(Long prodId);
	
	@Query("select p from Product p left join fetch p.categoryProduct where p.productId = ?1")
	Product findProbyIdWithCate(Long prodId);
	
	@Modifying
	@Transactional
    @Query("update Product p set p.productName=?1, p.productAmount=?2, p.productPrice=?3, "
    + "p.productThumbnail=?4, p.categoryProduct=?5, p.informationControl.modifiedBy=?6, p.informationControl.modifiedDate = ?7, "
    + "p.productDescription=?8 where p.productId =?9")
	void updateProduct(String productName, Long productAmount, BigDecimal productPrice, byte[] productThumbnail, 
			CategoryProduct categoryProduct, String modifiedBy, Date modifiDate, String productDescription, Long productId);
	
	@Modifying
	@Transactional
	@Query("update Product p set p.productAmount = ?1 where p.productId = ?2")
	void updateAmountProduct(Long amount, Long prodId);
	
}
