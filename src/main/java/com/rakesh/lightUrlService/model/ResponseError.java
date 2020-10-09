package com.rakesh.lightUrlService.model;

public class ResponseError {
	private String message;
	private String hint;
	
	public ResponseError() {
	}
	public ResponseError(String message, String hint) {
		this.message = message;
		this.hint = hint;
	}
	public String getMessage() {
		return message;
	}
	public String getHint() {
		return hint;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	 

}
