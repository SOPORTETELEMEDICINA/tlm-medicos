package net.amentum.niomedic.medicos.rest;

import java.util.List; // Sre19062020 Nuevo
import net.amentum.common.BaseController;
import net.amentum.niomedic.medicos.exception.MedicoException;
import net.amentum.niomedic.medicos.service.MedicoService;
import net.amentum.niomedic.medicos.views.MedicoPageView;
import net.amentum.niomedic.medicos.views.MedicoView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("medicos")
public class MedicoRest extends BaseController {
   private final Logger logger = LoggerFactory.getLogger(MedicoRest.class);
   private MedicoService medicoService;

   @Autowired
   public void setMedicoService(MedicoService medicoService) {
      this.medicoService = medicoService;
   }

   @Value("${application.controller.direccion}")
   private String direccion;

   @RequestMapping(method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
   public MedicoView createdMedico(@RequestBody @Validated MedicoView medicoView) throws MedicoException {
      try {
         logger.info("===>>>Guardar nuevo medico: {}", medicoView);
        return medicoService.createMedico(medicoView, direccion);
      } catch (MedicoException me) {
         throw me;
      } catch (Exception ex) {
         MedicoException me = new MedicoException("No fue posible insertar el medico", MedicoException.LAYER_REST, MedicoException.ACTION_INSERT);
         logger.error("===>>>Error al insertar el medico- CODE: {} - ", me.getExceptionCode(), ex);
         throw me;
      }
   }

   @RequestMapping(value = "{idMedico}", method = RequestMethod.PUT)
   @ResponseStatus(HttpStatus.OK)
   public MedicoView updateMedico(@PathVariable() String idMedico, @RequestBody @Valid MedicoView medicoView) throws MedicoException {
      try {
         medicoView.setIdMedico(idMedico);
         logger.info("===>>>Editar medico: {}", medicoView);
         return medicoService.updateMedico(medicoView, direccion);
      } catch (MedicoException me) {
         throw me;
      } catch (Exception ex) {
         MedicoException me = new MedicoException("No fue posible modificar el medico", MedicoException.LAYER_REST, MedicoException.ACTION_UPDATE);
         logger.error("===>>>Error al modificar el medico- CODE: {} - ", me.getExceptionCode(), ex);
         throw me;
      }
   }

   @RequestMapping(value = "{idMedico}", method = RequestMethod.GET)
   @ResponseStatus(HttpStatus.OK)
   public MedicoView getDetailsByIdMedico(@PathVariable() String idMedico) throws MedicoException {
      try {
         logger.info("===>>>Obtener los detalles del medico por Id: {}", idMedico);
         return medicoService.getDetailsByIdMedico(idMedico);
      } catch (MedicoException me) {
         throw me;
      } catch (Exception ex) {
         MedicoException me = new MedicoException("No fue posible obtener los detalles del medico por Id", MedicoException.LAYER_REST, MedicoException.ACTION_SELECT);
         logger.error("===>>>Error al obtener los detalles del medico por Id- CODE: {} - ", me.getExceptionCode(), ex);
         throw me;
      }
   }

   @RequestMapping(value = "/obtenerPorIdUsuario/{idUsuario}", method = RequestMethod.GET)
   @ResponseStatus(HttpStatus.OK)
   public MedicoView getDetailsByIdUsuario(@PathVariable() Long idUsuario) throws MedicoException {
      try {
         logger.info("===>>>Obtener los detalles del medico por IdUsuario: {}", idUsuario);
         return medicoService.getDetailsByIdUsuario(idUsuario);
      } catch (MedicoException me) {
         throw me;
      } catch (Exception ex) {
         MedicoException me = new MedicoException("No fue posible obtener los detalles del medico por IdUsuario", MedicoException.LAYER_REST, MedicoException.ACTION_SELECT);
         logger.error("===>>>Error al obtener los detalles del medico por IdUsuario- CODE: {} - ", me.getExceptionCode(), ex);
         throw me;
      }
   }
   // GGR20200617 agrego grupo seleccionado
   @RequestMapping(value = "page", method = RequestMethod.GET)
   @ResponseStatus(HttpStatus.OK)
   public Page<MedicoPageView> getMedicoPage(@RequestParam(required = false, defaultValue = "") String datosBusqueda,
                                             @RequestParam(required = false) Boolean active,
//                                              @RequestParam(required = false, defaultValue = "") String name,
                                             @RequestParam(required = false) Integer page,
                                             @RequestParam(required = false) Integer size,
                                             @RequestParam(required = false) String orderColumn,
                                             @RequestParam(required = false) String orderType,
                                             @RequestParam(required = false) Long selectGroup) throws MedicoException {
      logger.info("===>>>getMedicoPage(): - datosBusqueda {} - active {} - page {} - size {} - orderColumn {} - orderType {} - selectGroup {}",
         datosBusqueda, active, page, size, orderColumn, orderType, selectGroup);

      if (page == null)
         page = 0;
      if (size == null)
         size = 10;
      if (orderType == null || orderType.isEmpty())
         orderType = "asc";
      if (orderColumn == null || orderColumn.isEmpty())
         orderColumn = "nombre";

//        return medicoService.getMedicoPage(active, name != null ? name : "", page, size, orderColumn, orderType);
      return medicoService.getMedicoPage(datosBusqueda != null ? datosBusqueda : "", active, page, size, orderColumn, orderType, selectGroup);
   }

   @RequestMapping(value = "/obtenerPorNombreUsuario/{userName}", method = RequestMethod.GET)
   @ResponseStatus(HttpStatus.OK)
   public MedicoView getDetailsByUserName(@PathVariable() String userName) throws MedicoException {
      try {
         logger.info("Obtener los detalles del medico por UserName: {}", userName);
         return medicoService.getDetailsByUserName(userName);
      } catch (MedicoException me) {
         throw me;
      } catch (Exception ex) {
         MedicoException me = new MedicoException("No fue posible obtener los detalles del medico por UserName", MedicoException.LAYER_REST, MedicoException.ACTION_SELECT);
         logger.error("Error al obtener los detalles del medico por UserName- CODE: {} - ", me.getExceptionCode(), ex);
         throw me;
      }
   }
   
    // Sre19062020 Agrego metodo para actualizar grupos de medicos
   @RequestMapping(value = "/grupos/{idUsuario}", method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.OK)
   public void updateMedicoGroups(@PathVariable() Long idUsuario, @RequestBody List<Long> medicoGroups) throws MedicoException {
       // Aca debo actualizar los grupos del médico
      try {
         logger.info("Actualizar los grupos del medico por idUsuario: {}", idUsuario);
         medicoService.updateMedicoGroups(idUsuario, medicoGroups);
      } catch (MedicoException me) {
         throw me;
      } catch (Exception ex) {
         MedicoException me = new MedicoException("No fue posible actualizar los grupos del medico por idUsuario", MedicoException.LAYER_REST, MedicoException.ACTION_UPDATE);
         logger.error("Error al actualizar los grupos del medico por idUsuario- CODE: {} - ", me.getExceptionCode(), ex);
         throw me;
      }
   }
}
