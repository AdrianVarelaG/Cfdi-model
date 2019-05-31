package com.beet.model.invoice.assembler;

import com.beet.model.invoice.model.CatClave;
import com.beet.model.invoice.model.CatEstado;
import com.beet.model.invoice.model.CatFormaPago;
import com.beet.model.invoice.model.CatImpuesto;
import com.beet.model.invoice.model.CatLugarExpedicion;
import com.beet.model.invoice.model.CatMetodoPago;
import com.beet.model.invoice.model.CatMoneda;
import com.beet.model.invoice.model.CatRegimenFiscal;
import com.beet.model.invoice.model.CatTipoComprobante;
import com.beet.model.invoice.model.CatTipoFactor;
import com.beet.model.invoice.model.CatUnidad;
import com.beet.model.invoice.model.CatUso;

public interface CatalogoAssembler {
  static CatFormaPago toFormaPago(String fp) {
    CatFormaPago ret = null;
    if(Utilerias.existeInfo(fp))
      ret = new CatFormaPago(fp);
    return ret;
  }
  
  static CatMoneda toMoneda(String m) {
    CatMoneda ret = null;
    if(Utilerias.existeInfo(m))
      ret = new CatMoneda(m);
    return ret;
  }
  
  static CatMoneda toMoneda(com.beet.model.invoice.xml.model.comprobante.CMoneda m) {
    if(m != null) 
      return CatalogoAssembler.toMoneda(m.value());
    return null;
  }
  
  static CatTipoComprobante toCatTipoComprobante(String tc) {
    CatTipoComprobante ret = null;
    
    if(Utilerias.existeInfo(tc))
      ret = new CatTipoComprobante(tc);
    
    return ret;
  }
  static CatTipoComprobante toCatTipoComprobante(com.beet.model.invoice.xml.model.comprobante.CTipoDeComprobante tc) {
    if(tc != null) 
      return CatalogoAssembler.toCatTipoComprobante(tc.value());
    return null;
  }
  
  static CatMetodoPago toMetodoPago(String mp) {
    CatMetodoPago ret = null;
    
    if(Utilerias.existeInfo(mp)) 
      ret = new CatMetodoPago(mp);
    
    return ret;
  }
  
  static CatMetodoPago toMetodoPago(com.beet.model.invoice.xml.model.comprobante.CMetodoPago mp) {
    if(mp != null)
      return CatalogoAssembler.toMetodoPago(mp.value());
    return null;
  }
  
  static CatLugarExpedicion toLugarExpedicion(String le) {
    CatLugarExpedicion ret = null;
    
    if(le != null)
      ret = new CatLugarExpedicion(le);
    
    return ret;
  }
  
  static CatImpuesto toImpuesto(String i) {
    CatImpuesto ret = null;
    
    if(i != null)
      ret = new CatImpuesto(i);
    
    return ret;
  }
  
  static CatTipoFactor toTipoFactor(String tf) {
    CatTipoFactor ret = null;
    
    if(tf != null)
      ret = new CatTipoFactor(tf);
    return ret;
  }
  
  static CatRegimenFiscal toReginFiscal(String rf) {
    CatRegimenFiscal ret = null;
    if(rf != null)
      ret = new CatRegimenFiscal(rf);
    
    return ret;
  }
  
  static CatUso toUso(String u) {
    CatUso ret = null;
    
    if(u  != null)
      ret = new CatUso(u);
    
    return ret;
  }
  
  static CatClave toClaveProdServ(String cps) {
    CatClave ret = null;
    
    if(cps != null)
      ret = new CatClave(cps);
    
    return ret;
  }
  
  static CatUnidad toClaveUnidad(String cu) {
    CatUnidad ret = null;
    
    if(cu != null) {
      ret = new CatUnidad(cu);
    }
    
    return ret;
  }
  
  static CatEstado toEstado(String cu) {
    CatEstado ret = null;
    
    if(cu != null) {
      ret = new CatEstado(cu);
    }
    
    return ret;
  }
  
  
}
