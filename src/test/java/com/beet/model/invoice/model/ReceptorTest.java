package com.beet.model.invoice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.CatMetodoPago;
import com.beet.model.invoice.model.Rfc;
import com.beet.model.invoice.model.comprobante.Receptor;

public class ReceptorTest {
	
	private Receptor receptor;
	private CatMetodoPago metodoPago;
	
	@BeforeEach
	public void inicializa(){
		receptor = Objetos.creaReceptor();
		this.metodoPago = Objetos.creaCatMetodoPago();
	}
	
	@Test
	public void InicialOK() {
		receptor.valida(this.metodoPago);
	}
	
	@Test
	public void genericoUsonoValido(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, ()->{
	    receptor.getRfc().setValor(Rfc.GENERICO);
        receptor.setNombre(null);
        receptor.valida(this.metodoPago);
	  });
	  
	  Assertions.assertTrue(e.getMessage().contains("Para el RFC"));
	  Assertions.assertTrue(e.getMessage().contains("solo se permite el uso P01 - Por definir"));
	  
	}
	@Test
	public void genericoConNombre(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, ()->{
	    receptor.getRfc().setValor(Rfc.GENERICO);
        receptor.getUso().setClaveSat("P01");
        receptor.getUso().setDescripcion("Por definir");
        receptor.valida(this.metodoPago);
      });
      
      Assertions.assertTrue(e.getMessage().contains("No se debe incluir nombre en el receptor cuando se manda el RFC generico"));
      
	}
	@Test
	public void genericoSinNombre(){		
		receptor.getRfc().setValor(Rfc.GENERICO);
		receptor.getUso().setClaveSat("P01");
		receptor.getUso().setDescripcion("Por definir");
		receptor.setNombre(null);
		receptor.valida(this.metodoPago);
	}
	@Test
	public void genericoMetodoDePagoPPD(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, ()->{
        receptor.getRfc().setValor(Rfc.GENERICO);
        receptor.getUso().setClaveSat("P01");
        receptor.getUso().setDescripcion("Por definir");
        receptor.setNombre(null);
        this.metodoPago.setClaveSat("PPD");
        this.metodoPago.setDescripcion("Pago en parcialidades o diferido");
        receptor.valida(this.metodoPago);
      });
      Assertions.assertTrue(e.getMessage().contains("solo se permite el metodo de pago PUE - Pago en una sola exhibición"));
	}
	@Test
	public void fisicaUsoInvalidoFisica(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, ()->{
	    receptor.setRfc(Objetos.creaRfc(false));
        receptor.getUso().setFisica(false);
        receptor.valida(this.metodoPago);
      });
      Assertions.assertTrue(e.getMessage().contains("El uso"));
      Assertions.assertTrue(e.getMessage().contains("no es valido para persona fisica"));
      
	}
	@Test
	public void fisicaUsoInvalidoMoral(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, ()->{
        receptor.setRfc(Objetos.creaRfc(true));
        receptor.getUso().setMoral(false);
        receptor.valida(this.metodoPago);
      });
      Assertions.assertTrue(e.getMessage().contains("El uso"));
      Assertions.assertTrue(e.getMessage().contains("no es valido para persona moral"));
	  
	}
	@Test
	public void testValidaObligatorios(){
		receptor.validaObligatorios();
	}
	@Test
	public void testValidaObligatoriosNoRfc(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, ()->{
	    receptor.setRfc(null);
        receptor.validaObligatorios();
      });
      Assertions.assertTrue(e.getMessage().contains("El rfc del receptor es obligatorio"));

	}
	@Test
	public void testValidaObligatoriosNoUso(){
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, ()-> {
	    receptor.setUso(null);
        receptor.validaObligatorios();
      });
      Assertions.assertTrue(e.getMessage().contains("El uso es obligatorio"));
	}
}
