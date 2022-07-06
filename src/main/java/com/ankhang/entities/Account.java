package com.ankhang.entities;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Account",uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
public class Account implements Serializable {

	private static final long serialVersionUID = 5631551876479782117L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	
	@Column(name = "username", nullable = true)
	private String userName;
	
	@Column(name = "password", nullable = true)
	private String passWord;
}
