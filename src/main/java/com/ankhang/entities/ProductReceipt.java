package com.ankhang.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "ProductReceipt")
public class ProductReceipt {
	@Id
	@Column(name = "prodrcid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long productReceiptId;
	
	@Column(name = "prodrcname", length = 100, nullable = true)
	private String productReceiptName;
	
	@Column(name = "prodid")
	private Long productId;
	
	@Column(name = "prodrcprice",nullable = true) 
	private BigDecimal productReceiptPrice;
	
	@Column(name = "prodrcnumber",nullable = true)
	private Long productReceiptNumber;
	
	@Column(name = "prodcsum",nullable = true) 
	private BigDecimal productCartSum;
	
	@Column(name = "usernamereceipt", nullable = false)
    private String userNameReceipt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "receiptdtid",nullable = true) 
	private Long receiptdtId;
		
	@ManyToMany(cascade =  CascadeType.ALL)
	@JoinTable(
			name = "prod_receiptdetail",
			joinColumns = @JoinColumn(name = "prod_id"),
			inverseJoinColumns = @JoinColumn(name ="receiptdetail_id")
			)
	private List<ReceiptDetail> listReceipts;
	
	public void addReceiptDetail(ReceiptDetail receiptDetail) {
		if (listReceipts == null) {
			listReceipts = new ArrayList<>();
			listReceipts.add(receiptDetail);
		} else if (listReceipts != null) {
			listReceipts.add(receiptDetail);
		}
	}
	
}
