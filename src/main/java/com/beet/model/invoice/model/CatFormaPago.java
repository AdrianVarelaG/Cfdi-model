package com.beet.model.invoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true, exclude={
		"bancarizado", "numeroOperacion", "ordenante", 
		"beneficiario", "tipoCadenaPago"})
@ToString(callSuper=true, includeFieldNames=false)
public class CatFormaPago extends Catalogo implements ValueObject<CatFormaPago> {

	private static final long serialVersionUID = -6870167351004264197L;
	
	private Requerido bancarizado;
	private Requerido numeroOperacion;
	private ReferenciaCuenta ordenante;
	private ReferenciaCuenta beneficiario;
	private Requerido tipoCadenaPago;
	
	public CatFormaPago(String clave){
		super(clave);
	}
	public CatFormaPago(){
		
	}
	@Override
	public boolean sameValueAs(CatFormaPago other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
	@Override
	public String catalogoNombre() {
		return CatalogoEnum.FORMA_PAGO.getKey();
	}

}
