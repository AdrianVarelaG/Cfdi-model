package com.beet.model.invoice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.Confirmacion;

public class ConfirmacionTest {

	@Test
	public void testInisializaOK() {
		String valor = "24C5a";
		Confirmacion Confirmacion = new Confirmacion(valor);
		Assertions.assertTrue(Confirmacion.getvalor().equals(valor));
	}
	
	@Test
	public void testErrorFormato(){
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			String valor = "24ñ5a";
			Confirmacion Confirmacion = new Confirmacion(valor);
			Assertions.assertTrue(Confirmacion.getvalor().equals(valor));
		});
	}
	
	@Test
	public void testLongitudMayor(){
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			String valor = "24C5aA";
			Confirmacion Confirmacion = new Confirmacion(valor);
			Assertions.assertTrue(Confirmacion.getvalor().equals(valor));
		});
	}
	
	@Test
	public void testLongitudMenor(){
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			String valor = "2C5a";
			Confirmacion Confirmacion = new Confirmacion(valor);
			Assertions.assertTrue(Confirmacion.getvalor().equals(valor));
		});
	}

}
