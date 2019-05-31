package com.beet.model.invoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true, exclude={"ivaTrasladado", "iepsTrasladado", "complemento"})
@ToString(callSuper=true, includeFieldNames=false)
public class CatClave extends Catalogo implements ValueObject<CatClave> {
	private static final long serialVersionUID = -1836609856283819098L;
	
	private Requerido ivaTrasladado;
	private Requerido iepsTrasladado;
	private ComplementoConcepto complemento;
	
	public CatClave(String clave){
		super(clave);
	}
	public CatClave(){
		
	}
	@Override
	public boolean sameValueAs(CatClave other) {
		return super.equals(other);
	}
	@Override
	public String catalogoNombre() {
		return CatalogoEnum.CLAVE.getKey();
	}

}
