package com.beet.model.invoice.assembler.comprobante;

import com.beet.model.invoice.assembler.CatalogoAssembler;
import com.beet.model.invoice.assembler.Utilerias;
import com.beet.model.invoice.model.Serie;
import com.beet.model.invoice.model.Version;
import com.beet.model.invoice.model.Cantidad;
import com.beet.model.invoice.model.Certificado;
import com.beet.model.invoice.model.CondicionesPago;
import com.beet.model.invoice.model.Confirmacion;
import com.beet.model.invoice.model.FechaHora;
import com.beet.model.invoice.model.Folio;
import com.beet.model.invoice.model.Importe;
import com.beet.model.invoice.model.NumeroCertificado;
import com.beet.model.invoice.model.Sello;
import com.beet.model.invoice.model.comprobante.EntidadComprobante;
import com.beet.model.invoice.xml.model.comprobante.Comprobante;

public interface ComprobanteAssembler {

  static public EntidadComprobante toEntidadComprobante(Comprobante comprobanteXml) {
    EntidadComprobante ret = null;

    if (comprobanteXml != null) {
      ret = new EntidadComprobante();

      if(Utilerias.existeInfo(comprobanteXml.getVersion())) 
        ret.setVersion(new Version(comprobanteXml.getVersion()));
      
      if(Utilerias.existeInfo(comprobanteXml.getSerie())) 
        ret.setSerie(new Serie(comprobanteXml.getSerie()));
      
      if(Utilerias.existeInfo(comprobanteXml.getFolio()))
        ret.setFolio(new Folio(comprobanteXml.getFolio()));
      
      if(comprobanteXml.getFecha() != null)
        ret.setFecha(new FechaHora(Utilerias.toLocalDateTime(comprobanteXml.getFecha())));
      
      if(Utilerias.existeInfo(comprobanteXml.getSello()))
        ret.setSello(new Sello(comprobanteXml.getSello()));
      
      ret.setFormaPago(CatalogoAssembler.toFormaPago(comprobanteXml.getFormaPago()));
      
      if(Utilerias.existeInfo(comprobanteXml.getNoCertificado()))
        ret.setNumeroCertificado(new NumeroCertificado(comprobanteXml.getNoCertificado()));
      
      if(Utilerias.existeInfo(comprobanteXml.getCertificado()))
        ret.setCertificado(new Certificado(comprobanteXml.getCertificado()));
      
      if(Utilerias.existeInfo(comprobanteXml.getCondicionesDePago()))
        ret.setCondicionesPago(new CondicionesPago(comprobanteXml.getCondicionesDePago()));
      
      if(comprobanteXml.getSubTotal() != null)
        ret.setSubtotal(new Importe(comprobanteXml.getSubTotal()));
      
      if(comprobanteXml.getDescuento() != null) 
        ret.setDescuento(new Importe(comprobanteXml.getDescuento()));
      
      ret.setMoneda(CatalogoAssembler.toMoneda(comprobanteXml.getMoneda()));

      if(comprobanteXml.getTipoCambio() != null)
        ret.setTipoCambio(new Cantidad(comprobanteXml.getTipoCambio()));
      
      if(comprobanteXml.getTotal() != null)
        ret.setTotal(new Importe(comprobanteXml.getTotal()));
      
      ret.setTipoComprobante(CatalogoAssembler.toCatTipoComprobante(comprobanteXml.getTipoDeComprobante()));
      ret.setMetodoPago( CatalogoAssembler.toMetodoPago(comprobanteXml.getMetodoPago()));
      ret.setLugarExpedicion(CatalogoAssembler.toLugarExpedicion(comprobanteXml.getLugarExpedicion()));
      
      if(Utilerias.existeInfo(comprobanteXml.getConfirmacion()))
        ret.setConfirmacion(new Confirmacion(comprobanteXml.getConfirmacion()));
      
      //cfdiRelacionados 
      ret.setImpuestos(ImpuestoTotAssembler.toImpuestoTot(comprobanteXml.getImpuestos()));
      ret.setEmisor(EmisorAssembler.toEmisor(comprobanteXml.getEmisor()));
      ret.setReceptor(ReceptorAssembler.toReceptor(comprobanteXml.getReceptor()));
      ret.setConceptos(ConceptoAssembler.toConcepto(comprobanteXml.getConceptos()));
      
    }
    return ret;
  }
}
