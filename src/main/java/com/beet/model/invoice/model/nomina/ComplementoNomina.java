package com.beet.model.invoice.model.nomina;

import com.beet.model.invoice.model.Fecha;
import com.beet.model.invoice.model.ImporteMxn;
import com.beet.model.invoice.model.ValueObject;
import com.beet.model.invoice.model.Version;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoNomina;

import lombok.Data;

@Data
public class ComplementoNomina implements ValueObject<ComplementoNomina> {
  
  private static final long serialVersionUID = -5376583664204196732L;
  private Version version;
  private CatTipoNomina tipoNomina;
  private Fecha fechaPago;
  private Fecha fechaInicialPago;
  private Fecha fechaFinalPago;
  private DiasPagados numDiasPagados;
  private ImporteMxn totalPercepciones;
  private ImporteMxn totalDeducciones;
  private ImporteMxn totalOtrosPagos;
  
  private EmisorNomina emisor;
  private ReceptorNomina receptor;
  
  private Percepciones percepciones;
  private Deducciones deducciones;
  private OtrosPagos otrosPagos;
  
  //TODO: Incapasidades
  
  @Override
  public boolean sameValueAs(ComplementoNomina other) {
    return this.equals(other);
  }



}
