package com.beet.model.invoice.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=false)
public class Localidad implements ValueObject<Localidad> {


	private static final long serialVersionUID = -2533141759464372112L;
	private String clave;
	
	public Localidad(){
		
	}
	public Localidad(String localidad){
		this.clave = localidad;
	}
	
	@Override
	public boolean sameValueAs(Localidad other) {
		return this.equals(other);
	}

}
