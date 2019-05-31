package com.beet.model.invoice.model.comprobante;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.CatRegimenFiscal;
import com.beet.model.invoice.model.RazonSocial;
import com.beet.model.invoice.model.Rfc;
import com.beet.model.invoice.model.ValueObject;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=false)
public class Emisor implements ValueObject<Emisor> {

	private static final long serialVersionUID = 2807296019346118480L;
	
	private Rfc rfc;
	private RazonSocial nombre;
	private CatRegimenFiscal regimenFiscal;
	
	@Override
	public boolean sameValueAs(Emisor other) {
		return this.equals(other);
	}
	
	public void validaObligatorios()throws ValidacionExcepcion{
		if(this.rfc == null)
			throw new ValidacionExcepcion("El RFC para el emisor es obligatorio");
		if(this.regimenFiscal == null)
			throw new ValidacionExcepcion("El regimen fiscal para el emisor es obligatorio");
	}
	
	public boolean rfcEmisor(String rfc){
		return this.rfc.getvalor().compareToIgnoreCase(rfc) == 0;
	}
	
}
