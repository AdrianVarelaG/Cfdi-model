package com.beet.model.invoice.model.comprobante;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.Objetos;

public class EmisorTest {

  Emisor emisor;
  
  @BeforeEach
  public void inicializa(){
      emisor = Objetos.creaEmisor();
  }
  
  @Test
  public void testValidaObligatorios() {
      emisor.validaObligatorios();
  }
  @Test
  public void testValidaObligatoriosNoRfc(){
      
      ValidacionExcepcion exception = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
          emisor.setRfc(null);
          emisor.validaObligatorios();
      });
      Assertions.assertTrue(exception.getMessage().contains("El RFC para el emisor es obligatorio"));
  }
  @Test
  public void testValidaObligatoriosNoRegimenFiscal(){
      
      ValidacionExcepcion exception = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
          emisor.setRegimenFiscal(null);
          emisor.validaObligatorios();
      });
      Assertions.assertTrue(exception.getMessage().contains("El regimen fiscal para el emisor es obligatorio"));
  }

}
