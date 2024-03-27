package net.amentum.niomedic.medicos.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.amentum.niomedic.medicos.converter.RegionSanitariaConverter;
import net.amentum.niomedic.medicos.exception.ExceptionServiceCode;
import net.amentum.niomedic.medicos.exception.RegionSanitariaException;
import net.amentum.niomedic.medicos.exception.UnidadMedicaException;
import net.amentum.niomedic.medicos.model.RegionSanitaria;
import net.amentum.niomedic.medicos.persistence.RegionSanitariaRepository;
import net.amentum.niomedic.medicos.service.RegionSanitariaService;
import net.amentum.niomedic.medicos.views.RegionSanitariaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.Region;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;


@Service
@Slf4j
public class RegionSanitariaServiceImpl implements RegionSanitariaService {

    private final Map<String, Object> colOrderNames = new HashMap<>();
    private RegionSanitariaRepository repository;
    private RegionSanitariaConverter converter;

    {
        colOrderNames.put("id", "idRs");
    }

    @Autowired
    public void setRepository(RegionSanitariaRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setConverter(RegionSanitariaConverter converter) {
        this.converter = converter;
    }

    @Override
    public RegionSanitariaView createRegionSanitaria(RegionSanitariaView view) throws RegionSanitariaException {
        try {
            log.info("createRegionSanitaria - creando región sanitaria");
            RegionSanitaria entity = converter.toEntity(view);
            repository.save(entity);
            return converter.toView(entity);
        } catch(ConstraintViolationException ce) {
            final Set<ConstraintViolation<?>> violaciones = ce.getConstraintViolations();
            String errores="";
            for (Iterator<ConstraintViolation<?>> iterator = violaciones.iterator(); iterator.hasNext(); ) {
                ConstraintViolation<?> siguiente = iterator.next();
                errores = errores + siguiente.getPropertyPath()+": "+siguiente.getMessage()+"\n";
            }
            log.error("IMPL - createRegionSanitaria() -  Ocurrió un error de validación en la tabla, error: {}", errores);
            throw new RegionSanitariaException(HttpStatus.BAD_REQUEST, errores);
        }catch(DataIntegrityViolationException de) {
            log.error("IMPL - createRegionSanitaria() -  Ocurrió un error de integridad en la tabla, error: {}",de.getRootCause().getMessage());
            throw new RegionSanitariaException(HttpStatus.CONFLICT, de.getRootCause().getMessage());
        }catch(Exception e) {
            log.error("IMPL - createRegionSanitaria() -  Ocurrió un error inesperado al crear una Región Sanitaria, error: {}",e.getMessage());
            throw new RegionSanitariaException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(UnidadMedicaException.SERVER_ERROR, "crear"));
        }
    }

