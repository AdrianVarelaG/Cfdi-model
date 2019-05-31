package com.beet.model.invoice.model.nomina;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.ValueObject;
import com.beet.model.invoice.model.ValueObjectBase;

public class Departamento extends ValueObjectBase implements ValueObject<Departamento> {

  private static final long serialVersionUID = 5716709935935237666L;
  private final static String EXP = "^[^|]{1,100}$";

  public Departamento(String valor) {
    try {
      super.setValor(valor);
    } catch (ValidacionExcepcion e) {
      throw new ValidacionExcepcion("Error de formato para el campo Departamento valor" + valor, e);
    }
  }

  @Override
  public boolean sameValueAs(Departamento other) {
    return super.equals(other);
  }

  @Override
  public String getPattern() {
    return EXP;
  }

}
