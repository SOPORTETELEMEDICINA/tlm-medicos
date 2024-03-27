package net.amentum.niomedic.medicos.rest;


import net.amentum.common.BaseController;
import net.amentum.niomedic.medicos.exception.AgendamedicosException;
import net.amentum.niomedic.medicos.service.AgendaMedicosService;
import net.amentum.niomedic.medicos.views.AgendaMedicosView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Agendamedicos")
public class AgendaMedicosRest extends BaseController {


    private final Logger logger = LoggerFactory.getLogger(AgendaMedicosRest.class);
    private AgendaMedicosService AgendamedicosService;

    @Autowired
    public void setAgendamedicosService(AgendaMedicosService AgendamedicosService) {
        this.AgendamedicosService = AgendamedicosService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createAgendamedicos(@RequestBody @Valid AgendaMedicosView AgendamedicosView) throws AgendamedicosException {
        try {
            logger.info("Guardar nueva agenda: {}", AgendamedicosView);
            AgendamedicosService.createAgendamedicos(AgendamedicosView);
        } catch (AgendamedicosException dce) {
            throw dce;
        } catch (Exception ex) {
            AgendamedicosException dce = new AgendamedicosException("No fue posible agregar Niveles Covid", AgendamedicosException.LAYER_DAO, AgendamedicosException.ACTION_INSERT);
            dce.addError("Ocurrio un error al agregar la nueva agenda");
            logger.error("Error al insertar nueva agenda- CODE {} - {}", dce.getExceptionCode(), dce);
            throw dce;
        }
    }

    @RequestMapping(value = "{idagenda}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateAgendamedicos(@PathVariable() Integer idagenda, @RequestBody @Valid AgendaMedicosView AgendamedicosView) throws AgendamedicosException {
        try {
            AgendamedicosView.setIdagenda(idagenda);
            logger.info("Editar Agenda: {}", AgendamedicosView);
            AgendamedicosService.updateAgendamedicos(AgendamedicosView);
        } catch (AgendamedicosException dce) {
            throw dce;
        } catch (Exception ex) {
            AgendamedicosException dce = new AgendamedicosException("No fue posible modificar agenda", AgendamedicosException.LAYER_DAO, AgendamedicosException.ACTION_UPDATE);
            dce.addError("Ocurrio un error al modificar agenda");
            logger.error("Error al modificar agenda - CODE {} - {}", dce.getExceptionCode(), dce);
            throw dce;
        }
    }

    @RequestMapping(value = "{idagenda}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAgendamedicos(@PathVariable() Integer idagenda) throws AgendamedicosException {
        logger.info("Eliminar agenda: {}", idagenda);
        AgendamedicosService.deleteAgendamedicos(idagenda);
    }



    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<AgendaMedicosView> findAll() throws AgendamedicosException {
        return AgendamedicosService.findAll();
    }


    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<AgendaMedicosView> getAgendamedicosSearch(@RequestParam(required = true) String idmedico,
                                                          @RequestParam(required = false) Integer page,
                                                          @RequestParam(required = false) Integer size,
                                                          @RequestParam(required = false) String orderColumn,
                                                          @RequestParam(required = false) String orderType) throws AgendamedicosException {

        logger.info("===>>>getAgendamedicosSearch(): - idmedico: {} - idTipoEvento: {}  - page: {} - size: {} - orderColumn: {} - orderType: {}",
                idmedico,page, size, orderColumn, orderType);

        String uidmedico = null;
        try {
            if (idmedico != null && !idmedico.isEmpty()) {
                uidmedico = idmedico;
            }
        } catch (IllegalArgumentException iae) {
            logger.error("===>>>idmedico tiene valores incorrectos", idmedico);
            AgendamedicosException medPacE = new AgendamedicosException("Ocurrio un error al obtener Agendamedicos", AgendamedicosException.LAYER_REST, AgendamedicosException.ACTION_VALIDATE);
            medPacE.addError("idmedico tiene valores incorrectos: " + idmedico);
            throw medPacE;
        }

        if (page == null)
            page = 0;
        if (size == null)
            size = 120;
        if (orderType == null || orderType.isEmpty())
            orderType = "asc";
        if (orderColumn == null || orderColumn.isEmpty())
            orderColumn = "idagenda";

        return AgendamedicosService.getAgendamedicosSearch(uidmedico, page, size, orderColumn, orderType);
    }



}
