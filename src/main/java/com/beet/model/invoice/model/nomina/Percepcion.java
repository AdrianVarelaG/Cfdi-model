package com.beet.model.invoice.model.nomina;

import com.beet.model.invoice.model.ImporteMxn;
import com.beet.model.invoice.model.ValueObject;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoPercepcion;

import lombok.Data;

@Data
public class Percepcion implements ValueObject<Percepcion> {

  private static final long serialVersionUID = 3762925213368783915L;
  private CatTipoPercepcion tipoPercepcion;
  private Clave clave;
  private Concepto concepto;
  private ImporteMxn importeGravado;
  private ImporteMxn importeExecto;
  //TODO: AccionesOTitulos
  //TODO: HoraExtra

  @Override
  public boolean sameValueAs(Percepcion other) {
    return this.equals(other);
  }

}
