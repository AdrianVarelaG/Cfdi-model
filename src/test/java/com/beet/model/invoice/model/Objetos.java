package com.beet.model.invoice.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.beet.model.invoice.model.Cantidad;
import com.beet.model.invoice.model.CatClave;
import com.beet.model.invoice.model.CatFormaPago;
import com.beet.model.invoice.model.CatImpuesto;
import com.beet.model.invoice.model.CatLugarExpedicion;
import com.beet.model.invoice.model.CatMetodoPago;
import com.beet.model.invoice.model.CatMoneda;
import com.beet.model.invoice.model.CatRegimenFiscal;
import com.beet.model.invoice.model.CatTipoComprobante;
import com.beet.model.invoice.model.CatTipoFactor;
import com.beet.model.invoice.model.CatTipoRelacion;
import com.beet.model.invoice.model.CatUnidad;
import com.beet.model.invoice.model.CatUso;
import com.beet.model.invoice.model.Certificado;
import com.beet.model.invoice.model.CfdiRelacionados;
import com.beet.model.invoice.model.CfdiUuid;
import com.beet.model.invoice.model.CondicionesPago;
import com.beet.model.invoice.model.Decimal;
import com.beet.model.invoice.model.Descripcion;

import com.beet.model.invoice.model.Estado;
import com.beet.model.invoice.model.FechaHora;
import com.beet.model.invoice.model.Folio;
import com.beet.model.invoice.model.Importe;
import com.beet.model.invoice.model.ImpuestoConcepto;
import com.beet.model.invoice.model.ImpuestoEnum;
import com.beet.model.invoice.model.Localidad;
import com.beet.model.invoice.model.Municipio;
import com.beet.model.invoice.model.NumeroCertificado;
import com.beet.model.invoice.model.NumeroIdentificacion;
import com.beet.model.invoice.model.Patron;
import com.beet.model.invoice.model.RazonSocial;
import com.beet.model.invoice.model.ReferenciaCuenta;
import com.beet.model.invoice.model.Requerido;
import com.beet.model.invoice.model.RetencionConcepto;
import com.beet.model.invoice.model.RetencionTot;
import com.beet.model.invoice.model.Rfc;
import com.beet.model.invoice.model.Sello;
import com.beet.model.invoice.model.TrasladoConcepto;
import com.beet.model.invoice.model.TrasladoTot;
import com.beet.model.invoice.model.Unidad;
import com.beet.model.invoice.model.Version;
import com.beet.model.invoice.model.comprobante.Concepto;
import com.beet.model.invoice.model.comprobante.Emisor;
import com.beet.model.invoice.model.comprobante.EntidadComprobante;
import com.beet.model.invoice.model.comprobante.ImpuestoTot;
import com.beet.model.invoice.model.comprobante.Receptor;
import com.beet.model.invoice.model.pago.ComplementoPago;
import com.beet.model.invoice.model.timbre.TimbreFiscal;

public class Objetos {
	/***********************************SECCIONES******************************************/
	public static ImpuestoTot creaImpuestosTot() {
		ImpuestoTot impuestoTot = new  ImpuestoTot();
		
		RetencionTot retencionTot = new RetencionTot();
		
		Importe importe = new Importe ();
		importe.setValor("101.98");
		CatImpuesto impuesto = new CatImpuesto();
		impuesto.setClaveSat("002");
		impuesto.setDescripcion("IVA");
		impuesto.setRetencion(false);
		impuesto.setTipo(ImpuestoEnum.FEDERAL);
		impuesto.setTraslado(false);

		
		retencionTot.setImporte(importe);
		retencionTot.setImpuesto(impuesto);
		
		
		Importe importe2 = new Importe ();
		importe2.setValor(new BigDecimal("101.98").setScale(8));
		CatImpuesto impuesto2 = new CatImpuesto();
		impuesto2.setClaveSat("001");
		impuesto2.setDescripcion("ISR");
		impuesto2.setRetencion(true);
		impuesto2.setTipo(ImpuestoEnum.LOCAL);
		impuesto2.setTraslado(true);

		
		RetencionTot retencionTot2 = new RetencionTot();
		
		retencionTot2.setImporte(importe2);
		retencionTot2.setImpuesto(impuesto2);
		
		List<RetencionTot> lret = new ArrayList<>();
		lret.add(retencionTot);
		lret.add(retencionTot2);
		
		Importe total = creaImporte("203.96");
		
		List<TrasladoTot> ltra = new ArrayList<>();
		ltra.add(creaTrasladoTotIva());
		ltra.add(creaTrasladoTotISR());
		
		impuestoTot.setRetenciones(lret);
		impuestoTot.setTraslados(ltra);
		impuestoTot.setTotalImpuestosRetenidos(total);
		impuestoTot.setTotalImpuestosTransladados(creaImporte("308.05"));
		
		return impuestoTot;
	}

