package net.amentum.niomedic.medicos.converter;

import net.amentum.niomedic.medicos.model.Especialidad;
import net.amentum.niomedic.medicos.model.Medico;
import net.amentum.niomedic.medicos.model.UnidadMedica;
import net.amentum.niomedic.medicos.views.MedicoPageView;
import net.amentum.niomedic.medicos.views.MedicoView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@Component
public class MedicoConverter {
   private final Logger logger = LoggerFactory.getLogger(MedicoConverter.class);

   @Autowired
   private DomicilioConverter domicilioConverter;

   @Autowired
   private EspecialidadConverter especialidadConverter;

   public Medico toEntity(MedicoView medicoView, Medico medico, Boolean update,String direccion) throws Exception {
      medico.setNombre(medicoView.getNombre());
      medico.setApellidoPaterno(medicoView.getApellidoPaterno());
      medico.setApellidoMaterno(medicoView.getApellidoMaterno());
      medico.setFechaNacimiento(medicoView.getFechaNacimiento());
      medico.setLugarNacimiento(medicoView.getLugarNacimiento());
      medico.setEstadoCivil(medicoView.getEstadoCivil());
      medico.setSexo(medicoView.getSexo());
      medico.setCurp(medicoView.getCurp());
      medico.setRfc(medicoView.getRfc());
      medico.setEmail(medicoView.getEmail());
      medico.setTelefonoFijo(medicoView.getTelefonoFijo());
      medico.setTelefonoMovil(medicoView.getTelefonoMovil());
      medico.setId_cat_nacionalidades(medicoView.getId_cat_nacionalidades());
      medico.setId_cat_entidades(medicoView.getId_cat_entidades());
      medico.setId_institucion(medicoView.getId_institucion());
      medico.setPer_id(medicoView.getPer_id());
      medico.setAct_id(medicoView.getAct_id());
      medico.setAtr_id(medicoView.getAtr_id());
      medico.setId_cat_clues(medicoView.getId_cat_clues());
      medico.setJor_id(medicoView.getJor_id());
      medico.setId_cat_especialidades(medicoView.getId_cat_especialidades());
      medico.setCon_id(medicoView.getCon_id());
      medico.setPla_id(medicoView.getPla_id());
      medico.setIdUsuario(medicoView.getIdUsuario());
      medico.setActivo(medicoView.getActivo());
      medico.setUserName(medicoView.getUserName());
      medico.setFechaCreacion((update) ? medico.getFechaCreacion() : new Date());
      medico.setId_medico_firma(medicoView.getId_medico_firma());
//      el campo de busqueda
      String datosBusqueda = medicoView.getNombre() + " " + medicoView.getApellidoPaterno() + " " + medicoView.getApellidoMaterno() + " " + medicoView.getCurp();
      medico.setDatosBusqueda(datosBusqueda);
      //campo de zoomId
      medico.setIdUsuarioZoom(medicoView.getIdUsuarioZoom());
      medico.setIdUnidadMedica(medicoView.getIdUnidadMedica());
      if (!update) {

         if (medicoView.getDomicilioViewList() != null && !medicoView.getDomicilioViewList().isEmpty()) {
            medico.setDomicilioList(domicilioConverter.toEntity(
               medicoView.getDomicilioViewList(),
               medico.getDomicilioList(),
               medico,
               Boolean.FALSE
            ));
         }

         if (medicoView.getEspecialidadViewList() != null && !medicoView.getEspecialidadViewList().isEmpty()) {
            medico.setEspecialidadList(especialidadConverter.toEntity(
               medicoView.getEspecialidadViewList(),
               medico.getEspecialidadList(),
               medico,
               Boolean.FALSE,
               direccion
            ));
         }


      } else {

         if (medicoView.getDomicilioViewList() != null && !medicoView.getDomicilioViewList().isEmpty()) {
            medico.setDomicilioList(domicilioConverter.toEntity(
               medicoView.getDomicilioViewList(),
               medico.getDomicilioList(),
               medico,
               Boolean.TRUE
            ));
         }

         if (medicoView.getEspecialidadViewList() != null && !medicoView.getEspecialidadViewList().isEmpty()) {
            medico.setEspecialidadList(especialidadConverter.toEntity(
               medicoView.getEspecialidadViewList(),
               medico.getEspecialidadList(),
               medico,
               Boolean.TRUE,
               direccion
            ));
         }

      }

      logger.debug("convertir MedicoView to Entity: {}", medico);
      return medico;
   }

