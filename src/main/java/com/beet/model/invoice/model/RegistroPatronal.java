package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class RegistroPatronal extends ValueObjectBase implements ValueObject<RegistroPatronal> {

  private static final long serialVersionUID = -7033686283021213501L;
  private final static String EXP = "^[^|]{1,20}$";

  public RegistroPatronal() {

  }

  public RegistroPatronal(String rp) {
    this.setValor(rp);
  }

  @Override
  public void setValor(String valor) {
    try {
      super.setValor(valor);
    } catch (ValidacionExcepcion e) {
      throw new ValidacionExcepcion("Error de formato para el campo Registro Patronal valor:" + valor);
    }
  }

  @Override
  public boolean sameValueAs(RegistroPatronal other) {
    return super.equals(other);
  }

  @Override
  public String getPattern() {
    return EXP;
  }

}
