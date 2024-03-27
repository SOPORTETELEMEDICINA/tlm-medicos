package net.amentum.niomedic.medicos.exception;

import net.amentum.common.v2.GenericException;
import org.springframework.http.HttpStatus;

public class RegionSanitariaException extends GenericException {
    private static final long serialVersionUID = -9063291373207468449L;
    public static final String SERVER_ERROR = "No fue posible %s la Unidad Médica";
    public static final String ITEM_NO_ENCONTRADO = "No se encontró ninguna Región Sanitaria para el idRegionSanitaria: %s";
    public static final String ITEM_NO_ENCONTRADO_EDO = "No se encontró ninguna Región Sanitaria para el estado: %s";
    public static final String ITEM_NO_ENCONTRADO_SERVICIOS = "No se encontraron Servicios para la Región Sanitaria con el idUnidadMedica: %s";

    public RegionSanitariaException(HttpStatus status, String message) {
        super(status, message);
    }

}
