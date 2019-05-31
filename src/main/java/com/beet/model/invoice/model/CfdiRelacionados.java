package com.beet.model.invoice.model;

import com.beet.model.invoice.exception.ValidacionExcepcion;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=false)
public class CfdiRelacionados implements ValueObject<CfdiRelacionados> {

	private static final long serialVersionUID = 1867312001797884627L;
	
	private CatTipoRelacion tipoRelacion;
	
	//private List<Relacion> relacionados;
	
	
	@Override
	public boolean sameValueAs(CfdiRelacionados other) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void validaObligatorios() throws ValidacionExcepcion{
		if(tipoRelacion == null)
			throw new ValidacionExcepcion("El tipo de relacion es obligatoria");
		/*if(this.relacionados == null || this.relacionados.isEmpty())
			throw new ValidacionExcepcion("Los documentos relacionados son obligatorios");
		else{
			for(Relacion tmp: relacionados){
				tmp.validaObligatorios();
			}
		}*/
	}
	
	public void validaRelacion(CatTipoComprobante tipoComprobante)throws ValidacionExcepcion{
		//CatTipoComprobante tmp;
		switch(this.tipoRelacion.getClaveSat()){
			case "01":
			case "02":
				if(tipoComprobante.isPago() || tipoComprobante.isNomina() || tipoComprobante.isTraslado())
					throw new ValidacionExcepcion("La relacion incorrecta para el comprobante " + tipoComprobante.concatenado() + " Relacion: " + this.tipoRelacion.concatenado());	
				/*for (Map.Entry<CfdiUuid, EntidadComprobante> entry : relaciones.entrySet()){
					tmp = entry.getValue().getTipoComprobante();
					if(tmp.isPago() 						|| tmp.isNomina() 						|| tmp.isTraslado())
						throw new ValidacionExcepcion("La relacion incorrecta para el comprobante " + entry.getKey() + " Relacion: " + this.tipoRelacion.concatenado());			
				}*/
				break;
			case "03":
				if(tipoComprobante.isNomina() || tipoComprobante.isPago() || tipoComprobante.isEgreso())
					throw new ValidacionExcepcion("La relacion incorrecta para el comprobante " + tipoComprobante.concatenado() + " Relacion: " + this.tipoRelacion.concatenado() );
				/*for (Map.Entry<CfdiUuid, EntidadComprobante> entry : relaciones.entrySet()){
					tmp = entry.getValue().getTipoComprobante();
					if(tmp.isNomina() 						|| tmp.isPago() 						|| tmp.isEgreso())
						throw new ValidacionExcepcion("La relacion incorrecta para el comprobante " + entry.getKey() + " Relacion: " + this.tipoRelacion.concatenado());
				}*/
				break;
			case "04":
				if(tipoComprobante.isIngreso() || tipoComprobante.isEgreso()){
					/*for (Map.Entry<CfdiUuid, EntidadComprobante> entry : relaciones.entrySet()){
						tmp = entry.getValue().getTipoComprobante();
						if(!(tmp.isIngreso() || tmp.isEgreso()))
							throw new ValidacionExcepcion("La relacion incorrecta para el comprobante " + entry.getKey() + " Relacion: " + this.tipoRelacion.concatenado());
					}*/
				}else{
					/*for (Map.Entry<CfdiUuid, EntidadComprobante> entry : relaciones.entrySet()){
						tmp = entry.getValue().getTipoComprobante();
						if(!tipoComprobante.sameValueAs(tmp))
							throw new ValidacionExcepcion("La relacion incorrecta para el comprobante " + entry.getKey() + " Relacion: " + this.tipoRelacion.concatenado());
					}*/
				}
				break;
			case "05":
				if(tipoComprobante.isTraslado()){
					/*for (Map.Entry<CfdiUuid, EntidadComprobante> entry : relaciones.entrySet()){
						tmp = entry.getValue().getTipoComprobante();
						if(!(tmp.isIngreso() || tmp.isEgreso()))
							throw new ValidacionExcepcion("La relacion incorrecta para el comprobante " + entry.getKey() + " Relacion: " + this.tipoRelacion.concatenado());
					}*/
				}else
					throw new ValidacionExcepcion("La relacion incorrecta para el comprobante " + tipoComprobante.concatenado() + " Relacion: " + this.tipoRelacion.concatenado() );
				break;
			case "06":
				if(tipoComprobante.isIngreso() || tipoComprobante.isEgreso()){
					/*for (Map.Entry<CfdiUuid, EntidadComprobante> entry : relaciones.entrySet()){
						tmp = entry.getValue().getTipoComprobante();
						if(!tmp.isTraslado())
							throw new ValidacionExcepcion("La relacion incorrecta para el comprobante " + entry.getKey() + " Relacion: " + this.tipoRelacion.concatenado());
					}*/
				}else
					throw new ValidacionExcepcion("La relacion incorrecta para el comprobante " + tipoComprobante.concatenado() + " Relacion: " + this.tipoRelacion.concatenado() );
				break;
			case "07":
				if(tipoComprobante.isIngreso() || tipoComprobante.isEgreso()){
					/*for (Map.Entry<CfdiUuid, EntidadComprobante> entry : relaciones.entrySet()){
						tmp = entry.getValue().getTipoComprobante();
						if(!(tmp.isIngreso() || tmp.isEgreso()))
							throw new ValidacionExcepcion("La relacion incorrecta para el comprobante " + entry.getKey() + " Relacion: " + this.tipoRelacion.concatenado());
					}*/
				}else
					throw new ValidacionExcepcion("La relacion incorrecta para el comprobante " + tipoComprobante.concatenado() + " Relacion: " + this.tipoRelacion.concatenado() );
				break;
			default:
				throw new ValidacionExcepcion("La relacion incorrecta para el comprobante " + tipoComprobante.concatenado() + " Relacion: " + this.tipoRelacion.concatenado() );
		}
	}
	
	public boolean isNull(){
		return this.tipoRelacion == null; //&& !Utilerias.existeInfo(relacionados);
	}

}
