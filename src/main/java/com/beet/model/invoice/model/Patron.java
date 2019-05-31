package com.beet.model.invoice.model;

import lombok.Data;
import lombok.ToString;
@Data
@ToString(includeFieldNames=false)
public class Patron implements ValueObject<Patron> {

	private static final long serialVersionUID = -22123688721295848L;
	private String patron;
	
	public Patron(){
		
	}
	public Patron(String patron){
		this.patron = patron;
	}
	
	@Override
	public boolean sameValueAs(Patron other) {
		// TODO Auto-generated method stub
		return false;
	}

}
