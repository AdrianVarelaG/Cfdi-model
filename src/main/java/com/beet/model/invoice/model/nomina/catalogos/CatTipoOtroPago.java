package com.beet.model.invoice.model.nomina.catalogos;

import com.beet.model.invoice.model.Catalogo;
import com.beet.model.invoice.model.ValueObject;

public class CatTipoOtroPago extends Catalogo implements ValueObject<CatTipoOtroPago> {

  private static final long serialVersionUID = 5130672603479345334L;

  public CatTipoOtroPago() {

  }

  public CatTipoOtroPago(String valor) {
    super(valor);
  }

  @Override
  public boolean sameValueAs(CatTipoOtroPago other) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String catalogoNombre() {
    // TODO Auto-generated method stub
    return null;
  }

}
