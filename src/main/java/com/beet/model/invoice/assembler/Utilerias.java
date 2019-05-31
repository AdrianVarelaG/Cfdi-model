package com.beet.model.invoice.assembler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.beet.model.invoice.exception.ConversionException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utilerias {
	
	public static boolean existeInfo(String valor){
		return valor != null && !valor.trim().isEmpty();
	}
	public static boolean existeInfo(List<?> lista){
		return lista != null && !lista.isEmpty();
	}
	public static boolean existeInfo(Map<?,?> map){
		return map != null && !map.isEmpty();
	}
	public static XMLGregorianCalendar toXMLGregorianCalendar(LocalDateTime fechaHora ) throws ConversionException {
		try {
			DateTimeFormatter formatter  = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			String fecha = fechaHora.withNano(0).format(formatter);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(fecha);
		} catch (DatatypeConfigurationException e) {
		  log.error(e.getMessage(), e);
		  throw new ConversionException("Error en la conversion a XMLGregorianCalendar", e);
		}
	}
	public static LocalDateTime toLocalDateTime(XMLGregorianCalendar xmlfechaHora){
		LocalDateTime ret = null;
		ret = xmlfechaHora.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
		return ret;
	}
	public static LocalDate toLocalDate(XMLGregorianCalendar xmlfechaHora){
      LocalDate ret = null;
      ret = xmlfechaHora.toGregorianCalendar().toZonedDateTime().toLocalDate();
      return ret;
  }
	
}
