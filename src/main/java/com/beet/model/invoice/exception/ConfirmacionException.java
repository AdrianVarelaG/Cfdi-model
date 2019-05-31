package com.beet.model.invoice.exception;


public class ConfirmacionException extends RuntimeException {
	
	private static final long serialVersionUID = 8837237958545903743L; 
	
	public ConfirmacionException(){
		super();
	}
	public ConfirmacionException(String message){
		super(message);
	}
	public ConfirmacionException(String message, Throwable cause ){
		super(message, cause);
	}
	
	public ConfirmacionException(Throwable cause ){
		super(cause);
	}

}
