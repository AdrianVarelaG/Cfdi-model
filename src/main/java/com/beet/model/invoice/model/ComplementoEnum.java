package com.beet.model.invoice.model;


public enum ComplementoEnum {
	NO(""),
	OBRASARTE("OBRASARTE"),
	AEROLINEAS("AEROLÍNEAS"),
	DIVISAS("DIVISAS"),
	IEDU("IEDU"),
	DONAT("DONAT");

	String valor;
	
	ComplementoEnum(String valor){
		this.valor = valor;
	}
	
	public String getValor(){
		return this.valor;
	}
	
}
