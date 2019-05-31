package com.beet.model.invoice.model;

import lombok.Data;

@Data
public class ComplementoConcepto implements ValueObject<ComplementoConcepto> {

	private static final long serialVersionUID = -3642258971088307340L;
	private Requerido requerido;
	private ComplementoEnum complemento;
	@Override
	public boolean sameValueAs(ComplementoConcepto other) {
		return this.equals(other);
	}

}
