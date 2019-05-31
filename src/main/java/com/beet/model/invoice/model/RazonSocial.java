package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class RazonSocial extends ValueObjectBase implements ValueObject<RazonSocial> {

	private static final long serialVersionUID = -2201536909044095001L;
	private final static String EXP = "^([^|]){1,254}$";

	public RazonSocial(){
		
	}
	public RazonSocial(String valor) {
		setValor(valor);
	}
	@Override
	public void setValor(String valor){
		try{
			super.setValor(valor);
		}catch(ValidacionExcepcion e){
			throw new ValidacionExcepcion("Error de formato para el campo RazonSocial valor:" + valor);
		}
	}
	
	@Override
	public boolean sameValueAs(RazonSocial other) {
		// TODO Auto-generated method stub
		return super.equals(other);
	}

	@Override
	public String getPattern() {
		// TODO Auto-generated method stub
		return EXP;
	}

}