    @Override
    public RegionSanitariaView updateRegionSanitaria(Integer idRegionSanitaria, RegionSanitariaView view) throws RegionSanitariaException {
        try {
            log.info("updateRegionSanitaria() - Actualizando región sanitaria {} - {}", idRegionSanitaria, view);
            RegionSanitaria entity = converter.toEntity(view);
            repository.save(entity);
            return converter.toView(entity);
        } catch(ConstraintViolationException ce) {
            final Set<ConstraintViolation<?>> violaciones = ce.getConstraintViolations();
            String errores="";
            for (Iterator<ConstraintViolation<?>> iterator = violaciones.iterator(); iterator.hasNext(); ) {
                ConstraintViolation<?> siguiente = iterator.next();
                errores = errores + siguiente.getPropertyPath()+": "+siguiente.getMessage()+"\n";
            }
            log.error("updateRegionSanitaria() -  Ocurrió un error de validación en la tabla, error: {}", errores);
            throw new RegionSanitariaException(HttpStatus.BAD_REQUEST, errores);
        }catch(DataIntegrityViolationException de) {
            log.error("updateRegionSanitaria() -  Ocurrió un error de integridad en la tabla, error: {}",de.getRootCause().getMessage());
            throw new RegionSanitariaException(HttpStatus.CONFLICT, de.getRootCause().getMessage());
        }catch(Exception e) {
            log.error("updateUnidadMedica() -  Ocurrió un error inesperado al actualizar una Región Sanitaria, error: {}",e.getMessage());
            throw new RegionSanitariaException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(UnidadMedicaException.SERVER_ERROR, "crear"));
        }
    }

    @Override
    public Page<RegionSanitariaView> searchRegionSanitaria(Integer page, Integer size, String orderColumn, String orderType) throws RegionSanitariaException {
        List<RegionSanitariaView> regionViewList = new ArrayList<>();
        Page<RegionSanitaria> regionPage = null;
        Sort sort = new Sort(Sort.Direction.ASC, "idRs");
        if(orderColumn != null && orderType != null) {
            if(orderType.equalsIgnoreCase("asc"))
                sort = new Sort(Sort.Direction.ASC, (String) colOrderNames.get(orderColumn));
            else
                sort = new Sort(Sort.Direction.DESC, (String) colOrderNames.get(orderColumn));
        }
        PageRequest request = new PageRequest(page, size, sort);
        regionPage = repository.findAll(request);
        regionPage.getContent().forEach(regionSanitaria -> regionViewList.add(converter.toView(regionSanitaria)));
        return new PageImpl<>(regionViewList, request, regionPage.getTotalElements());
    }

    @Override
    public RegionSanitariaView getRegionSanitariaById(Integer idRegionSanitaria) throws RegionSanitariaException {
        try {
            log.info("getRegionSanitariaById() - Obteniendo región sanitaria con el id: {}", idRegionSanitaria);
            if(!repository.exists(idRegionSanitaria)) {
                log.error("getRegionSanitariaById() - No encontrado el id: {}", idRegionSanitaria);
                throw new RegionSanitariaException(HttpStatus.NOT_FOUND, String.format(RegionSanitariaException.ITEM_NO_ENCONTRADO, idRegionSanitaria));
            }
            RegionSanitaria entity = repository.findOne(idRegionSanitaria);
            log.info("getRegionSanitariaById() - Región sanitaria encontrada: {}", entity);
            return converter.toView(entity);
        } catch (RegionSanitariaException umE) {
            throw umE;
        } catch (Exception ex) {
            log.error("===>>>{} getRegionSanitariaById() - Ocurrio un error al obtener las Regiones Sanitarias - error: {}", ExceptionServiceCode.REGIONSANITARIA, ex);
            throw new RegionSanitariaException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(RegionSanitariaException.SERVER_ERROR, "Obtener por ID"));
        }
    }

    @Override
    public List<RegionSanitariaView> getRegionSanitariaByEstado(Integer idEstado) throws RegionSanitariaException {
        try {
            log.info("getRegionSanitariaById() - Obteniendo región sanitaria por estado: {}", idEstado);
            if(!repository.existEstado(idEstado)) {
                log.error("getRegionSanitariaByEstado() - No encontrado el estado con el id: {}", idEstado);
                throw new RegionSanitariaException(HttpStatus.NOT_FOUND, String.format(RegionSanitariaException.ITEM_NO_ENCONTRADO_EDO, idEstado));
            }
            List<RegionSanitaria> entityList = repository.getRegionByEstado(idEstado);
            List<RegionSanitariaView> viewList = new ArrayList<>();
            for(RegionSanitaria entity : entityList)
                viewList.add(converter.toView(entity));
            return viewList;
        } catch (RegionSanitariaException umE) {
            throw umE;
        } catch (Exception ex) {
            log.error("===>>>{} getRegionSanitariaById() - Ocurrio un error al obtener las Regiones Sanitarias - error: {}", ExceptionServiceCode.REGIONSANITARIA, ex);
            throw new RegionSanitariaException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(RegionSanitariaException.SERVER_ERROR, "Obtener por ID"));
        }
    }
}
