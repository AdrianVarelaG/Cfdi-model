package com.beet.model.invoice.model.nomina.catalogos;

import com.beet.model.invoice.model.Catalogo;
import com.beet.model.invoice.model.ValueObject;

public class CatPeriodicidadPago extends Catalogo implements ValueObject<CatPeriodicidadPago> {

  private static final long serialVersionUID = -4936597371672945993L;

  public CatPeriodicidadPago() {

  }

  public CatPeriodicidadPago(String valor) {
    super(valor);
  }

  @Override
  public boolean sameValueAs(CatPeriodicidadPago other) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String catalogoNombre() {
    // TODO Auto-generated method stub
    return null;
  }

}
