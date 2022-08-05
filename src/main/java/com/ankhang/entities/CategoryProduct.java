package com.ankhang.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Categoryproduct")
public class CategoryProduct implements Serializable {

	private static final long serialVersionUID = -2933482874751029930L;

	@Id
	@Column(name = "cateprodid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cateprodId;
	

	@Column(name = "cateprodcode",nullable = false,length = 10,unique=true)
	private String  cateprodCode;
	
	@Column(name = "cateprodname",nullable = false,length = 30)
	private String cateprodName;
	
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "cateprod_id")
	private List<Product> listProduct;
	
	 @Embedded
	private InformationControl informationControl;
   
	
}
	