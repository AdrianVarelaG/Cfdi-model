package com.beet.model.invoice.model;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.RetencionConcepto;
import com.beet.model.invoice.model.RetencionTot;
import com.beet.model.invoice.model.TrasladoConcepto;
import com.beet.model.invoice.model.TrasladoTot;
import com.beet.model.invoice.model.comprobante.ImpuestoTot;


public class ImpuestoTotTest {

  ImpuestoTot impuestoTot;

  @BeforeEach
  public void inicializa(){
    impuestoTot = Objetos.creaImpuestosTot();
  }

  @Test
  public void validaTest(){
    impuestoTot.valida(Objetos.creaListRetenciones(), Objetos.creaListTraslados(), Objetos.creaCatMoneda());
  }
  @Test
  public void validaNoListaRetenciones(){

    ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
      impuestoTot.valida(null, Objetos.creaListTraslados(), Objetos.creaCatMoneda());
    });

    Assertions.assertTrue(e.getMessage().contains("No existen impuestos en los conceptos no deberian existir retenciones en los totales"));
  }
  @Test
  public void validaListaRetencionesFaltanImpuestos(){

    ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
      List<RetencionConcepto> tmp = Collections.emptyList();
      impuestoTot.valida(tmp, Objetos.creaListTraslados(), Objetos.creaCatMoneda());
    });

    Assertions.assertTrue(e.getMessage().contains("No existen impuestos en los conceptos no deberian existir retenciones en los totales"));

  }

  @Test
  public void validaRetencionesTotFaltantes(){	
    ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
      impuestoTot.getRetenciones().remove(0);
      impuestoTot.valida(Objetos.creaListRetenciones(), Objetos.creaListTraslados(), Objetos.creaCatMoneda());
    });
    Assertions.assertTrue(e.getMessage().contains("Hacen falta impuestos en los totales"));
  }
  @Test
  public void validaRetencionesTotdoble(){	
    ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
      impuestoTot.getRetenciones().add(Objetos.creaRetencionTotIva());
      impuestoTot.valida(Objetos.creaListRetenciones(),Objetos.creaListTraslados(), Objetos.creaCatMoneda());
    });
    Assertions.assertTrue(e.getMessage().contains("No se encuentra en los conceptos el impuesto"));
  }
  
  @Test
  public void validaRetencionesTotmasDecimales(){
    ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
      RetencionTot tmp = impuestoTot.getRetenciones().get(0);
      tmp.setImporte(Objetos.creaImporte("152.981"));
      impuestoTot.valida(Objetos.creaListRetenciones(),Objetos.creaListTraslados(), Objetos.creaCatMoneda());
    });
    Assertions.assertTrue(e.getMessage().contains("Los decimales del impuesto retenido"));
  }
  
  @Test
  public void validaRetencionesConceptoOk(){

    List<RetencionConcepto> tmp = Objetos.creaListRetenciones();
    RetencionConcepto otro = Objetos.creaRetencionConcepto();
    tmp.add(otro);
    List<RetencionTot> tot = impuestoTot.getRetenciones();
    for(RetencionTot aux : tot){
      if(aux.getImpuesto().equals(otro.getImpuesto())){
        aux.getImporte().setValor("203.96");
      }
    }
    impuestoTot.setTotalImpuestosRetenidos(Objetos.creaImporte("305.94"));
    impuestoTot.valida(tmp,Objetos.creaListTraslados(), Objetos.creaCatMoneda());
  }

  @Test
  public void validaNoListaTraslados(){
    
    ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
      impuestoTot.valida(Objetos.creaListRetenciones(), null, Objetos.creaCatMoneda());
    });
    Assertions.assertTrue(e.getMessage().contains("No existen impuestos en los conceptos no deberian existir Traslados en los totales"));
  }
  
  @Test
  public void validaListaTrasladoFaltanImpuestos(){
    
    ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
      List<TrasladoConcepto> tmp = Objetos.creaListTraslados();
      tmp.remove(0);
      impuestoTot.valida(Objetos.creaListRetenciones(), tmp, Objetos.creaCatMoneda());
    });
    Assertions.assertTrue(e.getMessage().contains("No se encuentra en los conceptos el impuesto"));
    
  }

  @Test
  public void validaTrasladoTotFaltantes(){
    
    ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
      impuestoTot.getTraslados().remove(0);
      impuestoTot.valida(Objetos.creaListRetenciones(), Objetos.creaListTraslados(), Objetos.creaCatMoneda());
    });
    Assertions.assertTrue(e.getMessage().contains("Hacen falta impuestos en los totales"));
    
  }
  @Test
  public void validaTrasladoTotdoble(){
    
    ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
      impuestoTot.getTraslados().add(Objetos.creaTrasladoTotIva());
      impuestoTot.valida(Objetos.creaListRetenciones(),Objetos.creaListTraslados(), Objetos.creaCatMoneda());
    });
    Assertions.assertTrue(e.getMessage().contains("No se encuentra en los conceptos el impuesto"));
    
  }
  @Test
  public void validaTrasladoTotmasDecimales(){
    
    ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
      TrasladoTot tmp = impuestoTot.getTraslados().get(0);
      tmp.setImporte(Objetos.creaImporte("189.569"));
      impuestoTot.valida(Objetos.creaListRetenciones(),Objetos.creaListTraslados(), Objetos.creaCatMoneda());
    });
    Assertions.assertTrue(e.getMessage().contains("Los decimales del impuesto trasladado"));
  }
  @Test
  public void validaTrasladoConceptoOk(){

    List<TrasladoConcepto> tmp = Objetos.creaListTraslados();
    TrasladoConcepto otro = Objetos.creaTrasladoConcepto();
    tmp.add(otro);
    List<TrasladoTot> tot = impuestoTot.getTraslados();
    for(TrasladoTot aux : tot){
      if(aux.getImpuesto().equals(otro.getImpuesto())){
        aux.getImporte().setValor("379.13");
      }
    }
    impuestoTot.setTotalImpuestosTransladados(Objetos.creaImporte("497.60"));
    impuestoTot.valida(Objetos.creaListRetenciones(),tmp, Objetos.creaCatMoneda());
  }

  @Test
  public void validaObligatorios(){
    impuestoTot.validaObligatorios();
  }
  @Test
  public void validaObligatoriosNoImpuestos(){
    ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
      impuestoTot.setRetenciones(Collections.emptyList());
      impuestoTot.setTraslados(null);
      impuestoTot.validaObligatorios();
    });
    Assertions.assertTrue(e.getMessage().contains("Deben existir impuestos"));

  }

}