   public MedicoView toView(Medico medico, Boolean complete) throws Exception {
      MedicoView medicoView = new MedicoView();
      medicoView.setIdMedico(medico.getIdMedico());
      medicoView.setNombre(medico.getNombre());
      medicoView.setApellidoPaterno(medico.getApellidoPaterno());
      medicoView.setApellidoMaterno(medico.getApellidoMaterno());
      medicoView.setFechaNacimiento(medico.getFechaNacimiento());
      medicoView.setLugarNacimiento(medico.getLugarNacimiento());
      medicoView.setEstadoCivil(medico.getEstadoCivil());
      medicoView.setSexo(medico.getSexo());
      medicoView.setCurp(medico.getCurp());
      medicoView.setRfc(medico.getRfc());
      medicoView.setEmail(medico.getEmail());
      medicoView.setTelefonoFijo(medico.getTelefonoFijo());
      medicoView.setTelefonoMovil(medico.getTelefonoMovil());
      medicoView.setId_cat_nacionalidades(medico.getId_cat_nacionalidades());
      medicoView.setId_cat_entidades(medico.getId_cat_entidades());
      medicoView.setId_institucion(medico.getId_institucion());
      medicoView.setPer_id(medico.getPer_id());
      medicoView.setAct_id(medico.getAct_id());
      medicoView.setAtr_id(medico.getAtr_id());
      medicoView.setId_cat_clues(medico.getId_cat_clues());
      medicoView.setJor_id(medico.getJor_id());
      medicoView.setId_cat_especialidades(medico.getId_cat_especialidades());
      medicoView.setCon_id(medico.getCon_id());
      medicoView.setPla_id(medico.getPla_id());
      medicoView.setIdUsuario(medico.getIdUsuario());
      medicoView.setActivo(medico.getActivo());
      medicoView.setUserName(medico.getUserName());
      medicoView.setFechaCreacion(medico.getFechaCreacion());
      medicoView.setId_medico_firma(medico.getId_medico_firma());
      //Zoom id
      medicoView.setIdUsuarioZoom(medico.getIdUsuarioZoom());
      medicoView.setIdUnidadMedica(medico.getIdUnidadMedica());
      /*if(medico.getUnidadMedica() !=null && !medico.getUnidadMedica().isEmpty()) {
    	  for(UnidadMedica um:medico.getUnidadMedica()) {
    		  medicoView.setIdUnidadMedica(um.getIdUnidadMedica());  
    	  } 
      }*/

      if (complete) {
         if (medico.getDomicilioList() != null && !medico.getDomicilioList().isEmpty()) {
            medicoView.setDomicilioViewList(domicilioConverter.toView(medico.getDomicilioList(), true));
         }

         if (medico.getEspecialidadList() != null && !medico.getEspecialidadList().isEmpty()) {
            medicoView.setEspecialidadViewList(especialidadConverter.toView(medico.getEspecialidadList(), true));
         }
      }

      logger.debug("convertir medico to View: {}", medicoView);
      return medicoView;
   }

   public MedicoPageView toViewPage(Medico medico) throws Exception {
      MedicoPageView medicoPageView = new MedicoPageView();
      medicoPageView.setIdMedico(medico.getIdMedico());
      medicoPageView.setNombre(medico.getNombre() + " " + medico.getApellidoPaterno() + " " + medico.getApellidoMaterno());
      medicoPageView.setEmail(medico.getEmail());
      medicoPageView.setCurp(medico.getCurp());
      medicoPageView.setUserName(medico.getUserName());
      medicoPageView.setIdUsuario(medico.getIdUsuario());
      medicoPageView.setEspecialidades("");
      if (medico.getEspecialidadList() != null && !medico.getEspecialidadList().isEmpty()) {
         medicoPageView.setEspecialidadViewList(especialidadConverter.toView(medico.getEspecialidadList(), true));
         ArrayList<String> especialidad = new ArrayList<String>();
         for(Especialidad esp: medico.getEspecialidadList()){
            especialidad.add(esp.getEspecialidad());
         }
         Collections.sort(especialidad);
         medicoPageView.setEspecialidades(especialidad.toString());
      }
      medicoPageView.setIdUnidadMedica(medico.getIdUnidadMedica());
      /*if(medico.getUnidadMedica() !=null && !medico.getUnidadMedica().isEmpty()) {
    	  for(UnidadMedica um:medico.getUnidadMedica()) {
    		  medicoPageView.setIdUnidadMedica(um.getIdUnidadMedica());  
    	  } 
      }*/
      //ZoomId
      medicoPageView.setIdUsuarioZoom(medico.getIdUsuarioZoom());
      logger.debug("converter medico to View-Page: {}", medicoPageView);
      return medicoPageView;
   }
}
