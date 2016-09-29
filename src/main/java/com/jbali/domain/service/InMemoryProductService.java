/**
 * DOMAIN SERVICE CLASS
 * 
 * This class serves as a domain service class for Product domain
 * (following the Domain Driven Design model)
 */


package com.jbali.domain.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbali.dao.ProductDao;
import com.jbali.domain.Product;
import com.jbali.exception.ProductException;
import com.jbali.util.PosConstants;

@Service
public class InMemoryProductService implements ProductService {
	
	@Autowired
	ProductDao productDao;
	

	/**
	 * This method is used to add a product in to the cart
	 * If the scanned product isn't valid then, this method would throw a CHECKED EXCEPTION
	 * informing the caller that the product isn't valid. 
	 */
	@Override
	public void addProduct(String productName) throws ProductException{
		
		if(isValidProduct(productName)){			
			productDao.addProductInCart(productName);
		}
		else{
			throw new ProductException(ProductException.ERRORCODE.SCANNED_PRODUCT_NOT_VALID);
		}		
		
	}

	
	/**
	 * This method calculates the total price of the cart
	 * applying applicable unit price and volume price rules on all the 
	 * products present in the cart.
	 * @throws ProductException 
	 */
	@Override
	public double calculateCartPrice() throws ProductException  {
		
		Map<String, Product> allProductPrices = productDao.getAllPrices();
		
		if (allProductPrices == null || allProductPrices.keySet() == null){
			throw new ProductException(ProductException.ERRORCODE.PRODUCT_PRICES_NOT_AVAILABLE);
		}		
	
		double totalCartPrice = 0;	
		
		Map<String, Integer> currentCart = productDao.getCart();
		
		for(Map.Entry<String, Integer> cartEntry : currentCart.entrySet()){
			
			String productName = cartEntry.getKey();
			Integer productCount = cartEntry.getValue();
			
			Product product = allProductPrices.get(productName);			
			
			double productPrice = getTotalProductPrice(productCount, product);
			
			totalCartPrice += productPrice;		
			
		}
		
		return totalCartPrice;
	}
	
	
	@Override
	public void clearCart(){		
		productDao.clearCart();		
	}

	//////////////////////////////////////
	///////PRIVATE METHODS///////////////
	////////////////////////////////////
	

	/**
	 * This method calculates the sum total of the unit and the volume price for a set 
	 * of particular product present in the cart.
	 * @param productCount: A count of products of a specific type present in the cart.
	 * @param product: A fully blown Product object for a particular product type present in the car.
	 * @return
	 */
	private double getTotalProductPrice(Integer productCount, Product product) {
		
		int unitPriceItems = 0;
		int volumePriceItemUnits = 0;		
		
		if (product.getVolumePrice() > 0){
			 unitPriceItems = productCount % product.getItemsForVolumePrice();
			 volumePriceItemUnits = (productCount - unitPriceItems)/product.getItemsForVolumePrice();
		}
		else{
			unitPriceItems = productCount;
		}
		
		return (unitPriceItems * product.getUnitPrice()) + (volumePriceItemUnits * product.getVolumePrice());
		
	}
	
	
	/**
	 * Return true if a scanned product is in the list of allowed product inventory for 
	 * the POS system, return false otherwise.
	 * @param productName
	 * @return
	 */
	private boolean isValidProduct(String productName){
		
		return (productDao.getProductInventory()).containsKey(productName);
		
	}
	

}
