package net.amentum.niomedic.medicos.exception;

import net.amentum.common.v2.GenericException;
import org.springframework.http.HttpStatus;

public class EspecialidadUmException extends GenericException {
   public static final String SERVER_ERROR = "No fue posible %s la Especialidad de Unidad Medica";
   public static final String ITEM_NO_ENCONTRADO = "No se encontró ninguna Especialidad de Unidad Médica para el idEspecialidadUm: %s";


   public EspecialidadUmException(HttpStatus status, String message){
      super(status,message);
   }
}
