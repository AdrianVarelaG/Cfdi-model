package com.beet.model.invoice.model.nomina;

import com.beet.model.invoice.model.ImporteMxn;
import com.beet.model.invoice.model.ValueObject;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoDeduccion;

import lombok.Data;

@Data
public class Deduccion implements ValueObject<Deduccion> {

  private static final long serialVersionUID = 587382275975449663L;
  private CatTipoDeduccion tipoDeduccion;
  private Clave clave;
  private Concepto concepto;
  private ImporteMxn importe;
  
  @Override
  public boolean sameValueAs(Deduccion other) {
    
    return this.equals(other);
  }

}
