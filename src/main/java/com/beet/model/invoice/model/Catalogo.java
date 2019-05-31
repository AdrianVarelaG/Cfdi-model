package com.beet.model.invoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude={"descripcion"})
@ToString(includeFieldNames=false)
public abstract class Catalogo {
	private String claveSat;

	private String descripcion;
	
	public Catalogo(){
		
	}
	public Catalogo(String clave){
		this.claveSat = clave;
	}
	
	public Catalogo(Catalogo other){
		this.claveSat = new String(other.claveSat);
		this.descripcion = new String(other.descripcion);
	}
	
	public String concatenado(){
		String ret = claveSat;
		if (descripcion != null){
			ret = ret + " " + descripcion; 
		}
		return  ret;
	}
	
	public boolean igual(String other){
		if (this.claveSat == other) return true;
		if (this.claveSat == null ? other != null : !this.claveSat.equals(other)) return false;
		return true;
	}
	
	abstract public String catalogoNombre();
	
}
