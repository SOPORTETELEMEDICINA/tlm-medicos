package net.amentum.niomedic.medicos.service.impl;

import net.amentum.niomedic.medicos.converter.AgendamedicosConverter;
import net.amentum.niomedic.medicos.exception.AgendamedicosException;
import net.amentum.niomedic.medicos.exception.ExceptionServiceCode;
import net.amentum.niomedic.medicos.model.AgendaMedicos;
import net.amentum.niomedic.medicos.persistence.AgendamedicosRepository;
import net.amentum.niomedic.medicos.service.AgendaMedicosService;
import net.amentum.niomedic.medicos.views.AgendaMedicosView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class AgendaMedicosServiceImpl implements AgendaMedicosService {

    private final Logger logger = LoggerFactory.getLogger(AgendaMedicosServiceImpl.class);
    private final Map<String, Object> colOrderNames = new HashMap<>();
    private AgendamedicosRepository AgendamedicosRepository;
    private AgendamedicosConverter AgendamedicosConverter;

    {
        colOrderNames.put("idagenda", "idagenda");
        colOrderNames.put("idmedico", "idmedico");
    }

    @Autowired
    public void setAgendamedicosRepository(AgendamedicosRepository AgendamedicosRepository) {
        this.AgendamedicosRepository = AgendamedicosRepository;
    }

    @Autowired
    public void setAgendamedicosConverter(AgendamedicosConverter AgendamedicosConverter) {
        this.AgendamedicosConverter = AgendamedicosConverter;
    }

    @Transactional(readOnly = false, rollbackFor = {AgendamedicosException.class})
    @Override
    public void createAgendamedicos(AgendaMedicosView AgendamedicosView) throws AgendamedicosException {
        try {

            AgendaMedicos Agendamedicos = AgendamedicosConverter.toEntity(AgendamedicosView, new AgendaMedicos(), Boolean.FALSE);
            logger.debug("Insertar nueva Agendamedicos: {}", Agendamedicos);
            AgendamedicosRepository.save(Agendamedicos);

        } catch (DataIntegrityViolationException dive) {
            AgendamedicosException ncE = new AgendamedicosException("No fue posible agregar Agendamedicos", AgendamedicosException.LAYER_DAO, AgendamedicosException.ACTION_INSERT);
            ncE.addError("Ocurrio un error al agregar Agendamedicos");
            logger.error("Error al insertar nueva Agendamedicos - CODE {} - {}", ncE.getExceptionCode(), AgendamedicosView, ncE);
            throw ncE;
        } catch (Exception ex) {
            AgendamedicosException ncE = new AgendamedicosException("Error inesperado al agregar Agendamedicos", AgendamedicosException.LAYER_DAO, AgendamedicosException.ACTION_INSERT);
            ncE.addError("Ocurrió un error al agregar Agendamedicos");
            logger.error("Error al insertar nueva Agendamedicos - CODE {} - {}", ncE.getExceptionCode(), AgendamedicosView, ncE);
            throw ncE;
        }
    }

    @Transactional(readOnly = false, rollbackFor = {AgendamedicosException.class})
    @Override
    public void updateAgendamedicos(AgendaMedicosView AgendamedicosView) throws AgendamedicosException {
        try {
            if (AgendamedicosRepository.findByIdagenda(AgendamedicosView.getIdagenda()) == null) {
                AgendamedicosException ncE = new AgendamedicosException("No se encuentra en el sistema Agendamedicos.", AgendamedicosException.LAYER_DAO, AgendamedicosException.ACTION_VALIDATE);
                ncE.addError("Agendamedicos no encontrado");
                throw ncE;
            }
            AgendaMedicos Agendamedicos = AgendamedicosRepository.findByIdagenda(AgendamedicosView.getIdagenda());
            Agendamedicos = AgendamedicosConverter.toEntity(AgendamedicosView, Agendamedicos, Boolean.TRUE);
            logger.debug("Editar Agendamedicos: {}", Agendamedicos);
            AgendamedicosRepository.save(Agendamedicos);
        } catch (DataIntegrityViolationException dive) {
            AgendamedicosException ncE = new AgendamedicosException("No fue posible editar Agendamedicos", AgendamedicosException.LAYER_DAO, AgendamedicosException.ACTION_UPDATE);
            ncE.addError("Ocurrió un error al editar Agendamedicos");
            logger.error("Error al editar Agendamedicos - CODE {} - {}", ncE.getExceptionCode(), AgendamedicosView, ncE);
            throw ncE;
        } catch (Exception ex) {
            AgendamedicosException ncE = new AgendamedicosException("Error inesperado al editar Agendamedicos", AgendamedicosException.LAYER_DAO, AgendamedicosException.ACTION_UPDATE);
            ncE.addError("Ocurrió un error al editar Agendamedicos");
            logger.error("Error al editar Agendamedicos - CODE {} - {}", ncE.getExceptionCode(), AgendamedicosView, ncE);
            throw ncE;
        }
    }

    @Transactional(readOnly = false, rollbackFor = {AgendamedicosException.class})
    @Override
    public void deleteAgendamedicos(Integer idagenda) throws AgendamedicosException {
        try {
            if (AgendamedicosRepository.findByIdagenda(idagenda) == null) {
                AgendamedicosException ncE = new AgendamedicosException("No se encuentra en el sistema Agendamedicos.", AgendamedicosException.LAYER_DAO, AgendamedicosException.ACTION_VALIDATE);
                ncE.addError("Agendamedicos no encontrado");
                throw ncE;
            }
            AgendaMedicos Agendamedicos = AgendamedicosRepository.findByIdagenda(idagenda);
            AgendamedicosRepository.delete(Agendamedicos);
        } catch (DataIntegrityViolationException dive) {
            AgendamedicosException ncE = new AgendamedicosException("No fue posible editar Agendamedicos", AgendamedicosException.LAYER_DAO, AgendamedicosException.ACTION_UPDATE);
            ncE.addError("Ocurrió un error al eliminar Agendamedicos");
            logger.error("Error al eliminar Agendamedicos - CODE {} - {}", ncE.getExceptionCode(), idagenda, ncE);
            throw ncE;
        } catch (Exception ex) {
            AgendamedicosException ncE = new AgendamedicosException("Error inesperado al eliminar Agendamedicos", AgendamedicosException.LAYER_DAO, AgendamedicosException.ACTION_UPDATE);
            ncE.addError("Ocurrió un error al eliminar Agendamedicos");
            logger.error("Error al eliminar Agendamedicos - CODE {} - {}", ncE.getExceptionCode(), idagenda, ncE);
            throw ncE;
        }
    }




    @Override
    public List<AgendaMedicosView> findAll() throws AgendamedicosException {
        try {
            List<AgendaMedicos> agendaMedicosList = AgendamedicosRepository.findAll();
            List<AgendaMedicosView> AgendamedicosViewList = new ArrayList<>();
            for (AgendaMedicos cdl : agendaMedicosList) {
                AgendamedicosViewList.add(AgendamedicosConverter.toView(cdl, Boolean.TRUE));
            }
            return AgendamedicosViewList;
        } catch (Exception ex) {
            AgendamedicosException ncE = new AgendamedicosException("Ocurrio un error al seleccionar lista Agendamedicos", AgendamedicosException.LAYER_SERVICE, AgendamedicosException.ACTION_SELECT);
            logger.error(ExceptionServiceCode.GROUP + "- Error al tratar de seleccionar lista Agendamedicos - CODE: {}-{}", ncE.getExceptionCode(), ex.getMessage());
            throw ncE;
        }
    }


    @Override
    public Page<AgendaMedicosView> getAgendamedicosSearch(String idmedico, Integer page, Integer size, String orderColumn, String orderType) throws AgendamedicosException {
        try {
            logger.info("===>>>getAgendamedicosPage(): - idmedico: {} - page: {} - size: {} - orderColumn: {} - orderType: {}",
                    idmedico, page, size, orderColumn, orderType);
            if (idmedico == null) {
                logger.error("===>>>idmedico viene NULO/VACIO: {}", idmedico);
                AgendamedicosException pesoE = new AgendamedicosException("No se encuentra en el sistema Agendamedicos", AgendamedicosException.LAYER_DAO, AgendamedicosException.ACTION_VALIDATE);
                pesoE.addError("idmedico viene NULO/VACIO: " + idmedico);
                throw pesoE;
            }
            List<AgendaMedicosView> AgendamedicosViewList = new ArrayList<>();
            Page<AgendaMedicos> AgendamedicosPage = null;
            Sort sort = new Sort(Sort.Direction.ASC, (String) colOrderNames.get("idagenda"));
            if (orderColumn != null && orderType != null) {
                if (orderType.equalsIgnoreCase("asc"))
                    sort = new Sort(Sort.Direction.ASC, (String) colOrderNames.get(orderColumn));
                else
                    sort = new Sort(Sort.Direction.DESC, (String) colOrderNames.get(orderColumn));
            }
            PageRequest request = new PageRequest(page, size, sort);
            final String patternSearch = idmedico;
            Specifications<AgendaMedicos> spec = Specifications.where(
                    (root, query, cb) -> {
                        Predicate tc = null;
                        if (idmedico != null)
                            tc = (tc != null ?
                                    cb.and(tc, cb.equal(root.get("idmedico"), patternSearch)) : cb.equal(root.get("idmedico"), patternSearch));
                        return tc;
                    }
            );
            if (spec == null)
                AgendamedicosPage = AgendamedicosRepository.findAll(request);
            else
                AgendamedicosPage = AgendamedicosRepository.findAll(spec, request);
            AgendamedicosPage.getContent().forEach(AgendaMedicos -> AgendamedicosViewList.add(AgendamedicosConverter.toView(AgendaMedicos, Boolean.FALSE)));
            PageImpl<AgendaMedicosView> AgendamedicosViewPage = new PageImpl<>(AgendamedicosViewList, request, AgendamedicosPage.getTotalElements());
            return AgendamedicosViewPage;
        } catch (AgendamedicosException pesoE) {
            throw pesoE;
        } catch (IllegalArgumentException iae) {
            logger.error("===>>>Algun parametro no es correcto");
            AgendamedicosException pe = new AgendamedicosException("Algun parametro no es correcto:", AgendamedicosException.LAYER_SERVICE, AgendamedicosException.ACTION_VALIDATE);
            pe.addError("Puede que sea null, vacio o valor incorrecto");
            throw pe;
        } catch (Exception ex) {
            AgendamedicosException pesoE = new AgendamedicosException("Ocurrio un error al seleccionar lista Agendamedicos paginable", AgendamedicosException.LAYER_SERVICE, AgendamedicosException.ACTION_SELECT);
            logger.error(ExceptionServiceCode.GROUP + "===>>>Error al tratar de seleccionar lista Agendamedicos paginable - CODE: {}", pesoE.getExceptionCode(), ex);
            throw pesoE;
        }


    }





}
