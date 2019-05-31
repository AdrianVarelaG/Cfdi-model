package com.beet.model.invoice.model.nomina.catalogos;

import com.beet.model.invoice.model.Catalogo;
import com.beet.model.invoice.model.ValueObject;

public class CatTipoJornada extends Catalogo implements ValueObject<CatTipoJornada> {

  private static final long serialVersionUID = -48802444376591421L;

  public CatTipoJornada() {

  }

  public CatTipoJornada(String valor) {
    super(valor);
  }

  @Override
  public boolean sameValueAs(CatTipoJornada other) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String catalogoNombre() {
    // TODO Auto-generated method stub
    return null;
  }

}
