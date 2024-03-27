package net.amentum.niomedic.medicos.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import net.amentum.common.v2.RestBaseController;
import net.amentum.niomedic.medicos.exception.RegionSanitariaException;
import net.amentum.niomedic.medicos.service.RegionSanitariaService;
import net.amentum.niomedic.medicos.views.RegionSanitariaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("regionSanitaria")
@Slf4j
public class RegionSanitariaRest extends RestBaseController {

    @Autowired
    RegionSanitariaService service;

    @ApiOperation(value = "Creando una Región Sanitaria", response = RegionSanitariaView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, response = RegionSanitariaView.class, message = ""),
            @ApiResponse(code = 500, message = "No fue posible Obtener por ID la Región Sanitaria", response = RegionSanitariaException.class)
    })
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public RegionSanitariaView createRegionSanitaria(@RequestBody @Valid RegionSanitariaView regionSanitariaView) throws RegionSanitariaException {
        log.info("POST - createRegionSanitaria() - Creando una nueva región sanitaria: {}", regionSanitariaView);
        return service.createRegionSanitaria(regionSanitariaView);
    }


    @ApiOperation(value = "Actualizando una Región Sanitaria por ID", response = RegionSanitariaView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, response = RegionSanitariaView.class, message = ""),
            @ApiResponse(code = 404, message = "No se encontró ninguna Región Sanitaria para el idRegionSanitaria: {ID}", response = RegionSanitariaException.class),
            @ApiResponse(code = 500, message = "No fue posible Obtener por ID la Región Sanitaria", response = RegionSanitariaException.class)
    })
    @RequestMapping(value = "{idRegionSanitaria}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public RegionSanitariaView updateRegionSanitaria(@PathVariable()Integer idRegionSanitaria, @RequestBody @Valid RegionSanitariaView view) throws RegionSanitariaException {
        log.info("PUT - updateRegionSanitaria() - Actualizando una nueva región sanitaria con el id: {}",idRegionSanitaria);
        log.debug("PUT - updateRegionSanitaria() - Actualizando una nueva región sanitaria con el id: {} - {}",idRegionSanitaria, view);
        return service.updateRegionSanitaria(idRegionSanitaria, view);
    }

    @ApiOperation(value = "Búsqueda de Región Sanitaria", response = RegionSanitariaView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Page.class, message = ""),
            @ApiResponse(code = 500, message = "No fue posible filtrar las Regiones Sanitarias", response = RegionSanitariaException.class)
    })
    @RequestMapping(value="page", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<RegionSanitariaView> searchRegionesSanitarias(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                            @RequestParam(required = false, defaultValue = "10") Integer size,
                                                            @RequestParam(required = false, defaultValue = "id") String orderColumn,
                                                            @RequestParam(required = false, defaultValue = "asc") String orderType) throws RegionSanitariaException {
        log.info("searchRegionesSanitarias(): page: {} - size: {} - orderColumn: {} - orderType: {}",
                page, size, orderColumn, orderType);
        if(page == null)
            page = 0;
        if(size == null)
            size = 10;
        if(orderType == null || orderType.isEmpty())
            orderType = "asc";
        if (orderColumn == null || orderColumn.isEmpty())
            orderColumn = "id";
        log.info("search - Page: {} - size: {} - orderColumn: {} - orderType: {}",
                page, size, orderColumn, orderType);
        return service.searchRegionSanitaria(page, size, orderColumn, orderType);
    }

    @ApiOperation(value = "Obtiene la Región Sanitaria por su ID", response = RegionSanitariaView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Se obtiene el View de Región Sanitaria"),
            @ApiResponse(code = 404, message = "No se encontró ninguna Región Sanitaria para el idRegionSanitaria: {ID}", response = RegionSanitariaException.class),
            @ApiResponse(code = 500, message = "No fue posible Obtener por ID la Región Sanitaria", response = RegionSanitariaException.class)
    })
    @RequestMapping(value = "{idRegionSanitaria}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RegionSanitariaView getRegionSanitariaById(@ApiParam(name = "IdRegionSanitaria", value = "El ID de la Región Sanitaria", required = true)
                                                @PathVariable("idRegionSanitaria") Integer idRegionSanitaria) throws RegionSanitariaException {
        log.info("===>>>getRegionSanitariaById() - GET - idRegionSanitaria: {}", idRegionSanitaria);
        return service.getRegionSanitariaById(idRegionSanitaria);
    }

    /*@GetMapping("regionSanitaria/searchByEstado")
    List<RegionSanitariaView> getRegionSanitariaByEstado(@RequestParam(value = "idEstado") String idEstado);*/
    @RequestMapping(value = "searchByEstado", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<RegionSanitariaView> getRegionSanitariaByEstado(@RequestParam("idEstado") Integer idEstado) throws RegionSanitariaException {
        log.info("===>>>getRegionSanitariaByEstado() - GET - idEstado: {}", idEstado);
        return service.getRegionSanitariaByEstado(idEstado);
    }

}
