package com.beet.model.invoice.model.comprobante;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.CatMetodoPago;
import com.beet.model.invoice.model.CatUso;
import com.beet.model.invoice.model.EnumPersonaFiscal;
import com.beet.model.invoice.model.RazonSocial;
import com.beet.model.invoice.model.Rfc;
import com.beet.model.invoice.model.ValueObject;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=false)
public class Receptor implements ValueObject<Receptor> {

	private static final long serialVersionUID = -1286239234652684431L;
	private Rfc rfc;
	private RazonSocial nombre;
	private CatUso uso;
	
	@Override
	public boolean sameValueAs(Receptor other) {
		return this.equals(other);
	}
	
	public void validaObligatorios() throws ValidacionExcepcion{
		if(this.rfc == null)
			throw new ValidacionExcepcion("El rfc del receptor es obligatorio");
		if(this.uso == null)
			throw new ValidacionExcepcion("El uso es obligatorio");
	}
	
	public void valida(CatMetodoPago metodoPago) throws ValidacionExcepcion{
		if(this.rfc.getTipoPersona() == EnumPersonaFiscal.GENERICA){
			if(!this.uso.igual("P01"))
				throw new ValidacionExcepcion("Para el RFC " + rfc.getvalor() + " solo se permite el uso P01 - Por definir" );
			if(this.nombre!= null)
				throw new ValidacionExcepcion("No se debe incluir nombre en el receptor cuando se manda el RFC generico");
			if(!metodoPago.getClaveSat().equals("PUE"))
				throw new ValidacionExcepcion("Para el RFC " + rfc.getvalor() + " solo se permite el metodo de pago PUE - Pago en una sola exhibición" );
		}else if(this.rfc.getTipoPersona() == EnumPersonaFiscal.EXTRANGERO){
			if(!this.uso.igual("P01"))
				throw new ValidacionExcepcion("Para el RFC " + rfc.getvalor() + "solo se permite el uso P01 - Por definir" );
		}else{
			if(this.rfc.getTipoPersona() == EnumPersonaFiscal.FISICA){
				if(!this.uso.isFisica())
					throw new ValidacionExcepcion("El uso " + this.uso.concatenado() + " no es valido para persona fisica");
			}else if(this.rfc.getTipoPersona() == EnumPersonaFiscal.MORAL){
				if(!this.uso.isMoral())
					throw new ValidacionExcepcion("El uso " + this.uso.concatenado() + " no es valido para persona moral");
			}
		}
	}

}
