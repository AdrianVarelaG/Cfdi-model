package com.beet.model.invoice.model.comprobante;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.beet.model.invoice.exception.ConfirmacionException;
import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.Cantidad;
import com.beet.model.invoice.model.CatFormaPago;
import com.beet.model.invoice.model.CatLugarExpedicion;
import com.beet.model.invoice.model.CatMetodoPago;
import com.beet.model.invoice.model.CatMoneda;
import com.beet.model.invoice.model.CatTipoComprobante;
import com.beet.model.invoice.model.Certificado;
import com.beet.model.invoice.model.CfdiRelacionados;
import com.beet.model.invoice.model.CondicionesPago;
import com.beet.model.invoice.model.Confirmacion;
import com.beet.model.invoice.model.Decimal;
import com.beet.model.invoice.model.EstadoComprobante;
import com.beet.model.invoice.model.FechaHora;
import com.beet.model.invoice.model.Folio;
import com.beet.model.invoice.model.Importe;
import com.beet.model.invoice.model.NumeroCertificado;
import com.beet.model.invoice.model.RetencionConcepto;
import com.beet.model.invoice.model.Sello;
import com.beet.model.invoice.model.Serie;
import com.beet.model.invoice.model.TrasladoConcepto;
import com.beet.model.invoice.model.ValueObject;
import com.beet.model.invoice.model.Version;
import com.beet.model.invoice.model.pago.ComplementoPago;
import com.beet.model.invoice.model.timbre.TimbreFiscal;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude={"certificado"}, includeFieldNames=true)
public class EntidadComprobante implements ValueObject<EntidadComprobante> {

  private static final long serialVersionUID = 8408001914055057573L;
  private static final String VERSION = "3.3"; 
	private static final String MXN = "MXN";
	
	private TimbreFiscal timbreFiscal;
	private ComplementoPago pago;
	
	private Version version;
	private Serie serie;
	private Folio folio;
	private FechaHora fecha;
	private Sello sello;
	private CatFormaPago formaPago;
	private NumeroCertificado numeroCertificado;
	private Certificado certificado;
	private CondicionesPago condicionesPago;
	private Importe subtotal;
	private Importe descuento;
	private CatMoneda moneda;
	private Cantidad tipoCambio;
	private Importe total;
	private CatTipoComprobante tipoComprobante;
	private CatMetodoPago metodoPago;
	private CatLugarExpedicion lugarExpedicion;
	private Confirmacion confirmacion;
	private CfdiRelacionados cfdiRelacionados;
	private ImpuestoTot impuestos;
	private Emisor emisor;
	private Receptor receptor;
	private List<Concepto> conceptos;
	private EstadoComprobante estado;
	private int decimalesMoneda;
	
	public EntidadComprobante(){
		this.decimalesMoneda = 0;
		this.estado = EstadoComprobante.EMITIDO;
	}
	
	public void setTimbreFiscal(TimbreFiscal timbreFiscal){
		this.timbreFiscal = timbreFiscal;
		this.estado = EstadoComprobante.TIMBRADO;
	}
	
	
	public void validaComprobante() throws ValidacionExcepcion{
		this.validaVersion();
		this.validaTipoComprobante();
		this.validaFormaPago();
		this.validaSubTotal();
		this.validaDescuento();
		this.validaMoneda();
		this.validaTipoCambio();
		this.validaTotal();
		this.validaMetodoPago();
		this.validaCfdiRelacion();
		this.validaReceptor();
		this.validaConceptos();
		this.validaImpuestos();
	}
	
