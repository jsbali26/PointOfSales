/**
 * GATEWAY for the Database.
 * In Domain Driven Design approach, this would serve as a GATEWAY to the database (in memory data structure in this case)
 */

package com.jbali.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import com.jbali.domain.Product;

@Repository
public class InMemoryProductDao implements ProductDao {	
	
	
	/*
	 cart key: product name e.g. A, B etc
	 cart value: count of a specific product type
	 
	 Cart would look like this:
	 
	 Key(product tName)               Value (product count)
	 ----            			      -----
	  A                    				3
	  B					   				4
	  D					   				8	 
	  
	  NOTE: DATA STRUCTURES LIKE ConcurrentHashMap etc AREN'T USED HERE BECAUSE THIS PROBELM DOESN'T SUPPORT
	        MULTIPLE SESSIONS AND CONCURRENCY. OTHERWISE, THE CHOICE OF DATA-STRUCTURE WOULD BE DIFFERENT.
	        TO AVOID OVERKILL AND BASED ON WHAT THIS CODING ASSIGNMENT DEMANDS, A SIMPLE HASHMAP IS USED HERE
	  
	 */
	private ConcurrentMap<String, Integer> inMemoryCart = new ConcurrentHashMap<String, Integer>();
	
	
	/**
	 * This method inserts a valid scanned product in to the in-memory 
	 * data structure used to store cart contents.
	 */
	@Override
	public void addProductInCart(String productName) {
					
	 	if ( this.inMemoryCart.containsKey(productName)){		 		
	 		this.inMemoryCart.put(productName, this.inMemoryCart.get(productName) + 1);		 		
	 	}
	 	else{
	 		this.inMemoryCart.put(productName, 1);
	 	}		 	
		
	}
	
	/**
	 * This method returns the cart in its current state. 
	 */
	@Override
	public Map<String, Integer> getCart(){
		return inMemoryCart;
	}

	
	/**
	 * Returns a master list of products supported by 
	 * the POS system
	 * @return
	 */
	@Override
	public Map<String, String> getProductInventory(){
		
		Map<String, String> productInventory = new HashMap<String, String>();
	
		productInventory.put("A", "A");
		productInventory.put("B", "B");
		productInventory.put("C", "C");
		productInventory.put("D", "D");
		
		return productInventory;
	}
	
	/**
	 * Returns a Map of Product objects
	 * containing price details of all the products supported by 
	 * the POS system.
	 * 
	 * NOTE: ALL THE VALUES ARE HARD-CODED FOR THE SAKE OF CONVENIENCE AND THE NATURE OF THIS CODING ASSIGNMENT.
	 * 		 IN REAL LIFE THIS WOULD NOT BE THE CASE.
	 *       WE WOULD USE SOME ORM OR SOME OTHER TECHNIQUE TO POPULATE Product OBJECT
	 *       FROM THE SOURCE OF DATA.    
	 */
	@Override
	public Map<String, Product> getAllPrices(){
		
		 Product productA = new Product();
		 productA.setName("A");
		 productA.setUnitPrice(1.25);
		 productA.setVolumePrice(3);
		 productA.setItemsForVolumePrice(3);
		 
		 Product productB = new Product();
		 productB.setName("B");
		 productB.setUnitPrice(4.25);
		 
		 Product productC = new Product();
		 productC.setName("C");
		 productC.setUnitPrice(1);
		 productC.setVolumePrice(5);
		 productC.setItemsForVolumePrice(6);
		 
		 Product productD = new Product();
		 productD.setName("D");
		 productD.setUnitPrice(0.75);
		 
		 Map<String, Product> allPrices = new HashMap<String, Product>();
		 allPrices.put("A", productA);
		 allPrices.put("B", productB);
		 allPrices.put("C", productC);
		 allPrices.put("D", productD);
				 
		 return allPrices;
		
	}
	
	/**
	 * Method to clear the cart.
	 */
	@Override
	public void clearCart(){
		this.inMemoryCart = new ConcurrentHashMap<String, Integer>();
	}
	
}
