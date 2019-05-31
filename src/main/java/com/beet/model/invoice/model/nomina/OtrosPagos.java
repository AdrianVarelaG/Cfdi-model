package com.beet.model.invoice.model.nomina;

import java.util.List;

import com.beet.model.invoice.model.ValueObject;

import lombok.Data;

@Data
public class OtrosPagos implements ValueObject<OtrosPagos> {

  private static final long serialVersionUID = -56169671332956432L;
  private List<OtroPago> otrosPagos;
  
  @Override
  public boolean sameValueAs(OtrosPagos other) {
    // TODO Auto-generated method stub
    return this.equals(other);
  }

}
