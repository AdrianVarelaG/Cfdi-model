package com.beet.model.invoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true, exclude={"fisica", "moral"})
@ToString(callSuper=true, includeFieldNames=false)
public class CatUso extends Catalogo implements ValueObject<CatUso> {

	private static final long serialVersionUID = -4609536618221560457L;
	private boolean fisica;
	private boolean moral;
	
	public CatUso(String clave){
		super(clave);
	}
	public CatUso(){
		
	}
	
	@Override
	public boolean sameValueAs(CatUso other) {
		return equals(other);
	}
	@Override
	public String catalogoNombre() {
		return CatalogoEnum.USO.getKey();
	}
}
