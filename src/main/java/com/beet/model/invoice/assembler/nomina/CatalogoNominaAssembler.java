package com.beet.model.invoice.assembler.nomina;

import com.beet.model.invoice.assembler.Utilerias;
import com.beet.model.invoice.model.nomina.catalogos.CatBanco;
import com.beet.model.invoice.model.nomina.catalogos.CatPeriodicidadPago;
import com.beet.model.invoice.model.nomina.catalogos.CatRiesgoPuesto;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoContrato;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoDeduccion;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoJornada;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoNomina;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoOtroPago;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoPercepcion;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoRegimen;

public interface CatalogoNominaAssembler {

  static CatTipoNomina toTipoNomina(String valor) {
    CatTipoNomina ret = null;
    
    if(Utilerias.existeInfo(valor))
      ret = new CatTipoNomina(valor);
    
    return ret;
  }
  static CatTipoRegimen toTipoRegimen(String valor) {
    CatTipoRegimen ret = null;
    
    if(Utilerias.existeInfo(valor))
      ret = new CatTipoRegimen(valor);
    
    return ret;
  }
  static CatTipoPercepcion toTipoPercepcion(String valor) {
    CatTipoPercepcion ret = null;
    
    if(Utilerias.existeInfo(valor))
      ret = new CatTipoPercepcion(valor);
    
    return ret;
  }
  static CatTipoOtroPago toTipoOtroPago(String valor) {
    CatTipoOtroPago ret = null;
    
    if(Utilerias.existeInfo(valor))
      ret = new CatTipoOtroPago(valor);
    
    return ret;
  }
  static CatTipoJornada toTipoJornada(String valor) {
    CatTipoJornada ret = null;
    
    if(Utilerias.existeInfo(valor))
      ret = new CatTipoJornada(valor);
    
    return ret;
  }
  static CatTipoDeduccion toTipoDeduccion(String valor) {
    CatTipoDeduccion ret = null;
    
    if(Utilerias.existeInfo(valor))
      ret = new CatTipoDeduccion(valor);
    
    return ret;
  }
  static CatTipoContrato toTipoContrato(String valor) {
    CatTipoContrato ret = null;
    
    if(Utilerias.existeInfo(valor))
      ret = new CatTipoContrato(valor);
    
    return ret;
  }
  static CatRiesgoPuesto toRiesgoPuesto(String valor) {
    CatRiesgoPuesto ret = null;
    
    if(Utilerias.existeInfo(valor))
      ret = new CatRiesgoPuesto(valor);
    
    return ret;
  }
  static CatPeriodicidadPago toPeriodicidadPago(String valor) {
    CatPeriodicidadPago ret = null;
    
    if(Utilerias.existeInfo(valor))
      ret = new CatPeriodicidadPago(valor);
    
    return ret;
  }
  static CatBanco toBanco(String valor) {
    CatBanco ret = null;
    
    if(Utilerias.existeInfo(valor))
      ret = new CatBanco(valor);
    
    return ret;
  }
  
}
