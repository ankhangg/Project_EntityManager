package com.ankhang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProductInput {
	private Long cateprodId;
	private String  cateprodCode;
	private String cateprodName;
	private String createdBy;
	private String modifiedBy;

	
	
	
}
