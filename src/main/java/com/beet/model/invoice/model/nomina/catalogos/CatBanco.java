package com.beet.model.invoice.model.nomina.catalogos;

import com.beet.model.invoice.model.Catalogo;
import com.beet.model.invoice.model.ValueObject;

public class CatBanco extends Catalogo implements ValueObject<CatBanco> {

  private static final long serialVersionUID = 8595024411017186427L;

  public CatBanco() {

  }

  public CatBanco(String valor) {
    super(valor);
  }

  @Override
  public boolean sameValueAs(CatBanco other) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String catalogoNombre() {
    // TODO Auto-generated method stub
    return null;
  }

}
