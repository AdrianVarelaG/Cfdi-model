package com.beet.model.invoice.model;

import java.util.Base64;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=false)
public class Sello implements ValueObject<Sello> {

	private static final long serialVersionUID = -75218121500384675L;
	private String sello;
	
	public Sello(){
		
	}
	public Sello(byte[] sello){
		this.sello = Base64.getEncoder().encodeToString(sello);
	}
	
	public Sello(String sello) {
		this.sello = sello;
	}
	@Override
	public boolean sameValueAs(Sello other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}

}
