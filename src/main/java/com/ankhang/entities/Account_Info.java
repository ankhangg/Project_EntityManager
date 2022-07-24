package com.ankhang.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "account")
public class Account_Info implements Serializable {


	private static final long serialVersionUID = 1854616041744092605L;
	@Id
	@Column(name = "accinfoid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accinfoId;
	
	@Column(name = "firstname", nullable = true, length = 20 )
	private String firstName;
	
	@Column(name = "lastname", nullable = true, length = 20 )
	private String lastName;
	
	@Column(name = "adddress", nullable = true )
	private String addRess;
	
	@Column(name = "phonenumber", nullable = true, length = 10)
	private String phoneNumber;
	
	@Column(name = "email", nullable = true, length = 40)
	private String email;
	
	 @OneToOne(cascade = CascadeType.ALL,
	    		optional = true)
	  @JoinColumn(name = "username", referencedColumnName = "userName")
	private Account account;
}
