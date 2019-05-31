package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class Version extends ValueObjectBase implements ValueObject<Version> {

	private static final long serialVersionUID = -8164878495771670333L;
	private final static String EXP = "^(\\d\\.?){1,2}(\\d)?$";

	public Version(){
		
	}
	
	public Version(String valor){
		this.setValor(valor);
	}
	@Override
	public void setValor(String valor){
		try{
			super.setValor(valor);
		}catch(ValidacionExcepcion e){
			throw new ValidacionExcepcion("Error de formato para el campo Version valor: " + valor, e);
		}
	}
	
	@Override
	public boolean sameValueAs(Version other) {
		return super.equals(other);
	}

	@Override
	public String getPattern() {
		return EXP;
	}

}
