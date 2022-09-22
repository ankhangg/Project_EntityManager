package com.ankhang.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ankhang.entities.Product;
import com.ankhang.entities.ProductCart;
import com.ankhang.model.ProductCart_Model;
import com.ankhang.repository.ProductCartRepository;
import com.ankhang.repository.ProductRepository;
import com.ankhang.service.ProductCartService;
import com.ankhang.service.ProductService;

@Component
@Service
public class ProductCartServiceImpl implements ProductCartService {

	@Autowired
	private ProductCartRepository productCartRepository;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public boolean saveProductCart(ProductCart_Model productCart_Model) {
		
		int amountcheck = productCartRepository.checkCartExist(productCart_Model.getProductCartName(), productCart_Model.getUserNameCart());
		
		if (productCart_Model.getUserNameCart().length() != 0 && amountcheck==0) {
		ProductCart productCart = new ProductCart();
		productCart.setProductCartName(productCart_Model.getProductCartName());
		productCart.setProductCartPrice(productCart_Model.getProductCartPrice());
		productCart.setProductCartNumber(productCart_Model.getProductCartNumber());
		productCart.setUserNameCart(productCart_Model.getUserNameCart());
		productCart.setCreatedDate(new Date());
		productCart.setProductId(productCart_Model.getProductId());
		
             
		
		BigDecimal  productCartNumBigDecimal = new BigDecimal(productCart_Model.getProductCartNumber());
		BigDecimal productCartPriBigDecimal = productCart_Model.getProductCartPrice();
		BigDecimal productCartSumBigDecimal =  productCartNumBigDecimal.multiply(productCartPriBigDecimal);
		
		productCart.setProductCartSum(productCartSumBigDecimal);

		
		if (productCart_Model.getProductCartNumber()<=productCart_Model.getProductCartAmount()) {
			try {
			     productCartRepository.save(productCart);
			     return true;
				} catch (Exception e) {
					e.printStackTrace();
				}	
		}
		return false;
		} 
		else if (productCart_Model.getUserNameCart().length() != 0 && amountcheck==1) 
		 {
			Long prodCartId = productCartRepository.findProductCartId(productCart_Model.getProductCartName(), productCart_Model.getUserNameCart());
	        ProductCart productCartExist = productCartRepository.findProCartbyId(prodCartId);
	        BigDecimal productCartExistSumBigDecimal = productCartExist.getProductCartSum();
	        Long productCartExistNumber = productCartExist.getProductCartNumber();
	        
	        BigDecimal  productCartNumBigDecimal = new BigDecimal(productCart_Model.getProductCartNumber());
			BigDecimal productCartPriBigDecimal = productCart_Model.getProductCartPrice();
			BigDecimal productCartSumBigDecimal =  productCartNumBigDecimal.multiply(productCartPriBigDecimal);
			
			BigDecimal productCartSumExistBigDecimalNew = productCartSumBigDecimal.add(productCartExistSumBigDecimal);
			Long productCartExistNumberNew = productCartExistNumber + productCart_Model.getProductCartNumber();
			
			if (productCart_Model.getProductCartNumber()<=productCart_Model.getProductCartAmount() && productCartExistNumberNew <=productCart_Model.getProductCartAmount() ) {
			try {
				productCartRepository.updateProductCart(productCartExistNumberNew, productCartSumExistBigDecimalNew, prodCartId);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
		return false;
	}

	@Override
	public List<ProductCart> FindAllCartByUsername(String userName) {
		return productCartRepository.findCartByUserName(userName);
	}

	@Override
	public BigDecimal findSumCartUser(String userName) {
		return productCartRepository.findSumProductCart(userName);
	}

	@Override
	public ProductCart findProductCart(Long id) {
		return productCartRepository.findProCartbyId(id);
	}

	@Override
	public boolean updateProductCart(ProductCart_Model productCart_Model) {
		ProductCart productCart = productCartRepository.findProCartbyId(productCart_Model.getProductCartId());
		
		Product product = productService.findByIdProduct(productCart_Model.getProductId());
		
		if(productCart_Model.getProductCartNumber()<=0) {
			productCartRepository.delete(productCart);
			return true;
		} else if (productCart_Model.getProductCartNumber()<= product.getProductAmount()) {
			BigDecimal  productCartNumBigDecimal = new BigDecimal(productCart_Model.getProductCartNumber());
			BigDecimal productCartPriBigDecimal = productCart_Model.getProductCartPrice();
			BigDecimal productCartSumBigDecimal =  productCartNumBigDecimal.multiply(productCartPriBigDecimal);
			
			try {
				productCartRepository.updateProductCart(productCart_Model.getProductCartNumber(), productCartSumBigDecimal, productCart_Model.getProductCartId());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void deleteProductCart(ProductCart productCart) {
		try {
			productCartRepository.delete(productCart);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ProductCart findProductCartUserSelect(String userName, Long idCart) {
		return productCartRepository.findCartByUserNameandSelect(userName, idCart);
	}
}
