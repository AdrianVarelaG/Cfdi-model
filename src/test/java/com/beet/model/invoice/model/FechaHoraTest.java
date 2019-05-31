package com.beet.model.invoice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.FechaHora;

public class FechaHoraTest {

	FechaHora fecha;
	
	@Test
	public void testConversionOk() {
	 this.fecha = new FechaHora("2017-09-07T09:33:56");
	 Assertions.assertTrue(fecha.getFecha().getMinute() == 33);
	}

	@Test
	public void testConversionError() {
		Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			this.fecha = new FechaHora("2017-09-07 09:33:56");
			Assertions.assertTrue(fecha.getFecha().getMinute() == 33);
		});
	}
}
