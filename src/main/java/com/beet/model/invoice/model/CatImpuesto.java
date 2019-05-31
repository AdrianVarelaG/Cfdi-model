package com.beet.model.invoice.model;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Data
@EqualsAndHashCode(callSuper=true, exclude={"retencion", "traslado", "tipo"})
@ToString(callSuper = true, includeFieldNames=false)
public class CatImpuesto extends Catalogo implements ValueObject<CatImpuesto> {
	private static final long serialVersionUID = 2494542289531076210L;
	private boolean retencion;
	private boolean traslado;
	private ImpuestoEnum tipo;
	
	public CatImpuesto(){
		
	}
	public CatImpuesto(CatImpuesto other){
		super(other);
		this.retencion = other.retencion;
		this.traslado = other.traslado;
		this.tipo = other.tipo;
	}
	public CatImpuesto(String clave){
		super(clave);
	}
	
	@Override
	public boolean sameValueAs(CatImpuesto other) {
		return super.equals(other);
	}
	
	public boolean isIva(){
		return this.igual("002");
	}
	
	public boolean isIeps(){
		return this.igual("003");
	}
	
	@Override
	public String catalogoNombre() {
		return CatalogoEnum.IMPUESTO.getKey();
	}

	
}
