package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class NumeroCertificado extends ValueObjectBase implements ValueObject<NumeroCertificado> {

	private static final long serialVersionUID = -3948095119593304214L;
	private final static String EXP = "^([0-9]){20}$";
	
	public NumeroCertificado(String valor) {
		this.setValor(valor);
	}
	
	public NumeroCertificado(){
		
	}
	
	@Override
	public void setValor(String valor){
		try{
			super.setValor(valor);
		}catch(ValidacionExcepcion e){
			throw new ValidacionExcepcion("Error de formato para el campo NoCertificado valor: " + valor, e);
		}
	}
	
	@Override
	public boolean sameValueAs(NumeroCertificado other) {
		// TODO Auto-generated method stub
		return super.equals(other);
	}

	@Override
	public String getPattern() {
		// TODO Auto-generated method stub
		return EXP;
	}

}
