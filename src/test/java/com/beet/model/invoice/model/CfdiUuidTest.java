package com.beet.model.invoice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.CfdiUuid;

public class CfdiUuidTest {

	@Test
	public void testInisializaOK() {
		String valor = "214C574A-1407-46BE-8406-1B6A038608EA";
		CfdiUuid CfdiRelacion = new CfdiUuid(valor);
		Assertions.assertTrue(CfdiRelacion.getvalor().equals(valor));
	}
	
	@Test
	public void testErrorFormato(){
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			String valor = "214C574A 1407-46BE-8406-1B6A038608EA";
			CfdiUuid CfdiRelacion = new CfdiUuid(valor);
			Assertions.assertTrue(CfdiRelacion.getvalor().equals(valor));
		});
	}
	@Test
	public void testLongitudMayor(){
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			String valor = "214C574A-1407-46BE-8406-1B6A038608EAA";
			CfdiUuid CfdiRelacion = new CfdiUuid(valor);
			Assertions.assertTrue(CfdiRelacion.getvalor().equals(valor));
		  });
	}
	@Test
	public void testLongitudMenor(){
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			String valor = "214C574A-1G07-46BE-8406-1B6A038608EA";
			CfdiUuid CfdiRelacion = new CfdiUuid(valor);
			Assertions.assertTrue(CfdiRelacion.getvalor().equals(valor));
		});
	}

}
