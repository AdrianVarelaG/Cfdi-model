package com.beet.model.invoice.model;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.Importe;


public class ImporteTest {

	
	@Test
	public void testImporte6decimales() {
		Importe Importe = new Importe();
		Importe.setValor("-0.000000");
	}
	
	@Test
	public void testImportePrueba19digitos(){
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			Importe Importe = new Importe();
			Importe.setValor("1000000000000000000.000001");
		});
		
	}

	@Test
	public void testImporte8decimales() {
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			Importe Importe = new Importe();
			Importe.setValor("100,000,000,000,000,000.0000001");
		});
	}
	@Test
	public void testImporteScintific() {
		Importe Importe = new Importe();
		Importe.setValor("1.5052587954E6");
	}
	
	@Test
	public void testImporte() {
		Importe Importe = new Importe();
		Importe.setValor("00000000000100000000000000000.0000010000000000000");
	}
	
	@Test
	public void testImporte8errordecimales() {
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			Importe Importe = new Importe();
			Importe.setValor("0.00000101");
		});
	}
	
	@Test
	public void testImporteerrorNegativo() {
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			Importe Importe = new Importe();
			Importe.setValor("-1");
		});
	}
	@Test
	public void testImporteScintificNoDecimal() {
		Importe Importe = new Importe();
		Importe.setValor("1505.258E6");
	}
	
	@Test
	public void testImporteScintificNoDecimalBigdecimal() {
		Importe Importe = new Importe();
		Importe.setValor(new BigDecimal("1505.258E6"));
	}
	
	@Test
	public void pruebaEquals(){
		Importe importe = new Importe ();
		importe.setValor("152.98");
		Importe importe2 = new Importe ();
		importe2.setValor(new BigDecimal("152.98").setScale(8));
		
		Assertions.assertTrue(importe.equals(importe2));
	}

}
