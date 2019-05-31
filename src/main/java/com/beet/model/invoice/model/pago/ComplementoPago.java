package com.beet.model.invoice.model.pago;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.ValueObject;

public class ComplementoPago implements ValueObject<ComplementoPago> {

  private static final long serialVersionUID = -7425744227891888493L;

  public void validaObligatorios() throws ValidacionExcepcion{
		
	}

  @Override
  public boolean sameValueAs(ComplementoPago other) {
    // TODO Auto-generated method stub
    return false;
  }

}
