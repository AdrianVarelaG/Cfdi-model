package com.beet.model.invoice.model.nomina.catalogos;

import com.beet.model.invoice.model.Catalogo;
import com.beet.model.invoice.model.ValueObject;

public class CatTipoPercepcion extends Catalogo implements ValueObject<CatTipoPercepcion> {

  private static final long serialVersionUID = 2399131034772753543L;

  public CatTipoPercepcion() {

  }

  public CatTipoPercepcion(String valor) {
    super(valor);
  }

  @Override
  public boolean sameValueAs(CatTipoPercepcion other) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String catalogoNombre() {
    // TODO Auto-generated method stub
    return null;
  }

}
