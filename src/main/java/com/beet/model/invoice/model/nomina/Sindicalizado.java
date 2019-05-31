package com.beet.model.invoice.model.nomina;

public enum Sindicalizado {
  SI("Sí"),
  NO("No");
  
  String valor;
  
  Sindicalizado(String valor){
      this.valor = valor;
  }
  
  public String getValor(){
      return this.valor;
  }
}
