package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class Antiguedad extends ValueObjectBase implements ValueObject<Antiguedad> {

  private static final long serialVersionUID = -5105544852680419644L;
  private final static String EXP = "^P(([1-9][0-9]{0,3})|0)W|P([1-9][0-9]?Y)?(([1-9]|1[012])M)?(0|[1-9]|[12][0-9]|3[01])D$";

  public Antiguedad() {

  }

  public Antiguedad(String antiguedad) {
    this.setValor(antiguedad);
  }

  @Override
  public void setValor(String valor) {
    try {
      super.setValor(valor);
    } catch (ValidacionExcepcion e) {
      throw new ValidacionExcepcion("Error de formato para el campo Antiguedad valor:" + valor);
    }
  }

  @Override
  public boolean sameValueAs(Antiguedad other) {
    return super.equals(other);
  }

  @Override
  public String getPattern() {
    return EXP;
  }

}