	public static TimbreFiscal creaTimbreFiscal(){
		TimbreFiscal ret = new TimbreFiscal();
		Version version = creaVersion();
		version.setValor("1.1");
		ret.setVersion(version);
		ret.setUuid(creaCfdiUuid());
		ret.setFechaTimbrado(creaFecha());
		ret.setRfcProvCertif(creaRfc(false));
		ret.setSelloCFD(creaSello());
		ret.setNumeroCertificadoSAT(creaNumeroCertificado());
		ret.setSelloSAT(creaSello());
		return ret;
	}
	
	public static CfdiRelacionados creaCfdiRelacionados(){
		CfdiRelacionados ret = new CfdiRelacionados();
		ret.setTipoRelacion(creaCatTipoRelacion());
	//	ret.setRelacionados(creaListRelacion());
		return ret;
	}
	public static Emisor creaEmisor(){
		Emisor ret = new Emisor();
		ret.setRfc(creaRfc(true));
		ret.setNombre(creaRazonSocial());
		ret.setRegimenFiscal(creaCatRegimenFiscal());
		return ret;
	}
	public static Receptor creaReceptor(){
		Receptor ret = new Receptor();
		ret.setNombre(creaRazonSocial());
		ret.setRfc(creaRfc(false));
		ret.setUso(creaCatUso());
		return ret;
	}

	/***********************************ENTIDADES******************************************/
	public static EntidadComprobante creaComprobante(){
		EntidadComprobante ret = new EntidadComprobante();
		ret.setVersion(creaVersion());
		ret.setFolio(creaFolio());
		ret.setFecha(creaFecha());
		ret.setSello(creaSello());
		ret.setFormaPago(creaCatFormaPago());
		ret.setNumeroCertificado(creaNumeroCertificado());
		ret.setCertificado(creaCertificado());
		ret.setCondicionesPago(creaCondicionesPago());
		ret.setSubtotal(creaImporte("1184.79"));
		ret.setMoneda(creaCatMoneda());
		ret.setTotal(creaImporte("1288.88"));
		ret.setTipoComprobante(creaCatTipoComprobante());
		ret.setMetodoPago(creaCatMetodoPago());
		ret.setLugarExpedicion(creaCatLugarExpedicion());
		ret.setEmisor(creaEmisor());
		ret.setReceptor(creaReceptor());
		ret.setConceptos(creaListConcepto());
		ret.setImpuestos(creaImpuestosTot());
		return ret;
	}	

	public static Concepto creaConcepto(){
		Concepto ret = new Concepto();
		ret.setClaveProdServ(creaCatClave());
		ret.setNumeroIdentificacion(creaNumeroIdentificacion());
		ret.setCantidad(creaCantidad("5"));
		ret.setClaveUnidad(creaCatUnidad());
		ret.setUnidad(creaUnidad());
		ret.setDescripcion(creaDescripcion());
		ret.setValorUnitario(creaImporte("236.95875"));
		ret.setImporte(creaImporte("1184.79375"));
		//ret.setDescuento(creaImporte("15.897"));
		ret.setImpuestos(creatImpuestoConcepto());
		return ret;
	}

	
	public static RetencionConcepto creaRetencionConcepto(){
		RetencionConcepto ret = new RetencionConcepto();
		
		ret.setBase(creaCantidad("956.06"));
		ret.setImporte(creaImporte("101.98"));   //152.97
		ret.setImpuesto(creaCatImpuesto());
		ret.setTasaOCuota(creaDecimal(".106667"));
		ret.setTipoFactor(creaTipoFactor());
		
		return ret;
	}
	
