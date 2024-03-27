package net.amentum.niomedic.medicos.service.impl;

import net.amentum.niomedic.medicos.converter.DomicilioConverter;
import net.amentum.niomedic.medicos.exception.DomicilioException;
import net.amentum.niomedic.medicos.model.Domicilio;
import net.amentum.niomedic.medicos.model.Medico;
import net.amentum.niomedic.medicos.persistence.DomicilioRepository;
import net.amentum.niomedic.medicos.persistence.MedicoRepository;
import net.amentum.niomedic.medicos.service.DomicilioService;
import net.amentum.niomedic.medicos.views.DomicilioView;
import net.amentum.niomedic.medicos.views.MedicoView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DomicilioServiceImpl implements DomicilioService {
   private final Logger logger = LoggerFactory.getLogger(DomicilioServiceImpl.class);

   private DomicilioRepository domicilioRepository;
   private DomicilioConverter domicilioConverter;
   private MedicoRepository medicoRepository;

   @Autowired
   public void setDomicilioRepository(DomicilioRepository domicilioRepository) {
      this.domicilioRepository = domicilioRepository;
   }

   @Autowired
   public void setDomicilioConverter(DomicilioConverter domicilioConverter) {
      this.domicilioConverter = domicilioConverter;
   }

   @Autowired
   public void setMedicoRepository(MedicoRepository medicoRepository) {
      this.medicoRepository = medicoRepository;
   }


   @Transactional(readOnly = false, rollbackFor = {DomicilioException.class})
   @Override
   public void createDomicilio(ArrayList<DomicilioView> domicilioViewArrayList, String idMedico) throws DomicilioException {
      try {
         if (!medicoRepository.exists(idMedico)) {
            logger.error("===>>>idMedico no encotrado: {}", idMedico);
            DomicilioException dome = new DomicilioException("No se encuentra en el sistema al medico.", DomicilioException.LAYER_DAO, DomicilioException.ACTION_VALIDATE);
            dome.addError("idMedico no encontrado: " + idMedico);
            throw dome;
         }
         Medico medico = medicoRepository.findOne(idMedico);
         ArrayList<Domicilio> domicilioArrayList = new ArrayList<>();
         domicilioConverter.toEntity(domicilioViewArrayList, domicilioArrayList, medico, Boolean.FALSE);
         logger.debug("===>>>Insertar nuevo domicilio: {}", domicilioArrayList);
         domicilioRepository.save(domicilioArrayList);
      } catch (DomicilioException dome) {
         throw dome;
      } catch (DataIntegrityViolationException dte) {
         DomicilioException dome = new DomicilioException("No fue posible agregar domicilio", DomicilioException.LAYER_DAO, DomicilioException.ACTION_INSERT);
         dome.addError("Ocurrio un error al agregar domicilio");
         logger.error("===>>>Error al insertar nueva domicilio - CODE: {} - {}", dome.getExceptionCode(), domicilioViewArrayList, dte);
         throw dome;
      } catch (Exception ex) {
         DomicilioException dome = new DomicilioException("Error inesperado al agregar domicilio", DomicilioException.LAYER_DAO, DomicilioException.ACTION_INSERT);
         dome.addError("Ocurrio un error al agregar domicilio");
         logger.error("===>>>Error al insertar nueva domicilio - CODE: {} - {}", dome.getExceptionCode(), domicilioViewArrayList, ex);
         throw dome;
      }
   }

   @Transactional(readOnly = false, rollbackFor = {DomicilioException.class})
   @Override
   public void updateDomicilio(ArrayList<DomicilioView> domicilioViewArrayList, String idMedico) throws DomicilioException {
      try {
         if (!medicoRepository.exists(idMedico)) {
            logger.error("===>>>idMedico no encotrado: {}", idMedico);
            DomicilioException dome = new DomicilioException("No se encuentra en el sistema al medico.", DomicilioException.LAYER_DAO, DomicilioException.ACTION_VALIDATE);
            dome.addError("idMedico no encontrado: " + idMedico);
            throw dome;
         }
         Medico medico = medicoRepository.findOne(idMedico);
         MedicoView medicoView = new MedicoView();
         medicoView.setDomicilioViewList(domicilioViewArrayList);

//         los elimino de la DB
         Collection<String> noExistenDomicilioView = domicilioConverter.obtenerIDNoExistentesDomicilio(medico, medicoView);
         Domicilio dom;
         for (String IDdom : noExistenDomicilioView) {
            dom = domicilioRepository.findOne(IDdom);
            dom.setMedico(null);
            domicilioRepository.delete(IDdom);
         }
//         limpiar la lista
         medico.setDomicilioList(new ArrayList<>());
//         la "nueva lista"
         Collection<Domicilio> domicilio = new ArrayList<>();

         domicilioConverter.toEntity(domicilioViewArrayList, domicilio, medico, Boolean.TRUE);
         domicilioRepository.save(domicilio);
      } catch (DomicilioException dome) {
         throw dome;
      } catch (DataIntegrityViolationException dte) {
         DomicilioException dome = new DomicilioException("No fue posible modificar domicilio", DomicilioException.LAYER_DAO, DomicilioException.ACTION_UPDATE);
         dome.addError("Ocurrio un error al modificar domicilio");
         logger.error("===>>>Error al modificar nuevo domicilio - CODE: {} - {}", dome.getExceptionCode(), domicilioViewArrayList, dte);
         throw dome;
      } catch (Exception ex) {
         DomicilioException dome = new DomicilioException("Error inesperado al modificar domicilio", DomicilioException.LAYER_DAO, DomicilioException.ACTION_UPDATE);
         dome.addError("Ocurrio un error al modificar domicilio");
         logger.error("===>>>Error al modificar nuevo domicilio - CODE: {} - {}", dome.getExceptionCode(), domicilioViewArrayList, ex);
         throw dome;
      }
   }

   @Transactional(readOnly = false, rollbackFor = {DomicilioException.class})
   @Override
   public void deleteDomicilio(ArrayList<DomicilioView> domicilioViewArrayList, String idMedico) throws DomicilioException {
      try {
         if (!medicoRepository.exists(idMedico)) {
            logger.error("===>>>idMedico no encotrado: {}", idMedico);
            DomicilioException dome = new DomicilioException("No se encuentra en el sistema al medico.", DomicilioException.LAYER_DAO, DomicilioException.ACTION_VALIDATE);
            dome.addError("idMedico no encontrado: " + idMedico);
            throw dome;
         }
         Medico medico = medicoRepository.findOne(idMedico);
         MedicoView medicoView = new MedicoView();
         medicoView.setDomicilioViewList(domicilioViewArrayList);

//         obtener los id a borrar
         Collection<String> idsView = new ArrayList<>();
         idsView.addAll(
            medicoView.getDomicilioViewList().stream()
               .map(domV -> {
                  String idV = domV.getIdDomicilio();
                  return idV;
               }).collect(Collectors.toList())
         );
//         los elimino de la DB
         Domicilio dom;
         for (String IDdom : idsView) {
            dom = domicilioRepository.findOne(IDdom);
            if (dom != null) {
               dom.setMedico(null);
               domicilioRepository.delete(IDdom);
            } else {
               logger.error("===>>>idDomicilio no encotrado: {}", IDdom);
               DomicilioException dome = new DomicilioException("No se encuentra en el sistema al idDomicilio.", DomicilioException.LAYER_DAO, DomicilioException.ACTION_VALIDATE);
               dome.addError("idDomicilio no encotrado: " + IDdom);
               throw dome;
            }
         }
//         limpiar la lista
         medico.setDomicilioList(new ArrayList<>());
//         la "nueva lista"
         Collection<Domicilio> domicilio = new ArrayList<>();

         domicilioRepository.save(domicilio);
      } catch (DomicilioException dome) {
         throw dome;
      } catch (Exception ex) {
         DomicilioException dome = new DomicilioException("Error inesperado al borrar domicilio", DomicilioException.LAYER_DAO, DomicilioException.ACTION_DELETE);
         dome.addError("Ocurrio un error alborrar domicilio");
         logger.error("===>>>Error al borrar domicilio - CODE: {} - {}", dome.getExceptionCode(), idMedico, ex);
         throw dome;
      }

   }

}

