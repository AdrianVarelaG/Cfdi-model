package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class Confirmacion extends ValueObjectBase implements ValueObject<Confirmacion> {

	private static final long serialVersionUID = -1451774897528639195L;
	private final static String EXP = "^([0-9a-zA-Z]){5}$";
	
	public Confirmacion(String valor) {
		try{
			super.setValor(valor);
		}catch(ValidacionExcepcion e){
			throw new ValidacionExcepcion("Error de formato para el campo Confirmacion valor" + valor, e );
		}
	}
	
	@Override
	public boolean sameValueAs(Confirmacion other) {
		// TODO Auto-generated method stub
		return super.equals(other);
	}

	@Override
	public String getPattern() {
		// TODO Auto-generated method stub
		return EXP;
	}

}
