package com.ankhang.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Table(name = "Product",uniqueConstraints={@UniqueConstraint(columnNames={"prodname"})})
@Table(name = "ProductCart")
public class ProductCart {
	
	@Id
	@Column(name = "prodcid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long productCartId;
	
	@Column(name = "prodcname", length = 100, nullable = true)
	private String productCartName;
	
	@Column(name = "prodid")
	private Long productId;
	
	@Column(name = "prodcprice",nullable = true) 
	private BigDecimal productCartPrice;
	
	@Column(name = "prodcnumber",nullable = true)
	private Long productCartNumber;
	
	@Column(name = "prodcsum",nullable = true) 
	private BigDecimal productCartSum;
	
	@Column(name = "usernamecart", nullable = false)
    private String userNameCart;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
}
