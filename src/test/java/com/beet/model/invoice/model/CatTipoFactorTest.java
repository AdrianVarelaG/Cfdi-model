package com.beet.model.invoice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.model.CatTipoFactor;

public class CatTipoFactorTest {

	@Test
	public void EqualsTrueTest() {
		CatTipoFactor catTipoFactor = new CatTipoFactor();
		catTipoFactor.setClaveSat("Tasa");
		catTipoFactor.setDescripcion("Tasa");

		
		CatTipoFactor catTipoFactor2 = new CatTipoFactor();
		catTipoFactor2.setClaveSat("Tasa");
		catTipoFactor2.setDescripcion("Tasa2");

		
		Assertions.assertTrue(catTipoFactor.equals(catTipoFactor2));
	}

	@Test
	public void EqualsFalseTest() {
		CatTipoFactor catTipoFactor = new CatTipoFactor();
		catTipoFactor.setClaveSat("Tasa ");
		catTipoFactor.setDescripcion("Tasa");

		
		CatTipoFactor catTipoFactor2 = new CatTipoFactor();
		catTipoFactor2.setClaveSat("Tasa");
		catTipoFactor2.setDescripcion("Tasa");

		
		Assertions.assertFalse(catTipoFactor.equals(catTipoFactor2));
	}
}
