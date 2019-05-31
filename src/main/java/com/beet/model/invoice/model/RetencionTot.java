package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

import lombok.Data;

import lombok.ToString;

@Data
@ToString( includeFieldNames=false)
public class RetencionTot implements ValueObject<RetencionTot> {

	private static final long serialVersionUID = 3654446462876792873L;
	private CatImpuesto impuesto;
	private Importe importe;
	
	public void validaObligatorios() throws ValidacionExcepcion{
		if(this.impuesto == null)
			throw new ValidacionExcepcion("Debe existir el tipo de impuesto para la retencion");
		if(this.importe == null)
			throw new ValidacionExcepcion("Debe existir el importe del impuesto retenido");
	}

	public void ajustaDecimales(int decimalesMoneda) {
		if(importe != null) importe.setScale(decimalesMoneda);
	}

	@Override
	public boolean sameValueAs(RetencionTot other) {
		// TODO Auto-generated method stub
		return false;
	}

}
