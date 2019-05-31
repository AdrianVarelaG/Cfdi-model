package com.beet.model.invoice.assembler.comprobante;

import com.beet.model.invoice.assembler.CatalogoAssembler;
import com.beet.model.invoice.assembler.Utilerias;
import com.beet.model.invoice.model.RazonSocial;
import com.beet.model.invoice.model.Rfc;
import com.beet.model.invoice.model.comprobante.Emisor;
import com.beet.model.invoice.xml.model.comprobante.Comprobante;

public interface EmisorAssembler {
  
  static Emisor toEmisor(Comprobante.Emisor emisorXml) {
    Emisor ret = null;
    
    if(emisorXml != null) {
      ret = new Emisor();
      if(Utilerias.existeInfo(emisorXml.getRfc()))
        ret.setRfc(new Rfc(emisorXml.getRfc()));
      if(Utilerias.existeInfo(emisorXml.getNombre()))
        ret.setNombre(new RazonSocial(emisorXml.getNombre()));
      ret.setRegimenFiscal(CatalogoAssembler.toReginFiscal(emisorXml.getRegimenFiscal()));
    }
    
    return ret;
  }
}
