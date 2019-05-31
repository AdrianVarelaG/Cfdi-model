package com.beet.model.invoice.model.comprobante;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.Cantidad;
import com.beet.model.invoice.model.CatClave;
import com.beet.model.invoice.model.CatMoneda;
import com.beet.model.invoice.model.CatTipoComprobante;
import com.beet.model.invoice.model.CatUnidad;
import com.beet.model.invoice.model.Decimal;
import com.beet.model.invoice.model.Descripcion;
import com.beet.model.invoice.model.Importe;
import com.beet.model.invoice.model.ImpuestoConcepto;
import com.beet.model.invoice.model.NumeroIdentificacion;
import com.beet.model.invoice.model.Unidad;
import com.beet.model.invoice.model.ValueObject;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = false)
public class Concepto implements ValueObject<Concepto> {

  private static final long serialVersionUID = -1829119142869471885L;

  private static final Decimal ZERO = new Decimal("0");

  private CatClave claveProdServ;

  private NumeroIdentificacion numeroIdentificacion;

  private Cantidad cantidad;

  private CatUnidad claveUnidad;

  private Unidad unidad;

  private Descripcion descripcion;

  private Importe valorUnitario;

  private Importe importe;

  private Importe descuento;

  private ImpuestoConcepto impuestos;

  public void validaObligatorios() throws ValidacionExcepcion {
    if (this.claveProdServ == null)
      throw new ValidacionExcepcion("La clave de producto/servicio es obligatoria concepto: " + this.toString());
    if (this.cantidad == null)
      throw new ValidacionExcepcion("La cantidad es obligatoria concepto: " + this.toString());
    if (this.claveUnidad == null)
      throw new ValidacionExcepcion("La claveUnidad es obligatoria concepto: " + this.toString());
    if (this.descripcion == null)
      throw new ValidacionExcepcion("La descripcion es obligatoria concepto: " + this.toString());
    if (this.valorUnitario == null)
      throw new ValidacionExcepcion("El valor unitario es obligatorio concepto: " + this.toString());
    if (this.importe == null)
      throw new ValidacionExcepcion("El importe es obligatorio concepto: " + this.toString());
    if (this.impuestos != null) {
      this.impuestos.validaObligatorios();
    }
  }

  public void valida(CatTipoComprobante tipoComprobante, CatMoneda moneda) throws ValidacionExcepcion {

    this.validaValorUnitario(tipoComprobante);
    this.validaImporte();
    if (this.descuento != null)
      this.validaDescuento();
    this.validaClave();
    if (this.impuestos != null)
      this.validaImpuestos(moneda);
  }

  private void validaValorUnitario(CatTipoComprobante tipoComprobante) throws ValidacionExcepcion {
    if (tipoComprobante.isIngreso() || tipoComprobante.isEgreso() || tipoComprobante.isNomina()) {
      if (!(this.valorUnitario.compara(ZERO) > 0))
        throw new ValidacionExcepcion("El valor unitario no es valido " + this.valorUnitario.toString()
            + " debe ser mayor a cero para el tipo de comprobante " + tipoComprobante.concatenado());
    } else if (tipoComprobante.isPago()) {
      if (!(this.valorUnitario.compara(ZERO) == 0))
        throw new ValidacionExcepcion("El valor unitario no es valido " + this.valorUnitario.toString()
            + " debe ser cero para el tipo de comprobante " + tipoComprobante.concatenado());
    }
  }

  private void validaImporte() {
    Importe importeMinimo;
    Importe importeMaximo;

    if (!(this.valorUnitario.compara(ZERO) == 0)) {
      importeMinimo = calculaImporteMinimo();
      importeMaximo = calculaImporteMaximo();
      if (this.importe.compara(importeMinimo) < 0 || this.importe.compara(importeMaximo) > 0)
        throw new ValidacionExcepcion("El importe " + this.importe.toString() + " fuera de rango minimo: "
            + importeMinimo.toString() + " maximo: " + importeMaximo.toString());
    } else {
      if (!(this.importe.compara(ZERO) == 0))
        throw new ValidacionExcepcion("El importe " + this.importe.toString() + "deberia ser cero");
    }
  }

