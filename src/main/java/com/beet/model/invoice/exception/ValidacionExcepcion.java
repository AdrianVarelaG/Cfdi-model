package com.beet.model.invoice.exception;


public class ValidacionExcepcion extends RuntimeException {

	private static final long serialVersionUID = 2183619299900358226L;
	
	public ValidacionExcepcion(){
		super();
	}
	public ValidacionExcepcion(String message){
		super(message);
	}
	public ValidacionExcepcion(String message, Throwable cause ){
		super(message, cause);
	}
	
	public ValidacionExcepcion(Throwable cause ){
		super(cause);
	}
}
