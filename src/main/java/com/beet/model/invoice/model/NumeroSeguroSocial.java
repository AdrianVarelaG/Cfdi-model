package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class NumeroSeguroSocial extends ValueObjectBase implements ValueObject<NumeroSeguroSocial> {

  private static final long serialVersionUID = -7952706205781953684L;
  private final static String EXP = "^[0-9]{1,15}$";

  public NumeroSeguroSocial() {

  }

  public NumeroSeguroSocial(String nss) {
    this.setValor(nss);
  }

  @Override
  public void setValor(String valor) {
    try {
      super.setValor(valor);
    } catch (ValidacionExcepcion e) {
      throw new ValidacionExcepcion("Error de formato para el campo numeroSeguroSocial valor:" + valor);
    }
  }

  @Override
  public boolean sameValueAs(NumeroSeguroSocial other) {

    return super.equals(other);
  }

  @Override
  public String getPattern() {

    return EXP;
  }
}
