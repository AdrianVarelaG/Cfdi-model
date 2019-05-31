package com.beet.model.invoice.assembler.comprobante;

import java.util.List;
import java.util.stream.Collectors;

import com.beet.model.invoice.assembler.CatalogoAssembler;
import com.beet.model.invoice.model.Cantidad;
import com.beet.model.invoice.model.Decimal;
import com.beet.model.invoice.model.Importe;
import com.beet.model.invoice.model.ImpuestoConcepto;
import com.beet.model.invoice.model.RetencionConcepto;
import com.beet.model.invoice.model.TrasladoConcepto;
import com.beet.model.invoice.xml.model.comprobante.Comprobante;

public interface ImpuestoConceptoAssembler {

  static ImpuestoConcepto toImpuestoConcepto(Comprobante.Conceptos.Concepto.Impuestos conceptoImpuestoXml) {
    ImpuestoConcepto ret = null;
    
    if(conceptoImpuestoXml != null) {
      ret = new ImpuestoConcepto();
      ret.setRetenciones(ImpuestoConceptoAssembler.toRetencion(conceptoImpuestoXml.getRetenciones()));
      ret.setTraslados(ImpuestoConceptoAssembler.toTraslado(conceptoImpuestoXml.getTraslados()));
    }
    
    return ret;
  }
  
  static List<RetencionConcepto> toRetencion(Comprobante.Conceptos.Concepto.Impuestos.Retenciones retencionesXml){
    List<RetencionConcepto> ret = null;
    
    if(retencionesXml != null && retencionesXml.getRetencion() != null && !retencionesXml.getRetencion().isEmpty()) 
      ret = retencionesXml.getRetencion().stream().map(ImpuestoConceptoAssembler::toRetencion).collect(Collectors.toList());    
    
    return ret;
  }
  
  static RetencionConcepto toRetencion(Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion retencionXml) {
    RetencionConcepto ret = null;
    
    if(retencionXml != null) {
      ret = new RetencionConcepto();
      if(retencionXml.getBase() != null)
        ret.setBase(new Cantidad(retencionXml.getBase()));
      ret.setImpuesto(CatalogoAssembler.toImpuesto(retencionXml.getImpuesto()));
      ret.setTipoFactor(CatalogoAssembler.toTipoFactor(retencionXml.getTipoFactor().value()));
      if(retencionXml.getTasaOCuota() != null)
        ret.setTasaOCuota(new Decimal(retencionXml.getTasaOCuota()));
      if(retencionXml.getImporte() != null)
        ret.setImporte(new Importe(retencionXml.getImporte()));
    }
    
    return ret;
  }
  
  static List<TrasladoConcepto> toTraslado(Comprobante.Conceptos.Concepto.Impuestos.Traslados trasladosXml){
    List<TrasladoConcepto> ret = null;
    
    if(trasladosXml != null && trasladosXml.getTraslado() != null && !trasladosXml.getTraslado().isEmpty()) 
      ret = trasladosXml.getTraslado().stream().map(ImpuestoConceptoAssembler::toTraslado).collect(Collectors.toList());
    
    return ret;
  }
  
  static TrasladoConcepto toTraslado(Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado traslado) {
    TrasladoConcepto ret = null;
    
    if(traslado != null) {
      ret = new TrasladoConcepto();
      if(traslado.getBase() != null)
        ret.setBase(new Cantidad(traslado.getBase()));
      ret.setImpuesto(CatalogoAssembler.toImpuesto(traslado.getImpuesto()));
      ret.setTipoFactor(CatalogoAssembler.toTipoFactor(traslado.getTipoFactor().value()));
      if(traslado.getTasaOCuota() != null)
        ret.setTasaOCuota(new Decimal(traslado.getTasaOCuota()));
      if(traslado.getImporte() != null) 
        ret.setImporte(new Importe(traslado.getImporte()));
      
    }
    
    return ret;
  }
  
}
