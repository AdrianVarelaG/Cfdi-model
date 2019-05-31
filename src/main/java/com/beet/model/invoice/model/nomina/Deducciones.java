package com.beet.model.invoice.model.nomina;

import java.util.List;

import com.beet.model.invoice.model.ImporteMxn;
import com.beet.model.invoice.model.ValueObject;

import lombok.Data;

@Data
public class Deducciones implements ValueObject<Deducciones> {

  private static final long serialVersionUID = -6933901150461503439L;
  private ImporteMxn totalOtrasDeducciones;
  private ImporteMxn totalImpuestosRetenidos;
  private List<Deduccion> deducciones;
  
  @Override
  public boolean sameValueAs(Deducciones other) {
    // TODO Auto-generated method stub
    return false;
  }

}
