package com.ankhang.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ankhang.entities.CategoryProduct;
import com.ankhang.entities.InformationControl;
import com.ankhang.entities.Product;
import com.ankhang.repository.CategoryProductRepository;
import com.ankhang.repository.ProductRepository;

@SpringBootTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryProductRepository categoryProductRepository;
	
	
	@Test
	public void saveProduct(){
		InformationControl informationControl = InformationControl.builder()
				.createdBy("admin")
				.createdDate(new Date())
				.build();
		Product product01 = Product.builder()
				.productName("Laptop HP")
				.productAmount(9L)
				.productDescription("Đây là sản phẩm product Laptop HP")
				.productPrice(15000000L)
				.informationControl(informationControl)
				.build();
		
		CategoryProduct categoryProduct = CategoryProduct.builder()
				.cateprodCode("IP")
				.cateprodName("Iphone")
				.informationControl(informationControl)
				.listProduct(List.of(product01))
				.build();
				categoryProductRepository.save(categoryProduct);
	}

	
//	@Test
//	public void findAllCate(){
//     categoryProductRepository.findAll().forEach((p)-> {
//         System.out.println(p.getCateprodName());
//     });
//	}
	
//	@Test
//	public void findAllCate(){
//      CategoryProduct categoryProduct = categoryProductRepository.findById(2L).get();
//      Assertions.assertThat(categoryProduct.getCateprodId()).isEqualTo(2L);
//      System.out.println(categoryProduct);
//	}
	
//	@Test
//	public void saveProduct(){
//		InformationControl informationControl = InformationControl.builder()
//				.createdBy("admin")
//				.createdDate(new Date())
//				.build();
//		Product product01 = Product.builder()
//				.productName("Laptop HP")
//				.productAmount(9L)
//				.productDescription("Đây là sản phẩm product Laptop HP")
//				.productPrice(15000000L)
//				.informationControl(informationControl)
//				.build();
//		CategoryProduct categoryProduct = categoryProductRepository.findById(2L).get();
//		categoryProduct.setListProduct(List.of(product01));
//		
//		CategoryProduct categoryProduct2 = categoryProductRepository.save(categoryProduct);
//		Assertions.assertThat(categoryProduct2.getListProduct()).isEqualTo(List.of(product01));
//		
//	}
	
}
