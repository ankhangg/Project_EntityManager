package com.ankhang.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Product",uniqueConstraints={@UniqueConstraint(columnNames={"prodname"})})
public class Product implements Serializable {

	private static final long serialVersionUID = 4452587222820500863L;

	@Id
	@Column(name = "prodid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
	
	@Column(name = "prodname", length = 100, nullable = true)
	private String  productName;
	
	@Column(name = "prodamount",nullable = true)
	private Long productAmount;
	
	@Column(name = "proddescription", nullable = true, length = 200 )
	private String productDescription;
	
	@Column(name = "prodprice",nullable = true) 
	private Long productPrice;
	
	@Lob
	@Column(name = "prodthumnail", length = Integer.MAX_VALUE, nullable = true)
	private byte[] productThumbnail;
	
	
//	   @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	   @JoinColumn(
//			   name = "cateprodid",
//			   referencedColumnName = "cateprodId",
//			   nullable = true
//			   )
//	private CategoryProduct categoryProduct;
	
	   @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	   @JoinColumn(
			   name = "cateprod_id",
			   referencedColumnName = "cateprodId"
			   )
	private CategoryProduct categoryProduct;
	
	
	 @Embedded
	private InformationControl informationControl;
}
