package com.beet.model.invoice.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true, exclude={"decimales", "variacion"})
@ToString(callSuper=true, includeFieldNames=false)
public class CatMoneda extends Catalogo implements ValueObject<CatMoneda> {

	private static final long serialVersionUID = -2239193931127587607L;
	public static final String NO_MONEDA = "XXX";
	private int decimales;
	private BigDecimal variacion;
	
	public CatMoneda(String clave){
		super(clave);
		decimales = -1;
	}
	public CatMoneda(){
		decimales = -1;
	}
	
	@Override
	public boolean sameValueAs(CatMoneda other) {
		
		return this.equals(other);
	}

	public boolean requiereTipoCambio(){
	 boolean ret;
	 ret = !(super.igual("MXN") || super.igual(NO_MONEDA)); 
	 return ret;
	}
	@Override
	public void setClaveSat(String valor){
		if(valor.equals(NO_MONEDA))
			this.decimales = 0;
		super.setClaveSat(valor);
	}
	@Override
	public String catalogoNombre() {
		return CatalogoEnum.MONEDA.getKey();
	}
	
}
