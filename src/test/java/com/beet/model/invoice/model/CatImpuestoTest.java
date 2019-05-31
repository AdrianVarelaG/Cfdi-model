package com.beet.model.invoice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.model.CatImpuesto;
import com.beet.model.invoice.model.ImpuestoEnum;

public class CatImpuestoTest {

	@Test
	public void test() {
		CatImpuesto impuesto = new CatImpuesto();
		impuesto.setClaveSat("002");
		impuesto.setDescripcion("IVA");
		impuesto.setRetencion(false);
		impuesto.setTipo(ImpuestoEnum.FEDERAL);
		impuesto.setTraslado(false);

		
		CatImpuesto impuesto2 = new CatImpuesto();
		impuesto2.setClaveSat("002");
		impuesto2.setDescripcion("ISR");
		impuesto2.setRetencion(true);
		impuesto2.setTipo(ImpuestoEnum.LOCAL);
		impuesto2.setTraslado(true);

		
		Assertions.assertTrue((impuesto.equals(impuesto2)));
	}

}
