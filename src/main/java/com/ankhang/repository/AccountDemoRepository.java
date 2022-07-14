package com.ankhang.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ankhang.entities.AccountDeomo;

@Repository
public interface AccountDemoRepository extends CrudRepository<AccountDeomo, Long> {

}
