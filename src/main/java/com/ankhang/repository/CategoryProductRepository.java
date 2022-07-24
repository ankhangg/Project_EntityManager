package com.ankhang.repository;

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
public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {
	
	@Modifying
	@Transactional
    @Query("update CategoryProduct c set c.cateprodCode = ?1, c.cateprodName = ?2, c.informationControl.modifiedBy = ?3, c.informationControl.modifiedDate = ?4 where c.cateprodId = ?5")
	void updateCategory(String cateCode, String cateName, String modifiedBy, Date modifiedDate, Long cateId);
	
//	
//	@Modifying
//	@Transactional
//    @Query("update CategoryProduct c set c.listProduct = ?1 where c.cateprodId = ?2")
//	void updateProducttoCate(List.of(Product product) ,Long cateId);
	

}
