package com.beet.model.invoice.assembler.comprobante;

import java.util.List;
import java.util.stream.Collectors;

import com.beet.model.invoice.assembler.CatalogoAssembler;
import com.beet.model.invoice.model.Decimal;
import com.beet.model.invoice.model.Importe;
import com.beet.model.invoice.model.RetencionTot;
import com.beet.model.invoice.model.TrasladoTot;
import com.beet.model.invoice.model.comprobante.ImpuestoTot;
import com.beet.model.invoice.xml.model.comprobante.Comprobante;

public interface ImpuestoTotAssembler {

  static ImpuestoTot toImpuestoTot(Comprobante.Impuestos impuestosXml) {
    ImpuestoTot ret = null;

    if (impuestosXml != null) {
      ret = new ImpuestoTot();

      if (impuestosXml.getTotalImpuestosRetenidos() != null)
        ret.setTotalImpuestosRetenidos(new Importe(impuestosXml.getTotalImpuestosRetenidos()));

      if (impuestosXml.getTotalImpuestosTrasladados() != null)
        ret.setTotalImpuestosTransladados(new Importe(impuestosXml.getTotalImpuestosTrasladados()));
      
      ret.setRetenciones(ImpuestoTotAssembler.toRetencionTot(impuestosXml.getRetenciones()));
      ret.setTraslados(ImpuestoTotAssembler.toTrasladoTot(impuestosXml.getTraslados()));
    }

    return ret;
  }

  static List<RetencionTot> toRetencionTot(Comprobante.Impuestos.Retenciones retenciones) {
    List<RetencionTot> ret = null;

    if (retenciones != null && retenciones.getRetencion() != null && !retenciones.getRetencion().isEmpty())
      ret = retenciones.getRetencion().stream().map(ImpuestoTotAssembler::toRetencionTot).collect(Collectors.toList());

    return ret;
  }

  static RetencionTot toRetencionTot(Comprobante.Impuestos.Retenciones.Retencion retencion) {
    RetencionTot ret = null;
    if(retencion != null) {
      ret = new RetencionTot();
      ret.setImporte(new Importe(retencion.getImporte()));
      ret.setImpuesto(CatalogoAssembler.toImpuesto(retencion.getImpuesto()));
    }
    return ret;
  }
  
  static List<TrasladoTot> toTrasladoTot(Comprobante.Impuestos.Traslados trasladosXml){
    List<TrasladoTot> ret = null;
      
    if(trasladosXml != null && trasladosXml.getTraslado() != null && !trasladosXml.getTraslado().isEmpty()) 
      ret = trasladosXml.getTraslado().stream().map(ImpuestoTotAssembler::toTrasladoTot).collect(Collectors.toList());
    
    return ret;
  }
  
  static TrasladoTot toTrasladoTot(Comprobante.Impuestos.Traslados.Traslado traslado) {
    TrasladoTot ret = null;
    
    if(traslado != null) {
      ret = new TrasladoTot();
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
