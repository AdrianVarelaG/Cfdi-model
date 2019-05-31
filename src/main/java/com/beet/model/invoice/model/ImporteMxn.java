package com.beet.model.invoice.model;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.beet.model.invoice.exception.ValidacionExcepcion;

import lombok.Data;

@Data
public class ImporteMxn implements ValueObject<ImporteMxn>{

  private static final long serialVersionUID = -4955564645127261994L;
  private static final String REGEX = "^[0-9]{1,18}(.[0-9]{1,2})?$";
  private static final Pattern pattern  = Pattern.compile(REGEX);
  private static final BigDecimal MIN = new BigDecimal(0.0);
  private static final int DECIMALES = 2; 
  
  private BigDecimal valor;
  
  public ImporteMxn(String valor) {
    this.setValor(valor);
  }
  public ImporteMxn(BigDecimal valor) {
    this.setValor(valor);
  }
  
  public void setValor(BigDecimal valor){   
    if(valor.compareTo(MIN) < 0)
      throw  new ValidacionExcepcion("La cantidad no puede ser menor a" + MIN.toString());
    Decimal.validaDecimales(valor, DECIMALES, -1);
    this.valor = valor.setScale(DECIMALES);
  }
  
  public void setValor(String valor) throws ValidacionExcepcion{
    BigDecimal ret;
    if(validaRegex(valor)){
      try{
          ret = new BigDecimal(valor).setScale(DECIMALES);
      }catch(ArithmeticException exp){
          throw  new ValidacionExcepcion("Cantidad de decimales fuera de rango valor " + valor);
      }
      if(ret.compareTo(MIN) < 0)
        throw  new ValidacionExcepcion("La cantidad " + ret + " no puede ser menor a" + MIN.toString());
      
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
  public boolean sameValueAs(ImporteMxn other) {
    
    return this.equals(other);
  }

}
