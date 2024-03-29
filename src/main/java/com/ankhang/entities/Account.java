package com.ankhang.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
//@Table(name = "Account",uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
@Table(name =  "Account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1359964220022834762L;
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
	
    @Column(name = "accid")
    private Long accId; 
	
    @Id
	@Column(name = "username", nullable = false,unique=true)
	private String userName;
	
	@Column(name = "password", length = 100, nullable = false)
	private String passWord;
	
	private boolean active;
	
	@Column(name = "user_role",nullable = false)
	private String userRole;
	
	 @OneToOne(mappedBy = "account")
	private Account_Info account_Info;
	 
//		@OneToMany(cascade = CascadeType.ALL,orphanRemoval = false)
//		@JoinColumn(name = "account_id")
//		 private List<Receipt> receipt;
}
