package net.amentum.niomedic.medicos.service.impl;

import net.amentum.niomedic.medicos.converter.DomicilioConverter;
import net.amentum.niomedic.medicos.converter.EspecialidadConverter;
import net.amentum.niomedic.medicos.converter.MedicoConverter;
import net.amentum.niomedic.medicos.exception.EspecialidadException;
import net.amentum.niomedic.medicos.exception.ExceptionServiceCode;
import net.amentum.niomedic.medicos.exception.MedicoException;
import net.amentum.niomedic.medicos.model.Domicilio;
import net.amentum.niomedic.medicos.model.Especialidad;
import net.amentum.niomedic.medicos.model.Medico;
import net.amentum.niomedic.medicos.model.MedicoFirma;
import net.amentum.niomedic.medicos.persistence.DomicilioRepository;
import net.amentum.niomedic.medicos.persistence.EspecialidadRepository;
import net.amentum.niomedic.medicos.persistence.MedicoFirmaRepository;
import net.amentum.niomedic.medicos.persistence.MedicoRepository;
import net.amentum.niomedic.medicos.service.MedicoService;
import net.amentum.niomedic.medicos.views.MedicoPageView;
import net.amentum.niomedic.medicos.views.MedicoView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Sre19062020 Inicia nuevos imports
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// Sre19062020 Termina

@Service
@Transactional(readOnly = true)
public class MedicoServiceImpl implements MedicoService {
   private final Logger logger = LoggerFactory.getLogger(MedicoServiceImpl.class);
   private final Map<String, Object> colOrderNames = new HashMap<>();
   private MedicoRepository medicoRepository;
   private MedicoFirmaRepository medicoFirmaRepository;
   private MedicoConverter medicoConverter;
   private DomicilioConverter domicilioConverter;
   private DomicilioRepository domicilioRepository;
   private EspecialidadConverter especialidadConverter;
   private EspecialidadRepository especialidadRepository;

   {
      colOrderNames.put("nombre", "nombre");
      colOrderNames.put("email", "email");
      colOrderNames.put("cedula", "cedula");
   }

    // Sre19062020 Inicia inyecto entityManager
    protected EntityManager entityManager;
     
