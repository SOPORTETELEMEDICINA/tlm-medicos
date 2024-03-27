package net.amentum.niomedic.medicos.rest;

import net.amentum.niomedic.medicos.exception.EspecialidadException;
import net.amentum.niomedic.medicos.service.EspecialidadService;
import net.amentum.niomedic.medicos.views.EspecialidadView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;

@RestController
@RequestMapping("medicos")
public class EspecialidadRest {
   private final Logger logger = LoggerFactory.getLogger(EspecialidadRest.class);

   @Autowired
   private EspecialidadService especialidadService;
   @Value("${application.controller.direccion}")
	private String direccion;
   @RequestMapping(value = "{idMedico}/especialidad", method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
   public void createEspecialidad(@PathVariable() String idMedico, @RequestBody @Valid ArrayList<EspecialidadView> especialidadViewArrayList) throws EspecialidadException {
      try {
         logger.info("===>>>Guardar nuevo especialidad: {}", especialidadViewArrayList);
         especialidadService.createEspecialidad(especialidadViewArrayList, idMedico, direccion);
      } catch (EspecialidadException espe) {
         throw espe;
      } catch (Exception ex) {
         EspecialidadException espe = new EspecialidadException("No fue posible insertar especialidad", EspecialidadException.LAYER_REST, EspecialidadException.ACTION_INSERT);
         logger.error("===>>>Error al insertar especialidad- CODE: {} - ", espe.getExceptionCode(), ex);
         throw espe;
      }
   }

   @RequestMapping(value = "{idMedico}/especialidad",method = RequestMethod.PUT)
   @ResponseStatus(HttpStatus.OK)
   public void updateEspecialidad(@PathVariable()String idMedico, @RequestBody @Valid ArrayList<EspecialidadView> especialidadViewArrayList) throws EspecialidadException {
      try{
         logger.info("===>>>Modificar especialidad: {}", especialidadViewArrayList);
         especialidadService.updateEspecialidad(especialidadViewArrayList,idMedico, direccion);
      }catch (EspecialidadException espe){
         throw espe;
      }catch(Exception ex){
         EspecialidadException espe = new EspecialidadException("No fue posible modificar especialidad", EspecialidadException.LAYER_REST, EspecialidadException.ACTION_UPDATE);
         logger.error("===>>>Error al modificar especialidad- CODE: {} - ",espe.getExceptionCode(),ex);
         throw espe;
      }
   }

   @RequestMapping(value = "{idMedico}/especialidad",method = RequestMethod.DELETE)
   @ResponseStatus(HttpStatus.OK)
   public void deleteEspecialidad(@PathVariable()String idMedico, @RequestBody @Valid ArrayList<EspecialidadView> especialidadViewArrayList) throws EspecialidadException {
      try{
         logger.info("===>>>Borrar especialidad: {}", especialidadViewArrayList);
         especialidadService.deleteEspecialidad(especialidadViewArrayList,idMedico);
      }catch (EspecialidadException espe){
         throw espe;
      }catch(Exception ex){
         EspecialidadException espe = new EspecialidadException("No fue posible borrar especialidad", EspecialidadException.LAYER_REST, EspecialidadException.ACTION_DELETE);
         logger.error("===>>>Error al borrar especialidad- CODE: {} - ",espe.getExceptionCode(),ex);
         throw espe;
      }
   }

   @RequestMapping(value = "subirCedula/{idMedico}", method = RequestMethod.POST)
   public void subirCedula(@RequestParam("file") MultipartFile[] file, @PathVariable() String idMedico) {
      for (MultipartFile imagen : file) {
         String nombreArchivoAlmacenar = idMedico +"-"+ imagen.getOriginalFilename();
         String filePath = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + File.separator + "niomedic" + File.separator + "cedulas" + File.separator;
         if (!imagen.isEmpty()) {
            try {
               imagen.transferTo(new File(filePath + nombreArchivoAlmacenar));
               logger.debug("---> Va para aca ---> {}", filePath);
            } catch (Exception e) {
               logger.debug("--->Error al subir imagen en REST: ---> {}", e.getCause());
            }
         }
      }
   }

   @RequestMapping(value = "subirDiploma/{idMedico}", method = RequestMethod.POST)
   public void subirDiploma(@RequestParam("file") MultipartFile[] file, @PathVariable() String idMedico) {
      for (MultipartFile imagen : file) {
         String nombreArchivoAlmacenar = idMedico +"-"+ imagen.getOriginalFilename();
         String filePath = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + File.separator + "niomedic" + File.separator + "diplomas" + File.separator;
         if (!imagen.isEmpty()) {
            try {
               imagen.transferTo(new File(filePath + nombreArchivoAlmacenar));
               logger.debug("---> Va para aca ---> {}", filePath);
            } catch (Exception e) {
               logger.debug("--->Error al subir imagen en REST: ---> {}", e.getCause());
            }
         }
      }
   }

}
