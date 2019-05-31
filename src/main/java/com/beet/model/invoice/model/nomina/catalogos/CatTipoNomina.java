package com.beet.model.invoice.model.nomina.catalogos;

import com.beet.model.invoice.model.Catalogo;
import com.beet.model.invoice.model.ValueObject;

public class CatTipoNomina extends Catalogo implements ValueObject<CatTipoNomina> {

  private static final long serialVersionUID = -7370854858895911501L;

  public CatTipoNomina(String clave) {
    super(clave);
  }

  public CatTipoNomina() {
    
  }

  @Override
  public boolean sameValueAs(CatTipoNomina other) {
    return super.equals(other);
  }

  @Override
  public String catalogoNombre() {
    // TODO Auto-generated method stub
    return null;
  }

}
