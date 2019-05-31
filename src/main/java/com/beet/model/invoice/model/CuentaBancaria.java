package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class CuentaBancaria extends ValueObjectBase implements ValueObject<CuentaBancaria> {

  private static final long serialVersionUID = -8145076087979163318L;
  private final static String EXP = "^[0-9]{10,18}$";

  public CuentaBancaria(String valor) {
    try {
      super.setValor(valor);
    } catch (ValidacionExcepcion e) {
      throw new ValidacionExcepcion("Error de formato para el campo CuentaBancaria valor" + valor, e);
    }
  }

  @Override
  public boolean sameValueAs(CuentaBancaria other) {
    // TODO Auto-generated method stub
    return super.equals(other);
  }

  @Override
  public String getPattern() {
    // TODO Auto-generated method stub
    return EXP;
  }

}
