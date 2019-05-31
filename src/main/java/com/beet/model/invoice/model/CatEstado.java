package com.beet.model.invoice.model;

public class CatEstado extends Catalogo implements ValueObject<CatEstado> {

  private static final long serialVersionUID = 7182762664403436333L;

  public CatEstado() {

  }

  public CatEstado(String valor) {
    super(valor);
  }

  @Override
  public boolean sameValueAs(CatEstado other) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String catalogoNombre() {
    // TODO Auto-generated method stub
    return null;
  }

}
