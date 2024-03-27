package net.amentum.niomedic.medicos.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import net.amentum.common.v2.RestBaseController;
import net.amentum.niomedic.medicos.exception.EspecialidadUmException;
import net.amentum.niomedic.medicos.exception.ServicioUmException;
import net.amentum.niomedic.medicos.exception.UnidadMedicaException;
import net.amentum.niomedic.medicos.service.UnidadMedicaService;
import net.amentum.niomedic.medicos.views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@Api(tags = "Obtener de Unidad Médica con Servicio y/o Especialidad")
@RestController
@RequestMapping("unidad-medica")
@Slf4j
public class UnidadMedicaRest extends RestBaseController {
	@Autowired
	UnidadMedicaService unidadMedicaService;

	@ApiOperation(value = "Creando una Unidad Médica", response = UnidadMedicaView.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, response = UnidadMedicaView.class, message = ""),
			@ApiResponse(code = 500, message = "No fue posible Obtener por ID la Unidad Médica", response = UnidadMedicaException.class)
	})
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public UnidadMedicaView createUnindadMedica(@RequestBody @Valid UnidadMedicaView unidadMedicaView) throws UnidadMedicaException {
		log.info("POST - createUnindadMedica() - Creando una nueva unidad medica: {}", unidadMedicaView);
		return unidadMedicaService.createUnidadMedica(unidadMedicaView);
	}

	@ApiOperation(value = "Actualizando una Unidad Médica por ID", response = UnidadMedicaView.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, response = UnidadMedicaView.class, message = ""),
			@ApiResponse(code = 404, message = "No se encontró ninguna Unidad Médica para el idUnidadMedica: {ID}", response = UnidadMedicaException.class),
			@ApiResponse(code = 500, message = "No fue posible Obtener por ID la Unidad Médica", response = UnidadMedicaException.class)
	})
	@RequestMapping(value = "{idUnidadMedica}",method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public UnidadMedicaView updateUnindadMedica(@PathVariable()Long idUnidadMedica, @RequestBody @Valid UnidadMedicaView unidadMedicaView) throws UnidadMedicaException {
		log.info("PUT - updateUnindadMedica() - Actualizando una nueva unidad médica con el id: {}",idUnidadMedica);
		log.debug("PUT - updateUnindadMedica() - Actualizando una nueva unidad médica con el id: {} - {}",idUnidadMedica, unidadMedicaView);
		return unidadMedicaService.updateUnidadMedica(idUnidadMedica, unidadMedicaView);
	}
	
	
	@ApiOperation(value = "Búsqueda de Unidades Médica", response = UnidadMedicaView.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = Page.class, message = ""),
			@ApiResponse(code = 500, message = "No fue posible filtrar las Unidades Médicas", response = UnidadMedicaException.class)
	})
	@RequestMapping(value="search", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<UnidadMedicaPageView> searchUnidadesMedicas(@RequestParam(required = false, defaultValue = "") String datosBusqueda,
									  @RequestParam(required = false) Boolean activo,
									  @RequestParam(required = false, defaultValue = "0") Integer page,
									  @RequestParam(required = false, defaultValue = "10") Integer size,
									  @RequestParam(required = false, defaultValue = "idUnidadMedica") String orderColumn,
									  @RequestParam(required = false, defaultValue = "asc") String orderType) throws UnidadMedicaException {
		 log.info("searchUnidadesMedicas(): - datosBusqueda: {} - activo: {} - page: {} - size: {} - orderColumn: {} - orderType: {}",
				 datosBusqueda, activo, page, size, orderColumn, orderType);
			if(page < 0){page = 0;}
			if(size < 0){size = 10;}
			if(!orderType.equals("asc") && !orderType.equals("desc")) {orderType = "asc";}
			return unidadMedicaService.searchUnidadMedica(datosBusqueda, activo, page, size, orderColumn, orderType);
	}

	@RequestMapping(value="searchByRegion", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<UnidadMedicaView> searchUnidadesMedicasByRegion(@RequestParam() Integer idRegionSanitaria) throws UnidadMedicaException {
		log.info("searchRegionesSanitarias(): idRegionSanitaria() - {} ", idRegionSanitaria);
		return unidadMedicaService.getUnidadMedicaByRegion(idRegionSanitaria);
	}

	@RequestMapping(value="searchByEntidad", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<UnidadMedicaView> searchUnidadesMedicasByEntidad(@RequestParam() Integer idEntidad) throws UnidadMedicaException {
		log.info("searchUnidadesMedicasByEntidad(): idEntidad() - {} ", idEntidad);
		return unidadMedicaService.getUnidadMedicaByEntidad(idEntidad);
	}

	@ApiOperation(value = "Obtiene la Unidad Médica por su ID", response = UnidadMedicaView.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK - Se obtiene el View de Unidad Médica"),
			@ApiResponse(code = 404, message = "No se encontró ninguna Unidad Médica para el idUnidadMedica: {ID}", response = UnidadMedicaException.class),
			@ApiResponse(code = 500, message = "No fue posible Obtener por ID la Unidad Médica", response = UnidadMedicaException.class)
	})
	@RequestMapping(value = "{idUnidadMedica}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public UnidadMedicaView getUnidadMedicaById(@ApiParam(name = "IdUnidadMedica", value = "El ID de la Unidad Medica", required = true)
	@PathVariable("idUnidadMedica") Long idUnidadMedica) throws UnidadMedicaException {
		log.info("===>>>getUnidadMedicaById() - GET - idUnidadMedica: {}", idUnidadMedica);
		return unidadMedicaService.getUnidadMedicaById(idUnidadMedica);
	}

	@ApiOperation(value = "Obtiene los Servicios de una Unidad Médica por su ID", response = UnidadMedicaView.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK - Se obtiene el Arreglo de View de Servicio"),
			@ApiResponse(code = 404, message = "No se encontró ninguna Unidad Médica para el idUnidadMedica: {ID}", response = UnidadMedicaException.class),
			@ApiResponse(code = 404, message = "No se encontraron Servicios para la Unidad Médica con el idUnidadMedica: {ID}", response = UnidadMedicaException.class),
			@ApiResponse(code = 500, message = "No fue posible Obtener Servicios por ID la Unidad Médica", response = UnidadMedicaException.class)
	})
	@RequestMapping(value = "{idUnidadMedica}/servicio", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<ServicioUmView> getServiciosByIdUnidadMedica(@ApiParam(name = "IdUnidadMedica", value = "El ID de la Unidad Medica", required = true)
	@PathVariable("idUnidadMedica") Long idUnidadMedica) throws UnidadMedicaException {
		log.info("===>>>getServiciosByIdUnidadMedica() - GET - idUnidadMedica: {}", idUnidadMedica);
		return unidadMedicaService.getServiciosByIdUnidadMedica(idUnidadMedica);
	}

	@ApiOperation(value = "Obtiene el Servicio de una Unidad Médica por su ID", response = UnidadMedicaView.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK - Se obtiene el View de Servicio"),
			@ApiResponse(code = 404, message = "No se encontró ninguna Unidad Médica para el idUnidadMedica: {ID}", response = UnidadMedicaException.class),
			@ApiResponse(code = 404, message = "No se encontró ningún Servicio de Unidad Médica para el idServicioUm: {ID}", response = ServicioUmException.class),
			@ApiResponse(code = 500, message = "No fue posible Obtener el Servicio por ID la Unidad Médica", response = UnidadMedicaException.class)
	})
	@RequestMapping(value = "{idUnidadMedica}/servicio/{idServicio}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ServicioUmView getServicioByIdUnidadMedicaByIdServicio(@ApiParam(name = "IdUnidadMedica", value = "El ID de la Unidad Medica", required = true)
	@PathVariable("idUnidadMedica") Long idUnidadMedica,
	@ApiParam(name = "idServicio", value = "El ID del Servicio", required = true)
	@PathVariable("idServicio") Long idServicio) throws UnidadMedicaException {
		log.info("===>>>getServicioByIdUnidadMedicaByIdServicio() - GET - idUnidadMedica: {} - idServicio: {}", idUnidadMedica, idServicio);
		return unidadMedicaService.getServicioByIdUnidadMedicaByIdServicio(idUnidadMedica, idServicio);
	}

	@ApiOperation(value = "Obtiene las Especialidades de una Unidad Médica por su ID", response = UnidadMedicaView.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK - Se obtiene el Arreglo de View de Especialidad"),
			@ApiResponse(code = 404, message = "No se encontró ninguna Unidad Médica para el idUnidadMedica: {ID}", response = UnidadMedicaException.class),
			@ApiResponse(code = 404, message = "No se encontraron Especialides para la Unidad Médica con el idUnidadMedica: {ID}", response = UnidadMedicaException.class),
			@ApiResponse(code = 500, message = "No fue posible Obtener Especialides por ID la Unidad Médica", response = UnidadMedicaException.class)
	})
	@RequestMapping(value = "{idUnidadMedica}/especialidad", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<EspecialidadUmView> getEspecialidadesByIdUnidadMedica(@ApiParam(name = "IdUnidadMedica", value = "El ID de la Unidad Medica", required = true)
	@PathVariable("idUnidadMedica") Long idUnidadMedica) throws UnidadMedicaException {
		log.info("===>>>getEspecialidadesByIdUnidadMedica() - GET - idUnidadMedica: {}", idUnidadMedica);
		return unidadMedicaService.getEspecialidadesByIdUnidadMedica(idUnidadMedica);
	}

	@ApiOperation(value = "Obtiene la Especialidad de una Unidad Médica por su ID", response = UnidadMedicaView.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK - Se obtiene el View de Especialidad"),
			@ApiResponse(code = 404, message = "No se encontró ninguna Unidad Médica para el idUnidadMedica: {ID}", response = UnidadMedicaException.class),
			@ApiResponse(code = 404, message = "No se encontró ninguna Especialidad de Unidad Médica para el idServicioUm: {ID}", response = EspecialidadUmException.class),
			@ApiResponse(code = 500, message = "No fue posible Obtener la Especialidad por ID la Unidad Médica", response = UnidadMedicaException.class)
	})
	@RequestMapping(value = "{idUnidadMedica}/especialidad/{idEspecialidad}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public EspecialidadUmView getEspecialidadByIdUnidadMedicaByIdEspecialidad(@ApiParam(name = "IdUnidadMedica", value = "El ID de la Unidad Medica", required = true)
	@PathVariable("idUnidadMedica") Long idUnidadMedica,
	@ApiParam(name = "idEspecialidad", value = "El ID de la Especialidad", required = true)
	@PathVariable("idEspecialidad") Long idEspecialidad) throws UnidadMedicaException {
		log.info("===>>>getEspecialidadByIdUnidadMedicaByIdEspecialidad() - GET - idUnidadMedica: {} - idEspecialidad: {}", idUnidadMedica, idEspecialidad);
		return unidadMedicaService.getEspecialidadByIdUnidadMedicaByIdEspecialidad(idUnidadMedica, idEspecialidad);
	}

}
