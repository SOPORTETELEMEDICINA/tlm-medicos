package net.amentum.niomedic.medicos.service.impl;

import net.amentum.niomedic.medicos.converter.EspecialidadConverter;
import net.amentum.niomedic.medicos.exception.EspecialidadException;
import net.amentum.niomedic.medicos.model.Especialidad;
import net.amentum.niomedic.medicos.model.Medico;
import net.amentum.niomedic.medicos.persistence.EspecialidadRepository;
import net.amentum.niomedic.medicos.persistence.MedicoRepository;
import net.amentum.niomedic.medicos.service.EspecialidadService;
import net.amentum.niomedic.medicos.views.EspecialidadView;
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
public class EspecialidadServiceImpl implements EspecialidadService {
   private final Logger logger = LoggerFactory.getLogger(EspecialidadServiceImpl.class);

   private EspecialidadRepository especialidadRepository;
   private EspecialidadConverter especialidadConverter;
   private MedicoRepository medicoRepository;

   @Autowired
   public void setEspecialidadRepository(EspecialidadRepository especialidadRepository) {
      this.especialidadRepository = especialidadRepository;
   }

   @Autowired
   public void setEspecialidadConverter(EspecialidadConverter especialidadConverter) {
      this.especialidadConverter = especialidadConverter;
   }

   @Autowired
   public void setMedicoRepository(MedicoRepository medicoRepository) {
      this.medicoRepository = medicoRepository;
   }


   @Transactional(readOnly = false, rollbackFor = {EspecialidadException.class})
   @Override
   public void createEspecialidad(ArrayList<EspecialidadView> especialidadViewArrayList, String idMedico, String direccion) throws EspecialidadException {
      try {
         if (!medicoRepository.exists(idMedico)) {
            logger.error("===>>>idMedico no encotrado: {}", idMedico);
            EspecialidadException espe = new EspecialidadException("No se encuentra en el sistema al medico.", EspecialidadException.LAYER_DAO, EspecialidadException.ACTION_VALIDATE);
            espe.addError("idMedico no encontrado: " + idMedico);
            throw espe;
         }
         Medico medico = medicoRepository.findOne(idMedico);
         ArrayList<Especialidad> especialidadArrayList = new ArrayList<>();
         especialidadConverter.toEntity(especialidadViewArrayList, especialidadArrayList, medico, Boolean.FALSE, direccion);
         logger.debug("===>>>Insertar nueva especialidad: {}", especialidadArrayList);
         especialidadRepository.save(especialidadArrayList);
      } catch (EspecialidadException espe) {
         throw espe;
      } catch (DataIntegrityViolationException dte) {
         EspecialidadException espe = new EspecialidadException("No fue posible agregar especialidad", EspecialidadException.LAYER_DAO, EspecialidadException.ACTION_INSERT);
         espe.addError("Ocurrio un error al agregar especialidad");
         logger.error("===>>>Error al insertar nueva especialidad - CODE: {} - {}", espe.getExceptionCode(), especialidadViewArrayList, dte);
         throw espe;
      } catch (StringIndexOutOfBoundsException sioobe) {
         logger.error("===>>>No esta bien contruida la imagen de los archivos a subir");
         EspecialidadException espe = new EspecialidadException("No fue posible agregar especialidad", EspecialidadException.LAYER_DAO, EspecialidadException.ACTION_INSERT);
         espe.addError("No esta bien construida la imagen de los archivos a subir");
         throw espe;
      } catch (Exception ex) {
         EspecialidadException espe = new EspecialidadException("Error inesperado al agregar especialidad", EspecialidadException.LAYER_DAO, EspecialidadException.ACTION_INSERT);
         espe.addError("Ocurrio un error al agregar especialidad");
         logger.error("===>>>Error al insertar nueva especialidad - CODE: {} - {}", espe.getExceptionCode(), especialidadViewArrayList, ex);
         throw espe;
      }
   }

