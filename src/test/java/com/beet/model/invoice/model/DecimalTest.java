package com.beet.model.invoice.model;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.Decimal;

public class DecimalTest {

	@Test
	public void testDecimal6decimales() {
		Decimal Decimal = new Decimal();
		Decimal.setValor("-0.000000");
	}
	
	@Test
	public void testDecimalPrueba19digitos(){
		Decimal Decimal = new Decimal();
		Decimal.setValor("1000000000000000000.000001");
	}

	@Test
	public void testDecimal8decimales() {
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			Decimal Decimal = new Decimal();
			Decimal.setValor("100,000,000,000,000,000.0000001");
		});
		
	}
	@Test
	public void testDecimalScintific() {
		Decimal Decimal = new Decimal();
		Decimal.setValor("1.5052587954E6");
	}
	
	@Test
	public void testDecimal() {
		Decimal Decimal = new Decimal();
		Decimal.setValor("00000000000100000000000000000.0000010000000000000");
	}
	
	@Test
	public void testDecimal8errordecimales() {
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			Decimal Decimal = new Decimal();
			Decimal.setValor("0.00000101");
		});
	}
	
	@Test
	public void testDecimalerrorNegativo() {
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			Decimal Decimal = new Decimal();
			Decimal.setValor("-1");
		});
	}
	@Test
	public void testDecimalScintificNoDecimal() {
		Decimal Decimal = new Decimal();
		Decimal.setValor("1505.258E6");
	}
	
	@Test
	public void testDecimalScintificNoDecimalBigdecimal() {
		Decimal Decimal = new Decimal();
		Decimal.setValor(new BigDecimal("1505.258E6"));
	}
	
	@Test
	public void pruebaEquals(){
		Decimal Decimal = new Decimal ();
		Decimal.setValor("152.98");
		Decimal Decimal2 = new Decimal ();
		Decimal2.setValor(new BigDecimal("152.98").setScale(8));
		
		Assertions.assertTrue(Decimal.equals(Decimal2));
	}
	
	@Test
	public void compara(){
		Decimal decimal = new Decimal("158.96");
		Decimal decimal2 = new Decimal("158.95");
		
		Assertions.assertTrue(decimal.comparaMargen(decimal2) == 0 && decimal2.comparaMargen(decimal) == 0);
	}

	@Test
	public void comparaFueraRango(){
		Decimal decimal = new Decimal("159.96");
		Decimal decimal2 = new Decimal("158.95");
		
		Assertions.assertTrue(decimal.comparaMargen(decimal2) == 1 && decimal2.comparaMargen(decimal) == -1);
	}
}
