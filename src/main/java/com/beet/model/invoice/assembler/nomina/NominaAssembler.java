package com.beet.model.invoice.assembler.nomina;

import java.util.List;
import java.util.stream.Collectors;

import com.beet.model.invoice.assembler.CatalogoAssembler;
import com.beet.model.invoice.assembler.Utilerias;
import com.beet.model.invoice.model.Antiguedad;
import com.beet.model.invoice.model.CuentaBancaria;
import com.beet.model.invoice.model.Curp;
import com.beet.model.invoice.model.Fecha;
import com.beet.model.invoice.model.ImporteMxn;
import com.beet.model.invoice.model.NumeroSeguroSocial;
import com.beet.model.invoice.model.RegistroPatronal;
import com.beet.model.invoice.model.Rfc;
import com.beet.model.invoice.model.Version;
import com.beet.model.invoice.model.nomina.Clave;
import com.beet.model.invoice.model.nomina.ComplementoNomina;
import com.beet.model.invoice.model.nomina.Concepto;
import com.beet.model.invoice.model.nomina.Deduccion;
import com.beet.model.invoice.model.nomina.Deducciones;
import com.beet.model.invoice.model.nomina.Departamento;
import com.beet.model.invoice.model.nomina.DiasPagados;
import com.beet.model.invoice.model.nomina.EmisorNomina;
import com.beet.model.invoice.model.nomina.NumeroEmpleado;
import com.beet.model.invoice.model.nomina.OtroPago;
import com.beet.model.invoice.model.nomina.OtrosPagos;
import com.beet.model.invoice.model.nomina.Percepcion;
import com.beet.model.invoice.model.nomina.Percepciones;
import com.beet.model.invoice.model.nomina.Puesto;
import com.beet.model.invoice.model.nomina.ReceptorNomina;
import com.beet.model.invoice.model.nomina.Sindicalizado;
import com.beet.model.invoice.xml.model.nomina.Nomina;

public interface NominaAssembler {

  static ComplementoNomina toComplementoNomina(Nomina nominaXml) {
    ComplementoNomina ret = null;

    if (nominaXml != null) {
      ret = new ComplementoNomina();
      if (Utilerias.existeInfo(nominaXml.getVersion()))
        ret.setVersion(new Version(nominaXml.getVersion()));
      if (nominaXml.getTipoNomina() != null)
        ret.setTipoNomina(CatalogoNominaAssembler.toTipoNomina(nominaXml.getTipoNomina().value()));
      
      if (nominaXml.getFechaPago() != null)
        ret.setFechaPago(new Fecha(Utilerias.toLocalDate(nominaXml.getFechaPago())));
      
      if (nominaXml.getFechaInicialPago() != null)
        ret.setFechaInicialPago(new Fecha(Utilerias.toLocalDate(nominaXml.getFechaInicialPago())));
      
      if (nominaXml.getFechaFinalPago() != null)
        ret.setFechaFinalPago(new Fecha(Utilerias.toLocalDate(nominaXml.getFechaFinalPago())));
      
      if (nominaXml.getNumDiasPagados() != null)
        ret.setNumDiasPagados(new DiasPagados(nominaXml.getNumDiasPagados()));
      if (nominaXml.getTotalPercepciones() != null)
        ret.setTotalPercepciones(new ImporteMxn(nominaXml.getTotalPercepciones()));
      if (nominaXml.getTotalDeducciones() != null)
        ret.setTotalDeducciones(new ImporteMxn(nominaXml.getTotalDeducciones()));
      if (nominaXml.getTotalOtrosPagos() != null)
        ret.setTotalOtrosPagos(new ImporteMxn(nominaXml.getTotalOtrosPagos()));

      ret.setEmisor(NominaAssembler.toEmisor(nominaXml.getEmisor()));
      ret.setReceptor(NominaAssembler.toReceptor(nominaXml.getReceptor()));
      ret.setPercepciones(NominaAssembler.toPercepciones(nominaXml.getPercepciones()));
      ret.setDeducciones(NominaAssembler.toDeducciones(nominaXml.getDeducciones()));
      ret.setOtrosPagos(NominaAssembler.toOtrosPagos(nominaXml.getOtrosPagos()));
      
    }

    return ret;
  }

  static EmisorNomina toEmisor(Nomina.Emisor emisorXml) {
    EmisorNomina ret = null;

    if (emisorXml != null) {
      ret = new EmisorNomina();

      if (Utilerias.existeInfo(emisorXml.getCurp()))
        ret.setCurp(new Curp(emisorXml.getCurp()));
      if (Utilerias.existeInfo(emisorXml.getRegistroPatronal()))
        ret.setRegistroPatronal(new RegistroPatronal(emisorXml.getRegistroPatronal()));
      if (Utilerias.existeInfo(emisorXml.getRfcPatronOrigen()))
        ret.setRfcPatronOrigen(new Rfc(emisorXml.getRfcPatronOrigen()));
    }

    return ret;
  }