	public static TrasladoTot creaTrasladoTotIva(){
		TrasladoTot ret = new TrasladoTot();
		ret.setImpuesto(creaCatImpuesto());
		ret.setTipoFactor(creaTipoFactor());
		ret.setTasaOCuota(creaDecimal(".16"));
		ret.setImporte(creaImporte("189.57"));
		return ret;
	}
	public static TrasladoTot creaTrasladoTotISR(){
		TrasladoTot ret = new TrasladoTot();
		ret.setImpuesto(creaISR());
		ret.setTipoFactor(creaTipoFactor());
		ret.setTasaOCuota(creaDecimal("0.10"));
		ret.setImporte(creaImporte("118.48"));
		return ret;
	}
	
	public static RetencionTot creaRetencionTotIva(){
		RetencionTot ret = new RetencionTot();
		ret.setImporte(creaImporte("101.98"));
		ret.setImpuesto(creaCatImpuesto());
		return ret;
	}
	public static TrasladoConcepto creaTrasladoConcepto(){
		TrasladoConcepto ret = new TrasladoConcepto();
		ret.setBase(creaCantidad("1184.79375"));//1184.79375
		ret.setImporte(creaImporte("189.567"));
		ret.setImpuesto(creaCatImpuesto());
		ret.setTasaOCuota(creaDecimal(".16000"));
		ret.setTipoFactor(creaTipoFactor());
		
		return ret;
	}
/*	
	public static Relacion creaRelacion(){
		Relacion ret = new Relacion();
		ret.setUuid(creaCfdiUuid());
		return ret;
	}*/
	public static ComplementoPago creaComplementoPago(){
		ComplementoPago ret = new ComplementoPago();
		return ret;
	}
	
	/**********************************LISTAS**********************************************/
	public static List<RetencionConcepto> creaListRetenciones(){
		List<RetencionConcepto> ret = new ArrayList<>();
		ret.add(creaRetencionConcepto());
		RetencionConcepto tmp = creaRetencionConcepto();
		tmp.setImpuesto(creaISR());
		ret.add(tmp);
		return ret;
	}
	public static List<TrasladoConcepto> creaListTraslados(){
		List<TrasladoConcepto> ret = new ArrayList<>();
		ret.add(creaTrasladoConcepto());
		TrasladoConcepto tmp = creaTrasladoConcepto();
		tmp.setBase(creaCantidad("1184.79375"));
		tmp.setImpuesto(creaISR());
		tmp.setTasaOCuota(creaDecimal(".10"));
		tmp.setImporte(creaImporte("118.479375"));
		ret.add(tmp);
		return ret;
	}
	public static List<Concepto> creaListConcepto(){
		List<Concepto> ret = new ArrayList<>();
		ret.add(creaConcepto());
		return ret;
	}
/*	public static List<Relacion> creaListRelacion(){
		List<Relacion> ret = new ArrayList<>();
		ret.add(creaRelacion());
		return ret;
	}
	*/
/************************************ValueObject*****************************************/	
	public static Decimal creaDecimal(String valor){
		Decimal ret= new Decimal();
		ret.setValor(valor);
		return ret;
	}
	public static Importe creaImporte(String valor){
		Importe ret = new Importe();
		ret.setValor(valor);
		return ret;
	}
	public static Cantidad creaCantidad(String valor){
		Cantidad ret = new Cantidad();
		ret.setValor(valor);
		return ret;
	}
	public static NumeroIdentificacion creaNumeroIdentificacion(){
		NumeroIdentificacion ret = new NumeroIdentificacion("1235467");
		return ret;
	}
	public static Unidad creaUnidad(){
		Unidad ret = new Unidad("Unidades");
		return ret;
	}
	public static Descripcion creaDescripcion(){
		Descripcion ret = new Descripcion("Esto es un producto");
		return ret;
	}
	public static ImpuestoConcepto creatImpuestoConcepto(){
		ImpuestoConcepto impuestoConcepto = new ImpuestoConcepto();
		impuestoConcepto.setTraslados(creaListTraslados());
		impuestoConcepto.setRetenciones(creaListRetenciones());
		return impuestoConcepto;
	}
	
