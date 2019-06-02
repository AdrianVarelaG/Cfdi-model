package com.beet.model.invoice.xml.utils;

import com.beet.model.invoice.xml.model.comprobante.Comprobante;

public interface ComprobanteUtils {

  static Object obtenComplemento(Comprobante comprobante, Class<?> clase) {
    Object ret = null;

    for (int i = 0; i < comprobante.getComplemento().get(0).getAny().size(); i++) {
      if (comprobante.getComplemento().get(0).getAny().get(i).getClass() == clase) {
        ret = comprobante.getComplemento().get(0).getAny().get(i);
        break;
      }
    }

    return ret;
  }

}
