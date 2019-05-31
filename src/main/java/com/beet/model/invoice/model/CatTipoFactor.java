package com.beet.model.invoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class CatTipoFactor extends Catalogo implements ValueObject<CatTipoFactor> {

	private static final long serialVersionUID = 2621989421335581801L;

	@Override
	public boolean sameValueAs(CatTipoFactor other) {
		// TODO Auto-generated method stub
		return super.equals(other);
	}
	
	public CatTipoFactor(){
		
	}
	public CatTipoFactor(CatTipoFactor other){
		super(other);
	}
	public CatTipoFactor(String clave){
		super(clave);
	}
	
	public boolean isExento(){
		return this.igual("Exento");
	}
	public boolean isTasa(){
		return this.igual("Tasa");
	}
	public boolean isCuota(){
		return this.igual("Cuota");
	}

	@Override
	public String catalogoNombre() {
		return CatalogoEnum.TIPO_FACTOR.getKey();
	}
	
}
