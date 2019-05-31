package com.beet.model.invoice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.RazonSocial;

public class RazonSocialTest {

	@Test
	public void Nomarl() {
		new RazonSocial("com.cubetech.cfdi.domain.error.ValidacionExcepcion: Error de formato para el campo RazonSocialÑnñáéíóú&%$#vlakDPOKARGP98UJ3PFOJRTGIUHNwmfcakh.wef´kwepdlQ{LDLD.Ñ}.cq{po34fl}.C´PWF.{AW.F+´WA.ÑF´PALFa*WÑD´WA.F{PERAKLF´WELF´PLARE{FLAÉL<");
	}
	
	@Test
	public void ErrorLongitud() {
	  Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    new RazonSocial("com.cubetech.cfdi.domain.error.ValidacionExcepcion: Error de formato para el campo RazonSocialÑnñáéíóú&%$#vlakDPOKARGP98UJ3PFOJRTGIUHNwmfcakh.wef´kwepdlQ{LDLD.Ñ}.cq{po34fl}.C´PWF.{AW.F+´WA.ÑF´PALFa*WÑD´WA.F{PERAKLF´WELF´PLARE{FLAÉL<______________________ ");
	  });
	}

	@Test
	public void Pipe() {
	  Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    new RazonSocial("com.cubetech.cfdi.domain.error.ValidacionExcepcion: Error de f|ormato para el campo RazonSocialÑnñáéíóú&%$#vlakDPOKARGP98UJ3PFOJRTGIUHNwmfcakh.wef´kwepdlQ{LDLD.Ñ}.cq{po34fl}.C´PWF.{AW.F+´WA.ÑF´PALFa*WÑD´WA.F{PERAKLF´WELF´PLARE{FLAÉL<");
	  });
	}
}
