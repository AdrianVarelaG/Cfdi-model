package com.beet.model.invoice.model.timbre;

import com.beet.model.invoice.model.CfdiUuid;
import com.beet.model.invoice.model.FechaHora;
import com.beet.model.invoice.model.Leyenda;
import com.beet.model.invoice.model.NumeroCertificado;
import com.beet.model.invoice.model.Rfc;
import com.beet.model.invoice.model.Sello;
import com.beet.model.invoice.model.ValueObject;
import com.beet.model.invoice.model.Version;

import lombok.Data;

import lombok.ToString;

@Data
@ToString(includeFieldNames = false)
public class TimbreFiscal implements ValueObject<TimbreFiscal> {

  private static final long serialVersionUID = 8086801035898117173L;
  private Version version;
  private CfdiUuid uuid;
  private FechaHora fechaTimbrado;
  private Rfc rfcProvCertif;
  private Leyenda leyenda;
  private Sello selloCFD;
  private NumeroCertificado numeroCertificadoSAT;
  private Sello selloSAT;
  
  @Override
  public boolean sameValueAs(TimbreFiscal other) {
 
    return this.equals(other);
  }

}
