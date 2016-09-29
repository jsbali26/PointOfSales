/**
 * REST API Controller Class
 * with all methods exposed as various supported operation
 * of the API
 */

package com.jbali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jbali.domain.service.ProductService;
import com.jbali.exception.ProductException;
import com.jbali.util.PosConstants;
import com.jbali.util.ServiceResponse;

@RestController
@RequestMapping(value="pos")
public class PosController {
	
	@Autowired
	ProductService posService;
	
	/**
	 * REST operation to scan a product and add it in to the cart.
	 * @param productName
	 * @return: ServiceResponse object converted into JSON format containing Status Code and Status Message
	 */
	@RequestMapping(value = "scanProduct/{productName}", method = RequestMethod.PUT, produces = "application/json")	
	public ServiceResponse scanProduct(@PathVariable String productName){		
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try{			 	
			
			posService.addProduct(productName);
				
			serviceResponse.setStatus(PosConstants.SERVICE_RESPONSE_STATUS_CODE_OK);
			serviceResponse.setMessage(PosConstants.PRODUCT_SCAN_API_SUCCESSFUL_MESSAGE);
			 
		}
		catch(ProductException ex) { 
			
			serviceResponse.setStatus(PosConstants.SERVICE_RESPOSNE_STATUS_CODE_ERROR);
			serviceResponse.setMessage(ex.errorCode.value());	
			
		}
		
		return serviceResponse;
	}
	
	/**
	 * REST operation to calculate the total price of the cart. 
	 * @return
	 */
	@RequestMapping(value = "cartTotal", method = RequestMethod.GET, produces = "application/json")	
	public ServiceResponse calculateTotal(){
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		double totalCartPrice=0;
		
		try {
			
			totalCartPrice = posService.calculateCartPrice();
			
		} 
		catch (ProductException ex) {
			
			serviceResponse.setStatus(PosConstants.SERVICE_RESPOSNE_STATUS_CODE_ERROR);
			serviceResponse.setMessage(ex.errorCode.value());
			return serviceResponse;
						
		}	
		
		
		serviceResponse.setStatus(PosConstants.SERVICE_RESPONSE_STATUS_CODE_OK);
		serviceResponse.setMessage(PosConstants.CALCULATE_TOTAL_API_SUCCESS_MESSAGE + totalCartPrice);
		serviceResponse.setResponseData(totalCartPrice);
		
		return serviceResponse;
	}

	
	/**
	 * Method for Integration Testing ONLY.
	 * Needed to clear the cart and work on a fresh cart after every test case
	 * @return
	 */
	@RequestMapping(value = "cart/clear", method = RequestMethod.DELETE, produces = "application/json")
	public ServiceResponse clearCart(){
		
		posService.clearCart();
		
		ServiceResponse serviceResponse = new ServiceResponse();		
		serviceResponse.setStatus(PosConstants.SERVICE_RESPONSE_STATUS_CODE_OK);
		serviceResponse.setMessage(PosConstants.CART_DELETION_SUCCESS_MESSAGE);
		
		return serviceResponse;
		
	}
	
}