    public EntityManager getEntityManager() {
        return entityManager;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    // Sre19062020 Termina
   
   @Autowired
   public void setMedicoRepository(MedicoRepository medicoRepository) {
      this.medicoRepository = medicoRepository;
   }

   @Autowired
   public void setMedicoFirmaRepository(MedicoFirmaRepository medicoFirmaRepository) {
      this.medicoFirmaRepository = medicoFirmaRepository;
    }

   @Autowired
   public void setMedicoConverter(MedicoConverter medicoConverter) {
      this.medicoConverter = medicoConverter;
   }

   @Autowired
   public void setDomicilioConverter(DomicilioConverter domicilioConverter) {
      this.domicilioConverter = domicilioConverter;
   }

   @Autowired
   public void setDomicilioRepository(DomicilioRepository domicilioRepository) {
      this.domicilioRepository = domicilioRepository;
   }

   @Autowired
   public void setEspecialidadConverter(EspecialidadConverter especialidadConverter) {
      this.especialidadConverter = especialidadConverter;
   }

   @Autowired
   public void setEspecialidadRepository(EspecialidadRepository especialidadRepository) {
      this.especialidadRepository = especialidadRepository;
   }

   @Transactional(readOnly = false, rollbackFor = {MedicoException.class})
   @Override
   public MedicoView createMedico(MedicoView medicoView, String direccion) throws MedicoException {
      try {
         //son nulos
         if (medicoView.getCurp() != null && !medicoView.getCurp().trim().isEmpty()) {
            //se verifica por curp
            if (medicoRepository.findByCurp(medicoView.getCurp()) != null) {
               logger.error("===>>>CURP de Medico DUPLICADO: {}", medicoView.getCurp());
               MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
               me.addError("CURP de Medico DUPLICADO:" + medicoView.getCurp());
               throw me;
            }
         } else {
            logger.info("===>>>El CURP viene nulo");
         }
         //son nulos
         if (medicoView.getRfc() != null && !medicoView.getRfc().trim().isEmpty()) {
            //se verifica por rfc duplicado
            if (medicoRepository.findByRfc(medicoView.getRfc()) != null) {
               logger.error("===>>>RFC de Medico DUPLICADO: {}", medicoView.getRfc());
               MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
               me.addError("RFC de Medico DUPLICADO:" + medicoView.getRfc());
               throw me;
            }
         } else {
            logger.info("===>>>El RFC viene nulo");
         }

         if (medicoView.getIdUsuario() == null) {
            logger.error("===>>>idUsuario de Medico NULO/VACIO: {}", medicoView.getIdUsuario());
            MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
            me.addError("idUsuario de Medico NULO/VACIO:" + medicoView.getIdUsuario());
            throw me;
         }

         if (medicoView.getUserName() == null || medicoView.getUserName().trim().isEmpty()) {
            logger.error("===>>>userName de Medico NULO/VACIO: {}", medicoView.getUserName());
            MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
            me.addError("userName de Medico NULO/VACIO:" + medicoView.getUserName());
            throw me;
         }

         if (medicoRepository.findByUserName(medicoView.getUserName()) != null) {
            logger.error("===>>>userName de Medico DUPLICADO: {}", medicoView.getUserName());
            MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
            me.addError("userName de Medico DUPLICADO:" + medicoView.getUserName());
            throw me;
         }

         if (medicoRepository.findByIdUsuario(medicoView.getIdUsuario()) != null) {
            logger.error("===>>>idUsuario de Medico DUPLICADO: {}", medicoView.getIdUsuario());
            MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
            me.addError("idUsuario de Medico DUPLICADO:" + medicoView.getIdUsuario());
            throw me;
         }


         Medico medico = medicoConverter.toEntity(medicoView, new Medico(), Boolean.FALSE, direccion);
         logger.debug("===>>>Insertar nuevo medico: {}", medico);
         medicoRepository.save(medico);
         return medicoConverter.toView(medico, Boolean.TRUE);
      } catch (MedicoException me) {
         throw me;
      } catch (DataIntegrityViolationException dive) {
         MedicoException me = new MedicoException("No fue posible agregar Medico", MedicoException.LAYER_DAO, MedicoException.ACTION_INSERT);
         me.addError("Ocurrio un error al agregar medico");
         logger.error("===>>>Error al insertar nuevo medico - CODE: {} - {}", me.getExceptionCode(), medicoView, dive);
         throw me;
      } catch (StringIndexOutOfBoundsException sioobe) {
         logger.error("===>>>No esta bien contruida la imagen de los archivos a subir");
         MedicoException me = new MedicoException("No fue posible agregar especialidad", MedicoException.LAYER_DAO, MedicoException.ACTION_INSERT);
         me.addError("No esta bien contruida la imagen de los archivos a subir");
         throw me;
      } catch (Exception ex) {
         MedicoException me = new MedicoException("Error inesperado al agregar Medico", MedicoException.LAYER_DAO, MedicoException.ACTION_INSERT);
         me.addError("Ocurrio un error al agregar medico");
         logger.error("===>>>Error al insertar nuevo medico - CODE: {} - {}", me.getExceptionCode(), medicoView, ex);
         throw me;
      }

   }

   @Transactional(readOnly = false, rollbackFor = {MedicoException.class})
   @Override
   public MedicoView updateMedico(MedicoView medicoView, String direccion) throws MedicoException {
      try {
         if (!medicoRepository.exists(medicoView.getIdMedico())) {
            logger.error("===>>>idMedico no encontrado: {}", medicoView.getIdMedico());
            MedicoException me = new MedicoException("No se encuentra en el sistema Medico.", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
            me.addError("idMedico no encontrado:" + medicoView.getIdMedico());
            throw me;
         }
         Medico medico = medicoRepository.findOne(medicoView.getIdMedico());

         if (medicoView.getIdUsuario() == null) {
            logger.error("===>>>idUsuario de Medico NULO/VACIO: {}", medicoView.getIdUsuario());
            MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
            me.addError("idUsuario de Medico NULO/VACIO:" + medicoView.getIdUsuario());
            throw me;
         }

         if (medicoView.getUserName() == null || medicoView.getUserName().trim().isEmpty()) {
            logger.error("===>>>userName de Medico NULO/VACIO: {}", medicoView.getUserName());
            MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
            me.addError("userName de Medico NULO/VACIO:" + medicoView.getUserName());
            throw me;
         }

         Medico otroUserName = medicoRepository.findByUserName(medicoView.getUserName());
         if (otroUserName != null) {
            if (!medico.getIdMedico().equalsIgnoreCase(otroUserName.getIdMedico())) {
               logger.error("===>>>userName de Medico DUPLICADO en otro registro: {}", medicoView.getUserName());
               MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
               me.addError("userName de Medico DUPLICADO en otro registro:" + medicoView.getUserName());
               throw me;
            }
         }

//         if (medicoRepository.findByUserName(medicoView.getUserName()) != null) {
//            logger.error("===>>>userName de Medico DUPLICADO: {}", medicoView.getUserName());
//            MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
//            me.addError("userName de Medico DUPLICADO:" + medicoView.getUserName());
//            throw me;
//         }
         Medico otroIdUsuario = medicoRepository.findByIdUsuario(medicoView.getIdUsuario());
         if (otroIdUsuario != null) {
            if (!medico.getIdMedico().equalsIgnoreCase(otroIdUsuario.getIdMedico())) {
               logger.error("===>>>idUsuario de Medico DUPLICADO en otro registro: {}", medicoView.getIdUsuario());
               MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
               me.addError("idUsuario de Medico DUPLICADO en otro registro:" + medicoView.getIdUsuario());
               throw me;
            }
         }

//         if (medicoRepository.findByIdUsuario(medicoView.getIdUsuario()) != null) {
//            logger.error("===>>>idUsuario de Medico DUPLICADO: {}", medicoView.getIdUsuario());
//            MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
//            me.addError("idUsuario de Medico DUPLICADO:" + medicoView.getIdUsuario());
//            throw me;
//         }
         if(medicoView.getCurp()!=null) {
        	 Medico otroCurp = medicoRepository.findByCurp(medicoView.getCurp());
        	 if (otroCurp != null) {
        		 if (!medico.getIdMedico().equalsIgnoreCase(otroCurp.getIdMedico())) {
        			 logger.error("===>>>CURP de Medico DUPLICADO en otro registro: {}", medicoView.getCurp());
        			 MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
        			 me.addError("CURP de Medico DUPLICADO en otro registro:" + medicoView.getCurp());
        			 throw me;
        		 }
        	 }
         }
         if(medicoView.getRfc() != null) {
        	 Medico otroRfc = medicoRepository.findByRfc(medicoView.getRfc());
        	 if(otroRfc != null) {
        		 if (!medico.getIdMedico().equalsIgnoreCase(otroRfc.getIdMedico())) {
        			 logger.error("===>>>RFC de Medico DUPLICADO en otro registro: {}", medicoView.getRfc());
        			 MedicoException me = new MedicoException("Existe un error", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
        			 me.addError("RFC de Medico DUPLICADO en otro registro:" + medicoView.getRfc());
        			 throw me;
        		 }
        	 }
         }

//         los elimino de la DB
         Collection<String> noExistenDomicilioView = domicilioConverter.obtenerIDNoExistentesDomicilio(medico, medicoView);
         Domicilio dom;
         for (String IDdom : noExistenDomicilioView) {
            dom = domicilioRepository.findOne(IDdom);
            dom.setMedico(null);
            domicilioRepository.delete(IDdom);
         }

         Collection<String> noExistenEspecialidadView = especialidadConverter.obtenerIDNoExistentesEspecialidad(medico, medicoView);
         Especialidad esp;
         for (String IDesp : noExistenEspecialidadView) {
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
        	 }else{
        		 logger.error("===>>>idEspecialidad no encotrado: {}", IDesp);
        		 EspecialidadException espe = new EspecialidadException("No se encuentra en el sistema el idEspecialidad.", EspecialidadException.LAYER_DAO, EspecialidadException.ACTION_VALIDATE);
        		 espe.addError("idEspecialidad no encotrado: " + IDesp);
        		 throw espe;
        	 }
         }

//            limpiar las listas
         medico.setDomicilioList(new ArrayList<>());
         medico.setEspecialidadList(new ArrayList<>());

         medico = medicoConverter.toEntity(medicoView, medico, Boolean.TRUE, direccion);
         logger.debug("===>>>Editar medico: {}", medico);
         medicoRepository.save(medico);
         return medicoConverter.toView(medico, Boolean.TRUE);
      } catch (MedicoException me) {
         throw me;
      } catch (DataIntegrityViolationException dive) {
         MedicoException me = new MedicoException("No fue posible editar Medico", MedicoException.LAYER_DAO, MedicoException.ACTION_UPDATE);
         me.addError("Ocurrio un error al editar medico");
         logger.error("===>>>Error al editar medico - CODE: {} - {}", me.getExceptionCode(), medicoView, dive);
         throw me;
      } catch (StringIndexOutOfBoundsException sioobe) {
         logger.error("===>>>No esta bien contruida la imagen de los archivos a subir");
         MedicoException me = new MedicoException("No fue posible agregar especialidad", MedicoException.LAYER_DAO, MedicoException.ACTION_INSERT);
         me.addError("No esta bien contruida la imagen de los archivos a subir");
         throw me;
      } catch (InvalidDataAccessApiUsageException idaaue) {
         logger.error("===>>>Existen Id's repetidos");
         MedicoException me = new MedicoException("No fue posible editar Medico", MedicoException.LAYER_DAO, MedicoException.ACTION_INSERT);
         me.addError("Existen Id's repetidos");
         throw me;
      } catch (Exception ex) {
         MedicoException me = new MedicoException("Ocurrio un error al editar medico", MedicoException.LAYER_DAO, MedicoException.ACTION_UPDATE);
         me.addError("Ocurrio un error al editar medico");
         logger.error("===>>>Error al editar medico - CODE: {} - {}", me.getExceptionCode(), medicoView, ex);
         throw me;
      }

   }

   @Override
//   public MedicoView getDetailsByIdMedico(@NotNull String idMedico) throws MedicoException {
   public MedicoView getDetailsByIdMedico(String idMedico) throws MedicoException {
      try {
         if (!medicoRepository.exists(idMedico)) {
            logger.error("===>>>idMedico no encontrado: {}", idMedico);
            MedicoException me = new MedicoException("No se encuentra en el sistema Medico.", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
            me.addError("idMedico no encontrado:" + idMedico);
            throw me;
         }
         Medico medico = medicoRepository.findOne(idMedico);
         return medicoConverter.toView(medico, Boolean.TRUE);
      } catch (MedicoException me) {
         throw me;
      } catch (DataIntegrityViolationException dive) {
         MedicoException me = new MedicoException("No fue posible obtener detalles del medico", MedicoException.LAYER_DAO, MedicoException.ACTION_SELECT);
         me.addError("Ocurrio un error al obtener detalles del medico");
         logger.error("===>>>Error al obtener detalles del medico - CODE: {} - {}", me.getExceptionCode(), idMedico, dive);
         throw me;
      } catch (Exception ex) {
         MedicoException me = new MedicoException("Ocurrio un error al obtener detalles del medico", MedicoException.LAYER_DAO, MedicoException.ACTION_SELECT);
         me.addError("Ocurrio un error al obtener detalles del medico");
         logger.error("===>>>Error al obtener detalles del medico - CODE: {} - {}", me.getExceptionCode(), idMedico, ex);
         throw me;
      }
   }

   @Override
//   public MedicoView getDetailsByIdUsuario(@NotNull Long idUsuario) throws MedicoException {
   public MedicoView getDetailsByIdUsuario( Long idUsuario) throws MedicoException {
      try {
         Medico medico = medicoRepository.findByIdUsuario(idUsuario);
         if (medico == null) {
            logger.error("===>>>idUsuario no encotrado: {}", idUsuario);
            MedicoException me = new MedicoException("No se encuentra en el sistema Medico.", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
            me.addError("idUsuario no encontrado:" + idUsuario);
            throw me;
         }
         return medicoConverter.toView(medico, Boolean.TRUE);
      } catch (MedicoException me) {
         throw me;
      } catch (DataIntegrityViolationException dive) {
         MedicoException me = new MedicoException("No fue posible obtener detalles del medico", MedicoException.LAYER_DAO, MedicoException.ACTION_SELECT);
         me.addError("Ocurrio un error al obtener detalles del medico");
         logger.error("===>>>Error al obtener detalles del medico - CODE: {} - {}", me.getExceptionCode(), idUsuario, dive);
         throw me;
      } catch (Exception ex) {
         MedicoException me = new MedicoException("Ocurrio un error al obtener detalles del medico", MedicoException.LAYER_DAO, MedicoException.ACTION_SELECT);
         me.addError("Ocurrio un error al obtener detalles del medico");
         logger.error("===>>>Error al obtener detalles del medico - CODE: {} - {}", me.getExceptionCode(), idUsuario, ex);
         throw me;
      }
   }

   @Transactional(readOnly = false, rollbackFor = {MedicoException.class})
   @Override
//   public Page<MedicoPageView> getMedicoPage(Boolean active, String name, Integer page, Integer size, String orderColumn, String orderType) throws MedicoException {
   public Page<MedicoPageView> getMedicoPage(String datosBusqueda, Boolean active, Integer page, Integer size, String orderColumn, String orderType, Long selectGroup) throws MedicoException {
      try {
         logger.info("===>>>getMedicoPage(): - datosBusqueda {} - active {} - page {} - size {} - orderColumn {} - orderType {} - selectGroup {}",
            datosBusqueda, active, page, size, orderColumn, orderType, selectGroup);
//         List<MedicoView> medicoViewList = new ArrayList<>();
         List<MedicoPageView> medicoViewList = new ArrayList<>();
         Page<Medico> medicoPage = null;
         Sort sort = new Sort(Sort.Direction.ASC, (String) colOrderNames.get("nombre"));

         if (orderColumn != null && orderType != null) {
            if (orderType.equalsIgnoreCase("asc")) {
               sort = new Sort(Sort.Direction.ASC, (String) colOrderNames.get(orderColumn));
            } else {
               sort = new Sort(Sort.Direction.DESC, (String) colOrderNames.get(orderColumn));
            }

         }
         PageRequest request = new PageRequest(page, size, sort);
         final String patternSearch = "%" + datosBusqueda.toLowerCase() + "%";
         //         System.out.println("----->sin acentos----->" + sinAcentos(patternSearch));
         Specifications<Medico> spec = Specifications.where(
            (root, query, cb) -> {
               Predicate tc = null;
               if (datosBusqueda != null && !datosBusqueda.isEmpty()) {
                  tc = (tc != null ? cb.and(tc, cb.like(cb.function("unaccent", String.class, cb.lower(root.get("datosBusqueda"))), sinAcentos(patternSearch))) : cb.like(cb.function("unaccent", String.class, cb.lower(root.get("datosBusqueda"))), sinAcentos(patternSearch)));
//                  tc = (tc != null ? cb.and(tc, cb.like(cb.lower(root.get("nombre")), patternSearch)) : cb.like(cb.lower(root.get("nombre")), patternSearch));
               }
               if (active != null) {
                  tc = (tc != null ? cb.and(tc, cb.equal(root.get("activo"), active)) : cb.equal(root.get("activo"), active));
               }
               return tc;
            }
         );
         // Inicia GGR20200617 : Podría mejorarse usan el request y spec
         List<Long> losMedicosLong = new ArrayList<>();
         Boolean hayGrupo = false;
         if (selectGroup != null) {
            List<BigInteger> losMedicos = medicoRepository.findIdByGroup(selectGroup);
            losMedicos.forEach(unId -> {
               losMedicosLong.add(unId.longValue());
            });
            hayGrupo = true;
         }
         if (losMedicosLong.isEmpty()) {
            losMedicosLong.add(new Long(0));
         }

         logger.info("Estos son los medicos {} que están en el grupo {}", losMedicosLong, selectGroup);
         if (spec == null) {
            //medicoPage = medicoRepository.findAll(request);
            if (hayGrupo)
               medicoPage = medicoRepository.findAllByGroup(losMedicosLong, patternSearch, request);
            else
               medicoPage = medicoRepository.findAllByGroup(patternSearch, request);
         } else {
            //medicoPage = medicoRepository.findAll(spec, request);
            if (hayGrupo)
               medicoPage = medicoRepository.findAllByGroup(losMedicosLong, patternSearch, request);
            else
               medicoPage = medicoRepository.findAllByGroup(patternSearch, request);

         }
         // Fin GGR20200617
         medicoPage.getContent().forEach(medico -> {
            try {
               medicoViewList.add(medicoConverter.toViewPage(medico));
            } catch (Exception e) {
               logger.info("===>>>El medico no tiene una imagen de especialidad");

            }
         });
//         PageImpl<MedicoView> medicoViewPage = new PageImpl<MedicoView>(medicoViewList, request, medicoPage.getTotalElements());
         PageImpl<MedicoPageView> medicoViewPage = new PageImpl<MedicoPageView>(medicoViewList, request, medicoPage.getTotalElements());
         return medicoViewPage;
      } catch (IllegalArgumentException iae) {
         logger.error("===>>>Algun parametro no es correcto");
         MedicoException me = new MedicoException("Algun parametro no es correcto:", MedicoException.LAYER_SERVICE, MedicoException.ACTION_VALIDATE);
         me.addError("Puede que sea null, vacio o valor incorrecto");
         //iae.printStackTrace();
         throw me;
      } catch (Exception ex) {
         MedicoException medicoException = new MedicoException("Ocurrio un error al seleccionar lista de medicos paginable", MedicoException.LAYER_SERVICE, MedicoException.ACTION_SELECT);
         logger.error(ExceptionServiceCode.GROUP + "===>>>Error al tratar de seleccionar lista de medicos paginable - CODE: {}", medicoException.getExceptionCode(), ex);
         throw medicoException;
      }
   }

   @Override
   public MedicoView getDetailsByUserName(String userName) throws MedicoException {
      try {
         logger.info("getDetailsByUserName() UserName: {}", userName);
         Medico medico = medicoRepository.findByUserName(userName);
         logger.info("getDetailsByUserName() UserName: {}", medico.toString());
         if (medico == null) {
            logger.error("===>>>userName no encotrado: {}", userName);
            MedicoException me = new MedicoException("No se encuentra en el sistema Medico.", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
            me.addError("userName no encontrado:" + userName);
            throw me;
         }
         return medicoConverter.toView(medico, Boolean.TRUE);
      } catch (MedicoException me) {
         throw me;
      } catch (DataIntegrityViolationException dive) {
         MedicoException me = new MedicoException("No fue posible obtener detalles del medico", MedicoException.LAYER_DAO, MedicoException.ACTION_SELECT);
         me.addError("Ocurrio un error al obtener detalles del medico");
         logger.error("===>>>Error al obtener detalles del medico - CODE: {} - {}", me.getExceptionCode(), userName, dive);
         throw me;
      } catch (Exception ex) {
         MedicoException me = new MedicoException("Ocurrio un error al obtener detalles del medico", MedicoException.LAYER_DAO, MedicoException.ACTION_SELECT);
         me.addError("Ocurrio un error al obtener detalles del medico");
         logger.error("===>>>Error al obtener detalles del medico - CODE: {} - {}", me.getExceptionCode(), userName, ex);
         throw me;
      }
   }


   private String sinAcentos(String cadena) {
      return Normalizer.normalize(cadena, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
   }

    // Sre19062020 Nuevo servicio para actualizar grupos de medico
    @Transactional(readOnly = false, rollbackFor = {MedicoException.class})
    @Override
    public void updateMedicoGroups(Long idUsuario, List<Long> medicoGroups) throws MedicoException {
        try {
            Medico medico = medicoRepository.findByIdUsuario(idUsuario);
            if (medico == null) {
                logger.error("===>>>idUsuario no encontrado: {}", idUsuario);
                MedicoException me = new MedicoException("No se encuentra en el sistema Medico.", MedicoException.LAYER_DAO, MedicoException.ACTION_VALIDATE);
                me.addError("idUsuario no encontrado:" + idUsuario);
                throw me;
            }
            // Ahora obtengo una conn y opero
            Session session = (Session) entityManager.getDelegate();
            session.doWork(new Work() {
                @Override
                public void execute(Connection connectionToUse) throws SQLException {
                    // Aca opero borrar los que haya para medico
                    // Borro los que haya delete from public.medicos_grupos where id_medico=?
                    // Ahora inserto los que vengan en medicoGroups
                    boolean commitMode = connectionToUse.getAutoCommit();
                    try {
                        connectionToUse.setAutoCommit(false);
                        String query = "DELETE FROM public.medicos_grupos WHERE id_medico=?";
                        String queryIns = "INSERT INTO public.medicos_grupos (id_medico, id_group) values (?, ?)";
                        PreparedStatement pstmt = connectionToUse.prepareStatement(query);
                        pstmt.setString(1, medico.getIdMedico());
                        int resDel = pstmt.executeUpdate();
                        int resIns = 0;
                        pstmt = connectionToUse.prepareStatement(queryIns);
                        for (Long unGrupo : medicoGroups) {
                            pstmt.setString(1, medico.getIdMedico());
                            pstmt.setLong(2, unGrupo);
                            resIns += pstmt.executeUpdate();
                        }
                        connectionToUse.commit();
                        logger.info("Se actualizaron {}/{} grupos del medico", resDel, resIns);
                    } finally {
                        // Restore commit mode
                        connectionToUse.setAutoCommit(commitMode);
                    }
                }
            });
        } catch (MedicoException me) {
            throw me;
        } catch (Exception ex) {
            MedicoException me = new MedicoException("Ocurrio un error al actualizar los grupos del medico", MedicoException.LAYER_DAO, MedicoException.ACTION_SELECT);
            me.addError("Ocurrio un error al actualizar los grupos del medico");
            logger.error("===>>>Error al actualizar los grupos del medico - CODE: {} - {}", me.getExceptionCode(), idUsuario, ex);
            throw me;
        }
    }

   @Override
   public MedicoFirma getSignatureById(Integer idFirma) throws MedicoException {
      return medicoFirmaRepository.findOne(idFirma);
   }
}
