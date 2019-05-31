package com.beet.model.invoice.model.nomina;

import java.util.List;

import com.beet.model.invoice.model.ImporteMxn;
import com.beet.model.invoice.model.ValueObject;

import lombok.Data;

@Data
public class Percepciones implements ValueObject<Percepciones> {

  private static final long serialVersionUID = 8402064300402537745L;
  private ImporteMxn totalSueldo;
  private ImporteMxn totalSeparacionIndemnizacion;
  private ImporteMxn totalJubilacionPensionRetiro;
  private ImporteMxn totalGravado;
  private ImporteMxn totalExento;
  private List<Percepcion> percepciones;
  //TODO: JubilacionPensionRetiro
  //TODO: separacion Indemnizacion

  @Override
  public boolean sameValueAs(Percepciones other) {
    // TODO Auto-generated method stub
    return this.equals(other);
  }

}
