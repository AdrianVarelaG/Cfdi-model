package com.beet.model.invoice.model;

import java.math.BigDecimal;

import com.beet.model.invoice.exception.ValidacionExcepcion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper= true, includeFieldNames= false)
public class Cantidad extends Decimal implements ValueObject<Cantidad> {
	
	private static final long serialVersionUID = 6953242004956371230L;
	private static final BigDecimal MIN = new BigDecimal(0.000001);

	
	public Cantidad(){
		
	}
	
	public Cantidad(String valor){
		this.setValor(valor);
	}
	public Cantidad(BigDecimal valor){
      this.setValor(valor);
    }
	
	public void setValor(String valor){
		this.valor = Cantidad.Valor(valor);
	}
	public void setValor(BigDecimal valor){
		if(valor.compareTo(MIN) < 0)
			throw  new ValidacionExcepcion("La cantidad no puede ser menor a" + MIN.toString());
		validaDecimales(valor, DECIMALES, -1);
		this.valor = valor;
	}
	
	public static BigDecimal Valor(String valor) throws ValidacionExcepcion{
		BigDecimal ret;
		if(Decimal.validaRegex(valor)){
			try{
				ret = new BigDecimal(valor).setScale(6);
			}catch(ArithmeticException exp){
				throw  new ValidacionExcepcion("Cantidad de decimales fuera de rango");
			}
			if(ret.compareTo(MIN) <= 0)
				throw  new ValidacionExcepcion("La cantidad no puede ser menor a" + MIN.toString());
			Decimal.validaDecimales(ret, DECIMALES, -1);
		}else{
			throw new ValidacionExcepcion("Valor Invalido " + valor );
		}
		return ret;
	}
	
	@Override
	public boolean sameValueAs(Cantidad other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}

}
