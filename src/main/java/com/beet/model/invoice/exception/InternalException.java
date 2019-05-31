package com.beet.model.invoice.exception;

public class InternalException extends RuntimeException {

	private static final long serialVersionUID = 4811327557581254617L;  
	
	public InternalException(){
		super();
	}
	public InternalException(String message){
		super(message);
	}
	public InternalException(String message, Throwable cause ){
		super(message, cause);
	}
	
	public InternalException(Throwable cause ){
		super(cause);
	}
	
}
