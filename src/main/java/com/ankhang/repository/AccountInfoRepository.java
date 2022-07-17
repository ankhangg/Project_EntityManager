package com.ankhang.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ankhang.entities.Account;
import com.ankhang.entities.Account_Info;

@Repository
public interface AccountInfoRepository extends CrudRepository<Account_Info, Long> {
    

	@Modifying
	@Transactional
	@Query("update Account_Info af set af.firstName = ?1, af.lastName = ?2, af.addRess = ?3, af.phoneNumber = ?4, af.email = ?5 where af.account = ?6")
    void saveupdateAccountInfo(String firstName,
    		String lastName, String addRess,
    		String phoneNumber, String email, Account account);
     

	@Query("Select af from Account_Info af where af.account = ?1")
	Account_Info findAccountInfoByAccount(Account account);
	

}
