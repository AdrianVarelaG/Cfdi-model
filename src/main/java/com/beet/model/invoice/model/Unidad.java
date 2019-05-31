package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class Unidad extends ValueObjectBase implements ValueObject<Unidad> {

	private static final long serialVersionUID = -4075466758676215117L;
	private final static String EXP = "^([^|]){1,20}$";
	
	public Unidad(){
		
	}
	
	public Unidad(String valor) {
		this.setValor(valor);
	}
	@Override
	public void setValor(String valor) throws ValidacionExcepcion{
		try{
			super.setValor(valor);
		}catch(ValidacionExcepcion e){
			
			throw new ValidacionExcepcion("Error de formato para el campo Unidad valor: " + valor, e);
		}
	}
	
	@Override
	public boolean sameValueAs(Unidad other) {
		// TODO Auto-generated method stub
		return super.equals(other);
	}

	@Override
	public String getPattern() {
		// TODO Auto-generated method stub
		return EXP;
	}

}
