package com.ankhang.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ankhang.entities.Account_Info;

@Repository
public interface AccountInfoRepository extends CrudRepository<Account_Info, Long> {
    
	//Native
	@Modifying
	@Transactional
	@Query( value = "update Account_Info set firstname = ?1, lastname = ?2, adddress = ?3, phonenumber = ?4, email = ?5   where username = ?6", nativeQuery = true )
    void saveupdateAccountInfo(String firstName,
    		String lastName, String addRess,
    		String phoneNumber, String email, String userName);
// Not Use    
//	@Query("Select af from Account_Info af where af.userName = ?1")
//	Account_Info findAccountInfoByUsername(String userName);
}
