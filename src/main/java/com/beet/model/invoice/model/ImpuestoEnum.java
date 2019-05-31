package com.beet.model.invoice.model;

public enum ImpuestoEnum {
	
	LOCAL("LOCAL"),
	FEDERAL("FEDERAL");
	
	String valor;
	
	ImpuestoEnum(String valor){
		this.valor = valor;
	}
	public String getValor(){
		return this.valor;
	}
}
