package com.ankhang.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ankhang.entities.Account;
import com.ankhang.entities.Account_Info;
import com.ankhang.entities.Product;
import com.ankhang.entities.ProductCart;
import com.ankhang.entities.ProductReceipt;
import com.ankhang.entities.Receipt;
import com.ankhang.entities.ReceiptDetail;
import com.ankhang.model.ProductCart_Model;
import com.ankhang.model.ReceiptCartDetailModel;
import com.ankhang.repository.AccountInfoRepository;
import com.ankhang.repository.AccountRepository;
import com.ankhang.repository.ProductReceiptRepository;
import com.ankhang.repository.ReceiptDetailRepository;
import com.ankhang.repository.ReceiptRepository;
import com.ankhang.service.ProductCartService;
import com.ankhang.service.ProductService;
import com.ankhang.service.ReceiptService;
@Component
@Service
public class ReceiptServiceImpl implements ReceiptService {
	
	@Autowired
	private ReceiptRepository receiptRepository;
	
	
	@Autowired
	private ProductReceiptRepository productReceiptRepository;
	
	@Autowired
	private ReceiptDetailRepository receiptDetailRepository;
	
	@Autowired
    private ProductService productService;
	
	@Autowired
	private ProductCartService productCartService;

	@Transactional
	@Override
	public void saveReceipt(ReceiptCartDetailModel receiptCart) {
		Receipt receipt = new Receipt();
		ReceiptDetail receiptDetail = new ReceiptDetail();
		
		receipt.setCreatedDate(new Date());
		receipt.setTotalSum(receiptCart.getSumTotal());
		receipt.setUserName(receiptCart.getUserName());
		
		receiptDetail.setAddRess(receiptCart.getAddRess());
		receiptDetail.setFullName(receiptCart.getFullName());
		receiptDetail.setPhoneNumber(receiptCart.getPhoneNumber());
		receiptDetail.setReceipt(receipt);		
		
		receiptDetailRepository.save(receiptDetail);
		
		for(ProductCart productCart: receiptCart.getListCartofSelect()) {
		   ProductReceipt productReceipt = new ProductReceipt();
		   productReceipt.setCreatedDate(new Date());
		   productReceipt.setProductCartSum(productCart.getProductCartSum());
		   productReceipt.setProductId(productCart.getProductId());
		   productReceipt.setProductReceiptName(productCart.getProductCartName());
		   productReceipt.setProductReceiptNumber(productCart.getProductCartNumber());
		   productReceipt.setProductReceiptPrice(productCart.getProductCartPrice());
		   productReceipt.setReceiptdtId(receipt.getReceiptId());
		   productReceipt.setUserNameReceipt(receiptCart.getUserName());
		   productReceipt.addReceiptDetail(receiptDetail);
		  try {
			  productReceiptRepository.save(productReceipt);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		  
		}
		
		  for(Long idProductCart: receiptCart.getListidProductCart()) {
			  ProductCart productCartDelete = productCartService.findProductCart(idProductCart);
			  Product product = productService.findByIdProduct(productCartDelete.getProductId());
			  
			  Long amountUpdate = product.getProductAmount() - productCartDelete.getProductCartNumber();
			  try {
				  productService.updateAmountProduct(amountUpdate, product.getProductId());
				  productCartService.deleteProductCart(productCartDelete);
			} catch (Exception e) {
				e.printStackTrace();
			}
		  }
		
	 }


}
