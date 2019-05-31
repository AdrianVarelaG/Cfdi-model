package com.beet.model.invoice.model;

public enum TipoTasa {
	Fijo("Fijo"),
	Rango("Rango");
	
	private String valor;
	
	TipoTasa(String valor){
		this.valor = valor;
	}
	
	public String getValor(){
		return this.valor;
	}
}
