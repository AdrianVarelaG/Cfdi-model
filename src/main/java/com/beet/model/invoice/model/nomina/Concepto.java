package com.beet.model.invoice.model.nomina;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.ValueObject;
import com.beet.model.invoice.model.ValueObjectBase;

public class Concepto extends ValueObjectBase implements ValueObject<Concepto> {

  private static final long serialVersionUID = 8112949430953969543L;
  private final static String EXP = "^[^|]{1,100}$";

  public Concepto(String valor) {
    try {
      super.setValor(valor);
    } catch (ValidacionExcepcion e) {
      throw new ValidacionExcepcion("Error de formato para el campo Concepto valor" + valor, e);
    }
  }

  @Override
  public boolean sameValueAs(Concepto other) {
    return super.equals(other);
  }

  @Override
  public String getPattern() {
    // TODO Auto-generated method stub
    return EXP;
  }

}
