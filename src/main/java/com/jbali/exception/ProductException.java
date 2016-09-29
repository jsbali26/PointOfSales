/**
 * A CEHCKED EXCEPTION class for Product Domain
 */
package com.jbali.exception;

public class ProductException extends Exception{

	  
	  public ERRORCODE errorCode;
	
	  
	  public ProductException(ERRORCODE errorCode) {   
	        this.errorCode = errorCode;
	  }	  
	  
	  
	  public static enum ERRORCODE {
		  
	        SCANNED_PRODUCT_NOT_VALID("Invalid Product"),
		    PRODUCT_PRICES_NOT_AVAILABLE("Product prices not available");

	        private final String value;

	        ERRORCODE(String v) {
	            value = v;
	        }

	        public String value() {
	            return value;
	        }
	    }
	  
}
