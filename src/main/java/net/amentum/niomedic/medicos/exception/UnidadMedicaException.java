package net.amentum.niomedic.medicos.exception;

import net.amentum.common.v2.GenericException;
import org.springframework.http.HttpStatus;

public class UnidadMedicaException extends GenericException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9063291373207468449L;
	public static final String SERVER_ERROR = "No fue posible %s la Unidad Médica";
	public static final String ITEM_NO_ENCONTRADO = "No se encontró ninguna Unidad Médica para el idUnidadMedica: %s";
	public static final String ITEM_NO_ENCONTRADO_SERVICIOS = "No se encontraron Servicios para la Unidad Médica con el idUnidadMedica: %s";
	public static final String ITEM_NO_ENCONTRADO_ESPECIALIDADES = "No se encontraron Especialidades para la Unidad Médica con el idUnidadMedica: %s";

	public UnidadMedicaException(HttpStatus status, String message){
		super(status,message);
	}
}
