package com.beet.model.invoice.model;

import lombok.Data;

@Data
public class CatTasaCuota implements ValueObject<CatTasaCuota> {

	private static final long serialVersionUID = -4394595460262637578L;
	private TipoTasa tipoTasa;
	private Decimal minimo;
	private Decimal maximo;
	private boolean traslado;
	private boolean retencion; 
	
	
	@Override
	public boolean sameValueAs(CatTasaCuota other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
	
	public boolean valorValido(Decimal valor){
		boolean ret = false;
		
		if(tipoTasa == TipoTasa.Fijo)
			ret = this.maximo != null && valor.compara(maximo) == 0;
		else
			ret = this.maximo != null 						&& this.minimo != null 							&& 
						valor.compara(this.minimo) >= 0 && valor.compara(this.maximo) <= 0;
			
		return ret;
	}

}
