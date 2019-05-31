/**
 * 
 */
package com.beet.model.invoice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.beet.model.invoice.exception.ValidacionExcepcion;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Cesar Varela
 *
 */
@ToString(includeFieldNames=false)
public class Decimal implements Serializable {
	private static final long serialVersionUID = 8219245637139359169L;
	private static final String NUMBRE = "^\\d*\\.?\\d+$";
	private static final String SCIENTIFIC = "^-?\\d+(?:\\.\\d+)?(?:E[-+]?\\d+)?$";
	private static final Pattern patternNumber  = Pattern.compile(NUMBRE);
	private static final Pattern patternScientific  = Pattern.compile(SCIENTIFIC);
	private static final BigDecimal MIN = new BigDecimal(0.0);
	protected static final int DECIMALES = 6; 
	@Getter
	protected BigDecimal  valor;
	
	public Decimal(){
		
	}
	public Decimal(Decimal other){
		this.valor = other.valor;
	}
	public Decimal(String valor){
		this.setValor(valor);
	}
	public Decimal(BigDecimal valor){
	  this.setValor(valor);
	}
	
	public void setValor(String valor){
		this.valor = Decimal.Valor(valor);
	}
	public void setValor(BigDecimal valor){	
		if(valor.compareTo(MIN) < 0)
			throw  new ValidacionExcepcion("La cantidad no puede ser menor a" + MIN.toString());
		validaDecimales(valor, DECIMALES, -1);
		this.valor = valor;
	}
	
	protected void asigna(BigDecimal valor){
		this.valor = valor;
	}
	
	public boolean equals(Object o){
		if(o == this) return true;
		if(o == null) return false;
		if(o.getClass() != this.getClass()) return false;
		final Decimal other = (Decimal) o;
		if(this.valor == null ? other.valor != null : !(this.valor.compareTo(other.valor) == 0)) return false;
		return true;
	}
	public int hashCode(){
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + (this.valor == null? 0 : this.valor.hashCode());
		return result;
	}
	
	public static boolean validaRegex(String valor){
		Matcher matcherNumbre = patternNumber.matcher(valor);
		Matcher matcherScientific = patternScientific.matcher(valor);
		return matcherNumbre.matches() || matcherScientific.matches();
	}
	
	public static void validaDecimales(BigDecimal valor, int digitosDecimal, int digitosEntero){
		DecimalFormat df;
		String text;
		int decimalPlaces;
		int integerPlaces;
		df = new DecimalFormat("#");
		df.setMaximumFractionDigits(8);
		text = df.format(valor);
		integerPlaces = text.indexOf('.');
		if(integerPlaces == -1){
			decimalPlaces = 0;
		}else{
			decimalPlaces = text.length() - integerPlaces - 1;
		}
		if(!(decimalPlaces <= digitosDecimal && (digitosEntero == -1 || integerPlaces <= digitosEntero)))
			throw new ValidacionExcepcion("Valor Invalido " + valor );
	}
	
	public static BigDecimal Valor(String valor){
		BigDecimal ret;
		if(validaRegex(valor)){
			try{
				ret = new BigDecimal(valor).setScale(DECIMALES);
			}catch(ArithmeticException exp){
				throw  new ValidacionExcepcion("Cantidad de decimales fuera de rango valor" + valor);
			}
			if(ret.compareTo(MIN) < 0)
				throw  new ValidacionExcepcion("La cantidad no puede ser menor a" + MIN.toString());
			validaDecimales(ret, DECIMALES, -1);
		}else{
			throw new ValidacionExcepcion("Valor Invalido " + valor );
		}
		return ret;
	}
	
	protected BigDecimal add(Decimal other){
		return this.valor.add(other.valor);
	}
	
	protected BigDecimal multiply(Decimal other){
		return this.valor.multiply(other.valor).setScale(DECIMALES, RoundingMode.HALF_UP);
	}
	
	protected BigDecimal subtract(Decimal other){
		return this.valor.subtract(other.valor);
	}

	public void setScale(int decimales, RoundingMode redondeo){
		this.valor = this.valor.setScale(decimales, redondeo);
	}
	
	public void setScale(int decimales) throws ArithmeticException{
		this.valor = this.valor.setScale(decimales);
	}
	
	public int numeroDecimales(){
		DecimalFormat df;
		String text;
		int decimalPlaces;
		int integerPlaces;
		df = new DecimalFormat("#");
		df.setMaximumFractionDigits(8);
		text = df.format(this.valor);
		integerPlaces = text.indexOf('.');
		if(integerPlaces == -1){
			decimalPlaces = 0;
		}else{
			decimalPlaces = text.length() - integerPlaces - 1;
		}
		return decimalPlaces;
	}
	
	public int comparaMargen(Decimal other){
		int ret;
		BigDecimal resta = this.valor.subtract(other.valor);
		final BigDecimal MINIMO = new BigDecimal(-1);
		final BigDecimal MAXIMO = new BigDecimal(1);
		
		if(resta.compareTo(MINIMO) < 0 )
			ret = -1;
		else if(resta.compareTo(MAXIMO) > 0)
			ret = 1;
		else
			ret = 0;
		
		return ret;
	}
	
	public int compara(Decimal other){
		return this.valor.compareTo(other.valor);
	}
	
	public void ajustaDecimales(int decimales){
		int decimalesActual = this.numeroDecimales();
		if(decimalesActual > decimales)  decimales = decimalesActual;
		this.setScale(decimales);
	}
}