	public void validaObligatorios() throws ValidacionExcepcion{
		if(this.version == null)
			throw new ValidacionExcepcion("La version es obligatoria");
		if(this.fecha == null)
			throw new ValidacionExcepcion("La fecha es obligatoria");
		if(this.numeroCertificado == null)
			throw new ValidacionExcepcion("El numeroCertificado es obligatorio");
		if(this.certificado == null)
			throw new ValidacionExcepcion("El certificado es obligatorio");
		if(this.subtotal == null)
			throw new ValidacionExcepcion("El subtotal es obligatorio");
		if(this.moneda == null)
			throw new ValidacionExcepcion("La moneda es obligatoria");
		if(this.total == null)
			throw new ValidacionExcepcion("El total es obligatorio");
		if(this.tipoComprobante == null)
			throw new ValidacionExcepcion("El tipo de comprobante es obligatorio");
		if(this.lugarExpedicion == null)
			throw new ValidacionExcepcion("El lugar de expedicion es obligatorio");
		if(this.cfdiRelacionados != null)
			this.cfdiRelacionados.validaObligatorios();
		if(this.emisor == null)
			throw new ValidacionExcepcion("El emisor es obligatorio");
		else
			this.emisor.validaObligatorios();
		if(this.receptor == null)
			throw new ValidacionExcepcion("El receptor es obligatorio");
		else
			this.receptor.validaObligatorios();
		if(this.conceptos == null || this.conceptos.isEmpty())
			throw new ValidacionExcepcion("El comprobante debe contener al menos un concepto");
		else{
			for(Concepto tmp: conceptos){
				tmp.validaObligatorios();
			}
		}
		if(this.impuestos != null){
			this.impuestos.validaObligatorios();
		}
		if(this.tipoComprobante.isPago()){
			if(this.pago == null)
				throw new ValidacionExcepcion("El comprobante debe incluir el complemento de pago");
			else
				this.pago.validaObligatorios();
		}
	}
	
	private void validaVersion() throws ValidacionExcepcion{
		if(!this.version.igual(VERSION))
			throw new ValidacionExcepcion("La version que se admite es " + VERSION);
	}
	
	private void validaTipoComprobante() throws ValidacionExcepcion{
		final String mensaje;
		mensaje = "Para el tipo de comprobante " + this.tipoComprobante.concatenado();
		switch(this.tipoComprobante.getClaveSat()){
			case "I":
			case "E":			
				if(this.formaPago == null)
					throw new ValidacionExcepcion("La forma de pago debe existir");
				break;
			case "T":
				if(this.condicionesPago != null)
					throw new ValidacionExcepcion( mensaje + " el campo condicionesPago no debe existir" );
				if(sumaDescuentos() != null)
					throw new ValidacionExcepcion( mensaje + " no deben existir decuentos" );
				if(this.impuestos != null)
					throw new ValidacionExcepcion( mensaje + " no deben existir impuestos" );
				if(this.formaPago != null)
					throw new ValidacionExcepcion( mensaje + " no deben existir el campo forma de pago" );
				if(this.metodoPago != null)
					throw new ValidacionExcepcion( mensaje + " no deben existir el campo metodo de pago" );
				break;
			case "P":
				if(this.condicionesPago != null)
					throw new ValidacionExcepcion( mensaje + " el campo condicionesPago no debe existir" );
				if(sumaDescuentos() != null)
					throw new ValidacionExcepcion( mensaje + " no deben existir decuentos" );
				if(this.impuestos != null)
					throw new ValidacionExcepcion( mensaje + " no deben existir impuestos" );
				if(this.formaPago != null)
					throw new ValidacionExcepcion( mensaje + " no deben existir el campo forma de pago" );
				if(this.metodoPago != null)
					throw new ValidacionExcepcion( mensaje + " no deben existir el campo metodo de pago" );
				break;
			case "N":
				if(this.condicionesPago != null)
					throw new ValidacionExcepcion( mensaje + " el campo condicionesPago no debe existir" );
				if(this.impuestos != null)
					throw new ValidacionExcepcion( mensaje + " no deben existir impuestos" );
				break;
			default:
				throw new ValidacionExcepcion("Valor invalido de tipo de comprobante ");
		}
	}
	
	private void validaFormaPago() throws ValidacionExcepcion{
		final String porDefinir = "99";
		final String parcialidades = "PPD";

		switch(this.tipoComprobante.getClaveSat()){
		case "I":
		case "E":
			if(this.formaPago == null) throw new ValidacionExcepcion("La forma de pago es obligatoria");
			if(this.metodoPago == null) throw new ValidacionExcepcion("El metodo de pago es obligatorio");
			if(this.formaPago.igual(porDefinir)){
				if(!this.metodoPago.igual(parcialidades)){
					throw new ValidacionExcepcion("La forma de pago: " + this.formaPago.concatenado()+ "y el metodo de pago: " + this.metodoPago.concatenado() + " no es valido" );
				}
			}
			break;
		}
	}
	
