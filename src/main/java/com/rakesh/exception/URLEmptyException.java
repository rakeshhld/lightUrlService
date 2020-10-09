package com.rakesh.exception;

public class URLEmptyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String hint;
	
	public URLEmptyException(String message, String hint) {
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
