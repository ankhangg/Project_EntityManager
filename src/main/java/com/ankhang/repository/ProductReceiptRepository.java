package com.ankhang.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ankhang.entities.ProductReceipt;
@Repository
public interface ProductReceiptRepository extends CrudRepository<ProductReceipt, Long> {

}
