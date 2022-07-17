package com.ankhang.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ankhang.entities.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
	
	    @Query("Select a from Account a where a.userName = ?1")
        Account findAccountByUsername(String userName);
	    

		@Query("Select MAX (a.accId) from Account a")
		Long findmaxId();
		
		Account findByAccId(Long accId);
		
		@Modifying
		@Transactional
		@Query("update Account a set a.passWord = ?1 where a.userName = ?2")
		void updatePassword(String newPassword, String userName);
         
	    
}
  
