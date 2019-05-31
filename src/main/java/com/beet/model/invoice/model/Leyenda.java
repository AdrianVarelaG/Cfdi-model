package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class Leyenda extends ValueObjectBase implements ValueObject<Leyenda> {
	
	private static final long serialVersionUID = 830583670098435808L;
	private final static String EXP = "^([^|]){1,150}$";

	public Leyenda(String valor){
		this.setValor(valor);
	}
	
	@Override
	public void setValor(String valor){
		try{
			super.setValor(valor);
		}catch(ValidacionExcepcion e){
			throw new ValidacionExcepcion("Error de formato para el campo Leyenda valor: " + valor, e);
		}
	}
	
	@Override
	public boolean sameValueAs(Leyenda other) {
		return super.equals(other);
	}

	@Override
	public String getPattern() {
		return EXP;
	}

}
