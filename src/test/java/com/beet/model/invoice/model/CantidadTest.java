package com.beet.model.invoice.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.Cantidad;


public class CantidadTest {

	MathContext matContext = new MathContext(24, RoundingMode.HALF_UP);
	
	@Test
	public void testCantidad6decimales() {
		Cantidad cantidad = new Cantidad();
		cantidad.setValor(new BigDecimal(0.000001).setScale(6, RoundingMode.HALF_UP));
	}

	@Test
	public void testCantidad8decimales() {
		Cantidad cantidad = new Cantidad();
		cantidad.setValor(new BigDecimal(0.00000100).setScale(8, RoundingMode.HALF_UP));
	}
	
	@Test
	public void testCantidad8errordecimales() {
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			Cantidad cantidad = new Cantidad();
			cantidad.setValor(new BigDecimal(0.00000101).setScale(8, RoundingMode.HALF_UP));
		});
	}
	
	@Test
	public void testCantidaderrorNegativo() {
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			Cantidad cantidad = new Cantidad();
			cantidad.setValor(new BigDecimal(-1));
		});
	}
		
}
