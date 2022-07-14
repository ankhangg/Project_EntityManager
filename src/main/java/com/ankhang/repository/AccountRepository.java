package com.ankhang.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ankhang.entities.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
	
	    @Query("Select a from Account a where a.userName = ?1")
        Account findAccountByUsername(String userName);
         
	    
}
  
