package com.beet.model.invoice.model.nomina;

import com.beet.model.invoice.model.ImporteMxn;
import com.beet.model.invoice.model.ValueObject;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoOtroPago;

import lombok.Data;

@Data
public class OtroPago implements ValueObject<OtroPago> {

  private static final long serialVersionUID = -5368627226528522570L;
  private CatTipoOtroPago tipoOtroPago;
  private Clave clave;
  private Concepto concepto;
  private ImporteMxn importe;
  
  @Override
  public boolean sameValueAs(OtroPago other) {
    return this.equals(other);
  }

}
