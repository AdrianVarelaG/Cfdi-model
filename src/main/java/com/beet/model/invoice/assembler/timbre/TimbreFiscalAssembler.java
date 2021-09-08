package com.beet.model.invoice.assembler.timbre;

import com.beet.model.invoice.assembler.Utilerias;
import com.beet.model.invoice.model.*;
import com.beet.model.invoice.model.timbre.TimbreFiscal;
import com.beet.model.invoice.xml.model.timbre11.TimbreFiscalDigital;

public interface TimbreFiscalAssembler {
  static TimbreFiscal toTimbreFiscal(TimbreFiscalDigital timbreFiscalXml) {
    TimbreFiscal ret = null;

    if (timbreFiscalXml != null){
      ret = new TimbreFiscal();
      if(Utilerias.existeInfo(timbreFiscalXml.getVersion()))
        ret.setVersion(new Version(timbreFiscalXml.getVersion()));
      if(Utilerias.existeInfo(timbreFiscalXml.getUUID()))
        ret.setUuid(new CfdiUuid(timbreFiscalXml.getUUID()));
      if(timbreFiscalXml.getFechaTimbrado() != null)
        ret.setFechaTimbrado(new FechaHora(Utilerias.toLocalDateTime(timbreFiscalXml.getFechaTimbrado())));
      if(Utilerias.existeInfo(timbreFiscalXml.getRfcProvCertif()))
        ret.setRfcProvCertif(new Rfc(timbreFiscalXml.getRfcProvCertif()));
      if(Utilerias.existeInfo(timbreFiscalXml.getLeyenda()))
        ret.setLeyenda(new Leyenda(timbreFiscalXml.getLeyenda()));
      if(Utilerias.existeInfo(timbreFiscalXml.getSelloCFD()))
        ret.setSelloCFD(new Sello(timbreFiscalXml.getSelloCFD()));
      if(Utilerias.existeInfo((timbreFiscalXml.getNoCertificadoSAT())))
        ret.setNumeroCertificadoSAT(new NumeroCertificado(timbreFiscalXml.getNoCertificadoSAT()));
      if(Utilerias.existeInfo(timbreFiscalXml.getSelloSAT()))
        ret.setSelloSAT(new Sello(timbreFiscalXml.getSelloSAT()));
    }

    return ret;
  }
}
