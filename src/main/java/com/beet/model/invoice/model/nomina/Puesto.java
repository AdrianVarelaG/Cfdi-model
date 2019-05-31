package com.beet.model.invoice.model.nomina;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.ValueObject;
import com.beet.model.invoice.model.ValueObjectBase;

public class Puesto extends ValueObjectBase implements ValueObject<Puesto> {

  private static final long serialVersionUID = -5494897899074350649L;
  private final static String EXP = "^[^|]{1,100}$";

  public Puesto(String valor) {
    try {
      super.setValor(valor);
    } catch (ValidacionExcepcion e) {
      throw new ValidacionExcepcion("Error de formato para el campo Puesto valor" + valor, e);
    }
  }

  @Override
  public boolean sameValueAs(Puesto other) {
    // TODO Auto-generated method stub
    return super.equals(other);
  }

  @Override
  public String getPattern() {
    // TODO Auto-generated method stub
    return EXP;
  }

}
