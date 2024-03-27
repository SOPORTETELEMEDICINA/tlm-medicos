package net.amentum.niomedic.medicos.service.impl;

import net.amentum.niomedic.medicos.converter.EspecialidadUmConverter;
import net.amentum.niomedic.medicos.converter.ServicioUmConverter;
import net.amentum.niomedic.medicos.converter.UnidadMedicaConverter;
import net.amentum.niomedic.medicos.exception.EspecialidadUmException;
import net.amentum.niomedic.medicos.exception.ExceptionServiceCode;
import net.amentum.niomedic.medicos.exception.ServicioUmException;
import net.amentum.niomedic.medicos.exception.UnidadMedicaException;
import net.amentum.niomedic.medicos.model.EspecialidadUm;
import net.amentum.niomedic.medicos.model.ServicioUm;
import net.amentum.niomedic.medicos.model.UnidadMedica;
import net.amentum.niomedic.medicos.persistence.EspecialidadUmRepository;
import net.amentum.niomedic.medicos.persistence.ServicioUmRepository;
import net.amentum.niomedic.medicos.persistence.UnidadMedicaRepository;
import net.amentum.niomedic.medicos.service.UnidadMedicaService;
import net.amentum.niomedic.medicos.views.EspecialidadUmView;
import net.amentum.niomedic.medicos.views.ServicioUmView;
import net.amentum.niomedic.medicos.views.UnidadMedicaPageView;
import net.amentum.niomedic.medicos.views.UnidadMedicaView;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;


import java.math.BigInteger;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.Predicate;
import javax.validation.ConstraintViolation;

@Service
/*@Transactional(readOnly = true)*/
@Slf4j
public class UnidadMedicaServiceImpl implements UnidadMedicaService {
	private final Map<String, Object> colOrderNames = new HashMap<>();

	private UnidadMedicaRepository unidadMedicaRepository;
	private UnidadMedicaConverter unidadMedicaConverter;
	private ServicioUmRepository servicioUmRepository;
	private ServicioUmConverter servicioUmConverter;
	private EspecialidadUmRepository especialidadUmRepository;
	private EspecialidadUmConverter especialidadUmConverter;

	{
		colOrderNames.put("idUnidadMedica", "idUnidadMedica");
		colOrderNames.put("clues", "clues");
		colOrderNames.put("nombreUnidad", "nombreUnidad");
		colOrderNames.put("codigoPostal", "codigoPostal");
		colOrderNames.put("region", "idRegionSanitaria");
		colOrderNames.put("activo", "activo");
	}

	@Autowired
	public void setUnidadMedicaRepository(UnidadMedicaRepository unidadMedicaRepository) {
		this.unidadMedicaRepository = unidadMedicaRepository;
	}

	@Autowired
	public void setUnidadMedicaConverter(UnidadMedicaConverter unidadMedicaConverter) {
		this.unidadMedicaConverter = unidadMedicaConverter;
	}

	@Autowired
	public void setServicioUmRepository(ServicioUmRepository servicioUmRepository) {
		this.servicioUmRepository = servicioUmRepository;
	}

	@Autowired
	public void setServicioUmConverter(ServicioUmConverter servicioUmConverter) {
		this.servicioUmConverter = servicioUmConverter;
	}

	@Autowired
	public void setEspecialidadUmRepository(EspecialidadUmRepository especialidadUmRepository) {
		this.especialidadUmRepository = especialidadUmRepository;
	}

	@Autowired
	public void setEspecialidadUmConverter(EspecialidadUmConverter especialidadUmConverter) {
		this.especialidadUmConverter = especialidadUmConverter;
	}

