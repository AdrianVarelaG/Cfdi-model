package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

public class Curp extends ValueObjectBase implements ValueObject<Curp>{

  private static final long serialVersionUID = -2361506290317421985L;
  private final static String EXP = "^[A-Z][AEIOUX][A-Z]{2}[0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[MH]([ABCMTZ]S|[BCJMOT]C|[CNPST]L|[GNQ]T|[GQS]R|C[MH]|[MY]N|[DH]G|NE|VZ|DF|SP)[BCDFGHJ-NP-TV-Z]{3}[0-9A-Z][0-9]$";
  
  public Curp() {
    
  }
  public Curp(String curp) {
    this.setValor(curp);
  }
  
  @Override
  public void setValor(String valor){
      try{
          super.setValor(valor);
      }catch(ValidacionExcepcion e){
          throw new ValidacionExcepcion("Error de formato para el campo Curp valor:" + valor);
      }
  }
  
  @Override
  public boolean sameValueAs(Curp other) {
    
    return super.equals(other);
  }

  @Override
  public String getPattern() {

    return EXP;
  }

}
