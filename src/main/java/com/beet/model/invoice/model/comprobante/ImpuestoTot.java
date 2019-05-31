package com.beet.model.invoice.model.comprobante;

import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.CatImpuesto;
import com.beet.model.invoice.model.CatMoneda;
import com.beet.model.invoice.model.CatTipoFactor;
import com.beet.model.invoice.model.Decimal;
import com.beet.model.invoice.model.Importe;
import com.beet.model.invoice.model.RetencionConcepto;
import com.beet.model.invoice.model.RetencionTot;
import com.beet.model.invoice.model.TrasladoConcepto;
import com.beet.model.invoice.model.TrasladoTot;
import com.beet.model.invoice.model.ValueObject;

import lombok.Data;
import lombok.ToString;


@Data
@ToString(includeFieldNames=false)
public class ImpuestoTot implements ValueObject<ImpuestoTot> {
	
	private static final long serialVersionUID = 6933004275566916551L;
	private Importe totalImpuestosRetenidos;
	private Importe totalImpuestosTransladados;
	private List<RetencionTot> retenciones;
	private List<TrasladoTot> traslados;

	@Override
	public boolean sameValueAs(ImpuestoTot other) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Importe calculaTotalTranslado(){
		Importe ret = new Importe();
		if(traslados != null){
			ret.setValor("0");
			for(TrasladoTot tmp : traslados){
				ret = ret.suma(tmp.getImporte());
			}
		}
		return ret;
	}
	
	public Importe calculaTotalRetencion(){
		Importe ret = new Importe();
		if(traslados != null){
			ret.setValor("0");
			for(RetencionTot tmp : retenciones){
				ret = ret.suma(tmp.getImporte());
			}
		}
		return ret;
	}
	public void validaObligatorios() throws ValidacionExcepcion{
		boolean error = true;
		if(!(this.retenciones == null || this.retenciones.isEmpty())){
			error = false;
			for(RetencionTot tmp : this.retenciones){
				tmp.validaObligatorios();
			}
		}
		if(!(this.traslados== null || this.traslados.isEmpty())){
			error = false;
			for(TrasladoTot tmp : this.traslados){
				tmp.validaObligatorios();
			}
		}
		if(error)
			throw new ValidacionExcepcion("Deben existir impuestos ");	
	}
	
	public void valida(List<RetencionConcepto> retencionesLista, List<TrasladoConcepto> trasladosLista, CatMoneda moneda) throws ValidacionExcepcion{
		this.validaRetencion(retencionesLista, moneda);
		this.validaTraslado(trasladosLista, moneda);
		
	}
	private void validaRetencion(List<RetencionConcepto> retencionesLista, CatMoneda moneda)throws ValidacionExcepcion{
		if(this.retenciones != null && !this.retenciones.isEmpty()){
			if(retencionesLista == null || retencionesLista.isEmpty())
				throw new ValidacionExcepcion("No existen impuestos en los conceptos no deberian existir retenciones en los totales");
			if(this.totalImpuestosRetenidos == null)
				throw new ValidacionExcepcion("El campo TotalImpuestosRetenidos no puede estar vacio si se cuenta con impuestos retenidos");
			this.validaRetenciones(retencionesLista, moneda);
			this.validaTotalRetenciones(moneda);
		}
		else{
			if(!(retencionesLista == null || retencionesLista.isEmpty()))
				throw new ValidacionExcepcion("Los impuestos retenidos no corresponden con los registrados en los conceptos");
			if(this.totalImpuestosRetenidos != null)
				throw new ValidacionExcepcion("No hay detalle de impuestos retenidos no deberia existir el campo TotalImpuestosRetenidos " + this.totalImpuestosRetenidos.toString());
		}			
	}
	
