package com.beet.model.invoice.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.beet.model.invoice.exception.ValidacionExcepcion;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=false)
public class FechaHora implements ValueObject<FechaHora> {
	
	private static final long serialVersionUID = 3387923892465699726L;
	private LocalDateTime fecha;
	
	public FechaHora(){
		fecha = LocalDateTime.now().withNano(0);
	}
	public FechaHora(String fecha) throws ValidacionExcepcion{
		this.setStringFecha(fecha);
	}
	public FechaHora(LocalDateTime fecha){
		this.fecha = fecha.withNano(0);
	}
	
	public void setStringFecha(String fecha) throws ValidacionExcepcion{
		DateTimeFormatter formatter  = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		try{
			this.fecha = LocalDateTime.parse(fecha, formatter).withNano(0);
		}catch(DateTimeParseException e){
			throw new ValidacionExcepcion("Formato invalido en el campo fecha " + fecha  + " el formato valido es aaaa-mm-ddThh:mm:ss");
		}
	}
	
	@Override
	public boolean sameValueAs(FechaHora other) {
		return this.equals(other);
	}

}