	public static Localidad creaLocalidad(){
		Localidad ret = new Localidad();
		ret.setClave("05");
		return ret;
	}
	public static Municipio creaMunicipio(){
		Municipio ret = new Municipio();
		ret.setClave("03");
		return ret;
	}
	public static Estado creaEstado(){
		Estado ret = new Estado();
		ret.setClave("SLP");
		return ret;
	}
	public static Certificado creaCertificado(){
		Certificado ret = new Certificado();
		ret.setContent64("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");
		return ret;
	}
	public static NumeroCertificado creaNumeroCertificado(){
		NumeroCertificado ret = new NumeroCertificado("00001000000405003077");
		return ret;
	}
	public static Sello creaSello(){
		Sello ret = new Sello();
		ret.setSello("EKAtacGtgElJ3vvyjEAHA0vz+mNoWqLZSamELhfQKra70qmhc4SOrPaER6WbVEdQCp7fCXoE+EMHDjFlk26VBuBGAOJFqsYu/wa43ybZheCjBxbZfs8fHc+5Lg56f0pxdrjspS2WtzCLLRI1sLPvNjZkKT9l6rJuHKk+ClGEXFMbjC0mujGttXi7NQnROZAc9tCQcKJSx9cMneyvk/QPStpFIugXH0tep/038EVaC/sw4tfBuE0jGYy5PZzEeZTJweEVlHtD0KJB0yhi7qDLlAYnYO2RisI6hdQ+5dVctaSIQeXWqmOtoYoUSQmLZT9poga0RkrIVXjmN3YJjyMlgA==");
		return ret;
	}
	public static FechaHora creaFecha(){
		FechaHora ret = new FechaHora();
		
		return ret;
	}
	public static Version creaVersion(){
		Version ret = new Version();
		ret.setValor("3.3");
		return ret;
	}
	public static Rfc creaRfc(boolean moral){
		Rfc ret = new Rfc();
		if(moral)
			ret.setValor("CMC161114E10");
		else
			ret.setValor("VAGC850120PR6");
		return ret;
	}
	public static Rfc creaRfcGenerico(boolean extrangero){
		Rfc ret = new Rfc();
		if(extrangero)
			ret.setValor("XAXX010101000");
		else
			ret.setValor("XEXX010101000");
		return ret;
	}
	public static CfdiUuid creaCfdiUuid(){
		CfdiUuid ret = new CfdiUuid("5B7BBBCA-7942-4789-BF70-AF779ED3F795");
		return ret;
	}
	public static Folio creaFolio(){
		Folio ret = new Folio();
		ret.setValor("15647");
		return ret;
	}
	public static CondicionesPago creaCondicionesPago(){
		CondicionesPago ret = new CondicionesPago();
		ret.setValor("Pago a 90 dias");
		return ret;
	}
	public static RazonSocial creaRazonSocial(){
		RazonSocial ret = new RazonSocial();
		return ret;
	}
	/**********************************CATALOGOS******************************************/
	public static CatClave creaCatClave(){
		CatClave ret = new CatClave();
		ret.setClaveSat("02154569");
		ret.setComplemento(null);
		ret.setDescripcion("Producto de prueba");
		ret.setIepsTrasladado(Requerido.OPCIONAL);
		ret.setIvaTrasladado(Requerido.OPCIONAL);

		
		return ret;
	}
	public static CatImpuesto creaISR(){
		CatImpuesto ret = new CatImpuesto();
		
		ret.setClaveSat("001");
		ret.setDescripcion("ISR");
		ret.setRetencion(true);
		ret.setTipo(ImpuestoEnum.FEDERAL);
		ret.setTraslado(true);

		
		return ret;
	}
	public static CatImpuesto creaCatImpuesto(){
		CatImpuesto ret = new CatImpuesto();
		
		ret.setClaveSat("002");
		ret.setDescripcion("IVA");
		ret.setRetencion(true);
		ret.setTipo(ImpuestoEnum.FEDERAL);
		ret.setTraslado(true);

		
		return ret;
	}
	public static CatMoneda creaCatMoneda(){
		CatMoneda ret= new CatMoneda();
		ret.setClaveSat("MXN");
		ret.setDecimales(2);
		ret.setDescripcion("Peso Mexicano");
		ret.setVariacion(new BigDecimal(0.3565792));
		return ret;
	}
	public static CatTipoComprobante creaCatTipoComprobante(){
		CatTipoComprobante ret = new CatTipoComprobante();
		
		ret.setClaveSat("I");
		ret.setDescripcion("Ingresos");

		
		return ret;
	}
	public static CatTipoFactor creaTipoFactor(){
		CatTipoFactor ret = new CatTipoFactor();
		
		ret.setClaveSat("Tasa");
		ret.setDescripcion("Tasa");

		
		return ret;
	}
	public static CatUnidad creaCatUnidad(){
		CatUnidad ret = new CatUnidad();
		ret.setClaveSat("G23");
		ret.setDescripcion("Unidades");
		ret.setSimbolo("U");

		return ret;
	}
	public static CatLugarExpedicion creaCatLugarExpedicion(){
		CatLugarExpedicion ret = new CatLugarExpedicion();
		ret.setClaveSat("78398");
		ret.setDescripcion("78398");
		ret.setEstado(creaEstado());
		ret.setMunicipio(creaMunicipio());
		ret.setLocalidad(creaLocalidad());

		return ret;
	}
	public static CatFormaPago creaCatFormaPago(){
		CatFormaPago ret = new CatFormaPago();
		ret.setClaveSat("03");
		ret.setDescripcion("Transferencia electrónica de fondos");
		ret.setBancarizado(Requerido.SI);
		ret.setNumeroOperacion(Requerido.OPCIONAL);
		ret.setOrdenante(creaOrdenante());
		ret.setBeneficiario(creBeneficiaria());
		ret.setTipoCadenaPago(Requerido.OPCIONAL);
		return ret;
	}
	public static CatMetodoPago creaCatMetodoPago(){
		CatMetodoPago ret = new CatMetodoPago();
		ret.setClaveSat("PUE");
		ret.setDescripcion("Pago en una sola exhibición");
		return ret;
	}
	public static CatTipoRelacion creaCatTipoRelacion(){
		CatTipoRelacion ret = new CatTipoRelacion();
		ret.setClaveSat("01");
		ret.setDescripcion("Nota de crédito de los documentos relacionados");
		return ret;
	}
	public static CatRegimenFiscal creaCatRegimenFiscal(){
		CatRegimenFiscal ret = new CatRegimenFiscal();
		ret.setClaveSat("601");
		ret.setDescripcion("General de Ley Personas Morales");
		return ret;
	}
	public static CatUso creaCatUso(){
		CatUso ret = new CatUso();
		ret.setClaveSat("G03");
		ret.setDescripcion("Gastos en general");
		ret.setFisica(true);
		ret.setMoral(true);
		return ret;
	}
/*******************************************************PRIVADOS*********************************************/
	private static ReferenciaCuenta creaOrdenante(){
		ReferenciaCuenta ret = new ReferenciaCuenta();
		ret.setRfc(Requerido.OPCIONAL);
		ret.setCuenta(Requerido.OPCIONAL);
		ret.setPatron(creaPatron("[0-9]{10}|[0-9]{16}|[0-9]{18}"));
		return ret;
	}
	private static ReferenciaCuenta creBeneficiaria(){
		ReferenciaCuenta ret = creaOrdenante();
		ret.setPatron(creaPatron("[0-9]{10}|[0-9]{18}"));
		return ret;
	}
	private static Patron creaPatron(String patron){
		Patron ret = new Patron();
		ret.setPatron(patron);
		return ret;
	}
	
/*
 ************************************************************DTO*************************************************
 */

}
