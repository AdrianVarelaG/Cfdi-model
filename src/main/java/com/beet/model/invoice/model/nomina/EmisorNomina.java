package com.beet.model.invoice.model.nomina;

import com.beet.model.invoice.model.Curp;
import com.beet.model.invoice.model.RegistroPatronal;
import com.beet.model.invoice.model.Rfc;
import com.beet.model.invoice.model.ValueObject;

import lombok.Data;

@Data
public class EmisorNomina implements ValueObject<EmisorNomina>{

  private static final long serialVersionUID = -1812240812034118400L;
  private Curp curp;
  private RegistroPatronal registroPatronal;
  private Rfc rfcPatronOrigen;
  
  //TODO EntidadSNCF
  
  @Override
  public boolean sameValueAs(EmisorNomina other) {
    
    return false;
  }

}
