package com.beet.model.invoice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.Version;

public class VersionTest {

	@Test
	public void Normal() {
		
		new Version("3.3");
	}
	@Test
	public void digitos3() {
		new Version("3.3.1");
	}
	
	@Test
	public void ErrorLongitud() {
	  Assertions.assertThrows(ValidacionExcepcion.class, ()->{
	    new Version("3.3 ");
	  });
	}

	@Test
	public void valorDiferente() {
	  Assertions.assertThrows(ValidacionExcepcion.class, ()->{
        new Version("3.2.3.1");
      });
	}
	@Test
	public void igual(){
		Version v = new Version("3.3");
		Assertions.assertTrue(v.igual("3.3"));
	}
	@Test
	public void igualFalso(){
		Version v = new Version("3.3");
		Assertions.assertFalse(v.igual("V3.3"));
	}

	@Test
	public void igualNull(){
		Version v = new Version("3.3");
		Assertions.assertFalse(v.igual(null));
	}
	@Test
	public void ambosnull(){
		Version v = new Version();
		Assertions.assertTrue(v.igual(null));
	}
	@Test
	public void ValorVersionNull(){
		Version v = new Version();
		Assertions.assertFalse(v.igual("3.3"));
	}

}