	/*@Transactional(readOnly=false, rollbackFor = {UnidadMedicaException.class})*/
	@Override
	public UnidadMedicaView createUnidadMedica(UnidadMedicaView unidadMedicaView) throws UnidadMedicaException {
		try {
			unidadMedicaView.setIdUnidadMedica(null);
			unidadMedicaView.setActivo(true);
			log.info("createUnidadMedica() - Creando una nueva unidad médica");
			log.debug("createUnidadMedica() - Creando una nueva unidad médica: {}", unidadMedicaView);
			UnidadMedica entity = unidadMedicaConverter.toEntity(unidadMedicaView, new UnidadMedica(), Boolean.FALSE);
			log.info("createUnidadMedica() - Guardanda la unidad médica");
			log.debug("createUnidadMedica() - Guardanda la unidad médica");
			unidadMedicaRepository.save(entity);
			log.debug("createUnidadMedica() - La unidad médica se guardo con exito: {}", unidadMedicaView);
			log.info("createUnidadMedica() - La unidad médica se guardo con exito");
			return unidadMedicaConverter.toView(entity, Boolean.TRUE);
		}catch(ConstraintViolationException ce) {
			final Set<ConstraintViolation<?>> violaciones = ce.getConstraintViolations();
			String errores="";
			for (Iterator<ConstraintViolation<?>> iterator = violaciones.iterator(); iterator.hasNext(); ) {
				ConstraintViolation<?> siguiente = iterator.next();
				errores = errores + siguiente.getPropertyPath()+": "+siguiente.getMessage()+"\n";
			}
			log.error("IMPL - createUnidadMedica() -  Ocurrió un error de validación en la tabla, error: {}", errores);
			throw new UnidadMedicaException(HttpStatus.BAD_REQUEST, errores);   
		}catch(DataIntegrityViolationException de) {
			log.error("IMPL - createUnidadMedica() -  Ocurrió un error de integridad en la tabla, error: {}",de.getRootCause().getMessage());
			throw new UnidadMedicaException(HttpStatus.CONFLICT, de.getRootCause().getMessage());   
		}catch(Exception e) {
			log.error("IMPL - createUnidadMedica() -  Ocurrió un error inesperado al crear una Unidad Médidca, error: {}",e);
			throw new UnidadMedicaException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(UnidadMedicaException.SERVER_ERROR, "crear"));	
		}
	}

	/*@Transactional(readOnly = false, rollbackFor = UnidadMedicaException.class )*/
	@Override
	public UnidadMedicaView updateUnidadMedica(Long idUnidadMedica, UnidadMedicaView unidadMedicaView)
			throws UnidadMedicaException {
		try {
			log.info("updateUnidadMedica() - Actualizando unidad médica con el id: {}", idUnidadMedica);
			log.debug("updateUnidadMedica() - Actualizando unidad médica con el id: {} - {}", idUnidadMedica, unidadMedicaView);
			if(unidadMedicaView.getActivo() == null) {
				unidadMedicaView.setActivo(true);
			}
			UnidadMedica entity = obtenerUnidadMedicaById(idUnidadMedica);
			unidadMedicaView.setIdUnidadMedica(idUnidadMedica);
			entity = unidadMedicaConverter.toEntity(unidadMedicaView, entity, Boolean.TRUE);
			unidadMedicaRepository.save(entity);
			log.info("updateUnidadMedica() - Actualización exitosa");
			return unidadMedicaConverter.toView(entity, Boolean.TRUE);
		}catch(UnidadMedicaException ume) {
			throw ume;
		}catch(ConstraintViolationException ce) {
			final Set<ConstraintViolation<?>> violaciones = ce.getConstraintViolations();
			String errores="";
			for (Iterator<ConstraintViolation<?>> iterator = violaciones.iterator(); iterator.hasNext(); ) {
				ConstraintViolation<?> siguiente = iterator.next();
				errores = errores + siguiente.getPropertyPath()+": "+siguiente.getMessage()+"\n";
			}
			log.error("updateUnidadMedica() -  Ocurrió un error de validación en la tabla, error: {}", errores);
			throw new UnidadMedicaException(HttpStatus.BAD_REQUEST, errores);   
		}catch(DataIntegrityViolationException de) {
			log.error("updateUnidadMedica() -  Ocurrió un error de integridad en la tabla, error: {}",de.getRootCause().getMessage());
			throw new UnidadMedicaException(HttpStatus.CONFLICT, de.getRootCause().getMessage());   
		}catch(Exception e) {
			log.error("updateUnidadMedica() -  Ocurrió un error inesperado al actualizar una Unidad Médidca, error: {}",e);
			throw new UnidadMedicaException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(UnidadMedicaException.SERVER_ERROR, "crear"));	
		}
	}

	private UnidadMedica obtenerUnidadMedicaById(Long idUnidadMedica)throws UnidadMedicaException {
		log.debug("obtenerUnidadMedicaById() - Buscando la undiad médica con el id: {}",idUnidadMedica);
		if (!unidadMedicaRepository.exists(idUnidadMedica)) {
			log.error("obtenerUnidadMedicaById() - No encontrado el id: {}", idUnidadMedica);
			throw new UnidadMedicaException(HttpStatus.NOT_FOUND, String.format(UnidadMedicaException.ITEM_NO_ENCONTRADO, idUnidadMedica));
		}
		UnidadMedica unidadMedica = unidadMedicaRepository.findOne(idUnidadMedica);
		log.info("obtenerUnidadMedicaById() - Undiad médica encontrada: {}",unidadMedica);
		return unidadMedica;
	}

	@Override
	public UnidadMedicaView getUnidadMedicaById(Long idUnidadMedica) throws UnidadMedicaException {
		try {
			log.info("getUnidadMedicaById() - Obteniendo unidad médica con el id: {}", idUnidadMedica);
			UnidadMedica  unidadMedica = obtenerUnidadMedicaById(idUnidadMedica);
			return unidadMedicaConverter.toView(unidadMedica, Boolean.TRUE);
		} catch (UnidadMedicaException umE) {
			throw umE;
		} catch (Exception ex) {
			log.error("===>>>{} getUnidadMedicaById() - Ocurrio un error al obtener las Unidades Medicas - error: {}", ExceptionServiceCode.UNIDADMEDICA, ex);
			throw new UnidadMedicaException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(UnidadMedicaException.SERVER_ERROR, "Obtener por ID"));
		}
	}

	@Override
	public List<UnidadMedicaView> getUnidadMedicaByRegion(Integer idRegionSanitaria) throws UnidadMedicaException {
		try {
			if(!unidadMedicaRepository.existRegionMedica(idRegionSanitaria)) {
				log.error("===>>>getUnidadMedicaByRegion() - No encontrado el id: {}", idRegionSanitaria);
				throw new UnidadMedicaException(HttpStatus.NOT_FOUND, String.format(UnidadMedicaException.ITEM_NO_ENCONTRADO, idRegionSanitaria));
			}
			List<UnidadMedica> unidadMedicaList = unidadMedicaRepository.getUnidadMedicaByRegionMedica(idRegionSanitaria);
			List<UnidadMedicaView> viewList = new ArrayList<>();
			for(UnidadMedica entity: unidadMedicaList)
				viewList.add(unidadMedicaConverter.toView(entity, true));
			return viewList;
		} catch(Exception ex) {
			log.error("getUnidadMedicaByRegion()  - {}", ex.getMessage());
			throw new UnidadMedicaException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(UnidadMedicaException.SERVER_ERROR, "Obtener Servicios por ID"));
		}
	}

	@Override
	public List<UnidadMedicaView> getUnidadMedicaByEntidad(Integer idEntidad) throws UnidadMedicaException {
		try {
			List<UnidadMedica> unidadMedicaList = unidadMedicaRepository.getUnidadMedicaByEntidad(idEntidad.toString());
			List<UnidadMedicaView> viewList = new ArrayList<>();
			for(UnidadMedica entity: unidadMedicaList)
				viewList.add(unidadMedicaConverter.toView(entity, true));
			return viewList;
		} catch(Exception ex) {
			log.error("getUnidadMedicaByRegion()  - {}", ex.getMessage());
			throw new UnidadMedicaException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(UnidadMedicaException.SERVER_ERROR, "Obtener Servicios por ID"));
		}
	}


	@Override
	public List<ServicioUmView> getServiciosByIdUnidadMedica(Long idUnidadMedica) throws UnidadMedicaException {
		try {
			if (!unidadMedicaRepository.exists(idUnidadMedica)) {
				log.error("===>>>getServiciosByIdUnidadMedica() - No encontrado el id: {}", idUnidadMedica);
				throw new UnidadMedicaException(HttpStatus.NOT_FOUND, String.format(UnidadMedicaException.ITEM_NO_ENCONTRADO, idUnidadMedica));
			}

			List<Object[]> servicioUmList = null;
			servicioUmList = unidadMedicaRepository.getServiciosByIdUnidadMedica(idUnidadMedica);

			if (servicioUmList.isEmpty()) {
				log.error("===>>>getServiciosByIdUnidadMedica() - No encontrado servicio para el idUnidadMedica: {}", idUnidadMedica);
				throw new UnidadMedicaException(HttpStatus.NOT_FOUND, String.format(UnidadMedicaException.ITEM_NO_ENCONTRADO_SERVICIOS, idUnidadMedica));
			}

			List<ServicioUmView> servicioUmViewList = new ArrayList<>();

			servicioUmList.forEach((servicioV) -> {
				ServicioUm sumM = new ServicioUm();
				sumM.setIdServicioUm(((BigInteger) servicioV[0]).longValue());
				sumM.setNombre((String) servicioV[1]);

				servicioUmViewList.add(servicioUmConverter.toView(sumM, Boolean.TRUE));
			});

			return servicioUmViewList;

		} catch (UnidadMedicaException umE) {
			throw umE;
		} catch (Exception ex) {
			log.error("===>>>{} getServiciosByIdUnidadMedica() - Ocurrio un error al obtener las Unidades Medicas - error: {}", ExceptionServiceCode.UNIDADMEDICA, ex);
			throw new UnidadMedicaException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(UnidadMedicaException.SERVER_ERROR, "Obtener Servicios por ID"));
		}
	}

	@Override
	public ServicioUmView getServicioByIdUnidadMedicaByIdServicio(Long idUnidadMedica, Long idServicioUm) throws UnidadMedicaException {
		try {
			if (!unidadMedicaRepository.exists(idUnidadMedica)) {
				log.error("===>>>getServicioByIdUnidadMedicaByIdServicio() - No encontrado el idUnidadMedica: {}", idUnidadMedica);
				throw new UnidadMedicaException(HttpStatus.NOT_FOUND, String.format(UnidadMedicaException.ITEM_NO_ENCONTRADO, idUnidadMedica));
			}

			if (!servicioUmRepository.exists(idServicioUm)) {
				log.error("===>>>getServicioByIdUnidadMedicaByIdServicio() - No encontrado el idServicioUm: {}", idServicioUm);
				throw new UnidadMedicaException(HttpStatus.NOT_FOUND, String.format(ServicioUmException.ITEM_NO_ENCONTRADO, idServicioUm));
			}

			//         Object[] servicioUm = unidadMedicaRepository.getServicioByIdUnidadMedicaByIdServicio(idUnidadMedica,idServicioUm);
			//         ServicioUm sumM = new ServicioUm();
			//
			//         sumM.setIdServicioUm(((BigInteger)servicioUm[0]).longValue());
			//         sumM.setNombre((String)servicioUm[0]);
			//
			//         return servicioUmConverter.toView(sumM,Boolean.TRUE);

			List<Object[]> servicioUmList = null;
			servicioUmList = unidadMedicaRepository.getServicioByIdUnidadMedicaByIdServicio(idUnidadMedica, idServicioUm);
			//
			if (servicioUmList.isEmpty()) {
				log.error("===>>>getServiciosByIdUnidadMedica() - No encontrado el id: {}", idUnidadMedica);
				throw new UnidadMedicaException(HttpStatus.NOT_FOUND, String.format(ServicioUmException.ITEM_NO_ENCONTRADO, idUnidadMedica));
			}

			List<ServicioUmView> servicioUmViewList = new ArrayList<>();

			servicioUmList.forEach((servicioV) -> {
				ServicioUm sumM = new ServicioUm();
				sumM.setIdServicioUm(((BigInteger) servicioV[0]).longValue());
				sumM.setNombre((String) servicioV[1]);

				servicioUmViewList.add(servicioUmConverter.toView(sumM, Boolean.FALSE));
			});

			ServicioUmView sumV = new ServicioUmView();
			sumV.setIdServicioUm(servicioUmViewList.get(0).getIdServicioUm());
			sumV.setNombre(servicioUmViewList.get(0).getNombre());

			return sumV;

		} catch (UnidadMedicaException umE) {
			throw umE;
		} catch (Exception ex) {
			log.error("===>>>{} getServiciosByIdUnidadMedica() - Ocurrio un error al obtener las Unidades Medicas - error: {}", ExceptionServiceCode.UNIDADMEDICA, ex);
			throw new UnidadMedicaException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(UnidadMedicaException.SERVER_ERROR, "Obtener el Servicio por ID"));
		}
	}

	//   @Transactional(readOnly = false, rollbackFor = {UnidadMedicaException.class})
	@Override
	public List<EspecialidadUmView> getEspecialidadesByIdUnidadMedica(Long idUnidadMedica) throws UnidadMedicaException{
		try {
			if (!unidadMedicaRepository.exists(idUnidadMedica)) {
				log.error("===>>>getEspecialidadesByIdUnidadMedica() - No encontrado el id: {}", idUnidadMedica);
				throw new UnidadMedicaException(HttpStatus.NOT_FOUND, String.format(UnidadMedicaException.ITEM_NO_ENCONTRADO, idUnidadMedica));
			}

			List<Object[]> especialidadUmList = null;
			especialidadUmList = unidadMedicaRepository.getEspecialidadesByIdUnidadMedica(idUnidadMedica);

			if (especialidadUmList.isEmpty()) {
				log.error("===>>>getEspecialidadesByIdUnidadMedica() - No encontrado especialidad para el idUnidadMedica: {}", idUnidadMedica);
				throw new UnidadMedicaException(HttpStatus.NOT_FOUND, String.format(UnidadMedicaException.ITEM_NO_ENCONTRADO_ESPECIALIDADES, idUnidadMedica));
			}

			List<EspecialidadUmView> especialidadUmViewList = new ArrayList<>();

			especialidadUmList.forEach((especialidadV) -> {
				EspecialidadUm espM = new EspecialidadUm();
				espM.setIdEspecialidadUm(((BigInteger) especialidadV[0]).longValue());
				espM.setNombre((String) especialidadV[1]);

				especialidadUmViewList.add(especialidadUmConverter.toView(espM, Boolean.TRUE));
			});

			return especialidadUmViewList;

		} catch (UnidadMedicaException umE) {
			throw umE;
		} catch (Exception ex) {
			log.error("===>>>{} getEspecialidadesByIdUnidadMedica() - Ocurrio un error al obtener las Unidades Medicas - error: {}", ExceptionServiceCode.UNIDADMEDICA, ex);
			throw new UnidadMedicaException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(UnidadMedicaException.SERVER_ERROR, "Obtener Especialidades por ID"));
		}
	}

	//   @Transactional(readOnly = false, rollbackFor = {UnidadMedicaException.class})
	@Override
	public EspecialidadUmView getEspecialidadByIdUnidadMedicaByIdEspecialidad(Long idUnidadMedica, Long idEspecialidadUm) throws UnidadMedicaException{
		try {
			if (!unidadMedicaRepository.exists(idUnidadMedica)) {
				log.error("===>>>getEspecialidadByIdUnidadMedicaByIdEspecialidad() - No encontrado el idUnidadMedica: {}", idUnidadMedica);
				throw new UnidadMedicaException(HttpStatus.NOT_FOUND, String.format(UnidadMedicaException.ITEM_NO_ENCONTRADO, idUnidadMedica));
			}

			if (!especialidadUmRepository.exists(idEspecialidadUm)) {
				log.error("===>>>getEspecialidadByIdUnidadMedicaByIdEspecialidad() - No encontrado el idEspecialidadUm: {}", idEspecialidadUm);
				throw new UnidadMedicaException(HttpStatus.NOT_FOUND, String.format(EspecialidadUmException.ITEM_NO_ENCONTRADO, idEspecialidadUm));
			}

			//         Object[] especialidadUm = unidadMedicaRepository.getEspecialidadByIdUnidadMedicaByIdEspecialidad(idUnidadMedica,idEspecialidadUm);
			//         EspecialidadUm sumM = new EspecialidadUm();
			//
			//         sumM.setIdEspecialidadUm(((BigInteger)especialidadUm[0]).longValue());
			//         sumM.setNombre((String)especialidadUm[0]);
			//
			//         return especialidadUmConverter.toView(sumM,Boolean.TRUE);

			List<Object[]> especialidadUmList = null;
			especialidadUmList = unidadMedicaRepository.getEspecialidadByIdUnidadMedicaByIdEspecialidad(idUnidadMedica, idEspecialidadUm);
			//
			if (especialidadUmList.isEmpty()) {
				log.error("===>>>getEspecialidadByIdUnidadMedicaByIdEspecialidad() - No encontrado el id: {}", idUnidadMedica);
				throw new UnidadMedicaException(HttpStatus.NOT_FOUND, String.format(EspecialidadUmException.ITEM_NO_ENCONTRADO, idUnidadMedica));
			}

			List<EspecialidadUmView> especialidadUmViewList = new ArrayList<>();

			especialidadUmList.forEach((especialidadV) -> {
				EspecialidadUm espM = new EspecialidadUm();
				espM.setIdEspecialidadUm(((BigInteger) especialidadV[0]).longValue());
				espM.setNombre((String) especialidadV[1]);

				especialidadUmViewList.add(especialidadUmConverter.toView(espM, Boolean.FALSE));
			});

			EspecialidadUmView espV = new EspecialidadUmView();
			espV.setIdEspecialidadUm(especialidadUmViewList.get(0).getIdEspecialidadUm());
			espV.setNombre(especialidadUmViewList.get(0).getNombre());

			return espV;

		} catch (UnidadMedicaException umE) {
			throw umE;
		} catch (Exception ex) {
			log.error("===>>>{} getEspecialidadByIdUnidadMedicaByIdEspecialidad() - Ocurrio un error al obtener las Unidades Medicas - error: {}", ExceptionServiceCode.UNIDADMEDICA, ex);
			throw new UnidadMedicaException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(UnidadMedicaException.SERVER_ERROR, "Obtener Especialidad por ID"));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<UnidadMedicaPageView> searchUnidadMedica(String datosBusqueda, Boolean activo, Integer page, Integer size, String orderColumn,
			String orderType) throws UnidadMedicaException {
		try {
			log.info("searchUnidadesMedicas(): - datosBusqueda: {} - activo: {} - page: {} - size: {} - orderColumn: {} - orderType: {}",
					datosBusqueda, activo, page, size, orderColumn, orderType);
			if(!colOrderNames.containsKey(orderColumn)) {
				orderColumn = "idUnidadMedica";
			}
			Sort sort = new Sort(Sort.Direction.ASC, (String) colOrderNames.get("idUnidadMedica"));
			if(orderType.equals("asc")) {
				sort = new Sort(Sort.Direction.ASC, (String) colOrderNames.get(orderColumn));
			}else {
				sort = new Sort(Sort.Direction.DESC, (String) colOrderNames.get(orderColumn));
			}
			final String patternSearch = "%" + datosBusqueda.trim().toLowerCase() + "%";
			Specifications<UnidadMedica> spec  = Specifications.where(
					(root, query, cb) ->{
						Predicate tc = null;
						if(!datosBusqueda.isEmpty()) {
							tc = cb.like(cb.function("unaccent",String.class, cb.lower(root.get("nombreUnidad"))), sinAcentos(patternSearch));
						}
						if(activo != null) {
							tc =(tc != null ? cb.and(tc, cb.equal(root.get("activo"), activo)) : cb.equal(root.get("activo"), activo));	
						}
						return tc;
					});
			PageRequest request=new PageRequest(page,size,sort);

			Page<UnidadMedica> unidadMedicaPage = unidadMedicaRepository.findAll(spec,request);

			List<UnidadMedicaPageView> unidadMedicaList = new ArrayList<>();
			unidadMedicaPage.getContent().forEach(umPage -> {
				unidadMedicaList.add(unidadMedicaConverter.toPage(umPage));
			});
			PageImpl<UnidadMedicaPageView> pageView = new PageImpl<UnidadMedicaPageView>(unidadMedicaList,request, unidadMedicaPage.getTotalElements());
			return pageView;
		}catch(DataIntegrityViolationException de) {
			log.error("searchUnidadMedica() -  Ocurrió un error de integridad en la tabla, error: {}",de.getRootCause().getMessage());
			throw new UnidadMedicaException(HttpStatus.CONFLICT, de.getRootCause().getMessage());  
		}catch(Exception e){
			log.error("searchUnidadMedica() -  Ocurrió un error inesperado al filtrar las unidades médicas - error: {}", e);
			throw new UnidadMedicaException(HttpStatus.INTERNAL_SERVER_ERROR, "No fue posible filtrar las Unidades Médicas");
		}
	}

	private String sinAcentos(String cadena) {
		return Normalizer.normalize(cadena, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
