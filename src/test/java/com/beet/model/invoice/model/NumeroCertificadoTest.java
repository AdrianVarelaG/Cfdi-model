package com.beet.model.invoice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.NumeroCertificado;

public class NumeroCertificadoTest {

	@Test
	public void testInisializaOK() {
		String valor = "00000012345698745690";
		NumeroCertificado NumeroCertificado = new NumeroCertificado(valor);
		Assertions.assertTrue(NumeroCertificado.getvalor().equals(valor));
	}
	
	@Test
	public void testErrorFormato(){
	  
	  Assertions.assertThrows(ValidacionExcepcion.class, ()->{
	    String valor = "00000O12345698745690";
	    new NumeroCertificado(valor);
	  });
	  
	}
	
	@Test
	public void testLongitudMayor(){
	  
	  Assertions.assertThrows(ValidacionExcepcion.class, ()->{
	    String valor = "000000123456987456923";
        new NumeroCertificado(valor);
	  });
	  
	}
	@Test
	public void testLongitudMenor(){
	  
	  Assertions.assertThrows(ValidacionExcepcion.class, ()->{
	    String valor = "0000001234569874569";
        new NumeroCertificado(valor);
      });
	}

}