	private void validaRetenciones(List<RetencionConcepto> retencionesLista, CatMoneda moneda)throws ValidacionExcepcion{
		Map<String, RetencionTot> totales = new HashMap<>();
		String key;
		RetencionTot tmp;
		
		for(RetencionConcepto retencion : retencionesLista){
			key = retencion.getImpuesto().getClaveSat();
			if(totales.containsKey(key)){
				tmp = totales.get(key);
				tmp.setImporte(tmp.getImporte().suma(retencion.getImporte()));
			}else{
				tmp = new RetencionTot();
				tmp.setImpuesto(new CatImpuesto(retencion.getImpuesto()));
				tmp.setImporte(new Importe(retencion.getImporte()));
				totales.put(key, tmp);
			}
		}
		
		for(RetencionTot retencionTot : this.retenciones){
			try{
				retencionTot.getImporte().setScale(moneda.getDecimales());
			}catch(ArithmeticException e){
				throw new ValidacionExcepcion("Los decimales del impuesto retenido " + retencionTot.toString() + " exceden los decimales de la moneda " + moneda.toString());
			}
			
			key = retencionTot.getImpuesto().getClaveSat();
			if(totales.containsKey(key)){
				tmp = totales.remove(key);
				tmp.getImporte().setScale(moneda.getDecimales(), RoundingMode.HALF_UP);
				if(retencionTot.getImporte().comparaMargen(tmp.getImporte()) != 0){
					throw new ValidacionExcepcion("La suma de las retenciones no concuerda impuesto retenido " + retencionTot.getImpuesto().concatenado() + 
							" registrado: " + retencionTot.getImporte().toString() + 
							" calculado: " + tmp.getImporte().toString());
				}
			}else{
				throw new ValidacionExcepcion("No se encuentra en los conceptos el impuesto " + retencionTot.toString());
			}
		}
		if(totales.size() > 0){
			throw new ValidacionExcepcion("Hacen falta impuestos en los totales " + totales.toString());
		}
	}
	private void validaTotalRetenciones(CatMoneda moneda) throws ValidacionExcepcion{
		Importe retenidosTot;
		
		try{
			this.totalImpuestosRetenidos.setScale(moneda.getDecimales());
		}catch(ArithmeticException e){
			throw new ValidacionExcepcion("El numero de decimales de los impuestos retenido " + Integer.toString(this.totalImpuestosRetenidos.numeroDecimales()) + " sobrepasa la cantidad de la moneda " + moneda.toString());
		}
		retenidosTot = calculaTotalRetenciones();
		if(this.totalImpuestosRetenidos.comparaMargen(retenidosTot) != 0)
			throw new ValidacionExcepcion("El total de los impuestos retenidos " + this.totalImpuestosRetenidos.toString() + " no concuerda con la suma " + retenidosTot.toString());
	}
	
