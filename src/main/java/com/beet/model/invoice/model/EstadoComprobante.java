package com.beet.model.invoice.model;

public enum EstadoComprobante {
	
	EMITIDO("EMITIDO"),
	TIMBRADO("TIMBRADO"),
	CANCELADO("CANCELADO"),
	ERROR("ERROR");
	
	EstadoComprobante(String valor){
		this.valor =valor;
	}
	
	private String valor;
	
	public String getValor(){
		return this.valor;
	}
}
