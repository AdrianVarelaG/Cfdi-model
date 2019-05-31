package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class CondicionesPago extends ValueObjectBase implements ValueObject<CondicionesPago> {
	
	private static final long serialVersionUID = 5647062457619206433L;
	private final static String EXP = "^([^|]){1,1000}$";
	
	public CondicionesPago(){
		
	}
	public CondicionesPago(String valor) {
		setValor(valor);
	}
	@Override
	public void setValor(String valor) throws ValidacionExcepcion {
		try{
			super.setValor(valor);
		}catch(ValidacionExcepcion e){
			throw new ValidacionExcepcion("Error de formato para el campo CondicionesDePago v: " + valor, e);
		}
	}
	
	@Override
	public boolean sameValueAs(CondicionesPago other) {
		// TODO Auto-generated method stub
		return super.equals(other);
	}

	@Override
	public String getPattern() {
		// TODO Auto-generated method stub
		return EXP;
	}

}
