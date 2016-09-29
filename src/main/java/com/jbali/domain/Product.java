/**
 * Domain class for Products in the POS system (following the Domain Driven Design model)
 */

package com.jbali.domain;

public class Product {
	
	/**
	 * Stores name of the Product.
	 */
	String name;
	
	/**
	 * Stores unit price of the product.
	 */
	double unitPrice;
	
	/**
	 * Stores price per volume of the product.
	 */
	double volumePrice;
	
	/**
	 * Stores the number of units in one Unit Of Volume supported by a product.
	 */
	int unitsForVolumePrice;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public double getVolumePrice() {
		return volumePrice;
	}
	
	public void setVolumePrice(double volumePrice) {
		this.volumePrice = volumePrice;
	}
	
	public int getItemsForVolumePrice() {
		return unitsForVolumePrice;
	}
	
	public void setItemsForVolumePrice(int itemsForVolumePrice) {
		this.unitsForVolumePrice = itemsForVolumePrice;
	}	

}
