package com.beet.model.invoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class CatTipoRelacion extends Catalogo implements ValueObject<CatTipoRelacion> {

	private static final long serialVersionUID = 685689093816717355L;

	public CatTipoRelacion(String clave){
		super(clave);
	}
	public CatTipoRelacion(){
		
	}
	
	@Override
	public boolean sameValueAs(CatTipoRelacion other) {
		return this.equals(other);
	}
	@Override
	public String catalogoNombre() {
		return CatalogoEnum.TIPO_RELACION.getKey();
	}

}
