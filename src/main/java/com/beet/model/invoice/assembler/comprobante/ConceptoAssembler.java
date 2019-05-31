package com.beet.model.invoice.assembler.comprobante;

import java.util.List;
import java.util.stream.Collectors;

import com.beet.model.invoice.assembler.CatalogoAssembler;
import com.beet.model.invoice.assembler.Utilerias;
import com.beet.model.invoice.model.Cantidad;
import com.beet.model.invoice.model.Descripcion;
import com.beet.model.invoice.model.Importe;
import com.beet.model.invoice.model.NumeroIdentificacion;
import com.beet.model.invoice.model.Unidad;
import com.beet.model.invoice.model.comprobante.Concepto;
import com.beet.model.invoice.xml.model.comprobante.Comprobante;

public interface ConceptoAssembler {

  static List<Concepto> toConcepto(Comprobante.Conceptos conceptosXml){
    List<Concepto> ret = null;
    
    if(conceptosXml != null && conceptosXml.getConcepto() != null && !conceptosXml.getConcepto().isEmpty()) 
      ret = conceptosXml.getConcepto().stream().map(ConceptoAssembler::toConcepto).collect(Collectors.toList()); 
    
    return ret;
  }
  
  static Concepto toConcepto(Comprobante.Conceptos.Concepto conceptoXml) {
    Concepto ret = null;
    
    if(conceptoXml != null) {
      ret = new Concepto();
      ret.setClaveProdServ(CatalogoAssembler.toClaveProdServ(conceptoXml.getClaveProdServ()));
      if(Utilerias.existeInfo(conceptoXml.getNoIdentificacion()))
        ret.setNumeroIdentificacion(new NumeroIdentificacion(conceptoXml.getNoIdentificacion()));
      if(conceptoXml.getCantidad() != null)
        ret.setCantidad(new Cantidad(conceptoXml.getCantidad()));
      ret.setClaveUnidad(CatalogoAssembler.toClaveUnidad(conceptoXml.getClaveUnidad()));
      if(Utilerias.existeInfo(conceptoXml.getUnidad()))
        ret.setUnidad(new Unidad(conceptoXml.getClaveUnidad()));
      if(Utilerias.existeInfo(conceptoXml.getDescripcion()))
        ret.setDescripcion(new Descripcion(conceptoXml.getDescripcion()));
      if(conceptoXml.getValorUnitario() != null) 
        ret.setValorUnitario(new Importe(conceptoXml.getValorUnitario()));
      if(conceptoXml.getImporte() != null)
        ret.setImporte(new Importe(conceptoXml.getImporte()));
      if(conceptoXml.getDescuento() != null)
        ret.setDescuento(new Importe(conceptoXml.getDescuento()));
      ret.setImpuestos(ImpuestoConceptoAssembler.toImpuestoConcepto(conceptoXml.getImpuestos()));
      
    }
    
    return ret;
  }
  
}
