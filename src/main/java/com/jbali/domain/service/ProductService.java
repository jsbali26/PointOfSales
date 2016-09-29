/**
 * DOMAIN SERVICE for Product Domain (Following Domain Driven Design principle)
 */
package com.jbali.domain.service;

import com.jbali.exception.ProductException;

public interface ProductService {
	
	void addProduct(String productName) throws ProductException;
	
	double calculateCartPrice() throws ProductException;
	
	void clearCart();

}
