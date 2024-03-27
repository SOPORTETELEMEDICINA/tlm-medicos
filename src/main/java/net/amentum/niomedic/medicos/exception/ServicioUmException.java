package net.amentum.niomedic.medicos.exception;

import net.amentum.common.v2.GenericException;
import org.springframework.http.HttpStatus;

public class ServicioUmException extends GenericException {
   public static final String SERVER_ERROR = "No fue posible %s de Servicio Unidad Medica";
   public static final String ITEM_NO_ENCONTRADO = "No se encontró ningún Servicio de Unidad Médica para el idServicioUm: %s";

   public ServicioUmException(HttpStatus status, String message){
      super(status,message);
   }
}
