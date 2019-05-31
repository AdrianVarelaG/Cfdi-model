package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

import lombok.Data;
import lombok.ToString;


@Data
@ToString(includeFieldNames=false)
public class TrasladoTot implements ValueObject<TrasladoTot> {
	
	private static final long serialVersionUID = 5981354870145734968L;
	private CatImpuesto impuesto;
	private CatTipoFactor tipoFactor;
	private Decimal tasaOCuota;
	private Importe importe;
	
	public void validaObligatorios() throws ValidacionExcepcion{
		if(this.impuesto == null)
			throw new ValidacionExcepcion("Debe existir el tipo de impuesto para el traslado");
		if(this.tipoFactor == null)
			throw new ValidacionExcepcion("Debe existir el tipo factor para el traslado");
		if(this.tasaOCuota == null)
			throw new ValidacionExcepcion("Debe existir la tasaOCuota para el traslado");
		if(this.importe == null)
			throw new ValidacionExcepcion("Debe existir el importe para el traslado");
	}

	public void ajustaDecimales(int decimalesMoneda) {
		if(this.importe != null) this.importe.setScale(decimalesMoneda);
	}

	@Override
	public boolean sameValueAs(TrasladoTot other) {
		return this.equals(other);
	}

}
