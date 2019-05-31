package com.beet.model.invoice.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=false)
public class Certificado implements ValueObject<Certificado> {

	private static final long serialVersionUID = 6981319439061515150L;
	private String content64;
	
	public Certificado(String valor){
		this.content64 = valor; 
	}
	public Certificado(){
		
	}
	
	@Override
	public boolean sameValueAs(Certificado other) {
		return this.equals(other);
	}

}
