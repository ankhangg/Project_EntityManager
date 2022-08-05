package com.ankhang.model;



import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.ankhang.entities.CategoryProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInput {
	private Long productId;
	private String  productName;
	private Long productAmount;
	private String productDescription;
	private BigDecimal productPrice;
	private MultipartFile fileData;
	private MultipartFile fileDataUpdateCanNull;
	
	private String createdBy;
	private String modifiedBy;
	
	private Long idCategory;
	
	private byte[] productThumbnail;
		
}
