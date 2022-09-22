package com.ankhang.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ankhang.entities.Receipt;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {

}
