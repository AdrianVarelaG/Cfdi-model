package com.beet.model.invoice.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.beet.model.invoice.exception.ValidacionExcepcion;

import lombok.Data;

@Data
public class Fecha implements ValueObject<Fecha> {

  private static final long serialVersionUID = -6469386412443884646L;
  private LocalDate fecha;
  
  public Fecha() {
    this.fecha = LocalDate.now();
  }
  public Fecha(String fecha) {
    this.setStringFecha(fecha);
  }
  public Fecha(LocalDate fecha) {
    this.fecha = fecha;
  }
  
  
  public void setStringFecha(String fecha) throws ValidacionExcepcion{
      DateTimeFormatter formatter  = DateTimeFormatter.ISO_LOCAL_DATE;
      try{
          this.fecha = LocalDate.parse(fecha, formatter);
      }catch(DateTimeParseException e){
          throw new ValidacionExcepcion("Formato invalido en el campo fecha " + fecha  + " el formato valido es AAAA-MM-DD");
      }
  }
  
  @Override
  public boolean sameValueAs(Fecha other) {
    return this.equals(other);
  }

}
