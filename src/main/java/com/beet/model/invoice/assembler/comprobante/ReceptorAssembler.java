package com.beet.model.invoice.assembler.comprobante;

import com.beet.model.invoice.assembler.CatalogoAssembler;
import com.beet.model.invoice.assembler.Utilerias;
import com.beet.model.invoice.model.RazonSocial;
import com.beet.model.invoice.model.Rfc;
import com.beet.model.invoice.model.comprobante.Receptor;
import com.beet.model.invoice.xml.model.comprobante.Comprobante;

public interface ReceptorAssembler {

  static Receptor toReceptor(Comprobante.Receptor receptorXml) {
    Receptor ret = null;
    
    if(receptorXml != null) {
      ret = new Receptor();
      
      if(Utilerias.existeInfo(receptorXml.getRfc())) 
        ret.setRfc( new Rfc(receptorXml.getRfc()));
      if(Utilerias.existeInfo(receptorXml.getNombre()))
        ret.setNombre(new RazonSocial(receptorXml.getNombre()));
      ret.setUso(CatalogoAssembler.toUso(receptorXml.getUsoCFDI().value()));
      
    }
    
    return ret;
  }
  
}
