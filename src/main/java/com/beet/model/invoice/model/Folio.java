package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class Folio extends ValueObjectBase implements ValueObject<Folio> {

	private static final long serialVersionUID = 8872019824908559749L;
	private final static String EXP = "^([^|]){1,40}$";
	
	public Folio(){
		
	}
	public Folio(String valor) {
		setValor(valor);
	}
	@Override
	public void setValor(String valor) throws ValidacionExcepcion{
		try{
			super.setValor(valor);
		}catch(ValidacionExcepcion e){
			throw new ValidacionExcepcion("Error de formato para el campo Folio valor:" + valor, e);
		}
	}
	
	@Override
	public boolean sameValueAs(Folio other) {
		return super.equals(other);
	}

	@Override
	public String getPattern() {
		// TODO Auto-generated method stub
		return EXP;
	}

}
