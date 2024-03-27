package net.amentum.niomedic.medicos.converter;

import net.amentum.niomedic.medicos.model.Domicilio;
import net.amentum.niomedic.medicos.model.Medico;
import net.amentum.niomedic.medicos.views.DomicilioView;
import net.amentum.niomedic.medicos.views.MedicoView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class DomicilioConverter {
   private Logger logger = LoggerFactory.getLogger(DomicilioConverter.class);

   public Collection<Domicilio> toEntity(Collection<DomicilioView> domicilioViewArrayList, Collection<Domicilio> domicilioArrayList, Medico medico, Boolean update) {
      for (DomicilioView domV : domicilioViewArrayList) {
         Domicilio dom = new Domicilio();
         if (update) {
            dom.setIdDomicilio(domV.getIdDomicilio());
         } else {
            logger.debug("----->NO es un update");
         }
         dom.setDomicilio(domV.getDomicilio());
         dom.setLocalidad(domV.getLocalidad());
         dom.setMunicipio(domV.getMunicipio());
         dom.setEstado(domV.getEstado());
         dom.setPais(domV.getPais());
         dom.setCp(domV.getCp());
         dom.setTelefonoFijo(domV.getTelefonoFijo());
         dom.setRegistroSanitario(domV.getRegistroSanitario());
         dom.setFechaCreacion((!update) ? new Date() : medico.getFechaCreacion());
         dom.setActivo(domV.getActivo());
         dom.setTipo(domV.getTipo());
         dom.setMedico(medico);
         dom.setHorarioAtencion(domV.getHorarioAtencion()); // GGR20200731 agrego campo horario de atención
         domicilioArrayList.add(dom);
      }
      logger.debug("converter domicilioView to Entity: {}", domicilioArrayList);
      return domicilioArrayList;
   }

   public Collection<DomicilioView> toView(Collection<Domicilio> domicilioArrayList, Boolean completeConversion) {
      Collection<DomicilioView> domicilioViews = new ArrayList<>();
      for (Domicilio dom : domicilioArrayList) {
         DomicilioView domV = new DomicilioView();
         domV.setIdDomicilio(dom.getIdDomicilio());
         domV.setDomicilio(dom.getDomicilio());
         domV.setLocalidad(dom.getLocalidad());
         domV.setMunicipio(dom.getMunicipio());
         domV.setEstado(dom.getEstado());
         domV.setPais(dom.getPais());
         domV.setCp(dom.getCp());
         domV.setTelefonoFijo(dom.getTelefonoFijo());
         domV.setRegistroSanitario(dom.getRegistroSanitario());
         domV.setFechaCreacion(dom.getFechaCreacion());
         domV.setActivo(dom.getActivo());
         domV.setTipo(dom.getTipo());
         domV.setHorarioAtencion(dom.getHorarioAtencion()); // GGR20200731 agrego campo horario de atención
         domicilioViews.add(domV);
      }
      logger.debug("converter Domicilio to View: {}", domicilioViews);
      return domicilioViews;
   }

   public Collection<String> obtenerIDNoExistentesDomicilio(Medico medico, MedicoView medicoView) {
//      IDs de DB
      Collection<String> ids = new ArrayList<>();
      ids.addAll(
         medico.getDomicilioList().stream()
            .map(pv -> {
               String id = pv.getIdDomicilio();
               return id;
            }).collect(Collectors.toList())
      );

//      IDs de View
      Collection<String> idsView = new ArrayList<>();
      idsView.addAll(
         medicoView.getDomicilioViewList().stream()
            .map(pvV -> {
               String idV = pvV.getIdDomicilio();
               return idV;
            }).collect(Collectors.toList())
      );

//      Obtener los no existentes
      Collection<String> noExisten = new ArrayList<>(ids);
      noExisten.removeAll(idsView);

//      logger.info("--->ids--->{}", ids);
//      logger.info("--->idsView--->{}", idsView);
//      logger.info("--->noExisten--->{}", noExisten);

      return noExisten;
   }

}
