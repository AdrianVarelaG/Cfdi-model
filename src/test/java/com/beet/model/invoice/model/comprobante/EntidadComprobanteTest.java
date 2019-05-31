package com.beet.model.invoice.model.comprobante;

import java.math.RoundingMode;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.Objetos;

public class EntidadComprobanteTest {

	private EntidadComprobante comprobante;
	

	
	@BeforeEach
	public void inicializa(){
		comprobante = Objetos.creaComprobante();
	}
	
	@Test
	public void testValidaObligatorios() {
		comprobante.validaObligatorios();
	}
	@Test
	public void testValidaObligatoriosNoVersion(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.setVersion(null);
        comprobante.validaObligatorios();
	  });
	  
	  Assertions.assertTrue(e.getMessage().contains("La version es obligatoria"));
		
	}
	@Test
	public void testValidaObligatoriosNoFecha(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.setFecha(null);
        comprobante.validaObligatorios();
      });
      
      Assertions.assertTrue(e.getMessage().contains("La fecha es obligatoria"));
		
	}
	@Test
	public void testValidaObligatoriosNoNumeroCertificado(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.setNumeroCertificado(null);
        comprobante.validaObligatorios();
      });
      
      Assertions.assertTrue(e.getMessage().contains("El numeroCertificado es obligatorio"));
		
	}
	@Test
	public void testValidaObligatoriosNooCertificado(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.setCertificado(null);
        comprobante.validaObligatorios();
      });
      
      Assertions.assertTrue(e.getMessage().contains("El certificado es obligatorio"));
		
	}
	@Test
	public void testValidaObligatoriosNoSubtotal(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.setSubtotal(null);
        comprobante.validaObligatorios();
      });
      
      Assertions.assertTrue(e.getMessage().contains("El subtotal es obligatorio"));

	}
	@Test
	public void testValidaObligatoriosNoMoneda(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.setMoneda(null);
        comprobante.validaObligatorios();
      });   
      Assertions.assertTrue(e.getMessage().contains("La moneda es obligatoria"));

	}
	@Test
	public void testValidaObligatoriosNoTotal(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.setTotal(null);
        comprobante.validaObligatorios();
      });   
      Assertions.assertTrue(e.getMessage().contains("El total es obligatorio"));
		
	}
	@Test
	public void testValidaObligatoriosNoTipoComprobante(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.setTipoComprobante(null);
        comprobante.validaObligatorios();
      });   
      Assertions.assertTrue(e.getMessage().contains("El tipo de comprobante es obligatorio"));
	}
	@Test
	public void testValidaObligatoriosNoLugarExpedicion(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.setLugarExpedicion(null);
        comprobante.validaObligatorios();
      });   
      Assertions.assertTrue(e.getMessage().contains("El lugar de expedicion es obligatorio"));

	}
	@Test
	public void testValidaObligatoriosNoEmisor(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.setEmisor(null);
        comprobante.validaObligatorios();
      });   
      Assertions.assertTrue(e.getMessage().contains("El emisor es obligatorio"));

	}
	@Test
	public void testValidaObligatoriosNoReceptor(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.setReceptor(null);
        comprobante.validaObligatorios();
      });   
      Assertions.assertTrue(e.getMessage().contains("El receptor es obligatorio"));
		
	}
	@Test
	public void testValidaObligatoriosNoConcepto(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.setConceptos(new ArrayList<>());
        comprobante.validaObligatorios();
      });   
      Assertions.assertTrue(e.getMessage().contains("El comprobante debe contener al menos un concepto"));
		
	}
	@Test
	public void testValidaObligatoriosNoPago(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.getTipoComprobante().setClaveSat("P");
        comprobante.validaObligatorios();
      });   
      Assertions.assertTrue(e.getMessage().contains("El comprobante debe incluir el complemento de pago"));
		
	}
	@Test
	public void testValida(){
		comprobante.validaComprobante();
	}
	@Test
	public void testValidaVersion(){
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.getVersion().setValor("3.2");
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("La version que se admite es"));
	}
	@Test
	public void testValidaTipoComprobanteIFormaPago(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.setFormaPago(null);
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("La forma de pago debe existir"));
	  
	}
	@Test
	public void testValidaTipoComprobanteEFormaPago(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.getTipoComprobante().setClaveSat("E");
        comprobante.setFormaPago(null);
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("La forma de pago debe existir"));
	}
	@Test
	public void testValidaTipoComprobanteTConcionesPago(){
	  
	   ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	       comprobante.getTipoComprobante().setClaveSat("T");
	        comprobante.getTipoComprobante().setDescripcion("Traslado");
	        comprobante.validaComprobante();
	      });   
	   Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
	   Assertions.assertTrue(e.getMessage().contains("el campo condicionesPago no debe existir"));

	}
	@Test
	public void testValidaTipoComprobanteTDescuentos(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.getTipoComprobante().setClaveSat("T");
	    comprobante.getTipoComprobante().setDescripcion("Traslado");
	    comprobante.setCondicionesPago(null);
	    comprobante.getConceptos().get(0).setDescuento(Objetos.creaImporte("10"));
	    comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
      Assertions.assertTrue(e.getMessage().contains("no deben existir decuentos"));
      
	}
	@Test
	public void testValidaTipoComprobanteTImpuestos(){
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.getTipoComprobante().setClaveSat("T");
        comprobante.getTipoComprobante().setDescripcion("Traslado");
        comprobante.setCondicionesPago(null);

        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
      Assertions.assertTrue(e.getMessage().contains("no deben existir impuestos"));
	}
	@Test
	public void testValidaTipoComprobanteTFormaPago(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.getTipoComprobante().setClaveSat("T");
        comprobante.getTipoComprobante().setDescripcion("Traslado");
        comprobante.setCondicionesPago(null);
        comprobante.setImpuestos(null);
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
      Assertions.assertTrue(e.getMessage().contains("no deben existir el campo forma de pago"));
		

	}
	@Test
	public void testValidaTipoComprobanteTMetodoPago(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.getTipoComprobante().setClaveSat("T");
        comprobante.getTipoComprobante().setDescripcion("Traslado");
        comprobante.setCondicionesPago(null);
        comprobante.setImpuestos(null);
        comprobante.setFormaPago(null);
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
      Assertions.assertTrue(e.getMessage().contains("no deben existir el campo metodo de pago"));
      
	}
	@Test
	public void testValidaTipoComprobantePConcionesPago(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.getTipoComprobante().setClaveSat("P");
        comprobante.getTipoComprobante().setDescripcion("Pago");
        comprobante.setPago(Objetos.creaComplementoPago());
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
      Assertions.assertTrue(e.getMessage().contains("el campo condicionesPago no debe existir"));

	}
	@Test
	public void testValidaTipoComprobantePDescuentos(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.getTipoComprobante().setClaveSat("P");
        comprobante.getTipoComprobante().setDescripcion("Pago");
        comprobante.setCondicionesPago(null);
        comprobante.getConceptos().get(0).setDescuento(Objetos.creaImporte("10"));
        comprobante.setPago(Objetos.creaComplementoPago());
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
      Assertions.assertTrue(e.getMessage().contains("no deben existir decuentos"));

	}
	@Test
	public void testValidaTipoComprobantePImpuestos(){
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.getTipoComprobante().setClaveSat("P");
        comprobante.getTipoComprobante().setDescripcion("Pago");
        comprobante.setCondicionesPago(null);
        comprobante.setPago(Objetos.creaComplementoPago());
        comprobante.validaComprobante();
      });
	  
      Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
      Assertions.assertTrue(e.getMessage().contains("no deben existir impuestos"));
      

	}
	@Test
	public void testValidaTipoComprobantePFormaPago(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.getTipoComprobante().setClaveSat("P");
        comprobante.getTipoComprobante().setDescripcion("Pago");
        comprobante.setCondicionesPago(null);
        comprobante.setImpuestos(null);
        comprobante.setPago(Objetos.creaComplementoPago());
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
      Assertions.assertTrue(e.getMessage().contains("no deben existir el campo forma de pago"));
		
	}
	@Test
	public void testValidaTipoComprobantePMetodoPago(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.getTipoComprobante().setClaveSat("P");
        comprobante.getTipoComprobante().setDescripcion("Pago");
        comprobante.setCondicionesPago(null);
        comprobante.setImpuestos(null);
        comprobante.setFormaPago(null);
        comprobante.setPago(Objetos.creaComplementoPago());
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
      Assertions.assertTrue(e.getMessage().contains("no deben existir el campo metodo de pago"));
	
	}
	@Test
	public void testValidaTipoComprobanteNConcionesPago(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	     comprobante.getTipoComprobante().setClaveSat("N");
	     comprobante.getTipoComprobante().setDescripcion("Nomina");
	     comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
      Assertions.assertTrue(e.getMessage().contains("el campo condicionesPago no debe existir"));

	}

	@Test
	public void testValidaTipoComprobanteNImpuestos(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.getTipoComprobante().setClaveSat("N");
        comprobante.getTipoComprobante().setDescripcion("Nomina");
        comprobante.setCondicionesPago(null);

        comprobante.validaComprobante();
     });   
     Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
     Assertions.assertTrue(e.getMessage().contains("no deben existir impuestos"));

	}
	
	@Test
	public void testValidaTipoComprobanteOtro(){
	  
	 ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.getTipoComprobante().setClaveSat("J");
        comprobante.validaComprobante();
     });   
     Assertions.assertTrue(e.getMessage().contains("Valor invalido de tipo de comprobante"));
		
	}
	@Test
	public void testValidaFormaPagoNovalida(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.getFormaPago().setClaveSat("99");
        comprobante.validaComprobante();
     });   
     Assertions.assertTrue(e.getMessage().contains("La forma de pago:"));
     Assertions.assertTrue(e.getMessage().contains("y el metodo de pago:"));
     Assertions.assertTrue(e.getMessage().contains("no es valido"));
		
	}
	@Test
	public void testValidaFormaPago(){		
		comprobante.getFormaPago().setClaveSat("99");
		comprobante.getMetodoPago().setClaveSat("PPD");
		comprobante.validaComprobante();
	}
	@Test
	public void testValidaTSubTotal(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.getTipoComprobante().setClaveSat("T");
        comprobante.getTipoComprobante().setDescripcion("Traslado");
        comprobante.setCondicionesPago(null);
        comprobante.setImpuestos(null);
        comprobante.setFormaPago(null);
        comprobante.setMetodoPago(null);
        comprobante.validaComprobante();
     });   
     Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
     Assertions.assertTrue(e.getMessage().contains("el subtotal debe ser cero"));

	}
	@Test
	public void testValidaPSubTotal(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.getTipoComprobante().setClaveSat("P");
        comprobante.getTipoComprobante().setDescripcion("Pago");
        comprobante.setCondicionesPago(null);
        comprobante.setImpuestos(null);
        comprobante.setFormaPago(null);
        comprobante.setMetodoPago(null);
        comprobante.setPago(Objetos.creaComplementoPago());
        comprobante.validaComprobante();
     });   
     Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante"));
     Assertions.assertTrue(e.getMessage().contains("el subtotal debe ser cero"));
		
	}
	@Test
	public void testValidaSubTotalMasDecimales(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.setSubtotal(comprobante.getSubtotal().suma(Objetos.creaImporte("0.001")));
        comprobante.validaComprobante();
     });   
     Assertions.assertTrue(e.getMessage().contains("El numero de decimales del SubTotal"));
     Assertions.assertTrue(e.getMessage().contains("excede los decimales de la moneda"));

	}
	@Test
	public void testValidaSubTotalMayor(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.getTipoComprobante().setClaveSat("E");
        comprobante.setSubtotal(comprobante.getSubtotal().resta(Objetos.creaImporte("1.01")));
        comprobante.validaComprobante();
     });   
     Assertions.assertTrue(e.getMessage().contains("El subtotal"));
     Assertions.assertTrue(e.getMessage().contains("no concuerda con la suma de los conceptos"));

	}
	
	@Test
	public void testValidaDescuentos(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.getTipoComprobante().setClaveSat("N");
        comprobante.getTipoComprobante().setDescripcion("Nomina");
        comprobante.setCondicionesPago(null);
        comprobante.setImpuestos(null);
        comprobante.getConceptos().get(0).setDescuento(Objetos.creaImporte("10"));

        comprobante.validaComprobante();
     });   
     Assertions.assertTrue(e.getMessage().contains("Existen descuentos en los conceptos debe incluir el campo descuento en el comprobante"));		
		
	}
	@Test
	public void testValidaDescuentosMasDecimales(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.getConceptos().get(0).setDescuento(Objetos.creaImporte("10"));
        comprobante.setDescuento(Objetos.creaImporte("9.999"));
        comprobante.validaComprobante();
     });   
     Assertions.assertTrue(e.getMessage().contains("El numero de decimales del Descuento")); 
     Assertions.assertTrue(e.getMessage().contains("excede los decimales de la moneda"));
		
	}
	@Test
	public void testValidaDescuentosDecuentosNoconcuerdan(){
	  
	   ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	     comprobante.getTipoComprobante().setClaveSat("E");
	     comprobante.getConceptos().get(0).setDescuento(Objetos.creaImporte("10"));
	     comprobante.getConceptos().add(Objetos.creaConcepto());
	     comprobante.getConceptos().get(1).setDescuento(Objetos.creaImporte("20"));
	     comprobante.setSubtotal(comprobante.getSubtotal().suma(comprobante.getConceptos().get(1).getImporte()));
	     comprobante.getSubtotal().setScale(comprobante.getMoneda().getDecimales(), RoundingMode.HALF_UP);
	     comprobante.setDescuento(Objetos.creaImporte("25"));
	     comprobante.validaComprobante();
	   });   
	   Assertions.assertTrue(e.getMessage().contains("El descuento total")); 
	   Assertions.assertTrue(e.getMessage().contains("no concuerda con la suma de los descuentos"));

	}
	@Test
	public void testValidaDescuentosMayoaSubtotal(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.getTipoComprobante().setClaveSat("N");
        comprobante.getTipoComprobante().setDescripcion("Nomina");
        comprobante.setCondicionesPago(null);
        comprobante.setImpuestos(null);
        comprobante.getConceptos().get(0).setDescuento(comprobante.getSubtotal().suma(Objetos.creaImporte("3")));
        comprobante.setDescuento(comprobante.getSubtotal().suma(Objetos.creaImporte("3")));
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("El descuento")); 
      Assertions.assertTrue(e.getMessage().contains("no puede ser mayor que el subtotal"));

	}
	@Test
	public void testValidaDescuentosNoConceptos(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.setDescuento(Objetos.creaImporte("10"));
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("Los conceptos no contienen descuentos no se debe incluir el campo en el comprobante")); 
	
	}
	@Test
	public void testValidaDescuentosNoError(){		
		comprobante.getConceptos().get(0).setDescuento(Objetos.creaImporte("10"));
		comprobante.setDescuento(Objetos.creaImporte("10"));
		comprobante.setTotal(comprobante.getTotal().resta(comprobante.getDescuento()));
		comprobante.validaComprobante();
	}
	@Test
	public void testValidaNMonedaNOMxn(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.getTipoComprobante().setClaveSat("N");
        comprobante.getTipoComprobante().setDescripcion("Nomina");
        comprobante.setCondicionesPago(null);
        comprobante.setImpuestos(null);
        comprobante.getMoneda().setClaveSat("ARS");

        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("El tipo de moneda para el comprobante")); 
      Assertions.assertTrue(e.getMessage().contains("solo puede ser"));

	}
	@Test
	public void testValidaPTotal(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.getTipoComprobante().setClaveSat("P");
        comprobante.getTipoComprobante().setDescripcion("Pago");
        comprobante.setCondicionesPago(null);
        comprobante.setImpuestos(null);
        comprobante.setFormaPago(null);
        comprobante.setMetodoPago(null);
        comprobante.setPago(Objetos.creaComplementoPago());
        comprobante.setSubtotal(Objetos.creaImporte("0"));
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("Para el tipo de comprobante")); 
      Assertions.assertTrue(e.getMessage().contains("el total debe ser cero"));

	}
	@Test
	public void testValidaTotalMasDecimales(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
        comprobante.setTotal(comprobante.getTotal().suma(Objetos.creaImporte("0.001")));
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("El numero de decimales del Total")); 
      Assertions.assertTrue(e.getMessage().contains("excede los decimales de la moneda"));

	}
	
	@Test
	public void testValidaTotaldiferente(){
	  
	   ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	     comprobante.getImpuestos().setTotalImpuestosRetenidos(Objetos.creaImporte("10"));

	     comprobante.validaComprobante();
	   });   
	   Assertions.assertTrue(e.getMessage().contains("El valor del total")); 
	   Assertions.assertTrue(e.getMessage().contains("no corresponde con el valor calculado"));
	   
	}
	
	@Test
	public void testValidaMetodoPago(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () -> {
	    comprobante.setPago(Objetos.creaComplementoPago());
        comprobante.validaComprobante();
      });   
      Assertions.assertTrue(e.getMessage().contains("No debe existir el metodo de pago")); 
		
	}
	
}
