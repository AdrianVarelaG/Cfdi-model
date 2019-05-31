package com.beet.model.invoice.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true, exclude={"estado","municipio","localidad"})
@ToString(callSuper=true, includeFieldNames=false)
public class CatLugarExpedicion extends Catalogo implements ValueObject<CatLugarExpedicion> {

	private static final long serialVersionUID = -3462494676375931563L;
	
	private Estado estado;
	
	private Municipio municipio;
	
	private Localidad localidad;
	
	public CatLugarExpedicion(String clave){
		super(clave);
	}
	public CatLugarExpedicion(){
		
	}
	
	@Override
	public boolean sameValueAs(CatLugarExpedicion other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
	@Override
	public String catalogoNombre() {
		return CatalogoEnum.LUGAR_EXPEDICION.getKey();
	}

}
