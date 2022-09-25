package com.ankhang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ankhang.entities.Receipt;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
    
	@Query("Select r from Receipt r where r.userName = ?1")
	List<Receipt> listReceiptsofUser (String userName);
	
	Receipt findByReceiptId(Long receiptId);
	
	@Query("Select r from Receipt r where r.userName = ?1 and r.receiptId = ?2")
	Receipt findByReceiptIdandUsername(String userName, Long receiptId);
}
