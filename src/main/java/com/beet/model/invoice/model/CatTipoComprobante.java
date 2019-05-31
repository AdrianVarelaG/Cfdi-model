package com.beet.model.invoice.model;

import java.util.Map;

import com.beet.model.invoice.exception.ConfirmacionException;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true, exclude={"valorMaximo"})
@ToString(callSuper=true, includeFieldNames=false)
public class CatTipoComprobante extends Catalogo implements ValueObject<CatTipoComprobante> {

	private static final long serialVersionUID = -1924599245011998420L;
	public static final String VM = "ValorMaximo"; 
	private Map<String, Decimal> valorMaximo;
	
	public CatTipoComprobante(String clave){
		super(clave);
	}
	
	public CatTipoComprobante(){
		
	}
	
	@Override
	public boolean sameValueAs(CatTipoComprobante other) {
		return this.equals(other);
	}
	
	public void validaValorMaximo(Decimal valor) throws ConfirmacionException{
		if(this.isNomina()){
			
		}else{
			if(valorMaximo != null && valorMaximo.containsKey(VM) && valor.compara(this.valorMaximo.get(VM)) > 0 ){
				throw new ConfirmacionException("El valor: " + valor.toString() + " excede el valor maximo " + this.valorMaximo.get(VM).toString());
			}
		}
	}
	
	public boolean isPago(){
		return this.igual("P");
	}
	
	public boolean isNomina(){
		return this.igual("N");
	}
	public boolean isTraslado(){
		return this.igual("T");
	}
	
	public boolean isEgreso(){
		return this.igual("E");
	}
	
	public boolean isIngreso(){
		return this.igual("I");
	}
	@Override
	public String catalogoNombre() {
		return CatalogoEnum.TIPO_COMPROBANTE.getKey();
	}

}
