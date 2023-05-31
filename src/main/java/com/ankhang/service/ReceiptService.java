package com.ankhang.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;

import com.ankhang.entities.Receipt;
import com.ankhang.model.ReceiptCartDetailModel;

public interface ReceiptService {
   void saveReceipt(ReceiptCartDetailModel receipt);
   
   @PreAuthorize("#userName == authentication.principal.username")
   List<Receipt> findlistReceiptbyUsername(String userName);
   
   Receipt findReceiptByIdAndUserName(Long id, String userName);

}
