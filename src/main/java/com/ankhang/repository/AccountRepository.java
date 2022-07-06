package com.ankhang.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ankhang.entities.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

}