   @Transactional(readOnly = false, rollbackFor = {EspecialidadException.class})
   @Override
   public void updateEspecialidad(ArrayList<EspecialidadView> especialidadViewArrayList, String idMedico, String direccion) throws EspecialidadException {
      try {
         if (!medicoRepository.exists(idMedico)) {
            logger.error("===>>>idMedico no encotrado: {}", idMedico);
            EspecialidadException espe = new EspecialidadException("No se encuentra en el sistema al medico.", EspecialidadException.LAYER_DAO, EspecialidadException.ACTION_VALIDATE);
            espe.addError("idMedico no encontrado: " + idMedico);
            throw espe;
         }
         Medico medico = medicoRepository.findOne(idMedico);
         MedicoView medicoView = new MedicoView();
         medicoView.setEspecialidadViewList(especialidadViewArrayList);

//         los elimino de la DB
         Collection<String> noExistenEspecialidadView = especialidadConverter.obtenerIDNoExistentesEspecialidad(medico, medicoView);
         Especialidad esp;
         for (String IDesp : noExistenEspecialidadView) {
        	 esp = especialidadRepository.findOne(IDesp);
        	 esp.setMedico(null);
        	 //eliminando imagenes del fileSystem
        	 if(esp.getImgCedula64()!=null) {
        		 especialidadConverter.eliminarImagen(esp.getImgCedula64());
        	 }
        	 if(esp.getImgTitulo64()!=null) {
        		 especialidadConverter.eliminarImagen(esp.getImgTitulo64());
        	 }	
        	 especialidadRepository.delete(IDesp);
         }
//         limpiar la lista
         medico.setEspecialidadList(new ArrayList<>());
//         la "nueva lista"
         Collection<Especialidad> especialidad = new ArrayList<>();

         especialidadConverter.toEntity(especialidadViewArrayList, especialidad, medico, Boolean.TRUE, direccion);
         especialidadRepository.save(especialidad);
      } catch (EspecialidadException espe) {
         throw espe;
      } catch (DataIntegrityViolationException dte) {
         EspecialidadException espe = new EspecialidadException("No fue posible agregar especialidad", EspecialidadException.LAYER_DAO, EspecialidadException.ACTION_UPDATE);
         espe.addError("Ocurrio un error al agregar especialidad");
         logger.error("===>>>Error al insertar nuevo especialidad - CODE: {} - {}", espe.getExceptionCode(), especialidadViewArrayList, dte);
         throw espe;
      } catch (StringIndexOutOfBoundsException sioobe) {
         logger.error("===>>>No esta bien contruida la imagen de los archivos a subir");
         EspecialidadException espe = new EspecialidadException("No fue posible agregar especialidad", EspecialidadException.LAYER_DAO, EspecialidadException.ACTION_INSERT);
         espe.addError("No esta bien contruida la imagen de los archivos a subir");
         throw espe;
      } catch (Exception ex) {
         EspecialidadException espe = new EspecialidadException("Error inesperado al agregar especialidad", EspecialidadException.LAYER_DAO, EspecialidadException.ACTION_UPDATE);
         espe.addError("Ocurrio un error al agregar especialidad");
         logger.error("===>>>Error al insertar nuevo especialidad - CODE: {} - {}", espe.getExceptionCode(), especialidadViewArrayList, ex);
         throw espe;
      }
   }

   @Transactional(readOnly = false, rollbackFor = {EspecialidadException.class})
   @Override
   public void deleteEspecialidad(ArrayList<EspecialidadView> especialidadViewArrayList, String idMedico) throws EspecialidadException {
	   try {
		   if (!medicoRepository.exists(idMedico)) {
			   logger.error("===>>>idMedico no encotrado: {}", idMedico);
			   EspecialidadException espe = new EspecialidadException("No se encuentra en el sistema al medico.", EspecialidadException.LAYER_DAO, EspecialidadException.ACTION_VALIDATE);
			   espe.addError("idMedico no encontrado: " + idMedico);
			   throw espe;
		   }
		   Medico medico = medicoRepository.findOne(idMedico);
		   MedicoView medicoView = new MedicoView();
		   medicoView.setEspecialidadViewList(especialidadViewArrayList);

		   //         obtener los id a borrar
		   Collection<String> idsView = new ArrayList<>();
		   idsView.addAll(
				   medicoView.getEspecialidadViewList().stream()
				   .map(espV -> {
					   String idV = espV.getIdEspecialidad();
					   return idV;
				   }).collect(Collectors.toList())
				   );
		   //         los elimino de la DB
		   Especialidad esp;
		   for (String IDesp : idsView) {
			   esp = especialidadRepository.findOne(IDesp);
			   if (esp != null) {
				   esp.setMedico(null);
				   //eliminando imagenes del fileSystem
				   if(esp.getImgCedula64()!=null) {
					   especialidadConverter.eliminarImagen(esp.getImgCedula64());
				   }
				   if(esp.getImgTitulo64()!=null) {
					   especialidadConverter.eliminarImagen(esp.getImgTitulo64());
				   }	
				   especialidadRepository.delete(IDesp);
			   } else {
				   logger.error("===>>>idEspecialidad no encotrado: {}", IDesp);
				   EspecialidadException espe = new EspecialidadException("No se encuentra en el sistema al idDomicilio.", EspecialidadException.LAYER_DAO, EspecialidadException.ACTION_VALIDATE);
				   espe.addError("idEspecialidad no encotrado: " + IDesp);
				   throw espe;
			   }
		   }
		   //         limpiar la lista
		   medico.setEspecialidadList(new ArrayList<>());
		   //         la "nueva lista"
		   Collection<Especialidad> especialidad = new ArrayList<>();

		   especialidadRepository.save(especialidad);
	   } catch (EspecialidadException espe) {
		   throw espe;
	   } catch (Exception ex) {
		   EspecialidadException espe = new EspecialidadException("Error inesperado al borrar especialidad", EspecialidadException.LAYER_DAO, EspecialidadException.ACTION_DELETE);
		   espe.addError("Ocurrio un error alborrar especialidad");
		   logger.error("===>>>Error al borrar especialidad - CODE: {} - {}", espe.getExceptionCode(), idMedico, ex);
		   throw espe;
	   }

   }

}

