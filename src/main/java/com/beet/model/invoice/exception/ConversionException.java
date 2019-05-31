package com.beet.model.invoice.exception;

public class ConversionException extends RuntimeException {
  
  private static final long serialVersionUID = -656442557975641094L;

    public ConversionException(){
        super();
    }
    public ConversionException(String message){
        super(message);
    }
    public ConversionException(String message, Throwable cause ){
        super(message, cause);
    }
    
    public ConversionException(Throwable cause ){
        super(cause);
    }
}
