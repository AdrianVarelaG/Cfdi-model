package com.beet.model.invoice.model.nomina;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.beet.model.invoice.exception.ValidacionExcepcion;
import com.beet.model.invoice.model.Decimal;
import com.beet.model.invoice.model.ValueObject;

import lombok.Data;

@Data
public class DiasPagados implements ValueObject<DiasPagados> {
  
  private static final long serialVersionUID = 1156542044724823306L;
  private static final BigDecimal MIN = new BigDecimal(0.001);
  private static final BigDecimal MAX = new BigDecimal(36160.000);
  private static final int DECIMALES = 3;
  private static final Pattern pattern  = Pattern.compile("^(([1-9][0-9]{0,4})|[0])(.[0-9]{3})?$");
  
  
  private BigDecimal valor;
  
  public DiasPagados(String valor) {
    this.setValor(valor);
  }
  public DiasPagados(BigDecimal valor) {
    this.setValor(valor);
  }
  
  public void setValor(BigDecimal valor){   
    if(valor.compareTo(MIN) < 0)
      throw  new ValidacionExcepcion("La cantidad no puede ser menor a" + MIN.toString());
    if(valor.compareTo(MAX) > 0)
      throw  new ValidacionExcepcion("La cantidad no puede ser mayor a" + MAX.toString());
    Decimal.validaDecimales(valor, DECIMALES, -1);
    this.valor = valor;
  }
  
  public void setValor(String valor) throws ValidacionExcepcion{
    BigDecimal ret;
    if(validaRegex(valor)){
      try{
          ret = new BigDecimal(valor).setScale(DECIMALES);
      }catch(ArithmeticException exp){
          throw  new ValidacionExcepcion("Cantidad de decimales fuera de rango valor" + valor);
      }
      if(ret.compareTo(MIN) < 0)
        throw  new ValidacionExcepcion("La cantidad " + ret + " no puede ser menor a" + MIN.toString());
      if(ret.compareTo(MAX) > 0)
        throw  new ValidacionExcepcion("La cantidad "+ ret + " no puede ser Mayor a" + MAX.toString());
      
      Decimal.validaDecimales(ret, DECIMALES, -1);
      this.valor = ret;
    }else{
      throw new ValidacionExcepcion("Valor Invalido " + valor );
    }
  }
  
  private boolean validaRegex(String valor) {
    Matcher matcher = pattern.matcher(valor);
    return matcher.matches();
  }
  
  @Override
  public boolean sameValueAs(DiasPagados other) {
    // TODO Auto-generated method stub
    return false;
  }

}
