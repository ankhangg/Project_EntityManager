package com.ankhang.test;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ankhang.entities.CategoryProduct;
import com.ankhang.entities.InformationControl;
import com.ankhang.entities.Product;
import com.ankhang.repository.CategoryProductRepository;

@SpringBootTest
public class CateTest {

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
				
				.informationControl(informationControl)
				.build();
		
		CategoryProduct categoryProduct = CategoryProduct.builder()
				.cateprodCode("IP")
				.cateprodName("Iphone")
				.informationControl(informationControl)
				//.listProduct(List.of(product01))
				.build();
				categoryProductRepository.save(categoryProduct);
	}
}
