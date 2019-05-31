package com.beet.model.invoice.model.nomina.catalogos;

import com.beet.model.invoice.model.Catalogo;
import com.beet.model.invoice.model.ValueObject;

public class CatRiesgoPuesto extends Catalogo implements ValueObject<CatRiesgoPuesto> {

  private static final long serialVersionUID = 4563440637514570330L;

  public CatRiesgoPuesto() {

  }

  public CatRiesgoPuesto(String valor) {
    super(valor);
  }

  @Override
  public boolean sameValueAs(CatRiesgoPuesto other) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String catalogoNombre() {
    // TODO Auto-generated method stub
    return null;
  }

}
