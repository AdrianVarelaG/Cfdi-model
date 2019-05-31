package com.beet.model.invoice.model;

import java.util.List;

import com.beet.model.invoice.exception.ValidacionExcepcion;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=false)
public class ImpuestoConcepto implements ValueObject<ImpuestoConcepto> {

	private static final long serialVersionUID = 3904951368055457186L;

	private List<RetencionConcepto> retenciones;
	
	private List<TrasladoConcepto> traslados;
	
	@Override
	public boolean sameValueAs(ImpuestoConcepto other) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void validaObligatorios() throws ValidacionExcepcion{
		boolean error = true;
		
		if(!(this.traslados == null || this.traslados.isEmpty())){
			error = false;
			for(TrasladoConcepto tmp: this.traslados){
				tmp.validaObligatorios();
			}
		}
		if(!(this.retenciones == null || this.retenciones.isEmpty())){
			error = false;
			for(RetencionConcepto tmp : this.retenciones){
				tmp.validaObligatorios();
			}
		}
		
		if(error)
			throw new ValidacionExcepcion("Los impuestos en el concepto deben existir");
	}
	
	public boolean incluyeTrasladoIva(){
		boolean ret = false;
		
		for(TrasladoConcepto tmp: this.traslados){
			if(tmp.isIva()){
				ret = true;
				break;
			}
		}
		
		return ret;
	}
	
	public boolean incluyeTrasladoIeps(){
		boolean ret = false;
		
		for(TrasladoConcepto tmp: this.traslados){
			if(tmp.isIeps()){
				ret = true;
				break;
			}
		}
		
		return ret;
	}
	
	public void valida(CatMoneda moneda) throws ValidacionExcepcion{
		if(this.traslados != null){
			for(TrasladoConcepto tmp: this.traslados){
				tmp.valida();
			}
		}
		if(this.retenciones != null){
			for(RetencionConcepto tmp: this.retenciones){
				tmp.valida(moneda);
			}
		}
	}
	
	public boolean isNull(){
		return (this.retenciones == null || this.retenciones.isEmpty()) && (this.traslados == null || this.traslados.isEmpty()); 
	}

	public void ajustaDecimales(int decimalesMoneda) {
		if(this.retenciones != null){
			for(RetencionConcepto tmp: this.retenciones){
				tmp.ajustaDecimales(decimalesMoneda);
			}
		}
		if(this.traslados != null){
			for(TrasladoConcepto tmp : this.traslados){
				tmp.ajustaDecimales(decimalesMoneda);
			}
		}
	}
	
}
