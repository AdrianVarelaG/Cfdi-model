package com.beet.model.invoice.model.nomina.catalogos;

import com.beet.model.invoice.model.Catalogo;
import com.beet.model.invoice.model.ValueObject;

public class CatTipoDeduccion extends Catalogo implements ValueObject<CatTipoDeduccion> {

  private static final long serialVersionUID = 3197807492098576367L;

  public CatTipoDeduccion() {

  }

  public CatTipoDeduccion(String valor) {
    super(valor);
  }
  
  @Override
  public boolean sameValueAs(CatTipoDeduccion other) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String catalogoNombre() {
    // TODO Auto-generated method stub
    return null;
  }

}
