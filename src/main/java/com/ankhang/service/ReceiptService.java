package com.ankhang.service;

import java.math.BigDecimal;
import java.util.List;

import com.ankhang.entities.Account;
import com.ankhang.entities.Account_Info;
import com.ankhang.entities.ProductCart;
import com.ankhang.entities.Receipt;
import com.ankhang.model.ProductCart_Model;
import com.ankhang.model.ProductReceiptDetail_Model;
import com.ankhang.model.ReceiptCartDetailModel;

public interface ReceiptService {
   void saveReceipt(ReceiptCartDetailModel receipt);
   List<Receipt> findlistReceiptbyUsername(String userName);
   Receipt findReceiptbyIdandUserName(Long id);
}