  static ReceptorNomina toReceptor(Nomina.Receptor receptorXml) {
    ReceptorNomina ret = null;

    if (receptorXml != null) {
      ret = new ReceptorNomina();

      if (Utilerias.existeInfo(receptorXml.getCurp()))
        ret.setCurp(new Curp(receptorXml.getCurp()));
      if (Utilerias.existeInfo(receptorXml.getNumSeguridadSocial()))
        ret.setNumSeguridadSocial(new NumeroSeguroSocial(receptorXml.getNumSeguridadSocial()));
      if (receptorXml.getFechaInicioRelLaboral() != null)
        ret.setFechaInicioRelLaboral(new Fecha(Utilerias.toLocalDate(receptorXml.getFechaInicioRelLaboral())));
      if (Utilerias.existeInfo(receptorXml.getAntigüedad()))
        ret.setAntiguedad(new Antiguedad(receptorXml.getAntigüedad()));
      ret.setTipoContrato(CatalogoNominaAssembler.toTipoContrato(receptorXml.getTipoContrato()));
      if (Utilerias.existeInfo(receptorXml.getSindicalizado()))
        ret.setSindicalizado(Sindicalizado.valueOf(receptorXml.getSindicalizado()));
      ret.setTipoJornada(CatalogoNominaAssembler.toTipoJornada(receptorXml.getTipoJornada()));
      ret.setTipoRegimen(CatalogoNominaAssembler.toTipoRegimen(receptorXml.getTipoRegimen()));
      if (Utilerias.existeInfo(receptorXml.getNumEmpleado()))
        ret.setNumeroEmpleado(new NumeroEmpleado(receptorXml.getNumEmpleado()));
      if (Utilerias.existeInfo(receptorXml.getDepartamento()))
        ret.setDepartamento(new Departamento(receptorXml.getDepartamento()));
      if (Utilerias.existeInfo(receptorXml.getPuesto()))
        ret.setPuesto(new Puesto(receptorXml.getPuesto()));
      ret.setRiesgoPuesto(CatalogoNominaAssembler.toRiesgoPuesto(receptorXml.getRiesgoPuesto()));
      ret.setPeriodicidadPago(CatalogoNominaAssembler.toPeriodicidadPago(receptorXml.getPeriodicidadPago()));
      ret.setBanco(CatalogoNominaAssembler.toBanco(receptorXml.getBanco()));
      if (receptorXml.getCuentaBancaria() != null)
        ret.setCuentaBancaria(new CuentaBancaria(receptorXml.getCuentaBancaria().toString()));
      if (receptorXml.getSalarioBaseCotApor() != null)
        ret.setSalarioBaseCotApor(new ImporteMxn(receptorXml.getSalarioBaseCotApor()));
      if (receptorXml.getSalarioDiarioIntegrado() != null)
        ret.setSalarioDiarioIntegrado(new ImporteMxn(receptorXml.getSalarioDiarioIntegrado()));
      if (receptorXml.getClaveEntFed() != null)
        ret.setClaveEntFed(CatalogoAssembler.toEstado(receptorXml.getClaveEntFed().value()));
      
      
      
    }

    return ret;
  }
  
  static Percepciones toPercepciones(Nomina.Percepciones percepcionesXml) {
    Percepciones ret = null;
    
    if(percepcionesXml != null) {
      ret = new Percepciones();
      if(percepcionesXml.getTotalSueldos() != null)
        ret.setTotalSueldo(new ImporteMxn(percepcionesXml.getTotalSueldos()));
      if(percepcionesXml.getTotalSeparacionIndemnizacion() != null)
        ret.setTotalSeparacionIndemnizacion(new ImporteMxn(percepcionesXml.getTotalSeparacionIndemnizacion()));
      if(percepcionesXml.getTotalJubilacionPensionRetiro() != null)
        ret.setTotalJubilacionPensionRetiro(new ImporteMxn(percepcionesXml.getTotalJubilacionPensionRetiro()));
      if(percepcionesXml.getTotalGravado() != null)
        ret.setTotalGravado(new ImporteMxn(percepcionesXml.getTotalGravado()));
      if(percepcionesXml.getTotalExento() != null)
        ret.setTotalExento(new ImporteMxn(percepcionesXml.getTotalExento()));
      
      ret.setPercepciones(NominaAssembler.toPercepciones(percepcionesXml.getPercepcion()));
      
    }
    
    return ret;
  }
  
