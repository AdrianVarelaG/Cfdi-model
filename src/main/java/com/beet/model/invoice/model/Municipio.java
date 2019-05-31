package com.beet.model.invoice.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=false)
public class Municipio implements ValueObject<Municipio> {

	private static final long serialVersionUID = -8649205499990596401L;
	private String clave;
	
	public Municipio(){
		
	}
	public Municipio(String clave){
		this.clave = clave;
	}
	
	@Override
	public boolean sameValueAs(Municipio other) {
		
		return this.equals(other);
	}

}
