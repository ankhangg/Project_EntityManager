package com.ankhang.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReceipt_Model {
	private String productReceiptName;
	private BigDecimal productReceiptPrice;
	private Long productReceiptNumber;
	private BigDecimal productCartSum;
}
