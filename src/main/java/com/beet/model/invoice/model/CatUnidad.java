package com.beet.model.invoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true,  exclude={"simbolo"})
@ToString(callSuper=true, includeFieldNames=false)
public class CatUnidad extends Catalogo implements ValueObject<CatUnidad> {

	private static final long serialVersionUID = 6011631016987602895L;
	private String simbolo;
	
	public CatUnidad(String clave){
		super(clave);
	}
	public CatUnidad(){

	}
	
	@Override
	public boolean sameValueAs(CatUnidad other) {
		return this.equals(other);
	}
	@Override
	public String catalogoNombre() {
		return CatalogoEnum.UNIDAD.getKey();
	}
	
}
