package com.beet.model.invoice.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.beet.model.invoice.exception.ValidacionExcepcion;

import lombok.Data;

@Data
public class RetencionConcepto implements ValueObject<RetencionConcepto> {
	
	private static final long serialVersionUID = -5580469196842709202L;
	
	private Cantidad base;
	private CatImpuesto impuesto;
	private CatTipoFactor tipoFactor;
	private Decimal tasaOCuota;
	private Importe importe;
	
	public void validaObligatorios() throws ValidacionExcepcion{
		if(this.base == null)
			throw new ValidacionExcepcion("Debe existir la base para la retencion en el concepto");
		if(this.impuesto == null)
			throw new ValidacionExcepcion("Debe existir el tipo de impuesto para la retencion en el concepto");
		if(this.tipoFactor == null)
			throw new ValidacionExcepcion("Debe existir el tipo factor para la retencion en el concepto");
		if(this.tasaOCuota == null)
			throw new ValidacionExcepcion("Debe existir la tasa o cuota para la retencion en el concepto");
		if(this.importe == null)
			throw new ValidacionExcepcion("Debe existir el importe para la retencion en el concepto");
	}
	
	public void valida(CatMoneda moneda) throws ValidacionExcepcion{
		this.validaImpuesto();
		this.validaBase(moneda);
		this.validaTipoFactor();
		this.validaImporte();
	}
	private void validaImpuesto() throws ValidacionExcepcion{
		if(!this.impuesto.isRetencion())
			throw new ValidacionExcepcion("El impuesto \"" + this.impuesto.concatenado() + "\" no puede incluirse como un impuesto retenido");
	}
	private void validaBase(CatMoneda moneda) throws ValidacionExcepcion{
		
		if(this.tipoFactor.isTasa()){
			try{
				this.base.setScale(moneda.getDecimales());
			}catch(ArithmeticException e){
				throw new ValidacionExcepcion("Para el tipoFactor: " + this.tipoFactor.getClaveSat() + " la base " + this.base.toString() + " solo puede tener la cantidad de decimales de la moneda " + moneda.toString() );
			}	
		}
	}
	private void validaTipoFactor() throws ValidacionExcepcion{
		if(this.tipoFactor.isExento())
			throw new ValidacionExcepcion("Para los impuestos retenidos el tipo factor Exento no es valido");
	}
	private void validaImporte() throws ValidacionExcepcion{
		Decimal minimo;
		Decimal maximo;
	
		minimo = calculaImporteMinimo();
		maximo = calculaImporteMaximo();
			
		if(this.importe.compara(minimo) < 0 || this.importe.compara(maximo) > 0)
			throw new ValidacionExcepcion("El impuesto retenido: "  +this.importe.toString() + " fuera de rango minimo: " + minimo.toString() + " maximo " + maximo.toString());
		
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
		
		if(this.tipoFactor.isTasa()){
			this.base.setScale(decimalesMoneda);
		}
	}

	@Override
	public boolean sameValueAs(RetencionConcepto other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
}
