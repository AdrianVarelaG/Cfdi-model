package com.beet.model.invoice.model.nomina.catalogos;

import com.beet.model.invoice.model.Catalogo;
import com.beet.model.invoice.model.ValueObject;

public class CatTipoRegimen extends Catalogo implements ValueObject<CatTipoRegimen> {

  private static final long serialVersionUID = -3822438939763881936L;

  public CatTipoRegimen(){
    
  }
  public CatTipoRegimen(String valor) {
    super(valor);
  }
  
  
  @Override
  public boolean sameValueAs(CatTipoRegimen other) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String catalogoNombre() {
    // TODO Auto-generated method stub
    return null;
  }
  

}