	private void validaSubTotal() throws ValidacionExcepcion{
		final Decimal cero = new Decimal("0");
		final Importe subtotal;
		
		switch(this.tipoComprobante.getClaveSat()){
		case "T":
		case "P":
			if(!(this.subtotal.compara(cero) == 0)){
				throw new ValidacionExcepcion("Para el tipo de comprobante " + this.tipoComprobante.concatenado() + " el subtotal debe ser cero");
			}
			break;
		case "I":
		case "E":
		case "N":
			try{
				this.subtotal.setScale(this.moneda.getDecimales());
			}catch(ArithmeticException e){
				throw new ValidacionExcepcion("El numero de decimales del SubTotal " + this.subtotal.toString() + " excede los decimales de la moneda " + this.moneda.toString());
			}
			subtotal = this.sumaImportes();
			if(this.subtotal.comparaMargen(subtotal) != 0)
				throw new ValidacionExcepcion("El subtotal " + this.subtotal.toString() +  " no concuerda con la suma de los conceptos " + subtotal.toString());
			break;
		}
	}
	
	private void validaDescuento() throws ValidacionExcepcion{
		Importe sumaDescuentos;
		switch(this.tipoComprobante.getClaveSat()){
		case "I":
		case "E":
		case "N":
			sumaDescuentos = sumaDescuentos();
			if(sumaDescuentos != null){
				if(this.descuento == null)
					throw new ValidacionExcepcion("Existen descuentos en los conceptos debe incluir el campo descuento en el comprobante");
				else{
					try{
						this.descuento.setScale(this.moneda.getDecimales());
					}catch(ArithmeticException e){
						throw new ValidacionExcepcion("El numero de decimales del Descuento " + this.descuento.toString() + " excede los decimales de la moneda " + this.moneda.toString() );
					}	
					if(this.descuento.comparaMargen(sumaDescuentos) != 0)
						throw new ValidacionExcepcion("El descuento total " + this.descuento.toString() +  " no concuerda con la suma de los descuentos " + sumaDescuentos.toString());				
					if(this.descuento.compara(this.subtotal) > 0)
						throw new ValidacionExcepcion("El descuento " + this.descuento.toString() + " no puede ser mayor que el subtotal " + this.subtotal.toString());
				}
			}else{
				if(this.descuento != null)
					throw new ValidacionExcepcion("Los conceptos no contienen descuentos no se debe incluir el campo en el comprobante");
			}
				
			break;
		default:
			if(this.descuento != null)
				throw new ValidacionExcepcion("Para el TipoDeComprobante " + this.tipoComprobante.concatenado() + " no es valido el campo descuento");
			break;
		}
	}
	
	private void validaMoneda() throws ValidacionExcepcion{
		if(this.tipoComprobante.isNomina())
			if(!this.moneda.igual(MXN))
				throw new ValidacionExcepcion("El tipo de moneda para el comprobante " + this.tipoComprobante.concatenado() + " solo puede ser " + MXN );
	}
	
	private void validaTipoCambio() throws ValidacionExcepcion{
		if(this.tipoCambio == null){
			if(this.moneda.requiereTipoCambio())
				throw new ValidacionExcepcion("Para la moneda " + this.moneda.concatenado() + "debe existir el tipoCambio" );
		}else{
			if(this.moneda.igual(MXN)){
				if(this.tipoCambio.compara(new Decimal("1")) != 0)
					throw new ValidacionExcepcion("para la moneda " + this.moneda.concatenado() + " el tipo de cambio solo puede ser 1");
			}else if(this.moneda.igual("XXX")){
				throw new ValidacionExcepcion("Este campo no debe existir cuando no es una operacion que incluya un tipo de moneda");
			}
		}
	}
	
