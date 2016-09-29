/**
 * GATEWAY for the DB (following Domain Driven Design model)
 */

package com.jbali.dao;

import java.util.Map;

import com.jbali.domain.Product;

public interface ProductDao {
	
	void addProductInCart(String productName);	
	
	Map<String, Integer> getCart();
	
	Map<String, String> getProductInventory();
	
	Map<String, Product> getAllPrices();
	
	void clearCart();

}