  static List<Percepcion> toPercepciones(List<Nomina.Percepciones.Percepcion> percepcionList){
    List<Percepcion> ret = null;
    
    if(Utilerias.existeInfo(percepcionList)) 
      ret = percepcionList.stream().map(NominaAssembler::toPercepcion).collect(Collectors.toList());
    
    return ret;
  }
  
  static Percepcion toPercepcion(Nomina.Percepciones.Percepcion percepcionXml) {
    Percepcion ret = null;
    
    if(percepcionXml != null) {
      ret = new Percepcion();
      
      ret.setTipoPercepcion(CatalogoNominaAssembler.toTipoPercepcion(percepcionXml.getTipoPercepcion()));
      if(Utilerias.existeInfo(percepcionXml.getClave()))
        ret.setClave(new Clave(percepcionXml.getClave()));
      if(Utilerias.existeInfo(percepcionXml.getConcepto()))
        ret.setConcepto(new Concepto(percepcionXml.getConcepto()));
      if(percepcionXml.getImporteGravado() != null)
        ret.setImporteGravado(new ImporteMxn(percepcionXml.getImporteGravado()));
      if(percepcionXml.getImporteExento() != null)
        ret.setImporteExecto(new ImporteMxn(percepcionXml.getImporteExento()));
    }
    
    return ret;
  }
  
  static Deducciones toDeducciones(Nomina.Deducciones deduccionesXml) {
    Deducciones ret = null;
    
    if(deduccionesXml != null) {
      ret = new Deducciones();
      if(deduccionesXml.getTotalOtrasDeducciones() != null)
        ret.setTotalOtrasDeducciones(new ImporteMxn(deduccionesXml.getTotalOtrasDeducciones()));
      if(deduccionesXml.getTotalImpuestosRetenidos() != null)
        ret.setTotalImpuestosRetenidos(new ImporteMxn(deduccionesXml.getTotalImpuestosRetenidos()));
      ret.setDeducciones(NominaAssembler.toDeducciones(deduccionesXml.getDeduccion()));
    }
    
    return ret;
  }
  
  static List<Deduccion> toDeducciones(List<Nomina.Deducciones.Deduccion> deducionesList){
    List<Deduccion> ret = null;
    
    if(Utilerias.existeInfo(deducionesList))
      ret = deducionesList.stream().map(NominaAssembler::toDeduccion).collect(Collectors.toList());
    
    return ret;
  }
  
  static Deduccion toDeduccion(Nomina.Deducciones.Deduccion deduccionXml) {
    Deduccion ret = null;
    
    if(deduccionXml != null) {
      ret = new Deduccion();
      ret.setTipoDeduccion(CatalogoNominaAssembler.toTipoDeduccion(deduccionXml.getTipoDeduccion()));
      if( Utilerias.existeInfo(deduccionXml.getClave()) )
        ret.setClave(new Clave(deduccionXml.getClave()));
      if(Utilerias.existeInfo(deduccionXml.getConcepto()))
        ret.setConcepto(new Concepto(deduccionXml.getConcepto()));
      if(deduccionXml.getImporte() != null)
        ret.setImporte(new ImporteMxn(deduccionXml.getImporte()));
    }
    
    return ret;
  }
  
  static OtrosPagos toOtrosPagos(Nomina.OtrosPagos otrosPagosXml) {
    OtrosPagos ret = null;
    
    if(otrosPagosXml != null && Utilerias.existeInfo(otrosPagosXml.getOtroPago())) {
      ret = new OtrosPagos();
      
      ret.setOtrosPagos(NominaAssembler.toOtrosPagos(otrosPagosXml.getOtroPago()));
    }
    
    return ret;
  }
  
  static List<OtroPago> toOtrosPagos(List<Nomina.OtrosPagos.OtroPago> otrosPagosXml ){
    List<OtroPago> ret = null;
    
    if(Utilerias.existeInfo(otrosPagosXml))
      ret = otrosPagosXml.stream().map(NominaAssembler::toOtroPago).collect(Collectors.toList());
    
    return ret;
  }
  
  static OtroPago toOtroPago(Nomina.OtrosPagos.OtroPago otroPagoXml ) {
    OtroPago ret = null;
    
    if(otroPagoXml != null) {
      ret = new OtroPago();
      ret.setTipoOtroPago(CatalogoNominaAssembler.toTipoOtroPago(otroPagoXml.getTipoOtroPago()));
      if(Utilerias.existeInfo(otroPagoXml.getClave()))
        ret.setClave(new Clave(otroPagoXml.getClave()));
      if(Utilerias.existeInfo(otroPagoXml.getConcepto()))
        ret.setConcepto(new Concepto(otroPagoXml.getConcepto()));
      if(otroPagoXml.getImporte() != null)
        ret.setImporte(new ImporteMxn(otroPagoXml.getImporte()));
    }
    
    return ret;
  }

}
