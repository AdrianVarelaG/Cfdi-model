package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class NumeroIdentificacion extends ValueObjectBase implements ValueObject<NumeroIdentificacion> {

	private static final long serialVersionUID = 7307440056854451568L;
	private final static String EXP = "^([^|]){1,100}$";
	
	public NumeroIdentificacion(){
		
	}
	@Override
	public void setValor(String valor){
		try{
			super.setValor(valor);
		}catch(ValidacionExcepcion e){
			throw new ValidacionExcepcion("Error de formato para el campo NoIdentificacion valor: " + valor, e);
		}
	}
	
	public NumeroIdentificacion(String valor) {
		this.setValor(valor);
	}
	
	@Override
	public boolean sameValueAs(NumeroIdentificacion other) {
		// TODO Auto-generated method stub
		return super.equals(other);
	}

	@Override
	public String getPattern() {
		// TODO Auto-generated method stub
		return EXP;
	}
}
