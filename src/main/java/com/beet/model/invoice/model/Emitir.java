package com.beet.model.invoice.model;

import lombok.Data;
import lombok.ToString;
@Data
@ToString(exclude={"password"}, includeFieldNames=false)
public class Emitir implements ValueObject<Emitir> {

	private static final long serialVersionUID = -3237310255605848078L;
	
	private String password;
	private String correlacion;
	
	@Override
	public boolean sameValueAs(Emitir other) {
		// TODO Auto-generated method stub
		return false;
	}

}
