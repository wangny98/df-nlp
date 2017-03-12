package com.ineuron.api;


public class NLPResponse {
	
	String originalText;
	Object value;
	String errorMessage;
	
	public NLPResponse(){}
	
	
	public String getOriginalText() {
		return originalText;
	}


	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	

}
