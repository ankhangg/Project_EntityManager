package com.ankhang.model;



import org.springframework.web.multipart.MultipartFile;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInput {
	private String  productName;
	private Long productAmount;
	private String productDescription;
	private Long productPrice;
	private MultipartFile fileData;
	
	private String createdBy;
	private String modifiedBy;
	
	private Long idCategory;
}
