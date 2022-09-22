package com.ankhang.model;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCart_Model {
	 private Long productCartId;
	 private String productCartName;
	 private BigDecimal productCartPrice;
	 private Long productCartAmount;
	 private BigDecimal productCartSum;
	 private String productCartDescription; 
	 private String userNameCart;
	 private Long productCartNumber;
	 
	 private Long productId;
	 
	 
}