	private Importe calculaTotalRetenciones(){
		Importe ret= null;
		
		if(this.retenciones != null || !this.retenciones.isEmpty()){
			ret = new Importe("0");
			for(RetencionTot tmp : this.retenciones){
				ret = ret.suma(tmp.getImporte());
			}
		}
		
		return ret;
	}
	private void validaTraslado(List<TrasladoConcepto> trasladosLista, CatMoneda moneda)throws ValidacionExcepcion{
		if(this.traslados != null && !this.traslados.isEmpty()){
			if(trasladosLista == null || trasladosLista.isEmpty())
				throw new ValidacionExcepcion("No existen impuestos en los conceptos no deberian existir Traslados en los totales");
			if(this.totalImpuestosTransladados == null)
				throw new ValidacionExcepcion("El campo TotalImpuestosTrasladados no puede estar vacio si se cuenta con impuestos trasladados");
			this.validaTraslados(trasladosLista, moneda);
			this.validaTotalTraslados(moneda);
		}
		else{
			if(!(trasladosLista == null || trasladosLista.isEmpty()))
				throw new ValidacionExcepcion("Los impuestos trasladados no corresponden con los registrados en los conceptos");
			if(this.totalImpuestosTransladados != null)
				throw new ValidacionExcepcion("No hay detalle de impuestos trasladados no deberia existir el campo TotalImpuestosRetenidos " + this.totalImpuestosRetenidos.toString());
		}			
	}
	private void validaTraslados(List<TrasladoConcepto> trasladosLista, CatMoneda moneda)throws ValidacionExcepcion{
		Map<String, TrasladoTot> totales = new HashMap<>();
		String key;
		TrasladoTot tmp;
		
		for(TrasladoConcepto traslado : trasladosLista){
			if(!traslado.getTipoFactor().isExento()){
				key = traslado.getImpuesto().getClaveSat() + traslado.getTipoFactor().getClaveSat() + traslado.getTasaOCuota().toString();
				if(totales.containsKey(key)){
					tmp = totales.get(key);
					tmp.setImporte(tmp.getImporte().suma(traslado.getImporte()));
				}else{
					tmp = new TrasladoTot();
					tmp.setImpuesto(new CatImpuesto(traslado.getImpuesto()));
					tmp.setTipoFactor(new CatTipoFactor(traslado.getTipoFactor()));
					tmp.setTasaOCuota(new Decimal(traslado.getTasaOCuota()));
					tmp.setImporte(new Importe(traslado.getImporte()));
					totales.put(key, tmp);
				}
			}
		}
		
		for(TrasladoTot trasladoTot : this.traslados){
			
			try{
				trasladoTot.getImporte().setScale(moneda.getDecimales());
			}catch(ArithmeticException e){
				throw new ValidacionExcepcion("Los decimales del impuesto trasladado " + trasladoTot.toString() + " exceden los decimales de la moneda " + moneda.toString());
			}
			
			key = trasladoTot.getImpuesto().getClaveSat() + trasladoTot.getTipoFactor().getClaveSat() + trasladoTot.getTasaOCuota().toString();
			if(totales.containsKey(key)){
				tmp = totales.remove(key);
				tmp.getImporte().setScale(moneda.getDecimales(), RoundingMode.HALF_UP);
				if(trasladoTot.getImporte().comparaMargen(tmp.getImporte()) != 0){
					throw new ValidacionExcepcion("La suma de los traslados no concuerda impuesto trasladado " + trasladoTot.getImpuesto().concatenado() + 
							" registrado: " + trasladoTot.getImporte().toString() + 
							" calculado: " + tmp.getImporte().toString());
				}
			}else{
				throw new ValidacionExcepcion("No se encuentra en los conceptos el impuesto " + trasladoTot.toString());
			}
		}
		if(totales.size() > 0){
			throw new ValidacionExcepcion("Hacen falta impuestos en los totales " + totales.toString());
		}
	}
	private void validaTotalTraslados(CatMoneda moneda) throws ValidacionExcepcion{
		Importe trasladoTot;
		
		try{
			this.totalImpuestosTransladados.setScale(moneda.getDecimales());
		}catch(ArithmeticException e){
			throw new ValidacionExcepcion("El numero de decimales de los impuestos trasladados " + Integer.toString(this.totalImpuestosTransladados.numeroDecimales()) + " sobrepasa la cantidad de la moneda " + moneda.toString());
		}
				
		trasladoTot = calculaTotaltraslados();
		if(this.totalImpuestosTransladados.comparaMargen(trasladoTot) != 0)
			throw new ValidacionExcepcion("El total de los impuestos retenidos " + this.totalImpuestosTransladados.toString() + " no concuerda con la suma " + trasladoTot.toString());
	}
	private Importe calculaTotaltraslados(){
		Importe ret= null;
		
		if(this.traslados != null || !this.traslados.isEmpty()){
			ret = new Importe("0");
			for(TrasladoTot tmp : this.traslados){
				ret = ret.suma(tmp.getImporte());
			}
		}
		
		return ret;
	}
	
	public boolean isNull(){
		return totalImpuestosRetenidos == null && totalImpuestosTransladados == null && 
				(this.retenciones == null || this.retenciones.isEmpty()) && (this.traslados == null || this.traslados == null);
	}

	public void ajustaDecimales(int decimalesMoneda) {
		if(this.totalImpuestosRetenidos != null) this.totalImpuestosRetenidos.setScale(decimalesMoneda);
		if(this.totalImpuestosTransladados != null) this.totalImpuestosTransladados.setScale(decimalesMoneda);
		if(this.retenciones != null){
			for(RetencionTot tmp: this.retenciones){
				tmp.ajustaDecimales(decimalesMoneda);
			}
		}
		if(this.traslados != null){
			for(TrasladoTot tmp: this.traslados){
				tmp.ajustaDecimales(decimalesMoneda);
			}
		}
	}
	
}
