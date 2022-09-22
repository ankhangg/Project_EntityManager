package com.ankhang.model;

import java.math.BigDecimal;
import java.util.List;

import com.ankhang.entities.Account;
import com.ankhang.entities.Account_Info;
import com.ankhang.entities.ProductCart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptCartDetailModel {
    private List<ProductCart> listCartofSelect;
    private String fullName;
    private BigDecimal sumTotal;
    private String userName;
    
    private String addRess;
    private String phoneNumber;
    private Long[] listidProductCart;
    
}
