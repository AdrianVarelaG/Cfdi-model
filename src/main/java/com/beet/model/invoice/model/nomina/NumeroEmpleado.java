package com.beet.model.invoice.model.nomina;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.ValueObject;
import com.beet.model.invoice.model.ValueObjectBase;

public class NumeroEmpleado extends ValueObjectBase implements ValueObject<NumeroEmpleado> {

  private static final long serialVersionUID = -1942310799522749086L;
  private final static String EXP = "^[^|]{1,15}$";

  public NumeroEmpleado(String valor) {
    try {
      super.setValor(valor);
    } catch (ValidacionExcepcion e) {
      throw new ValidacionExcepcion("Error de formato para el campo NumeroEmpleado valor" + valor, e);
    }
  }

  @Override
  public boolean sameValueAs(NumeroEmpleado other) {
    return super.equals(other);
  }

  @Override
  public String getPattern() {
    // TODO Auto-generated method stub
    return EXP;
  }

}
