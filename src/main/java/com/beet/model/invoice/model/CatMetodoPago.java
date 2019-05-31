package com.beet.model.invoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class CatMetodoPago extends Catalogo implements ValueObject<CatMetodoPago> {

	private static final long serialVersionUID = 3439580336759163814L;

	public CatMetodoPago(String clave){
		super(clave);
	}
	public CatMetodoPago(){
		
	}
	
	@Override
	public boolean sameValueAs(CatMetodoPago other) {
		return this.equals(other);
	}
	@Override
	public String catalogoNombre() {
		return CatalogoEnum.METODO_PAGO.getKey();
	}

}
