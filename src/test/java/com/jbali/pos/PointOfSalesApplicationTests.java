package com.jbali.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.jbali.pos.PointOfSalesApplication;
import com.jbali.util.ServiceResponse;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=PointOfSalesApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT)
public class PointOfSalesApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@After
	public void tearDown(){
		this.restTemplate.delete("/pos/cart/clear", ServiceResponse.class);
	}

	@Test
	public void test_If_Cart_Contains_ABCDABA_Then_Cart_Total_Is_13Dollars25Cents() {

		
		//Arrange
		double expectedCartTotal = 13.25;
		
		//Act
			
		 restTemplate.put("/pos/scanProduct/A", null);
	     restTemplate.put("/pos/scanProduct/B", null);
	     restTemplate.put("/pos/scanProduct/C", null);
	     restTemplate.put("/pos/scanProduct/D", null);
	     restTemplate.put("/pos/scanProduct/A", null);
	     restTemplate.put("/pos/scanProduct/B", null);
	     restTemplate.put("/pos/scanProduct/A", null);
	     
	     ResponseEntity<ServiceResponse> apiResponse =  this.restTemplate.getForEntity("/pos/cartTotal", ServiceResponse.class);
	     
		
		//Assert	
		     
	    assertEquals(expectedCartTotal, (double)apiResponse.getBody().getResponseData(), 0);
	     		
	}

	@Test
	public void test_If_Cart_Contains_CCCCCCC_Then_Cart_Total_Is_6Dollars() {

		
		//Arrange
		double expectedCartTotal = 6;
		
		//Act
		
		 restTemplate.put("/pos/scanProduct/C", null);
	     restTemplate.put("/pos/scanProduct/C", null);
	     restTemplate.put("/pos/scanProduct/C", null);
	     restTemplate.put("/pos/scanProduct/C", null);
	     restTemplate.put("/pos/scanProduct/C", null);
	     restTemplate.put("/pos/scanProduct/C", null);
	     restTemplate.put("/pos/scanProduct/C", null);
	     
	     ResponseEntity<ServiceResponse> apiResponse =  this.restTemplate.getForEntity("/pos/cartTotal", ServiceResponse.class);
		
		//Assert	
		     
	    assertEquals(expectedCartTotal, (double)apiResponse.getBody().getResponseData(), 0);
	     		
	}

	@Test
	public void test_If_Cart_Contains_ABCD_Then_Cart_Total_Is_7Dollars25Cents() {

		
		//Arrange
		double expectedCartTotal = 7.25;
		
		//Act
		
		 restTemplate.put("/pos/scanProduct/A", null);
	     restTemplate.put("/pos/scanProduct/B", null);
	     restTemplate.put("/pos/scanProduct/C", null);
	     restTemplate.put("/pos/scanProduct/D", null);
	     
	     ResponseEntity<ServiceResponse> apiResponse =  this.restTemplate.getForEntity("/pos/cartTotal", ServiceResponse.class);
		
		//Assert	
		     
	    assertEquals(expectedCartTotal, (double)apiResponse.getBody().getResponseData(), 0);
	     		
	}

	
}
