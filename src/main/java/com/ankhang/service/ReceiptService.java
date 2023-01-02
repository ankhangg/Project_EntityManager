package com.ankhang.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ankhang.entities.Account;
import com.ankhang.entities.Account_Info;
import com.ankhang.entities.ProductCart;
import com.ankhang.entities.Receipt;
import com.ankhang.model.ProductCart_Model;
import com.ankhang.model.ProductReceiptDetail_Model;
import com.ankhang.model.ReceiptCartDetailModel;

public interface ReceiptService {
   void saveReceipt(ReceiptCartDetailModel receipt);
   
   @PreAuthorize("#userName == authentication.principal.username")
   List<Receipt> findlistReceiptbyUsername(String userName);
   
   Receipt findReceiptbyIdandUserName(Long id);
}
