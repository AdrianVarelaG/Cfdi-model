package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class Serie extends ValueObjectBase implements ValueObject<Serie> {

	private static final long serialVersionUID = -1327698486883130041L;
	private final static String EXP = "^([^|]){1,25}$";
	
	public Serie(){
		
	}
	
	public Serie(String valor) {
		this.setValor(valor);
	}
	@Override
	public void setValor(String valor){
		try{
			super.setValor(valor);
		}catch(ValidacionExcepcion e){
			throw new ValidacionExcepcion("Error de formato para el campo Serie valor: " + valor, e);
		}
	}

	@Override
	public boolean sameValueAs(Serie other) {
		return super.equals(other);
	}

	@Override
	public String getPattern() {
		return EXP;
	}

}
