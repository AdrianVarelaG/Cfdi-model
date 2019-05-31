package com.beet.model.invoice.model;

public enum CatalogoEnum {
	CLAVE							("productoServicio"),
	FORMA_PAGO				("formaPago"),
	IMPUESTO					("impuesto"),
	LUGAR_EXPEDICION	("codigoPostal"),
	METODO_PAGO				("metodoPago"),
	MONEDA						("moneda"),
	PAIS							("pais"),
	REGIMEN_FISCAL		("regimenFiscal"),
	TIPO_COMPROBANTE	("tipoComprobante"),
	TIPO_FACTOR				("tipoFactor"),
	TIPO_RELACION			("tipoRelacion"),
	UNIDAD						("unidad"),
	USO								("uso")
	;
	
	private String key;
	
	CatalogoEnum(String key){
		this.key = key;
	}
	
	public String getKey(){
		return this.key;
	}
	
}
