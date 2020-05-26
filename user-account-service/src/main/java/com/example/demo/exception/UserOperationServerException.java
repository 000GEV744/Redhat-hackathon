package com.example.demo.exception;

public class UserOperationServerException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserOperationServerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserOperationServerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
