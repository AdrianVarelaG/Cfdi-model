package com.beet.model.invoice.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.beet.model.invoice.exception.ValidacionExcepcion;

import lombok.Data;


@Data
public class TrasladoConcepto implements ValueObject<TrasladoConcepto> {
		
	private static final long serialVersionUID = 2753662950192653923L;
	private Cantidad base;
	private CatImpuesto impuesto;
	private CatTipoFactor tipoFactor;
	private Decimal tasaOCuota;
	private Importe importe;
	
	public void validaObligatorios() throws ValidacionExcepcion{
		if(this.base == null)
			throw new ValidacionExcepcion("Debe existir la base para el traslado en el concepto");
		if(this.impuesto == null)
			throw new ValidacionExcepcion("Debe existir el tipo de impuesto para el traslado en el concepto");
		if(this.tipoFactor == null)
			throw new ValidacionExcepcion("Debe existir el tipo factor para el traslado en el concepto");
	}
	
	public boolean isIva(){
		return this.impuesto.isIva();
	}
	
	public boolean isIeps(){
		return this.impuesto.isIeps();
	}
	
	public void valida() throws ValidacionExcepcion{
		this.validaImpuesto();
		this.validaTipoFactor();
		this.validaImporte();
	}
	private void validaImpuesto() throws ValidacionExcepcion{
		if(!this.impuesto.isTraslado())
			throw new ValidacionExcepcion("El impuesto \"" + this.impuesto.concatenado() + "\" no puede incluirse como un impuesto trasladado");
	}
	private void validaTipoFactor() throws ValidacionExcepcion{
		if(this.tipoFactor.isExento()){
			if(this.tasaOCuota!= null)
				throw new ValidacionExcepcion("Para el tipo factor Exento no debe existir el atributo TasaOCuota");
			if(this.importe!= null)
				throw new ValidacionExcepcion("Para el tipo factor Exento no debe existir el atributo Importe");
		}
		else{
			if(this.tasaOCuota== null)
				throw new ValidacionExcepcion("Para el tipo factor: \"" + this.tipoFactor.getClaveSat() + "\" debe existr el atributo TasaOCuota");
			if(this.importe == null)
				throw new ValidacionExcepcion("Para el tipo factor: " + this.tipoFactor.getClaveSat() + " debe existr el atributo Importe");
		}
	}
	private void validaImporte() throws ValidacionExcepcion{
		Decimal minimo;
		Decimal maximo;
		if(this.importe!= null){
			minimo = calculaImporteMinimo();
			maximo = calculaImporteMaximo();
			
			if(this.importe.compara(minimo) < 0 || this.importe.compara(maximo) > 0)
				throw new ValidacionExcepcion("El impuesto trasladado: "  +this.importe.toString() + " fuera de rango minimo: " + minimo.toString() + " maximo " + maximo.toString());
		}
	}
	
	private Decimal calculaImporteMinimo(){
		Decimal ret = new Decimal();
		
		final BigDecimal dos = new BigDecimal("2");
		final BigDecimal uno = new BigDecimal("1");
		final int baseDec = this.base.numeroDecimales();
		final int importeDec = this.importe.numeroDecimales();
		BigDecimal resultado = this.base.getValor().subtract(uno.movePointLeft(baseDec).divide(dos)).multiply(this.tasaOCuota.getValor());
		resultado = resultado.setScale(importeDec, RoundingMode.DOWN);
		ret.setValor(resultado);
		return ret;
	}
	
	private Decimal calculaImporteMaximo(){
		Decimal ret = new Decimal();
		final BigDecimal dos = new BigDecimal("2");
		final BigDecimal uno = new BigDecimal("1");
		final int baseDec = this.base.numeroDecimales();
		final int importeDec = this.importe.numeroDecimales();
		
		BigDecimal resultado = this.base.getValor().add(uno.movePointLeft(baseDec).divide(dos)).subtract(uno.movePointLeft(12)).multiply(this.tasaOCuota.getValor());
		resultado = resultado.setScale(importeDec, RoundingMode.UP);
		ret.setValor(resultado);
		
		return ret;
	}

	public void ajustaDecimales(int decimalesMoneda) {
		
		this.base.ajustaDecimales(decimalesMoneda);
		this.importe.ajustaDecimales(decimalesMoneda);
	}

	@Override
	public boolean sameValueAs(TrasladoConcepto other) {
		return this.equals(other);
	}

}
