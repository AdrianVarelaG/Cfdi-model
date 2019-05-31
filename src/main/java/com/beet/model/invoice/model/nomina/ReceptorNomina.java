package com.beet.model.invoice.model.nomina;

import com.beet.model.invoice.model.Antiguedad;
import com.beet.model.invoice.model.CatEstado;
import com.beet.model.invoice.model.CuentaBancaria;
import com.beet.model.invoice.model.Curp;
import com.beet.model.invoice.model.Fecha;
import com.beet.model.invoice.model.ImporteMxn;
import com.beet.model.invoice.model.NumeroSeguroSocial;
import com.beet.model.invoice.model.ValueObject;
import com.beet.model.invoice.model.nomina.catalogos.CatBanco;
import com.beet.model.invoice.model.nomina.catalogos.CatPeriodicidadPago;
import com.beet.model.invoice.model.nomina.catalogos.CatRiesgoPuesto;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoContrato;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoJornada;
import com.beet.model.invoice.model.nomina.catalogos.CatTipoRegimen;

import lombok.Data;

@Data
public class ReceptorNomina implements ValueObject<ReceptorNomina>{

  private static final long serialVersionUID = -1976127878470877283L;
  private static final String EXTRANGERO_H = " XEXX010101HNEXXXA4";
  private static final String EXTRANGERO_M = " XEXX010101MNEXXXA8";
  
  private Curp curp;
  private NumeroSeguroSocial numSeguridadSocial;
  private Fecha fechaInicioRelLaboral;
  private Antiguedad antiguedad;
  private CatTipoContrato tipoContrato;
  private Sindicalizado sindicalizado;
  private CatTipoJornada tipoJornada;
  private CatTipoRegimen tipoRegimen;
  private NumeroEmpleado numeroEmpleado;
  private Departamento departamento;
  private Puesto puesto;
  private CatRiesgoPuesto riesgoPuesto;
  private CatPeriodicidadPago periodicidadPago;
  private CatBanco banco;
  private CuentaBancaria cuentaBancaria;
  private ImporteMxn salarioBaseCotApor;
  private ImporteMxn salarioDiarioIntegrado;
  private CatEstado claveEntFed;
  
  //TODO: Subcontratacion.
  
  
  public boolean esExtrangero() {
    return this.curp.igual(EXTRANGERO_H) || this.curp.igual(EXTRANGERO_M);
  }
  
  @Override
  public boolean sameValueAs(ReceptorNomina other) {
    // TODO Auto-generated method stub
    return false;
  }
}
