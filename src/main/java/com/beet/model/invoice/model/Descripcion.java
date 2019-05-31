package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class Descripcion extends ValueObjectBase implements ValueObject<Descripcion> {

	private static final long serialVersionUID = -7220013172661889437L;
	private final static String EXP = "^([^|]){1,1000}$";

	public Descripcion(){
		
	}
	public Descripcion(String valor) {
		this.setValor(valor);
	}
	
	@Override
	public void setValor(String valor){
		try{
			super.setValor(valor);
		}catch(ValidacionExcepcion e){
			throw new ValidacionExcepcion("Error de formato para el campo Descripcion valor: "+ valor, e);
		}
	}
	
	@Override
	public boolean sameValueAs(Descripcion other) {
		// TODO Auto-generated method stub
		return super.equals(other);
	}

	@Override
	public String getPattern() {
		// TODO Auto-generated method stub
		return EXP;
	}

}
