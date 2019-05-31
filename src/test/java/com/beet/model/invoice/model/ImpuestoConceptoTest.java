package com.beet.model.invoice.model;

import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.ImpuestoConcepto;

public class ImpuestoConceptoTest {
	
	ImpuestoConcepto impuestoConcepto;
	
	@BeforeEach
	public void inicialiaza(){
		impuestoConcepto = Objetos.creatImpuestoConcepto();
	}
	
	@Test
	public void testValidaObligatorios() {
		impuestoConcepto.validaObligatorios();
	}

	@Test
	public void validaObligatoriosNoImpuestos(){		
		ValidacionExcepcion exception = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
			
			impuestoConcepto.setRetenciones(Collections.emptyList());
			impuestoConcepto.setTraslados(null);
			impuestoConcepto.validaObligatorios();
		});
		Assertions.assertTrue(exception.getMessage().contains("Los impuestos en el concepto deben existir"));
		
		
	}
	
}
