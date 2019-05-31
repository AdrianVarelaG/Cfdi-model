package com.beet.model.invoice.model.nomina;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.ValueObject;
import com.beet.model.invoice.model.ValueObjectBase;

public class Clave extends ValueObjectBase implements ValueObject<Clave>{

  private static final long serialVersionUID = 3571096074207070447L;
  private final static String EXP = "^[^|]{3,15}$";
  
  public Clave(String valor) {
    try {
      super.setValor(valor);
    } catch (ValidacionExcepcion e) {
      throw new ValidacionExcepcion("Error de formato para el campo Clave valor" + valor, e);
    }
  }
  
  @Override
  public boolean sameValueAs(Clave other) {
    return super.equals(other);
  }

  @Override
  public String getPattern() {
    
    return EXP;
  }

}