  private void validaDescuento() {
    int importeDec = this.importe.numeroDecimales();
    int descuentoDec = this.descuento.numeroDecimales();

    if (descuentoDec > importeDec)
      throw new ValidacionExcepcion("El numero de decimales del descuento: " + this.descuento.toString()
          + "supera el numero de decimales del importe " + this.importe.toString());
    if (this.importe.compara(this.descuento) < 0)
      throw new ValidacionExcepcion(
          "El descuento " + this.descuento.toString() + " no debe ser mayor que el importe " + this.importe.toString());
  }

  private Importe calculaImporteMinimo() {
    int cantidadDec = this.cantidad.numeroDecimales();
    int valorUnitarioDec = this.valorUnitario.numeroDecimales();
    Importe ret = new Importe();

    final BigDecimal dos = new BigDecimal("2");
    final BigDecimal uno = new BigDecimal("1");
    BigDecimal potenciaCantidad = uno.movePointLeft(cantidadDec).divide(dos);
    BigDecimal potenciaValorUni = uno.movePointLeft(valorUnitarioDec).divide(dos);

    BigDecimal minimo = (this.cantidad.getValor().subtract(potenciaCantidad))
        .multiply(this.valorUnitario.getValor().subtract(potenciaValorUni)).setScale(6, RoundingMode.DOWN);
    ret.setValor(minimo);
    return ret;
  }

  private Importe calculaImporteMaximo() {
    int cantidadDec = this.cantidad.numeroDecimales();
    int valorUnitarioDec = this.valorUnitario.numeroDecimales();
    Importe ret = new Importe();

    final BigDecimal dos = new BigDecimal("2");
    final BigDecimal uno = new BigDecimal("1");

    BigDecimal potenciaCantidad = uno.movePointLeft(cantidadDec).divide(dos);
    BigDecimal potenciaValorUni = uno.movePointLeft(valorUnitarioDec).divide(dos);
    BigDecimal potenciaMenos12 = uno.movePointLeft(12);

    BigDecimal maximo = (this.cantidad.getValor().add(potenciaCantidad).subtract(potenciaMenos12))
        .multiply(this.valorUnitario.getValor().add(potenciaValorUni).subtract(potenciaMenos12))
        .setScale(6, RoundingMode.UP);
    ret.setValor(maximo);
    return ret;
  }

  private void validaClave() throws ValidacionExcepcion {
    switch (this.claveProdServ.getIvaTrasladado()) {
    case SI:
      if (this.impuestos == null)
        throw new ValidacionExcepcion("El concepto " + this.toString() + " requiere IVA trasladado");
      else {
        if (!this.impuestos.incluyeTrasladoIva())
          throw new ValidacionExcepcion("El concepto " + this.toString() + " requiere IVA trasladado");
      }
      break;
    case NO:
      if (this.impuestos != null) {
        if (this.impuestos.incluyeTrasladoIva())
          throw new ValidacionExcepcion("El concepto " + this.toString() + " no puede contener traslado de IVA");
      }
      break;
    case OPCIONAL:
      break;
    }
    switch (this.claveProdServ.getIepsTrasladado()) {
    case SI:
      if (this.impuestos == null)
        throw new ValidacionExcepcion("El concepto " + this.toString() + " requiere IEPS trasladado");
      else {
        if (!this.impuestos.incluyeTrasladoIeps())
          throw new ValidacionExcepcion("El concepto " + this.toString() + " requiere IEPS trasladado");
      }
      break;
    case NO:
      if (this.impuestos != null) {
        if (this.impuestos.incluyeTrasladoIeps())
          throw new ValidacionExcepcion("El concepto " + this.toString() + " no puede contener traslado de IEPS");
      }
      break;
    case OPCIONAL:
      break;
    }
  }

  private void validaImpuestos(CatMoneda moneda) throws ValidacionExcepcion {
    this.impuestos.valida(moneda);
  }

  public void ajustaDecimales(int decimalesMoneda) {

    this.valorUnitario.ajustaDecimales(decimalesMoneda);
    this.importe.ajustaDecimales(decimalesMoneda);

    if (this.impuestos != null)
      this.impuestos.ajustaDecimales(decimalesMoneda);
  }

  @Override
  public boolean sameValueAs(Concepto other) {
    return this.equals(other);

  }

}
