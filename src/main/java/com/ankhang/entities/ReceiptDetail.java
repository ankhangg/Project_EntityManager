package com.ankhang.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Receiptdetail")
public class ReceiptDetail implements Serializable {

	private static final long serialVersionUID = -8939405240688459373L;
	@Id
	@Column(name = "rcdetailid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rcdetailId;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "receiptid")
	private Receipt receipt;
	
	@Column(name = "fullname", nullable = true, length = 100 )
	private String fullName;
	
	@Column(name = "phonenumber", nullable = true, length = 10)
	private String phoneNumber;
	
	@Column(name = "adddress", nullable = true )
	private String addRess;
	
	@ManyToMany(mappedBy = "listReceipts")
	private List<ProductReceipt> listproductReceipts;
}
