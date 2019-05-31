package com.beet.model.invoice.model;

import java.math.BigDecimal;

import com.beet.model.invoice.exception.ValidacionExcepcion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class Importe extends Decimal implements ValueObject<Importe> {

	private static final long serialVersionUID = 8219245637139359169L;
	private static final BigDecimal MIN = new BigDecimal(0.0);
	
	public Importe(){
		
	}
	public Importe(Importe other){
		super(other);
	}
	public Importe(String valor){
		this.setValor(valor);
	}
	public Importe(BigDecimal valor){
      this.setValor(valor);
  }
	
	public void setValor(String valor){
		this.valor = Importe.Valor(valor);
	}
	public void setValor(BigDecimal valor){
		if(valor.compareTo(MIN) < 0)
			throw  new ValidacionExcepcion("La cantidad no puede ser menor a " + MIN.toString());
		validaDecimales(valor, DECIMALES, 18);
		this.valor = valor;
	}
	public void setValor(Importe other){
		this.valor = other.valor;
	}
	
	public static BigDecimal Valor(String valor){
		BigDecimal ret;
		if(validaRegex(valor)){
			try{
				ret = new BigDecimal(valor).setScale(6);
			}catch(ArithmeticException exp){
				throw  new ValidacionExcepcion("Cantidad de decimales fuera de rango valor: " + valor, exp);
			}
			if(ret.compareTo(MIN) < 0)
				throw  new ValidacionExcepcion("La cantidad no puede ser menor a" + MIN.toString());
			validaDecimales(ret, DECIMALES, 18);
		}else{
			throw new ValidacionExcepcion("Valor Invalido " + valor );
		}
		return ret;
	}
	
	@Override
	public boolean sameValueAs(Importe other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
	
	public Importe suma(Importe other){
		Importe ret = new Importe();
		ret.asigna(super.add(other));
		return ret;
	}
	
	public Importe resta(Importe other){
		Importe ret = new Importe();
		ret.asigna(super.subtract(other));
		return ret;
	}
	
	public Importe multiplica(Decimal other){
		Importe ret = new Importe();
		ret.asigna(super.multiply(other));
		return ret;
	}

}
