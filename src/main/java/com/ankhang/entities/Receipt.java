package com.ankhang.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "Receipt")
public class Receipt implements Serializable {
	private static final long serialVersionUID = -5653187098228066118L;
	
	@Id
	@Column(name = "receiptid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long receiptId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@OneToOne(mappedBy = "receipt")
	private ReceiptDetail receiptDetail;
	
	@Column(name = "username", nullable = true )
	private String userName;
	
	@Column(name = "totalsum",nullable = false) 
	private BigDecimal totalSum;
}
