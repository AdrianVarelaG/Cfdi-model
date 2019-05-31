package com.beet.model.invoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class CatRegimenFiscal extends Catalogo implements ValueObject<CatRegimenFiscal> {

	private static final long serialVersionUID = -3724348694947503437L;

	public CatRegimenFiscal(String clave){
		super(clave);
	}
	public CatRegimenFiscal(){
		
	}
	@Override
	public boolean sameValueAs(CatRegimenFiscal other) {
		return super.equals(other);
	}
	@Override
	public String catalogoNombre() {
		return CatalogoEnum.REGIMEN_FISCAL.getKey();
	}

}
