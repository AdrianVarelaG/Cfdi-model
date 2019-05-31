package com.beet.model.invoice.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=false)
public class ReferenciaCuenta implements ValueObject<ReferenciaCuenta> {

	private static final long serialVersionUID = -1484665387823628557L;
	private Requerido rfc;
	private Requerido cuenta;
	private Patron patron;
	
	@Override
	public boolean sameValueAs(ReferenciaCuenta other) {
		// TODO Auto-generated method stub
		return false;
	}

}
