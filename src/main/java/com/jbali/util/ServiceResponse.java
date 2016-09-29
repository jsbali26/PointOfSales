/**
 * The REST API would return this object in JSON format. 
 */

package com.jbali.util;

public class ServiceResponse {
	
	String status;
	
	String message;
	
	double responseData;

	public double getResponseData() {
		return responseData;
	}

	public void setResponseData(double responseData) {
		this.responseData = responseData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
