package com.beet.model.invoice.model;


import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=false)
public class Estado implements ValueObject<Estado> {

	private static final long serialVersionUID = 7319017088411675689L;
	private String clave;
	
	public Estado(){
		
	}
	public Estado(String clave){
		this.clave = clave;
	}
	
	@Override
	public boolean sameValueAs(Estado other) {
		return this.equals(other);
	}

}
