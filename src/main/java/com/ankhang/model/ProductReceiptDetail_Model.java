package com.ankhang.model;

import java.math.BigDecimal;
import java.util.List;

import com.ankhang.entities.Account;
import com.ankhang.entities.Account_Info;
import com.ankhang.entities.ProductCart;
import com.ankhang.entities.ProductReceipt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReceiptDetail_Model {
    private List<ProductReceipt_Model> listProductReceipts;
    private String fullName;
    private BigDecimal sumTotal;
    
    private String addRess;
    private String phoneNumber;
    private String userName;
    
}