	private void validaTotal() throws ValidacionExcepcion, ConfirmacionException{
		final Decimal cero = new Decimal("0");
		Importe suma ;
		switch(this.tipoComprobante.getClaveSat()){
		case "T":
		case "P":
			if(!(this.total.compara(cero) == 0)){
				throw new ValidacionExcepcion("Para el tipo de comprobante " + this.tipoComprobante.concatenado() + " el total debe ser cero");
			}
			break;
		default:
				try{
					this.total.setScale(this.moneda.getDecimales());
				}catch(ArithmeticException e){
					throw new ValidacionExcepcion("El numero de decimales del Total " + this.total.toString() + " excede los decimales de la moneda " + this.moneda.toString());
				}	

				suma = this.subtotal.suma(new Importe("0"));
				if(this.descuento != null)
					suma = suma.resta(this.descuento);
				if(this.impuestos != null){
					if(this.impuestos.getTotalImpuestosTransladados() != null)
						suma = suma.suma(this.impuestos.getTotalImpuestosTransladados());
					if(this.impuestos.getTotalImpuestosRetenidos() != null)
						suma = suma.resta(this.impuestos.getTotalImpuestosRetenidos());
				}
				if(this.total.comparaMargen(suma) != 0)
					throw new ValidacionExcepcion("El valor del total " + this.total.toString() + " no corresponde con el valor calculado " + suma.toString());
				
				try{
					this.tipoComprobante.validaValorMaximo(this.total);
				}catch(ConfirmacionException excep){
					if(this.confirmacion == null)
						throw new ConfirmacionException("El valor total " + this.total.toString() + " hace falta el valor de la confirmacion" );
				}
				break;
		}
	}
	
	private void validaMetodoPago() throws ValidacionExcepcion{
		if(this.pago != null){
			if(this.metodoPago != null)
				throw new ValidacionExcepcion("No debe existir el metodo de pago " + this.metodoPago.concatenado() );
		}
	}
	
	private void validaCfdiRelacion() throws ValidacionExcepcion{
		if(this.cfdiRelacionados != null)
			this.cfdiRelacionados.validaRelacion(this.tipoComprobante);
	}
	private void validaReceptor() throws ValidacionExcepcion{
		this.receptor.valida(this.metodoPago);
	}
	
	private void validaConceptos() throws ValidacionExcepcion{
		for(Concepto concepto : this.conceptos){
			concepto.valida(this.tipoComprobante, this.moneda);
		}
	}
	private void validaImpuestos() throws ValidacionExcepcion{
		if(this.impuestos != null){
			impuestos.valida(consultaRetenciones(), consultaTraslados(), this.moneda);
		}
	}
	private List<RetencionConcepto> consultaRetenciones(){
		List<RetencionConcepto> ret = new ArrayList<>();
		List<RetencionConcepto> tmp;
		for(Concepto concepto : this.conceptos){
			if(concepto.getImpuestos() != null){
				tmp = concepto.getImpuestos().getRetenciones();
				if(tmp != null) ret.addAll(tmp);
			}
		}
		return ret;
	}
	private List<TrasladoConcepto> consultaTraslados(){
		List<TrasladoConcepto> ret = new ArrayList<>();
		List<TrasladoConcepto> tmp;
		
		for(Concepto concepto : this.conceptos){
			if(concepto.getImpuestos() != null){
				tmp = concepto.getImpuestos().getTraslados();
				if(tmp != null) ret.addAll(tmp);
			}
		}
		
		return ret;
	}
	private Importe sumaDescuentos(){
		Importe ret = null;
		
		for(Concepto tmp : this.conceptos){
			if(tmp.getDescuento() != null){
				if(ret == null){
					ret = new Importe("0");
				}
				ret = ret.suma(tmp.getDescuento());
			}
		}
		return ret;
	}
	
	private Importe sumaImportes(){
		Importe ret = new Importe("0");
		
		for(Concepto tmp : this.conceptos){
			ret = ret.suma(tmp.getImporte());
		}
		ret.setScale(this.moneda.getDecimales(), RoundingMode.HALF_UP);
		return ret;
	}
	
	public void setMoneda(CatMoneda moneda){
		this.moneda = moneda;
		if(this.moneda != null && this.moneda.getDecimales() >= 0){
			this.decimalesMoneda = this.moneda.getDecimales();
		}
	}
	
	public void ajustaDecimales(){
		if(this.subtotal != null) 	this.subtotal.setScale(this.decimalesMoneda);
		if(this.descuento != null)	this.descuento.setScale(this.decimalesMoneda);
		if(this.total != null)			this.total.setScale(this.decimalesMoneda);
		
		if(this.conceptos != null){
			for(Concepto concepto : this.conceptos){
				concepto.ajustaDecimales(this.decimalesMoneda);
			}
		}
		if(this.impuestos != null) this.impuestos.ajustaDecimales(this.decimalesMoneda);
		
	}

  @Override
  public boolean sameValueAs(EntidadComprobante other) {
    // TODO Auto-generated method stub
    return false;
  }
	
}
