package com.beet.model.invoice.model.nomina.catalogos;

import com.beet.model.invoice.model.Catalogo;
import com.beet.model.invoice.model.ValueObject;

public class CatTipoContrato extends Catalogo implements ValueObject<CatTipoContrato> {

  private static final long serialVersionUID = 41210740057468306L;

  public CatTipoContrato() {

  }

  public CatTipoContrato(String valor) {
    super(valor);
  }

  @Override
  public boolean sameValueAs(CatTipoContrato other) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String catalogoNombre() {
    // TODO Auto-generated method stub
    return null;
  }

}
