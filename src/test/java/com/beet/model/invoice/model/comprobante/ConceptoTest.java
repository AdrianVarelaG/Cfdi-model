package com.beet.model.invoice.model.comprobante;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.CatImpuesto;
import com.beet.model.invoice.model.CatMoneda;
import com.beet.model.invoice.model.CatTipoComprobante;
import com.beet.model.invoice.model.Objetos;
import com.beet.model.invoice.model.Requerido;

public class ConceptoTest {
	
	Concepto concepto;

	
	@BeforeEach
	public void inicializa(){
		concepto = Objetos.creaConcepto();
	}
	
	@Test
	public void testValorDentroRango() {
		concepto.valida(Objetos.creaCatTipoComprobante(), Objetos.creaCatMoneda());
	}
	
	@Test
	public void testValorMenor() {
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.getImporte().setValor("193.186524");
        concepto.valida(Objetos.creaCatTipoComprobante(), Objetos.creaCatMoneda());
	  });
	  Assertions.assertTrue(e.getMessage().contains("El importe"));
	  Assertions.assertTrue(e.getMessage().contains("fuera de rango minimo"));
		
	}
	
	@Test
	public void testValorMayor() {
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.getImporte().setValor("193.564726");
	    concepto.valida(Objetos.creaCatTipoComprobante(), Objetos.creaCatMoneda());  
	  });
	  Assertions.assertTrue(e.getMessage().contains("El importe"));
	  Assertions.assertTrue(e.getMessage().contains("fuera de rango minimo"));

	}
	
	@Test
	public void testValorIngresoCero() {
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.getValorUnitario().setValor("0");
        concepto.valida(Objetos.creaCatTipoComprobante(), Objetos.creaCatMoneda()); 
      });
      Assertions.assertTrue(e.getMessage().contains("El valor unitario no es valido"));
      Assertions.assertTrue(e.getMessage().contains("debe ser mayor a cero para el tipo de comprobante I"));
		
	}
	@Test
	public void testValorEgresoCero() {
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
        Concepto concepto = Objetos.creaConcepto();
        CatTipoComprobante tipoComprobante = Objetos.creaCatTipoComprobante();
        tipoComprobante.setClaveSat("E");
        concepto.getValorUnitario().setValor("0");
        concepto.valida(tipoComprobante, Objetos.creaCatMoneda());
      });
      Assertions.assertTrue(e.getMessage().contains("El valor unitario no es valido"));
      Assertions.assertTrue(e.getMessage().contains("debe ser mayor a cero para el tipo de comprobante E"));

	}
	@Test
	public void testValorNominaCero() {
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
        CatTipoComprobante tipoComprobante = Objetos.creaCatTipoComprobante();
        tipoComprobante.setClaveSat("N");
        concepto.getValorUnitario().setValor("0");
        concepto.valida(tipoComprobante, Objetos.creaCatMoneda());
      });
      Assertions.assertTrue(e.getMessage().contains("El valor unitario no es valido"));
      Assertions.assertTrue(e.getMessage().contains("debe ser mayor a cero para el tipo de comprobante N"));

	}
	@Test
	public void testValorPagoCero() {
		CatTipoComprobante tipoComprobante = Objetos.creaCatTipoComprobante();
		tipoComprobante.setClaveSat("P");
		concepto.getValorUnitario().setValor("0");
		concepto.getImporte().setValor("0");
		concepto.valida(tipoComprobante, Objetos.creaCatMoneda());
	}
	@Test
	public void testMinimo() {
		CatTipoComprobante tipoComprobante = Objetos.creaCatTipoComprobante();
		//tipoComprobante.setClaveSat("P");
		concepto.getValorUnitario().setValor("0.000001");
		concepto.getCantidad().setValor("0.000001");
		concepto.getImporte().setValor("0.00000");
		concepto.valida(tipoComprobante, Objetos.creaCatMoneda());
	}
	
	@Test
	public void testValorDescuento() {
		
		concepto.setCantidad(Objetos.creaCantidad("14.16"));
		concepto.setValorUnitario(Objetos.creaImporte("13.66"));
		concepto.getImporte().setValor("193.4256");
		concepto.setDescuento(Objetos.creaImporte("193.425"));

		concepto.valida(Objetos.creaCatTipoComprobante(), Objetos.creaCatMoneda());
	}
	
	@Test
	public void testDescuentoMasDecimales() {
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
        concepto.setCantidad(Objetos.creaCantidad("14.16"));
        concepto.setValorUnitario(Objetos.creaImporte("13.66"));
        concepto.getImporte().setValor("193.4256");
        concepto.setDescuento(Objetos.creaImporte("193.42559"));
        
        concepto.valida(Objetos.creaCatTipoComprobante(), Objetos.creaCatMoneda());
      });
      Assertions.assertTrue(e.getMessage().contains("El numero de decimales del descuento"));
      Assertions.assertTrue(e.getMessage().contains("supera el numero de decimales del importe"));

	}
	
	@Test
	public void testDescuentoMayor() {
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
        concepto.setCantidad(Objetos.creaCantidad("14.16"));
        concepto.setValorUnitario(Objetos.creaImporte("13.66"));
        concepto.getImporte().setValor("193.4256");
        concepto.setDescuento(Objetos.creaImporte("193.4257"));
        
        concepto.valida(Objetos.creaCatTipoComprobante(), Objetos.creaCatMoneda());
      });
      Assertions.assertTrue(e.getMessage().contains("El descuento"));
      Assertions.assertTrue(e.getMessage().contains("no debe ser mayor que el importe"));	

	}
	
	@Test
	public void testTrasladoIvaNO() {
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.getClaveProdServ().setIvaTrasladado(Requerido.NO);
        concepto.valida(Objetos.creaCatTipoComprobante(), Objetos.creaCatMoneda());
      });
      Assertions.assertTrue(e.getMessage().contains("El concepto"));
      Assertions.assertTrue(e.getMessage().contains("no puede contener traslado de IVA"));
		
	}
	@Test
	public void testTrasladoIvaSINullImpuestos() {
	  
	   ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	       concepto.getClaveProdServ().setIvaTrasladado(Requerido.SI);
	        concepto.setImpuestos(null);
	        concepto.valida(Objetos.creaCatTipoComprobante(), Objetos.creaCatMoneda());
	    });
	    Assertions.assertTrue(e.getMessage().contains("requiere IVA trasladado"));
		

	}
	@Test
	public void testTrasladoIvaSIsinIvatrasladado() {
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.getClaveProdServ().setIvaTrasladado(Requerido.SI);
        CatImpuesto tmp =concepto.getImpuestos().getTraslados().get(0).getImpuesto(); 
        tmp.setClaveSat("003");
        tmp.setDescripcion("IEPS");
        concepto.valida(Objetos.creaCatTipoComprobante(), Objetos.creaCatMoneda()); 
	  });
	  Assertions.assertTrue(e.getMessage().contains("El concepto"));
	  Assertions.assertTrue(e.getMessage().contains("requiere IVA trasladado"));
		
	}
	@Test
	public void testTrasladoIepsNO() {
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.getClaveProdServ().setIepsTrasladado(Requerido.NO);
	      CatImpuesto tmp =concepto.getImpuestos().getTraslados().get(0).getImpuesto(); 
	      tmp.setClaveSat("003");
	      tmp.setDescripcion("IEPS");
	      concepto.valida(Objetos.creaCatTipoComprobante(), Objetos.creaCatMoneda()); 
      });
      Assertions.assertTrue(e.getMessage().contains("El concepto"));
      Assertions.assertTrue(e.getMessage().contains("no puede contener traslado de IEPS"));
		
	  
	}
	@Test
	public void testTrasladoIepsSINullImpuestos() {
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.getClaveProdServ().setIepsTrasladado(Requerido.SI);
        concepto.setImpuestos(null);
        concepto.valida(Objetos.creaCatTipoComprobante(), Objetos.creaCatMoneda()); 
      });
      Assertions.assertTrue(e.getMessage().contains("requiere IEPS trasladado"));
		
	}
	@Test
	public void testTrasladoIvaSIsinIepstrasladado() {
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.getClaveProdServ().setIepsTrasladado(Requerido.SI);
        concepto.valida(Objetos.creaCatTipoComprobante(), Objetos.creaCatMoneda()); 
      });
      Assertions.assertTrue(e.getMessage().contains("El concepto"));
      Assertions.assertTrue(e.getMessage().contains("requiere IEPS trasladado"));
	  
	}
	@Test
	public void testConceptoPago(){
		
		concepto.setImpuestos(null);
		concepto.getClaveProdServ().setClaveSat("84111506");
		concepto.getCantidad().setValor("1");
		concepto.getUnidad().setValor("ACT");
		concepto.getClaveUnidad().setClaveSat("ZZ");
		concepto.getDescripcion().setValor("Pago");
		concepto.getValorUnitario().setValor("0");
		concepto.getImporte().setValor("0");
		
		CatTipoComprobante tipoComprobante = Objetos.creaCatTipoComprobante();
		tipoComprobante.setClaveSat("P");
		tipoComprobante.setDescripcion("Pagos");
		
		CatMoneda moneda = Objetos.creaCatMoneda();
		moneda.setClaveSat("XXX");
		
		concepto.valida(tipoComprobante, moneda);
	}
	
	@Test
	public void testValidaObligatorios(){
		concepto.validaObligatorios();
	}
	@Test
	public void testValidaObligatoriosNoClave(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.setClaveProdServ(null);
        concepto.validaObligatorios(); 
      });
      Assertions.assertTrue(e.getMessage().contains("La clave de producto/servicio es obligatoria concepto:"));
		
	}
	@Test
	public void testValidaObligatoriosNoCantidad(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.setCantidad(null);
        concepto.validaObligatorios();
      });
      Assertions.assertTrue(e.getMessage().contains("La cantidad es obligatoria concepto:"));
	
	}
	@Test
	public void testValidaObligatoriosNoClaveUnidad(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.setClaveUnidad(null);
        concepto.validaObligatorios();
      });
      Assertions.assertTrue(e.getMessage().contains("La claveUnidad es obligatoria concepto:"));
	
	}
	@Test
	public void testValidaObligatoriosNoDescripcion(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.setDescripcion(null);
	    concepto.validaObligatorios();
      });
      Assertions.assertTrue(e.getMessage().contains("La descripcion es obligatoria concepto:"));
      
	}
	@Test
	public void testValidaObligatoriosNoValorUnitario(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.setValorUnitario(null);
        concepto.validaObligatorios();
      });
      Assertions.assertTrue(e.getMessage().contains("El valor unitario es obligatorio concepto:"));
		
	}
	@Test
	public void testValidaObligatoriosNoImporte(){
	  
	  ValidacionExcepcion e = Assertions.assertThrows(ValidacionExcepcion.class, () ->{
	    concepto.setImporte(null);
        concepto.validaObligatorios();
      });
      Assertions.assertTrue(e.getMessage().contains("El importe es obligatorio concepto:"));
	}
	
}
